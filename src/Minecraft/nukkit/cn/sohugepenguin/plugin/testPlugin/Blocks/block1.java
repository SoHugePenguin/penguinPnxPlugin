package Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Blocks;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.block.customblock.CustomBlock;
import cn.nukkit.block.customblock.CustomBlockDefinition;
import cn.nukkit.blockproperty.BlockProperties;
import cn.nukkit.item.Item;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.utils.BlockColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class block1 extends Block implements CustomBlock {

    @Override
    public String getNamespaceId() {
        return "np:color_block1";
    }

   @Override
    public CustomBlockDefinition getDefinition() {
        return CustomBlockDefinition
                .builder(this, "color_block1")//texture name
                .customBuild((nbt) -> {//custom processing result compound
                    nbt.getCompound("components").putCompound("minecraft:part_visibility", new CompoundTag()
                            .putCompound("boneConditions", new CompoundTag()
                                    .putCompound("lower", new CompoundTag()
                                            .putString("bone_condition", "!query.block_property('bridge:top_slot_bit') || query.block_property('bridge:is_full_bit')")
                                            .putString("bone_name", "lower")
                                            .putInt("molang_version", 6))
                                    .putCompound("upper", new CompoundTag()
                                            .putString("bone_condition", "query.block_property('bridge:top_slot_bit') || query.block_property('bridge:is_full_bit')")
                                            .putString("bone_name", "upper")
                                            .putInt("molang_version", 6))));
                });
    }

//    public String getNamespace() {
//        return "np:color_block1";
//    }
//
//
//    public String getTexture() {
//        return "color_block1";
//    }

    @Override
    public double calculateBreakTime(@NotNull Item item, @Nullable Player player) {
        return 3;
    }

    @NotNull
    @Override
    public BlockProperties getProperties() {
        return new BlockProperties();
    }

    //摩擦系数
    @Override
    public double getFrictionFactor() {
        return 0.4;
    }

    @Override
    public BlockColor getColor() {
        return new BlockColor(180,28,48);
    }

    //阻力
    @Override
    public double getResistance() {
        return 2.0;
    }

    @Override
    public int getLightLevel() {
        return 0;
    }

    @Override
    public String getName() {
        return CustomBlock.super.getName();
    }

    @Override
    public int getId() {
        return CustomBlock.super.getId();
    }

    @Override
    public int getItemMaxStackSize() {
        return 64;
    }
}
