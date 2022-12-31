package com.ershgem.mf.network;

import com.ershgem.mf.entity.base.AbstractDragonBase;
import com.ershgem.mf.network.message.DragonRideMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class ClientPacketHandler {
    public static void rideDragon(DragonRideMessage message){
        Player player = Minecraft.getInstance().player;
        Entity entity = player.level.getEntity(message.getDragonId());
        if (entity instanceof AbstractDragonBase dragonBase) {
            if (dragonBase.position().distanceTo(player.position()) < 14) {
                if (message.isRide()) {
                    dragonBase.startRiding(player, true);
                } else {
                    dragonBase.stopRiding();
                }
            }
        }
    }
}
