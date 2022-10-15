package com.ershgem.mf.entity.dragons.gem;

import com.ershgem.mf.MFConfig;
import com.ershgem.mf.init.ModEntities;
import com.ershgem.mf.init.ModKeybinds;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.List;
import java.util.UUID;

import static net.minecraft.world.entity.ai.attributes.Attributes.*;

public class GemDragon extends TamableAnimal implements Saddleable, FlyingAnimal, PlayerRideableJumping, IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);
    // base attributes
    public static final double BASE_SPEED_GROUND = 0.27F;
    public static final double BASE_SPEED_FLYING = 0.15F;
    public static final double BASE_DAMAGE = 2;
    public static final double BASE_HEALTH = 25;
    public static final double BASE_FOLLOW_RANGE = 16;
    public static final double BASE_FOLLOW_RANGE_FLYING = BASE_FOLLOW_RANGE * 2;
    public static final int BASE_KB_RESISTANCE = 1;

    // data value IDs
    private static final EntityDataAccessor<String> DATA_BREED = SynchedEntityData.defineId(GemDragon.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Boolean> DATA_FLYING = SynchedEntityData.defineId(GemDragon.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_SADDLED = SynchedEntityData.defineId(GemDragon.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> DATA_AGE = SynchedEntityData.defineId(GemDragon.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> IS_GOINGUP = SynchedEntityData.defineId(GemDragon.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_HOVERING = SynchedEntityData.defineId(GemDragon.class, EntityDataSerializers.BOOLEAN);

    // data NBT IDs
    public static final String NBT_BREED = "Breed";
    private static final String NBT_SADDLED = "Saddle";
    private static final String NBT_REPRO_COUNT = "ReproCount";

    // other constants
    public static final int AGE_UPDATE_INTERVAL = 100;
    public static final UUID SCALE_MODIFIER_UUID = UUID.fromString("856d4ba4-9ffe-4a52-8606-890bb9be538b"); // just a random uuid I took online
    public static final int ALTITUDE_FLYING_THRESHOLD = 3;
    public static final int DEFAULT_REPRO_LIMIT = 2;
    public static final int DEFAULT_GROWTH_TIME = 72000;

    // server/client delegates
    private int reproCount;
    private float ageProgress;

    // animations
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if(this.isFlying()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("fly.Gem_Model", true));
            return PlayState.CONTINUE;
        }
        if(this.isVehicle() && event.isMoving() && this.onGround) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("run.Gem_Model", true));
            return PlayState.CONTINUE;
        }
        if(event.isMoving() && this.onGround) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("Walk.Gem_Model", true));
            return PlayState.CONTINUE;
        }
        if(this.isInSittingPose() && this.onGround) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("sit.Gem_Model", true));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle.Gem_Model", true));
        return PlayState.CONTINUE;
    }

    public GemDragon(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        this.maxUpStep = 1;
        this.noCulling = true;
        this.fireImmune();
        this.moveControl = new GemMoveController(this);
        this.setTame(false);
    }

    public static AttributeSupplier.Builder createAttributes()
    {
        return Mob.createMobAttributes()
                .add(MOVEMENT_SPEED, BASE_SPEED_GROUND)
                .add(MAX_HEALTH, BASE_HEALTH)
                .add(ATTACK_DAMAGE, BASE_FOLLOW_RANGE)
                .add(KNOCKBACK_RESISTANCE, BASE_KB_RESISTANCE)
                .add(ATTACK_DAMAGE, BASE_DAMAGE)
                .add(FLYING_SPEED, BASE_SPEED_FLYING);
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public boolean canBeRiddenInWater(Entity rider) {
        return true;
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        //this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.2, false));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, Ingredient.of(ItemTags.FISHES), false));
    }

    @Override
    protected void defineSynchedData()
    {
        super.defineSynchedData();

        entityData.define(DATA_BREED,"");
        entityData.define(DATA_FLYING, false);
        entityData.define(DATA_SADDLED, false);
        entityData.define(IS_GOINGUP, false);
        entityData.define(DATA_AGE, 0); // default to adult stage
        entityData.define(IS_HOVERING, false);
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> data)
    {
        if (DATA_FLAGS_ID.equals(data)) refreshDimensions();
        else super.onSyncedDataUpdated(data);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound)
    {
        super.addAdditionalSaveData(compound);
        compound.putBoolean(NBT_SADDLED, isSaddled());
        compound.putBoolean("is_goingup", this.isGoingUp());
        compound.putInt(NBT_REPRO_COUNT, reproCount);
        compound.putBoolean("is_hovering", this.isHovering());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound)
    {
        super.readAdditionalSaveData(compound);
        setSaddled(compound.getBoolean(NBT_SADDLED));
        this.setIsGoingUp(compound.getBoolean("is_goingup"));
        this.reproCount = compound.getInt(NBT_REPRO_COUNT);
        entityData.set(DATA_AGE, getAge());
        this.setIsHovering(compound.getBoolean("is_hovering"));
    }

    public boolean isHovering() {
        return this.entityData.get(IS_HOVERING);
    }

    public void setIsHovering(boolean hovering) {
        this.entityData.set(IS_HOVERING, hovering);
    }

    @Override
    public void onPlayerJump(int pJumpPower) {
        this.getEntityData().set(DATA_FLYING, true);
        this.jumpFromGround();
    }

    @Override
    public boolean canJump() {
        return !this.getEntityData().get(DATA_FLYING);
    }

    @Override
    public void handleStartJump(int pJumpPower) {}

    @Override
    public void handleStopJump() {}

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob p_146744_) {
        return ModEntities.GEM_DRAGON.get().create(serverLevel);
    }

    /**
     * Returns true if the dragon is saddled.
     */
    public boolean isSaddled()
    {
        return entityData.get(DATA_SADDLED);
    }

    @Override
    public boolean isSaddleable()
    {
        return isAlive() && isTame();
    }

    @Override
    public void equipSaddle(@Nullable SoundSource source)
    {
        setSaddled(true);
        level.playSound(null, getX(), getY(), getZ(), SoundEvents.HORSE_SADDLE, getSoundSource(), 1, 1);
    }

    /**
     * Set or remove the saddle of the dragon.
     */
    public void setSaddled(boolean saddled)
    {
        entityData.set(DATA_SADDLED, saddled);
    }

    public boolean canFly()
    {
        return !isBaby();
    }

    public boolean shouldFly()
    {
        return canFly() && !isInWater() && isHighEnough(ALTITUDE_FLYING_THRESHOLD);
    }

    /**
     * Returns true if the entity is flying.
     */
    public boolean isFlying()
    {
        return entityData.get(DATA_FLYING);
    }

    /**
     * Set the flying flag of the entity.
     */
    public void setFlying(boolean flying)
    {
        entityData.set(DATA_FLYING, flying);
    }

    public boolean isGoingUp() {
        return this.entityData.get(IS_GOINGUP);
    }

    public void setIsGoingUp(boolean jumping) {
        this.entityData.set(IS_GOINGUP, jumping);
    }

    @Override
    public void tick()
    {
        super.tick();

        if (isServer())
        {
            if (age < 0 && tickCount % AGE_UPDATE_INTERVAL == 0) entityData.set(DATA_AGE, age);

            // update flying state based on the distance to the ground
            boolean flying = shouldFly();
            if (flying != isFlying())
            {
                // notify client
                setFlying(flying);

                // update AI follow range (needs to be updated before creating
                // new PathNavigate!)
                getAttribute(FOLLOW_RANGE).setBaseValue(flying? BASE_FOLLOW_RANGE_FLYING : BASE_FOLLOW_RANGE);

                // update pathfinding method
                if (flying) navigation = new FlyingPathNavigation(this, level);
                else navigation = new GroundPathNavigation(this, level);
            }
        }
    }

    @Override
    public void travel(Vec3 vec3)
    {
        boolean isFlying = isFlying();
        //float speed = (float) getAttributeValue(isFlying? FLYING_SPEED : FLYING_SPEED) * 0.25F;

        if (canBeControlledByRider()) // Were being controlled; override ai movement
        {
            LivingEntity driver = (LivingEntity) getControllingPassenger();
            double moveSideways = vec3.x;
            double moveY = vec3.y;
            double moveForward = Math.min(Math.abs(driver.zza) + Math.abs(driver.xxa), 1);

            // rotate head to match driver.
            float yaw = driver.yHeadRot;
            if (moveForward > 0) // rotate in the direction of the drivers controls
                yaw += (float) Mth.atan2(driver.zza, driver.xxa) * (180f / (float) Math.PI) - 90;
            yHeadRot = yaw;
            setXRot(driver.getXRot() * 0.68f);

            // rotate body towards the head
            setYRot(Mth.rotateIfNecessary(yHeadRot, getYRot(), 4));

            if (isControlledByLocalInstance()) // Client applies motion
            {
                if (isFlying)
                {
                    moveForward = moveForward > 0? moveForward : 0;
                    if (moveForward > 0 && MFConfig.cameraFlight()) moveY = -driver.getXRot() * (Math.PI / 180);
                    else moveY = ModKeybinds.DRAGON_ASCEND.isDown()? 1 : ModKeybinds.DRAGON_DESCEND.isDown()? -1 : 0;
                }
                else if (ModKeybinds.DRAGON_ASCEND.isDown() && canFly()) liftOff();

                vec3 = new Vec3(moveSideways, moveY, moveForward);
                setSpeed(0.25F); //Moving speed
            }
            else if (driver instanceof Player) // other clients recieve animations
            {
                calculateEntityAnimation(this, true);
                setDeltaMovement(Vec3.ZERO);
                return;
            }
        }

        if (isFlying)
        {
            // Move relative to yaw - handled in the move controller or by driver
            moveRelative(0.15F, vec3); //Flying Speed
            move(MoverType.SELF, getDeltaMovement());
            if (getDeltaMovement().lengthSqr() < 0.1) // we're not actually going anywhere, bob up and down.
                setDeltaMovement(getDeltaMovement().add(0, Math.sin(tickCount / 4f) * 0.01, 0));
            setDeltaMovement(getDeltaMovement().scale(0.9f)); // smoothly slow down

            calculateEntityAnimation(this, true);
        }
        else super.travel(vec3);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand)
    {
        ItemStack stack = player.getItemInHand(hand);

        InteractionResult stackResult = stack.interactLivingEntity(player, this, hand);
        if (stackResult.consumesAction()) return stackResult;

        final InteractionResult SUCCESS = InteractionResult.sidedSuccess(level.isClientSide);

        // heal
        if (getHealthRelative() < 1 && isFoodItem(stack))
        {
            heal(stack.getItem().getFoodProperties().getNutrition());
            playSound(getEatingSound(stack), 0.7f, 1);
            stack.shrink(1);
            return SUCCESS;
        }

        // saddle up!
        /*if (isTamedFor(player) && isSaddleable() && !isSaddled() && stack.getItem() instanceof SaddleItem)
        {
            stack.shrink(1);
            equipSaddle(getSoundSource());
            return SUCCESS;
        }*/

        // tame
        if (isFood(stack) && !isTame())
        {
            stack.shrink(1);
            if (isServer()) tamedFor(player, getRandom().nextInt(5) == 0);
            return SUCCESS;
        }

        // sit!
        if (this.isTame() && stack.is(Items.STICK))
        {
            if (isServer())
            {
                navigation.stop();
                setOrderedToSit(!isOrderedToSit());
                if (isOrderedToSit()) setTarget(null);
            }
            return SUCCESS;
        }

        // ride on
        if (isTamedFor(player) && isSaddled() && !isBaby() && !isFood(stack))
        {
            if (isServer())
            {
                setRidingPlayer(player);
                navigation.stop();
                setTarget(null);
            }
            setOrderedToSit(false);
            setInSittingPose(false);
            return SUCCESS;
        }

        return super.mobInteract(player, hand);
    }

    /**
     * Returns the int-precision distance to solid ground.
     * Describe an inclusive limit to reduce iterations.
     */
    public double getAltitude(int limit)
    {
        var pointer = blockPosition().mutable().move(0, -1, 0);
        var min = level.dimensionType().minY();
        var i = 0;

        while(i <= limit && pointer.getY() > min && !level.getBlockState(pointer).getMaterial().isSolid())
            pointer.setY(getBlockY() - ++i);

        return i;
    }

    /**
     * Returns the distance to the ground while the entity is flying.
     */
    public double getAltitude()
    {
        return getAltitude(level.getMaxBuildHeight());
    }

    public boolean isHighEnough(int height)
    {
        return getAltitude(height) >= height;
    }

    public void liftOff()
    {
        if (canFly()) jumpFromGround();
    }

    @Override
    protected float getJumpPower()
    {
        // stronger jumps for easier lift-offs
        return super.getJumpPower() * (canFly()? 3 : 1);
    }

    @Override
    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource)
    {
        return !canFly() && super.causeFallDamage(pFallDistance, pMultiplier, pSource);
    }

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean isFoodItem(ItemStack stack)
    {
        return stack.getItem().isEdible() && stack.getItem().getFoodProperties().isMeat();
    }

    @Override
    public boolean isFood(ItemStack stack)
    {
        return stack.is(ItemTags.FISHES);
    }

    public void tamedFor(Player player, boolean successful)
    {
        if (successful)
        {
            setTame(true);
            navigation.stop();
            setTarget(null);
            setOwnerUUID(player.getUUID());
            level.broadcastEntityEvent(this, (byte) 7);
        }
        else
        {
            level.broadcastEntityEvent(this, (byte) 6);
        }
    }

    public boolean isTamedFor(Player player)
    {
        return isTame() && isOwnedBy(player);
    }

    public boolean canBeControlledByRider()
    {
        return getControllingPassenger() instanceof LivingEntity driver && isOwnedBy(driver);
    }

    /**
     * For vehicles, the first passenger is generally considered the controller and "drives" the vehicle. For example,
     * Pigs, Horses, and Boats are generally "steered" by the controlling passenger.
     */
    @Override
    public Entity getControllingPassenger()
    {
        List<Entity> list = getPassengers();
        return list.isEmpty()? null : list.get(0);
    }

    public void setRidingPlayer(Player player)
    {
        player.setYRot(getYRot());
        player.setXRot(getXRot());
        player.startRiding(this);
    }

    /**
     * Returns the entity's health relative to the maximum health.
     *
     * @return health normalized between 0 and 1
     */
    public double getHealthRelative()
    {
        return getHealth() / (double) getMaxHealth();
    }

    // simple helper method to determine if we're on the server thread.
    public boolean isServer()
    {
        return !level.isClientSide;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
