package Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Items.Armors;

import cn.nukkit.item.Item;
import cn.nukkit.item.ItemArmor;
import cn.nukkit.item.customitem.CustomItemDefinition;
import cn.nukkit.item.customitem.ItemCustomArmor;
import cn.nukkit.item.customitem.data.ItemCreativeCategory;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.List;

public class leggings_1 extends ItemCustomArmor {
    public leggings_1() {
        super("np:leggings_1", "§d紫水晶裤衩", "amethyst_leggings");
    }

    @NonNull
    @Override
    public CustomItemDefinition getDefinition() {
        return CustomItemDefinition
                .armorBuilder(this, ItemCreativeCategory.EQUIPMENT)
                .addRepairItems(List.of(Item.fromString("minecraft:amethyst_shard")), 100)
                .addRepairItems(List.of(Item.fromString("yes:amethyst_helmet")), 220)
                .creativeGroup("itemGroup.name.leggings")
                .build();
    }

    @Override
    public boolean isLeggings() {
        return true;
    }

    @Override
    public int getTier() {
        return ItemArmor.TIER_DIAMOND;
    }

    @Override
    public int getMaxDurability() {
        return 276;
    }

    @Override
    public int getEnchantAbility() {
        return 22;
    }

    @Override
    //护甲值
    public int getArmorPoints() {
        return 5;
    }
}