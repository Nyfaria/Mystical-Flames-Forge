package com.ershgem.mf.entity.base;

import com.ershgem.mf.init.ModKeybinds;
import com.ershgem.mf.network.ControlNetwork;
import com.ershgem.mf.network.message.ControlMessageGoingDown;
import com.ershgem.mf.network.message.ControlMessageJumping;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.List;

public abstract class AbstractDragonBase extends TamableAnimal implements Saddleable, FlyingAnimal, PlayerRideable, IAnimatable {

    BlockPos flightTarget;

    private static final EntityDataAccessor<Boolean> DATA_SADDLED = SynchedEntityData.defineId(AbstractDragonBase.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_FLYING = SynchedEntityData.defineId(AbstractDragonBase.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> TICKS_FLY_WANDERING = SynchedEntityData.defineId(AbstractDragonBase.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> IN_AIR_TICKS_VEHICLE = SynchedEntityData.defineId(AbstractDragonBase.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> IS_LANDING = SynchedEntityData.defineId(AbstractDragonBase.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_GOINGUP = SynchedEntityData.defineId(AbstractDragonBase.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_GOINDOWN = SynchedEntityData.defineId(AbstractDragonBase.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_HOVERING = SynchedEntityData.defineId(AbstractDragonBase.class, EntityDataSerializers.BOOLEAN);

    private AnimationFactory factory = new AnimationFactory(this);
    private static final String NBT_SADDLED = "Saddle";

    public AbstractDragonBase(EntityType<? extends TamableAnimal> p_21803_, Level p_21804_) {
        super(p_21803_, p_21804_);
        this.maxUpStep = 1;
        this.noCulling = true;
        this.fireImmune();
        this.setTame(false);
    }

    public float getRideCameraDistanceBack() {
        return 7;
    }

    public float getRideCameraDistanceFront() {
        return getRideCameraDistanceBack();
    }
    @OnlyIn(Dist.CLIENT)
    public void updateClientControls() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.options.keyJump.isDown()) {
            ControlNetwork.INSTANCE.sendToServer(new ControlMessageJumping(true));
        } else {
            ControlNetwork.INSTANCE.sendToServer(new ControlMessageJumping(false));
        }
        if (ModKeybinds.DRAGON_DESCEND.isDown()) {
            ControlNetwork.INSTANCE.sendToServer(new ControlMessageGoingDown(true));
        } else {
            ControlNetwork.INSTANCE.sendToServer(new ControlMessageGoingDown(false));
        }

    }

    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        return false;
    }

    public boolean isLanding() {
        return this.entityData.get(IS_LANDING);
    }

    public void setIsLanding(boolean isLanding) {
        this.entityData.set(IS_LANDING, isLanding);
    }

    public boolean isFlying() {
        return this.entityData.get(IS_FLYING);
    }

    public void setIsFlying(boolean flying) {
        this.entityData.set(IS_FLYING, flying);
    }

    public int maxTicksFly() {
        return 45;
    }

    public int getTicksFlyWandering() {
        return this.entityData.get(TICKS_FLY_WANDERING);
    }

    public void setTicksFlyWandering(int flying) {
        this.entityData.set(TICKS_FLY_WANDERING, flying);
    }

    public int getInAirTicksVehicle() {
        return this.entityData.get(IN_AIR_TICKS_VEHICLE);
    }

    public void setInAirTicksVehicle(int flying) {
        this.entityData.set(IN_AIR_TICKS_VEHICLE, flying);
    }

    public boolean isGoingUp() {
        return this.entityData.get(IS_GOINGUP);
    }

    public void setIsGoingUp(boolean jumping) {
        this.entityData.set(IS_GOINGUP, jumping);
    }

    public boolean isGoingDown() {
        return this.entityData.get(IS_GOINDOWN);
    }

    public void setIsGoingDown(boolean goingdown) {
        this.entityData.set(IS_GOINDOWN, goingdown);
    }

    public boolean isHovering() {
        return this.entityData.get(IS_HOVERING);
    }

    public void setIsHovering(boolean hovering) {
        this.entityData.set(IS_HOVERING, hovering);
    }

    @Override
    protected void defineSynchedData()
    {
        super.defineSynchedData();
        entityData.define(DATA_SADDLED, false);
        this.entityData.define(IS_FLYING, false);
        this.entityData.define(TICKS_FLY_WANDERING, 0);
        this.entityData.define(IN_AIR_TICKS_VEHICLE, 0);
        this.entityData.define(IS_LANDING, false);
        this.entityData.define(IS_GOINGUP, false);
        this.entityData.define(IS_GOINDOWN, false);
        this.entityData.define(IS_HOVERING, false);
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> data)
    {
        if (DATA_FLAGS_ID.equals(data)) refreshDimensions();
        else super.onSyncedDataUpdated(data);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound)
    {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean(NBT_SADDLED, isSaddled());
        pCompound.putBoolean("is_flying", this.isFlying());
        pCompound.putInt("ticks_fly_wandering", this.getTicksFlyWandering());
        pCompound.putInt("in_air_ticks_vehicle", this.getInAirTicksVehicle());
        pCompound.putBoolean("is_goingup", this.isGoingUp());
        pCompound.putBoolean("is_goingdown", this.isGoingDown());
        pCompound.putBoolean("is_hovering", this.isHovering());
        pCompound.putBoolean("is_landing", this.isLanding());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound)
    {
        super.readAdditionalSaveData(pCompound);
        this.setSaddled(pCompound.getBoolean(NBT_SADDLED));
        this.setIsLanding(pCompound.getBoolean("is_landing"));
        this.setIsFlying(pCompound.getBoolean("is_flying"));
        this.setTicksFlyWandering(pCompound.getInt("ticks_fly_wandering"));
        this.setInAirTicksVehicle(pCompound.getInt("in_air_ticks_vehicle"));
        this.setIsGoingUp(pCompound.getBoolean("is_goingup"));
        this.setIsGoingDown(pCompound.getBoolean("is_goingdown"));
        this.setIsHovering(pCompound.getBoolean("is_hovering"));
    }

    /**
     * For vehicles, the first passenger is generally considered the controller and "drives" the vehicle. For example,
     * Pigs, Horses, and Boats are generally "steered" by the controlling passenger.
     */
    /*@javax.annotation.Nullable
    public Entity getControllingPassenger() {
        return this.getFirstPassenger();
    }*/

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
//        System.out.println(moveControl);
//        System.out.println(navigation);
        if (navigation.getTargetPos() != null) System.out.println("getTargetPos: " + navigation.getTargetPos());
        if (navigation.getTargetPos() != null)
            System.out.println("getTargetPos Y: " + navigation.getTargetPos().getY());
////        System.out.println("isDone: " + navigation.getPath().isDone());
////        System.out.println("endNode: " + navigation.getPath().getEndNode());
//        System.out.println("distToTarget: " + navigation.getPath().getDistToTarget());
        return super.hurt(pSource, pAmount);
    }

    @Override
    public void dismountTo(double pX, double pY, double pZ) {
        super.dismountTo(pX, pY, pZ);
    }

    @Override
    protected void removePassenger(Entity pPassenger) {
        super.removePassenger(pPassenger);
    }

    @Override
    protected float getWaterSlowDown() {
        return 0.9F;
    }

    @Override
    public LookControl getLookControl() {
        return super.getLookControl();
    }

    // facing up: xHeadRot = -12.5;
    // facing down: xHeadRot = 22.5;
    // facing forward: xHeadRot = 0;
    private double calculateFlightHeight(double xHeadRot) {
        if (this.getY() < 250) {
            double y = isFlying() && (isGoingUp() || isGoingDown()) ? 0 : xHeadRot * -0.005;
            return y;
        } else {
            return 0;
        }
    }

    @Override
    public void travel(Vec3 pTravelVector) {
        if (this.isVehicle() && this.canBeControlledByRider()) {
            LivingEntity pilot = (LivingEntity) this.getControllingPassenger();

            assert pilot != null;
            pilot.setYBodyRot(pilot.getYHeadRot());
            this.setYRot(pilot.getYRot());
            this.yRotO = this.getYRot();
            this.setXRot(pilot.getXRot() * 0.5F);
            if (isFlying()) {
                this.setXRot(pilot.getXRot() * 0.5F);
            } else {
                this.setXRot(0);
            }
            this.setRot(this.getYRot(), this.getXRot());
            this.yBodyRot = this.getYRot();
            this.yHeadRot = this.getYRot();

            float f = pilot.xxa * 0.5F;
            float f1 = pilot.zza;

            // facing straight up is -10
            double xHeadRot;
            if (this.xRotO > 0) {
                xHeadRot = this.xRotO / 2;
            } else {
                xHeadRot = this.xRotO / 3.6;
            }

            double xHeadRotABS = Math.abs(this.xRotO) / 450;
            double y = calculateFlightHeight(xHeadRot);
            if (f1 <= 0.0F) {
                f1 *= 0.25F;
            }

            this.xRotO = this.getXRot();
            Vec3 delta = this.getDeltaMovement();
            this.setDeltaMovement(delta.x / 2, delta.y / 2, delta.z / 2);

            if (f1 > 0.0F) {
                float f2 = Mth.sin(this.getYRot() * 0.017453292F);
                float f3 = Mth.cos(this.getYRot() * 0.017453292F);

                double speed = this.getAttributeValue(Attributes.FLYING_SPEED) * (isInWater() ? this.getAttributeValue(ForgeMod.SWIM_SPEED.get()) : 1);
                this.setDeltaMovement(delta.add(
                        (isFlying() ? -speed : -speed + xHeadRotABS) * f2,
                        y, (isFlying() ? speed : speed - xHeadRotABS) * f3));
                this.setIsHovering(false);
            } else if (f1 == 0 && (isGoingUp() || isGoingDown())) {
                this.setIsHovering(false);
            } else if (f1 == 0) {
                this.setIsHovering(true);
            }

            if (this.isGoingUp()) {
                this.setDeltaMovement(this.getDeltaMovement().add(0, f1 > 0 ? this.getAttributeValue(Attributes.FLYING_SPEED) : 0.4, 0));
            } else if (this.isGoingDown()) {
                this.setDeltaMovement(this.getDeltaMovement().add(0, f1 > 0 ? -this.getAttributeValue(Attributes.FLYING_SPEED) : -0.6, 0));
            }

            if (this.isControlledByLocalInstance()) {
                this.setSpeed((float) this.getAttributeValue(Attributes.FLYING_SPEED));
                super.travel(new Vec3(f, y, f1)); // pTravelVector.y
            } else if (pilot instanceof Player) {
                this.setDeltaMovement(Vec3.ZERO);
            }
            this.tryCheckInsideBlocks();
        } else {
            super.travel(pTravelVector);
        }
    }

    /**
     * Check if the ground 4 blocks below is a solid. Replacement for Vanilla onGround
     *
     * @return solidBlockState
     */
    public boolean isDragonOnGround() {
        // fix height
        for (int i = 0; i < 4; ++i) {
            BlockPos solidPos = new BlockPos(this.position().x, this.position().y - i, this.position().z);
            if (!level.getBlockState(solidPos).isAir())
                return true;
        }
        return false;
    }

    /**
     * Check if the ground 150 blocks below is not water or lava
     *
     * @return solidBlockState
     */
    public boolean isHighAboveGround() {
        for (int i = 0; i < 150; ++i) {
            BlockPos solidPos = new BlockPos(this.position().x, this.position().y - i, this.position().z);
            if (!(level.getBlockState(solidPos).getBlock() instanceof LiquidBlock))
                return false;
        }
        return true;
    }

    @Override
    public void tick() {
        // I never allowed flying units to fly on their own without the rider because we cannot switch navigators
        if (isVehicle()) {
            setIsFlying(!isDragonOnGround() || isInWater());
        } else {
            setIsFlying(false);
        }

        LivingEntity owner = getOwner();
//        if(owner!=null) {
//            this.getNavigation().moveTo(owner.getX(),owner.getY(),owner.getZ(), 1);
//        }
        if (isTame() && owner == null) {
            isInSittingPose();
        }

        // play hovering anim
        if (this.zza == 0 && isFlying()) this.setIsHovering(true);

        this.setNoGravity(isFlying());

        if (level.isClientSide()) {
            updateClientControls();
        }

        super.tick();
    }

    @Override
    public boolean rideableUnderWater() {
        return true;
    }

    public void circleEntity(Vec3 target, float radius, float speed, boolean direction, int circleFrame, float offset, float moveSpeedMultiplier) {
        int directionInt = direction ? 1 : -1;
        double t = directionInt * circleFrame * 0.5 * speed / radius + offset;
        Vec3 movePos = target.add(radius * Math.cos(t), 0, radius * Math.sin(t));
        this.getNavigation().moveTo(movePos.x(), movePos.y(), movePos.z(), speed * moveSpeedMultiplier);
    }

    public int getMaxHeadYRot() {
        return 0;
    }

    @Override
    public boolean canBeRiddenInWater(Entity rider) {
        return true;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
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
}
