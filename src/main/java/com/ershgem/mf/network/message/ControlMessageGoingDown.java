package com.ershgem.mf.network.message;

import com.ershgem.mf.entity.base.AbstractDragonBase;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ControlMessageGoingDown {

    boolean isGoingDown;

    public ControlMessageGoingDown() {

    }

    public ControlMessageGoingDown(boolean isGoingDown) {
        this.isGoingDown = isGoingDown;
    }

    public static void encode(ControlMessageGoingDown message, FriendlyByteBuf buffer) {
        buffer.writeBoolean(message.isGoingDown);
    }

    public static ControlMessageGoingDown decode(FriendlyByteBuf buffer) {
        return new ControlMessageGoingDown(buffer.readBoolean());
    }

    public static void handle(ControlMessageGoingDown message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player.getVehicle() instanceof AbstractDragonBase) {
                AbstractDragonBase dragon = (AbstractDragonBase) player.getVehicle();
                dragon.setIsGoingDown(message.isGoingDown);
            }
        });
        context.setPacketHandled(true);

    }
}
