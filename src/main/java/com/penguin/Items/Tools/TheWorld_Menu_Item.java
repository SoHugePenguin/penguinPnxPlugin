package com.penguin.Items.Tools;

import cn.nukkit.Server;
import cn.nukkit.event.Listener;
import cn.nukkit.item.Item;
import cn.nukkit.item.customitem.CustomItemDefinition;
import cn.nukkit.item.customitem.ItemCustomTool;
import cn.nukkit.item.customitem.data.ItemCreativeCategory;
import cn.nukkit.item.customitem.data.RenderOffsets;


public class TheWorld_Menu_Item extends ItemCustomTool implements Listener {
    static {
        Server.getInstance().getScheduler().scheduleRepeatingTask(Server.getInstance().getPluginManager().getPlugin("Penguin_Plugin_1"), () -> Server.getInstance().getOnlinePlayers().values().forEach(player -> {
            if (player.getInventory().getItemInHand() instanceof TheWorld_Menu_Item) {
                if (player.getFoodData().getLevel() == player.getFoodData().getMaxLevel())
                    player.getFoodData().setLevel(player.getFoodData().getMaxLevel() - 1);
            }
        }), 20);
    }

    public TheWorld_Menu_Item() {
        super("np:world_menu", "世界菜单", "world_menu");
    }

    @Override
    public CustomItemDefinition getDefinition() {
        return CustomItemDefinition
                .toolBuilder(this, ItemCreativeCategory.EQUIPMENT)
                .speed(10)
                //Set the digging speed of the pickaxe
                .addExtraBlock("minecraft:nether_brick", 10)
                //Add additional matching block, if the block do not match then it is normal digging speed
                .allowOffHand(true)
                //If true is the way the tool is displayed, false is the item
                .foil(true)
                .renderOffsets(RenderOffsets.scaleOffset(32))
//                textureSize必须是16的倍数
                .build();
    }

    @Override
    public boolean isUnbreakable() {
        return true;
    }

    @Override
    public int getTier() {
        return Item.CARROT;
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
