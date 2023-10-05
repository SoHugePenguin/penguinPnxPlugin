package com.penguin.Items.Armors;

import cn.nukkit.item.Item;
import cn.nukkit.item.ItemArmor;
import cn.nukkit.item.customitem.CustomItemDefinition;
import cn.nukkit.item.customitem.ItemCustomArmor;
import cn.nukkit.item.customitem.data.ItemCreativeCategory;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class chests_1 extends ItemCustomArmor {
    public chests_1() {
        super("np:chest_1", "§d紫水晶盔甲", "amethyst_chestplate");
    }

    @NotNull
    @Override
    public CustomItemDefinition getDefinition() {
        return CustomItemDefinition
                .armorBuilder(this, ItemCreativeCategory.EQUIPMENT)
                .addRepairItems(List.of(Item.fromString("minecraft:amethyst_shard")), 100)
                .addRepairItems(List.of(Item.fromString("yes:amethyst_helmet")), 220)
                .creativeGroup("itemGroup.name.chestplate")
                .build();
    }

    @Override
    public boolean isChestplate() {
        return true;
    }

    @Override
    public int getTier() {
        return ItemArmor.TIER_DIAMOND;
    }

    @Override
    //耐久度
    public int getMaxDurability() {
        return 295;
    }

    @Override
    public int getEnchantAbility() {
        return 22;
    }

    @Override
    //护甲值
    public int getArmorPoints() {
        return 7;
    }
}