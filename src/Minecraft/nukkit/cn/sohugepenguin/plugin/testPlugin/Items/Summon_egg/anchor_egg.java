package Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Items.Summon_egg;

import Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Entity.Anchor;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.entity.Entity;
import cn.nukkit.item.Item;
import cn.nukkit.item.customitem.CustomItemDefinition;
import cn.nukkit.item.customitem.ItemCustomTool;
import cn.nukkit.item.customitem.data.ItemCreativeCategory;
import cn.nukkit.math.Vector3;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.scheduler.TaskHandler;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class anchor_egg extends ItemCustomTool {
    TaskHandler handler;

    public anchor_egg() {
        super("np:anchor_egg", "anchor_egg", "empty");
    }

    @Override
    public CustomItemDefinition getDefinition() {
        return CustomItemDefinition
                .toolBuilder(this, ItemCreativeCategory.NATURE)
                .creativeGroup("itemGroup.name.mobEgg")
                .creativeGroup("itemGroup.name.sword")
                .allowOffHand(false)
                .handEquipped(true)
                .customBuild(nbt -> {
                    nbt.getCompound("components")
                            .putCompound("minecraft:cooldown", new CompoundTag()
                                    .putString("category", "anchor_summon")
                                    .putFloat("duration", 3f))
                            .getCompound("item_properties").putBoolean("animates_in_toolbar", true)
                            .getCompound("item_properties").putInt("use_duration", 640);
                });
    }

    @Override
    public boolean onClickAir(Player player, Vector3 directionVector) {
        if (!player.containTag("noUseStab")) {
            if (!anchor_information.name.contains(player.getInventory().getItemInHand().getName())) {
                Anchor anchor = new Anchor(player.getLocation().getChunk(), Entity.getDefaultNBT(player.getPosition())
                        .putString("account", "null")
                        .putCompound("Skin", (new CompoundTag()))
                );
                anchor.setNameTag(player.getInventory().getItemInHand().getName());
                anchor.spawnTo(player);
                player.sendMessage("You Create a Anchor name " + player.getInventory().getItemInHand().getName());
            } else {
                player.sendMessage("已经有该名的传送锚点了，请用铁砧改名手中的物品");
            }


            player.setItemCoolDown(60, "anchor_summon");//cd 3s 60tick
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
}
