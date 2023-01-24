package com.talhanation.smallships.client.option;

import com.talhanation.smallships.network.ModPackets;
import com.talhanation.smallships.world.entity.ship.Ship;
import com.talhanation.smallships.world.entity.ship.abilities.Cannonable;
import com.talhanation.smallships.world.entity.ship.abilities.Sailable;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

public class KeyEvent {
    public static void onKeyInput(Minecraft client) {
        Player player = client.player;
        if (player == null) return;
        boolean pressedSailKey = ModGameOptions.SAIL_KEY.consumeClick();
        boolean pressedJumpKey = client.options.keyJump.consumeClick();
        if (player.getVehicle() instanceof Ship ship) {
            if (player.equals(ship.getControllingPassenger())) { // is driver
                if (pressedSailKey && ship instanceof Sailable) ModPackets.clientSendPacket(player, ModPackets.serverToggleShipSail.apply());
                if (pressedJumpKey && ship instanceof Cannonable) ModPackets.clientSendPacket(player, ModPackets.serverShootShipCannon.apply());
            } // else {} // is passenger
        }
    }
}