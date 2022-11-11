package com.ershgem.mf.entity.base;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.level.Level;

public abstract class SleepingDragoBase extends AbstractDragonBase {
        private static final EntityDataAccessor<Boolean> SLEEPING = SynchedEntityData.defineId(SleepingDragoBase.class, EntityDataSerializers.BOOLEAN);
        private static final EntityDataAccessor<Boolean> IS_INCAPACITATED = SynchedEntityData.defineId(SleepingDragoBase.class, EntityDataSerializers.BOOLEAN);

        protected SleepingDragoBase(EntityType<? extends TamableAnimal> p_21803_, Level p_21804_) {
            super(p_21803_, p_21804_);
        }

        @Override
        protected void defineSynchedData() {
            super.defineSynchedData();
            this.entityData.define(SLEEPING, false);
            this.entityData.define(IS_INCAPACITATED, false);
        }

        public void addAdditionalSaveData(CompoundTag pCompound) {
            super.addAdditionalSaveData(pCompound);
            pCompound.putBoolean("sleeping", this.isDragonSleeping());
            pCompound.putBoolean("incapacitated", this.isDragonIncapacitated());
        }

        public void readAdditionalSaveData(CompoundTag pCompound) {
            super.readAdditionalSaveData(pCompound);
            this.setIsDragonIncapacitated(pCompound.getBoolean("incapacitated"));
        }

        public boolean isDragonSleeping() {
            return this.entityData.get(SLEEPING);
        }

        public void setIsDragonSleeping(boolean sleeping) {
            this.entityData.set(SLEEPING, sleeping);
        }

        protected boolean isNocturnal() {
            return false;
        }

        int sleepDisturbTicks = 0;

        public int getSleepDisturbTicks() {
            return sleepDisturbTicks;
        }

        public void setSleepDisturbTicks(int sleepDisturbTicks) {
            this.sleepDisturbTicks = sleepDisturbTicks;
        }


        public boolean shouldStopMoving() {
            return this.isDragonSleeping() || this.isInSittingPose() && !isVehicle() || isDragonIncapacitated();
        }

        public boolean isDragonIncapacitated() {
            return this.entityData.get(IS_INCAPACITATED);
        }

        public void setIsDragonIncapacitated(boolean sleeping) {
            this.entityData.set(IS_INCAPACITATED, sleeping);
        }

        @Override
        public void tick() {
            super.tick();
            if (shouldStopMoving()) {
                this.getNavigation().stop();
                this.getNavigation().timeoutPath();
                //this.setRot(this.getYRot(), this.getXRot());
            }

            if (sleepDisturbTicks > 0)
                sleepDisturbTicks--;

            LivingEntity lastHurt = getLastHurtByMob();
            LivingEntity target = getTarget();
            int i = getLastHurtByMobTimestamp();
            boolean canSleep = lastHurt == null && i < 2500 && target == null;

            int light = level.getBlockState(new BlockPos(position())).getBlock().getLightEmission(level.getBlockState(new BlockPos(position())), level, new BlockPos(position()));

            if (!level.isClientSide) {
                if (!isNocturnal()) {
                    // get light level instead of daytime
                    // nocturnal dragons sleep on bright areas
                    if (!this.isInWater() &&
                            (this.isOnGround() || onGround) && canSleep && level.isNight()
                            && this.getPassengers().isEmpty() && sleepDisturbTicks <= 1 && random.nextInt(50) == 1) {
                        this.setIsDragonSleeping(true);
                    }

                    if (!level.isClientSide && level.isDay() && random.nextInt(50) == 1) {
                        setIsDragonSleeping(false);
                    }
                } else {
                    // some nocturnal speed stingers spawn in caves (gray ones)
                    // they sleep in caves regardless if they can see the surface caves have no daytime cycle
                    if (!this.isInWater() &&
                            (this.isOnGround() || onGround) && canSleep && level.isDay()
                            && this.getPassengers().isEmpty() && sleepDisturbTicks <= 1 && random.nextInt(50) == 1) {
                        this.setIsDragonSleeping(true);
                    }

                    if (!level.isClientSide && level.isNight() && random.nextInt(50) == 1) {
                        setIsDragonSleeping(false);
                    }
                }
            }
        }
}
