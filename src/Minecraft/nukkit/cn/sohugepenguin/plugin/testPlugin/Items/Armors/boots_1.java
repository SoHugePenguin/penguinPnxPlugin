package Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Items.Armors;

import cn.nukkit.item.Item;
import cn.nukkit.item.ItemArmor;
import cn.nukkit.item.customitem.CustomItemDefinition;
import cn.nukkit.item.customitem.ItemCustomArmor;
import cn.nukkit.item.customitem.data.ItemCreativeCategory;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.List;

public class boots_1 extends ItemCustomArmor {
    public boots_1() {
        super("np:boot_1", "§d紫水晶护靴", "amethyst_boots");
    }

    @NonNull
    @Override
    public CustomItemDefinition getDefinition() {
        return CustomItemDefinition
                .armorBuilder(this, ItemCreativeCategory.EQUIPMENT)
                .addRepairItems(List.of(Item.fromString("minecraft:amethyst_shard")), 100)
                .addRepairItems(List.of(Item.fromString("yes:amethyst_helmet")), 220)
                .creativeGroup("itemGroup.name.boots")
                .build();
    }

    @Override
    public boolean isBoots() {
        return true;
    }

    @Override
    public int getTier() {
        return ItemArmor.TIER_DIAMOND;
    }

    @Override
    public int getMaxDurability() {
        return 240;
    }

    @Override
    public int getEnchantAbility() {
        return 22;
    }

    @Override
    //护甲值
    public int getArmorPoints() {
        return 3;
    }
}