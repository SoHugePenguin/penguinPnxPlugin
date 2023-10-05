package com.penguin.Items.Tools;

import cn.nukkit.Player;
import cn.nukkit.event.Listener;
import cn.nukkit.item.Item;
import cn.nukkit.item.customitem.CustomItemDefinition;
import cn.nukkit.item.customitem.ItemCustomTool;
import cn.nukkit.item.customitem.data.ItemCreativeCategory;
import cn.nukkit.item.customitem.data.RenderOffsets;
import cn.nukkit.math.Vector3;
import cn.nukkit.nbt.tag.CompoundTag;
import com.penguin.Windows.Build_Item_Win.Build_Menu;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BuildGod_Item extends ItemCustomTool implements Listener {
    public BuildGod_Item() {
        super("np:build_item", "万 物 启 原 (右键开启面板)", "build_item");
    }

    @Override
    public CustomItemDefinition getDefinition() {
        return CustomItemDefinition
                .toolBuilder(this, ItemCreativeCategory.EQUIPMENT)
                .addRepairItems(List.of(Item.fromString("minecraft:amethyst_shard")), 100)
                .addRepairItems(List.of(Item.fromString("yes:amethyst_spear")), 400)
                .renderOffsets(RenderOffsets.scaleOffset(64)) // textureSize必须是16的倍数
//                .creativeGroup("itemGroup.name.sword")
                .allowOffHand(true)
                .handEquipped(true)
                .creativeGroup("超级工具类")
                .customBuild(nbt -> nbt.getCompound("components")
                        .putCompound("minecraft:cooldown", new CompoundTag()
                                .putString("category", "build_god")
                                .putFloat("duration", 3f))
                        .getCompound("item_properties").putBoolean("animates_in_toolbar", true)
                        .getCompound("item_properties").putInt("use_duration", 640));
    }

    @Override
    public int getTier() {
        return 4;
    }

    @Override
    public int getAttackDamage() {
        return 999;
    }


    @Override
    public boolean onClickAir(@NotNull Player p, Vector3 directionVector) {
        if (!p.isSpectator() && p.isOp()) {
            p.showFormWindow(Build_Menu.getBuildWindow());
            return true;
        }
        return false;
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
    public int getMaxStackSize() {
        return 1;
    }
}
