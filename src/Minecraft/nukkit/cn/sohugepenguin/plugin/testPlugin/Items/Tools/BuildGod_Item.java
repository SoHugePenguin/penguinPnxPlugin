package Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Items.Tools;

import cn.nukkit.Player;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerItemConsumeEvent;
import cn.nukkit.item.Item;
import cn.nukkit.item.customitem.CustomItemDefinition;
import cn.nukkit.item.customitem.ItemCustomTool;
import cn.nukkit.item.customitem.data.ItemCreativeCategory;
import cn.nukkit.item.customitem.data.RenderOffsets;
import cn.nukkit.item.food.Food;
import cn.nukkit.level.Sound;
import cn.nukkit.nbt.tag.CompoundTag;

import java.util.List;

public class BuildGod_Item extends ItemCustomTool implements Listener {
    public BuildGod_Item() {
        super("np:build_item", "创造神", "build_item");
    }

    @Override
    public CustomItemDefinition getDefinition() {
        return CustomItemDefinition
                .toolBuilder(this, ItemCreativeCategory.EQUIPMENT)
                .addRepairItems(List.of(Item.fromString("minecraft:amethyst_shard")), 100)
                .addRepairItems(List.of(Item.fromString("yes:amethyst_spear")), 400)
                .renderOffsets(RenderOffsets.scaleOffset(64))
//                textureSize必须是16的倍数
                .creativeGroup("itemGroup.name.sword")
                .allowOffHand(false)
                .handEquipped(true)
                .customBuild(nbt -> {
                    nbt.getCompound("components")
                            .putCompound("minecraft:cooldown", new CompoundTag()
                                    .putString("category", "build_god")
                                    .putFloat("duration", 3f))
                            .getCompound("item_properties").putBoolean("animates_in_toolbar", true)
                            .getCompound("item_properties").putInt("use_duration", 640);
                });
    }

    @Override
    public int getTier() {
        return 4;
    }

    @Override
    public Item setLore(String... lines) {
        return this;
    }

//    @Override
//    public boolean allowOffHand() {
//        return false;
//    }

    @Override
    public boolean onRelease(Player player, int ticksUsed) {
        return super.onRelease(player, ticksUsed);
    }

    @Override
    public boolean onUse(Player player, int ticksUsed) {
        PlayerItemConsumeEvent consumeEvent = new PlayerItemConsumeEvent(player, this);
        if (ticksUsed < 10) {
            return false;
        } else {
            player.getServer().getPluginManager().callEvent(consumeEvent);
            if (consumeEvent.isCancelled()) {
                return false;
            } else {
                Food food = Food.getByRelative(this);
                player.completeUsingItem(this.getNetworkId(), 1);
                player.getLevel().addSound(player, Sound.RANDOM_BURP);
                if (player.isCreative() && !player.isSpectator()) {
                    player.sendMessage("awa");
                }
                return true;
            }
        }
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
