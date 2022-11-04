package Minecraft.nukkit.cn.shygard.nukkit.testPlugin.Blocks;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.block.customblock.CustomBlock;
import cn.nukkit.block.customblock.CustomBlockDefinition;
import cn.nukkit.block.customblock.data.Permutation;
import cn.nukkit.block.customblock.data.Permutations;
import cn.nukkit.blockproperty.BlockProperties;
import cn.nukkit.blockproperty.BooleanBlockProperty;
import cn.nukkit.item.Item;
import cn.nukkit.math.BlockFace;
import cn.nukkit.math.Vector3f;
import cn.nukkit.nbt.tag.CompoundTag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class test_slab extends Block implements CustomBlock {
    public final BooleanBlockProperty BRIDGE_TOP_SLOT_BIT = new BooleanBlockProperty("bridge:top_slot_bit", false);
    public final BooleanBlockProperty BRIDGE_IS_FULL_BIT = new BooleanBlockProperty("bridge:is_full_bit", false);

    @Override
    public String getNamespaceId() {
        return "blocklynukkit:blue_mahoe_slab";
    }

    @Override
    public String getName() {
        return CustomBlock.super.getName();
    }

    @Override
    public int getId() {
        return CustomBlock.super.getId();
    }

    @NotNull
    @Override
    public BlockProperties getProperties() {
        return new BlockProperties(
                BRIDGE_TOP_SLOT_BIT, BRIDGE_IS_FULL_BIT
        );
    }

    @Override
    public CustomBlockDefinition getDefinition() {
        return CustomBlockDefinition
                .builder(this, "color_block1")//texture name
                .geometry("geometry.custom_slab")//geometry name
                .permutations(new Permutations(//permutation data
                        Permutation.builder()
                                .collision_box_enabled(true)
                                .collision_box_origin(new Vector3f(-8, 0, -8))
                                .collision_box_size(new Vector3f(16, 8, 16))
                                .selection_box_enabled(true)
                                .selection_box_origin(new Vector3f(-8, 0, -8))
                                .selection_box_size(new Vector3f(16, 8, 16))
                                .condition("query.block_property('bridge:top_slot_bit') == false && query.block_property('bridge:is_full_bit') == false"),
                        Permutation.builder()
                                .collision_box_enabled(true)
                                .collision_box_origin(new Vector3f(-8, 8, -8))
                                .collision_box_size(new Vector3f(16, 16, 16))
                                .selection_box_enabled(true)
                                .selection_box_origin(new Vector3f(-8, 8, -8))
                                .selection_box_size(new Vector3f(16, 16, 16))
                                .condition("query.block_property('bridge:top_slot_bit') == true && query.block_property('bridge:is_full_bit') == false"),
                        Permutation.builder()
                                .collision_box_enabled(true)
                                .collision_box_origin(new Vector3f(-8, 0, -8))
                                .collision_box_size(new Vector3f(16, 16, 16))
                                .selection_box_enabled(true)
                                .selection_box_origin(new Vector3f(-8, 0, -8))
                                .selection_box_size(new Vector3f(16, 16, 16))
                                .condition("query.block_property('bridge:is_full_bit') == true")
                ))
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
    //Block digging takes time
    @Override
    public double calculateBreakTime(@NotNull Item item, @Nullable Player player) {
        return 3;
    }
    //Block friction factor
    @Override
    public double getFrictionFactor() {
        return 0.1;
    }
    //Block explosion resistance
    @Override
    public double getResistance() {
        return 5;
    }
    //Block emit light level
    @Override
    public int getLightLevel() {
        return 15;
    }
    //Block absorb light level
    @Override
    public int getLightFilter() {
        return 15;
    }
    //burn ability
    @Override
    public int getBurnAbility() {
        return 0;
    }
    //burn chance
    @Override
    public int getBurnChance() {
        return 0;
    }
    //Block item max stack size
    @Override
    public int getItemMaxStackSize() {
        return 64;
    }

    @Override
    public boolean place(@NotNull Item item, @NotNull Block block, @NotNull Block target, @NotNull BlockFace face, double fx, double fy, double fz, @Nullable Player player) {
        //....Server-side processing behavior
        return true;
    }
}