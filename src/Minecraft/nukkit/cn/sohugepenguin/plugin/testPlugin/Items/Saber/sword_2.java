package Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Items.Saber;

import cn.nukkit.blockstate.BlockState;
import cn.nukkit.item.Item;
import cn.nukkit.item.customitem.CustomItemDefinition;
import cn.nukkit.item.customitem.ItemCustomTool;
import cn.nukkit.item.customitem.data.ItemCreativeCategory;
import cn.nukkit.item.customitem.data.Offset;
import cn.nukkit.item.customitem.data.RenderOffsets;

import java.util.List;

public class sword_2 extends ItemCustomTool {
    public sword_2() {
        super("np:sword_2", "§d凛冽之刃·寒霜秩序", "sword_2");
    }

    @Override
    public CustomItemDefinition getDefinition() {
        return CustomItemDefinition
                .toolBuilder(this, ItemCreativeCategory.EQUIPMENT)
                .speed(10)
                .addExtraBlockTag(BlockState.of("minecraft:web").getBlock(), 15)
                .addRepairItems(List.of(Item.fromString("minecraft:amethyst_shard")), 100)
                .addRepairItems(List.of(Item.fromString("yes:amethyst_spear")), 400)
                .creativeGroup("itemGroup.name.sword")
                .allowOffHand(false)
                .handEquipped(true)
                .foil(false)
                .renderOffsets(new RenderOffsets(
                                Offset.builder().position(-0.2f, -0.5f, -1f).rotation(60f, 35f, 90f),
                                Offset.builder().position(0.7f, 2f, -1f),
                                Offset.builder().position(0f, 0f, 0f),
                                Offset.builder().position(0f, 0f, 0f)
                        )
                )
                .build();
    }

    @Override
    public boolean isPickaxe() {
        return true;
    }

    @Override
    public boolean isUnbreakable() {
        return true;
    }

    @Override
    public int getAttackDamage() {
        return 9;
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