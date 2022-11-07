package Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Items.Saber;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemSwordDiamond;
import cn.nukkit.item.customitem.CustomItemDefinition;
import cn.nukkit.item.customitem.ItemCustomTool;
import cn.nukkit.item.customitem.data.ItemCreativeCategory;
import cn.nukkit.item.customitem.data.Offset;
import cn.nukkit.item.customitem.data.RenderOffsets;
import cn.nukkit.math.Vector3;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.scheduler.TaskHandler;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class spear_1 extends ItemCustomTool {
    TaskHandler handler;

    public spear_1() {
        super("yes:amethyst_spear", "penguin_spear", "empty");
    }

    @Override
    public CustomItemDefinition getDefinition() {
        return CustomItemDefinition
                .toolBuilder(this, ItemCreativeCategory.EQUIPMENT)
                .addRepairItems(List.of(Item.fromString("minecraft:amethyst_shard")), 100)
                .addRepairItems(List.of(Item.fromString("yes:amethyst_spear")), 400)
                .creativeGroup("itemGroup.name.sword")
                .allowOffHand(false)
                .handEquipped(true)
                .renderOffsets(new RenderOffsets(
                        Offset.builder()
                                .position(0.48f, -0.128f, -0.946f)
                                .rotation(11.696f, -64.536f, 79.413f)
                                .scale(0.038f, 0.037f, 0.038f),
                        Offset.builder()
                                .position(0.258f, 0.979f, -0.541f)
                                .rotation(-63.268f, -43.969f, 144.041f)
                                .scale(0.094f, 0.094f, 0.094f),
                        Offset.builder()
                                .position(-1.053f, 0.136f, -0.803f)
                                .rotation(27.273f, 67.731f, -64.494f)
                                .scale(0.063f, 0.063f, 0.063f),
                        Offset.builder()
                                .position(0.258f, 0.979f, -0.541f)
                                .rotation(-63.268f, -43.969f, 144.041f)
                                .scale(0.094f, 0.094f, 0.094f)
                )).customBuild(nbt -> {
                    nbt.getCompound("components")
                            .putCompound("minecraft:cooldown", new CompoundTag()
                                    .putString("category", "amethyst_spear")
                                    .putFloat("duration", 3f))
                            .getCompound("item_properties").putBoolean("animates_in_toolbar", true)
                            .getCompound("item_properties").putInt("use_duration", 640);
                });
    }

    @Override
    public int getMaxDurability() {
        return 500;
    }

    @Override
    public int getTier() {
        return ItemSwordDiamond.TIER_DIAMOND;
    }

    @Override
    public int getAttackDamage() {
        return 4;
    }

    @Override
    public int getEnchantAbility() {
        return 22;
    }

    @Override
    public boolean isSword() {
        return true;
    }

    @Override
    public boolean onClickAir(Player player, Vector3 directionVector) {
        if (!player.containTag("noUseStab")) {
            player.sendMessage("You Right Click Air!");

//            执行右键命令


            player.setItemCoolDown(60, "amethyst_spear");//cd 3s 60tick
            player.addTag("noUseStab");
            AtomicInteger i = new AtomicInteger();
            handler = Server.getInstance().getScheduler().scheduleRepeatingTask(Server.getInstance().getPluginManager().getPlugin("Penguin_Plugin_1"), () -> {
                if(i.get() >=1){
                    player.removeTag("noUseStab");
                    handler.cancel();
                }
                i.getAndIncrement();
            }, 60);
        }else {
            player.sendTip("冷却中！");
        }
        return super.onClickAir(player, directionVector);
    }
}
