package Minecraft.nukkit.cn.shygard.nukkit.testPlugin.Items.Saber;

import cn.nukkit.blockstate.BlockState;
import cn.nukkit.item.ItemTool;
import cn.nukkit.item.customitem.CustomItemDefinition;
import cn.nukkit.item.customitem.ItemCustomTool;
import cn.nukkit.item.customitem.data.ItemCreativeCategory;
import cn.nukkit.item.customitem.data.Offset;
import cn.nukkit.item.customitem.data.RenderOffsets;
import edu.umd.cs.findbugs.annotations.NonNull;

public class sword_1 extends ItemCustomTool{
    public sword_1() {
        super("np:sword_1", "§d武士刀·参之形", "sword_1");
    }

    @NonNull
    @Override
    public CustomItemDefinition getDefinition() {
        return CustomItemDefinition
                .toolBuilder(this, ItemCreativeCategory.EQUIPMENT)
                .speed(10)
                .addExtraBlockTag(BlockState.of("minecraft:web").getBlock(), 15)
                .allowOffHand(false)
                .handEquipped(true)
                .foil(false)
                .renderOffsets(new RenderOffsets(
                                Offset.builder().position(-0.2f, -0.5f , -1f).rotation(60f, 35f, 90f),
                                Offset.builder().position(0.7f,2f,-1f),
                                Offset.builder().position(0f,0f,0f),
                                Offset.builder().position(0f,0f,0f)
                        )
                )
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