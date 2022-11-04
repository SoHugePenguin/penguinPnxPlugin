package Minecraft.nukkit.cn.shygard.nukkit.testPlugin;

import cn.nukkit.Player;

import java.util.ArrayList;

public class Player_Access {
    private Player player;
    private final ArrayList<Long> player_id_list = new ArrayList();

    public Player_Access() {
    }

    public boolean NewPlayerTest(Player player) {
        boolean new_player_test = false;

        for (Long aLong : this.player_id_list) {
            if (player.getId() == aLong) {
                new_player_test = true;
            }
        }

        if (!new_player_test) {
            this.player_id_list.add(player.getId());
        }

        return !new_player_test;
    }

    public final ArrayList list() {
        ArrayList list = new ArrayList();

        for (Long aLong : this.player_id_list) {
            list.add(String.valueOf(aLong));
        }

        return list;
    }
}
