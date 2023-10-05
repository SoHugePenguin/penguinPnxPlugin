/**
 * @Project MC
 * @File SoundSend
 * @Time 2023/1/25 2:37
 * @ToDo
 * @Author SoHugePenguin
 */
package com.penguin.util;

import cn.nukkit.Player;
import cn.nukkit.level.Position;
import cn.nukkit.network.protocol.PlaySoundPacket;
import com.google.common.collect.Lists;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SoundSend {
    public SoundSend() {
    }

    public static void playSound(@NotNull Player p, String sound, float volume, float pitch) {
        try {
            Position position = p.getPosition();
            List<?> targets = Lists.newArrayList(p.asPlayer());
            if (!targets.isEmpty()) {
                double maxDistance = volume > 1.0 ? volume * 16.0 : 16.0;
                for (Object o : targets) {
                    Player player = (Player) o;
                    PlaySoundPacket packet = new PlaySoundPacket();
                    if (position.distance(player) > maxDistance) {
                        packet.volume = 1;
                        packet.x = player.getFloorX();
                        packet.y = player.getFloorY();
                        packet.z = player.getFloorZ();
                    } else {
                        packet.volume = volume;
                        packet.x = position.getFloorX();
                        packet.y = position.getFloorY();
                        packet.z = position.getFloorZ();
                    }
                    packet.name = sound;
                    packet.pitch = pitch;
                    player.dataPacket(packet);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
