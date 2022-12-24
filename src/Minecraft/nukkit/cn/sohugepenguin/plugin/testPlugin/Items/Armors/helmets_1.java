package Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Items.Armors;

import cn.nukkit.item.Item;
import cn.nukkit.item.ItemArmor;
import cn.nukkit.item.customitem.CustomItemDefinition;
import cn.nukkit.item.customitem.ItemCustomArmor;
import cn.nukkit.item.customitem.data.ItemCreativeCategory;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.List;

public class helmets_1 extends ItemCustomArmor {
    public helmets_1() {
        super("np:helmet_1", "§d紫水晶头盔", "amethyst_helmet");
    }

    @NonNull
    @Override
    public CustomItemDefinition getDefinition() {
        return CustomItemDefinition
                .armorBuilder(this, ItemCreativeCategory.EQUIPMENT)
                .addRepairItems(List.of(Item.fromString("minecraft:amethyst_shard")), 100)
                .addRepairItems(List.of(Item.fromString("yes:amethyst_helmet")), 220)
                //装备类型
                .creativeGroup("itemGroup.name.helmet")
                .build();
    }

    @Override
    //是头盔
    public boolean isHelmet() {
        return true;
    }

    @Override
    public int getTier() {
        return ItemArmor.TIER_DIAMOND;
    }

    @Override
    //耐久度
    public int getMaxDurability() {
        return 203;
    }

    @Override
    //附魔类型
    public int getEnchantAbility() {
        return 22;
    }

    @Override
    //护甲值
    public int getArmorPoints() {
        return 3;
    }
}