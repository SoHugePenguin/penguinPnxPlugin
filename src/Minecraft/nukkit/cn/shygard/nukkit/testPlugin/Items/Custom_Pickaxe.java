package Minecraft.nukkit.cn.shygard.nukkit.testPlugin.Items;

import cn.nukkit.blockstate.BlockState;
import cn.nukkit.item.ItemTool;
import cn.nukkit.item.customitem.CustomItemDefinition;
import cn.nukkit.item.customitem.ItemCustomTool;
import cn.nukkit.item.customitem.data.ItemCreativeCategory;
import cn.nukkit.item.customitem.data.Offset;
import cn.nukkit.item.customitem.data.RenderOffsets;

public class Custom_Pickaxe extends ItemCustomTool {
    public Custom_Pickaxe() {
        super("blocklynukkit:my_pickaxe", "测试镐", "world_menu");
    }

    @Override
    public CustomItemDefinition getDefinition() {
        return CustomItemDefinition
                .toolBuilder(this, ItemCreativeCategory.EQUIPMENT)
                .speed(10)//Set the digging speed of the pickaxe
                .addExtraBlockTag(BlockState.of("minecraft:nether_brick").getBlock(), 10)
                //Add additional matching block, if the block do not match then it is normal digging speed
                .allowOffHand(false)
                .handEquipped(true)
                //If true is the way the tool is displayed, false is the item
                .foil(false)
                .renderOffsets(new RenderOffsets(
                                Offset.builder().position(-0.2f, -0.5f , -1f).rotation(60f, 35f, 90f).scale(1f,1f,1f),
                                Offset.builder().position(0.7f,0.2f,-1f).rotation(120f, 70f, 160f).scale(1f,1f,1f),
                                Offset.builder().position(0f,0f,0f).rotation(60f, 35f, 90f).scale(1f,1f,1f),
                                Offset.builder().position(0f,0f,0f).rotation(60f, 35f, 90f).scale(1f,1f,1f)
                        )
                )
                //Items with enchanted light
                .build();
    }

    @Override
    public int getMaxDurability() {
        return ItemTool.DURABILITY_STONE;
    }

    @Override
    public boolean isPickaxe() {
        return true;
    }


    @Override
    public int getAttackDamage() {
        return 3;
    }

    @Override
    public int getEnchantAbility() {
        return 50;
    }

    @Override
    public int getTier() {
        return ItemCustomTool.TIER_NETHERITE;
    }
}