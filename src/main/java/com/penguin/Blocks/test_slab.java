package com.penguin.Blocks;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.block.customblock.CustomBlock;
import cn.nukkit.block.customblock.CustomBlockDefinition;
import cn.nukkit.block.customblock.data.CollisionBox;
import cn.nukkit.block.customblock.data.Component;
import cn.nukkit.block.customblock.data.Permutation;
import cn.nukkit.block.customblock.data.SelectionBox;
import cn.nukkit.blockproperty.BlockProperties;
import cn.nukkit.blockproperty.BooleanBlockProperty;
import cn.nukkit.item.Item;
import cn.nukkit.math.BlockFace;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class test_slab extends Block implements CustomBlock {
    public final BooleanBlockProperty BRIDGE_TOP_SLOT_BIT = new BooleanBlockProperty("bridge:top_slot_bit", false);
    public final BooleanBlockProperty BRIDGE_IS_FULL_BIT = new BooleanBlockProperty("bridge:is_full_bit", false);

    @NotNull
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
                .builder(this, "blue_mahoe_planks")
                .geometry("geometry.custom_slab")
                .permutations(
                        new Permutation(Component.builder()
                                .collisionBox(new CollisionBox(-8, 0, -8, 16, 8, 16))
                                .selectionBox(new SelectionBox(-8, 0, -8, 16, 8, 16))
                                .build(),
                                "query.block_property('bridge:top_slot_bit') == false && query.block_property('bridge:is_full_bit') == false"),
                        new Permutation(Component.builder()
                                .collisionBox(new CollisionBox(-8, 8, -8, 16, 16, 16))
                                .selectionBox(new SelectionBox(-8, 8, -8, 16, 16, 16))
                                .build(),
                                "query.block_property('bridge:top_slot_bit') == true && query.block_property('bridge:is_full_bit') == false"),
                        new Permutation(Component.builder()
                                .collisionBox(new CollisionBox(-8, 0, -8, 16, 16, 16))
                                .selectionBox(new SelectionBox(-8, 0, -8, 16, 16, 16))
                                .build(),
                                "query.block_property('bridge:is_full_bit') == true")
                )
                .build();
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