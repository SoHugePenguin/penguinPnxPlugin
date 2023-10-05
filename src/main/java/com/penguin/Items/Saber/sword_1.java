package com.penguin.Items.Saber;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemTool;
import cn.nukkit.item.customitem.CustomItemDefinition;
import cn.nukkit.item.customitem.ItemCustomTool;
import cn.nukkit.item.customitem.data.ItemCreativeCategory;
import cn.nukkit.item.customitem.data.Offset;
import cn.nukkit.item.customitem.data.RenderOffsets;
import cn.nukkit.math.Vector3;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.scheduler.TaskHandler;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class sword_1 extends ItemCustomTool {
    TaskHandler handler;

    public sword_1() {
        super("np:sword_1", "§d武士刀·参之形", "sword_1");
    }

    @NotNull
    @Override
    public CustomItemDefinition getDefinition() {
        return CustomItemDefinition
                .toolBuilder(this, ItemCreativeCategory.EQUIPMENT)
                .speed(10)
                .addExtraBlock("minecraft:web", 15)
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
                .customBuild(nbt -> {
                    nbt.getCompound("components")
                            .putCompound("minecraft:cooldown", new CompoundTag()
                                    .putString("category", "sword_1")
                                    .putFloat("duration", 3f))
                            .getCompound("item_properties").putBoolean("animates_in_toolbar", true)
                            .getCompound("item_properties").putInt("use_duration", 640);
                });
    }

    @Override
    public boolean onClickAir(Player player, Vector3 directionVector) {
        if (!player.containTag("noUseStab")) {
            player.sendMessage("You Right Click Air!");

//            执行右键命令


            player.setItemCoolDown(60, "sword_1");//cd 3s 60tick
            player.addTag("noUseStab");
            AtomicInteger i = new AtomicInteger();
            handler = Server.getInstance().getScheduler().scheduleRepeatingTask(Server.getInstance().getPluginManager().getPlugin("Penguin_Plugin_1"), () -> {
                if (i.get() >= 1) {
                    player.removeTag("noUseStab");
                    handler.cancel();
                }
                i.getAndIncrement();
            }, 60);
        } else {
            player.sendTip("冷却中！");
        }
        return super.onClickAir(player, directionVector);
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
        return 10;
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