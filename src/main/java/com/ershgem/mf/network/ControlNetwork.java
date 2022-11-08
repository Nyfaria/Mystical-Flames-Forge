package com.ershgem.mf.network;

import com.ershgem.mf.MysticalFlames;
import com.ershgem.mf.network.message.ControlMessageGoingDown;
import com.ershgem.mf.network.message.ControlMessageJumping;
import com.ershgem.mf.network.message.ControlMessageServer;
import com.ershgem.mf.network.message.DragonRideMessage;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.server.ServerLifecycleHooks;

public class ControlNetwork {

    public static final String NETWORK_VERSION = "0.1.0";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(MysticalFlames.MOD_ID, "control"), () -> NETWORK_VERSION, NETWORK_VERSION::equals, NETWORK_VERSION::equals);

    public static void init() {
        INSTANCE.registerMessage(0, ControlMessageJumping.class, ControlMessageJumping::encode, ControlMessageJumping::decode, ControlMessageJumping::handle);
        INSTANCE.registerMessage(1, ControlMessageGoingDown.class, ControlMessageGoingDown::encode, ControlMessageGoingDown::decode, ControlMessageGoingDown::handle);
        INSTANCE.registerMessage(5, ControlMessageServer.class, ControlMessageServer::encode, ControlMessageServer::decode, ControlMessageServer::handle);
        INSTANCE.registerMessage(7, DragonRideMessage.class, DragonRideMessage::encode, DragonRideMessage::decode, DragonRideMessage::handle);
    }

    public static <MSG> void sendMSGToAll(MSG message) {
        for (ServerPlayer player : ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayers()) {
            INSTANCE.sendTo(message, player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
        }
    }
}
