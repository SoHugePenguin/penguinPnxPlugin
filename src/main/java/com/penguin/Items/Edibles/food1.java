package com.penguin.Items.Edibles;

import cn.nukkit.Player;
import cn.nukkit.item.food.Food;

/**
 * @author Snake1999
 * @since 2016/1/21
 */
public class food1 extends Food {
    public food1() {
    }

    @Override
    protected boolean onEatenBy(Player player) {
        player.sendMessage("辣不辣？辣就对了~");
        return super.onEatenBy(player);
    }

}