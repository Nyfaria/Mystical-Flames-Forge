package com.ershgem.mf.entity.dragons;

import com.ershgem.mf.entity.base.AbstractDragonBase;
import com.ershgem.mf.entity.dragons.gem.GemDragon;
import com.ershgem.mf.entity.dragons.gem.GemMoveController;
import com.ershgem.mf.init.ModEntities;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;

import java.util.UUID;

import static net.minecraft.world.entity.ai.attributes.Attributes.*;
import static net.minecraft.world.entity.ai.attributes.Attributes.FLYING_SPEED;

public class Gem2 extends AbstractDragonBase {

    // base attributes
    public static final double BASE_SPEED_GROUND = 0.27F;
    public static final double BASE_SPEED_FLYING = 0.15F;
    public static final double BASE_DAMAGE = 2;
    public static final double BASE_HEALTH = 25;
    public static final double BASE_FOLLOW_RANGE = 16;
    public static final double BASE_FOLLOW_RANGE_FLYING = BASE_FOLLOW_RANGE * 2;
    public static final int BASE_KB_RESISTANCE = 1;

    // animations
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
       /*if ((isFlying() && isHovering())) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("hover.Gem_Model", true));
            return PlayState.CONTINUE;
        }
        if (isFlying() && !isHovering()) {
            if (this.xRotO < 8 && this.xRotO > -20) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("fly.Gem_Model", true));
                return PlayState.CONTINUE;
            }
            if (this.xRotO > 12 && this.xRotO < 20 && this.isGoingDown()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("dive.Gem_Model", true));
                return PlayState.CONTINUE;
            }
        }*/
        if ((isFlying() && isHovering())) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("hover.Gem_Model", true)); // hover
            return PlayState.CONTINUE;
        }
        if (isFlying() && !isHovering()) {
            if (this.xRotO < -20 || isGoingUp()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("up.Gem_Model", true)); //flyup
                return PlayState.CONTINUE;
            }
            if (this.xRotO < 8 && this.xRotO > -20) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("fly.Gem_Model", true)); // fly
                return PlayState.CONTINUE;
            }
            if (this.xRotO > 8 && this.xRotO < 12) { // < 20
                event.getController().setAnimation(new AnimationBuilder().addAnimation("glide.Gem_Model", true)); // glide
                return PlayState.CONTINUE;
            }
            /*if (this.xRotO > 12 && this.xRotO < 15) { // < 30
                event.getController().setAnimation(new AnimationBuilder().addAnimation("DeadlyNadderGlideDown", true)); // glidedown
                return PlayState.CONTINUE;
            }*/
            if (this.xRotO > 15) { // > 30
                event.getController().setAnimation(new AnimationBuilder().addAnimation("dive.Gem_Model", true)); // dive
                return PlayState.CONTINUE;
            }
        }
        if (event.isMoving()) {
            if (getTarget() != null && !getTarget().isDeadOrDying() && distanceTo(getTarget()) < 14 || isVehicle()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("run.Gem_Model", true));
                return PlayState.CONTINUE;

            } else {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("Walk.Gem_Model", true));
                return PlayState.CONTINUE;
            }
        }
        /*if(this.isVehicle() && event.isMoving() && this.onGround) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("run.Gem_Model", true));
            return PlayState.CONTINUE;
        }
        if(event.isMoving() && this.onGround) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("Walk.Gem_Model", true));
            return PlayState.CONTINUE;
        }*/
        if(this.isInSittingPose() && this.onGround) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("sit.Gem_Model", true));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle.Gem_Model", true));
        return PlayState.CONTINUE;
    }

    public Gem2(EntityType<? extends TamableAnimal> p_21803_, Level p_21804_) {
        super(p_21803_, p_21804_);
        this.maxUpStep = 1;
        this.noCulling = true;
        this.fireImmune();
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

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob p_146744_) {
        return ModEntities.GEM_DRAGON.get().create(serverLevel);
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
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }
}
