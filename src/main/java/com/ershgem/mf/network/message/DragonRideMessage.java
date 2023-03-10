package com.ershgem.mf.network.message;

import com.ershgem.mf.entity.base.AbstractDragonBase;
import com.ershgem.mf.network.ClientPacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class DragonRideMessage {
    int dragonId;
    boolean ride;

    public DragonRideMessage() {

    }

    public DragonRideMessage(int dragonId, boolean ride) {
        this.dragonId = dragonId;
        this.ride = ride;
    }

    public static void encode(DragonRideMessage message, FriendlyByteBuf buffer) {
        buffer.writeDouble(message.dragonId);
        buffer.writeBoolean(message.ride);
    }

    public static DragonRideMessage decode(FriendlyByteBuf buffer) {
        return new DragonRideMessage(buffer.readInt(), buffer.readBoolean());
    }

    public static void handle(DragonRideMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            Player player = context.getSender();
            if (context.getDirection().getReceptionSide() == LogicalSide.CLIENT) {
                ClientPacketHandler.rideDragon(message);
            } else {
                if (player.level != null) {
                    Entity entity = player.level.getEntity(message.dragonId);
                    if (entity != null && entity instanceof AbstractDragonBase) {
                        AbstractDragonBase dragonBase = (AbstractDragonBase) entity;
                        if (dragonBase.position().distanceTo(player.position()) < 14) {
                            if (message.ride) {
                                dragonBase.startRiding(player, true);
                            } else {
                                dragonBase.stopRiding();
                            }
                        }
                    }
                }
            }

        });
        context.setPacketHandled(true);
    }

    public int getDragonId() {
        return dragonId;
    }

    public boolean isRide() {
        return ride;
    }
}