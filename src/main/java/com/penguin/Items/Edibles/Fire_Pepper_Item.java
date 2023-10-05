package com.penguin.Items.Edibles;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.item.customitem.CustomItemDefinition;
import cn.nukkit.item.customitem.ItemCustomEdible;
import cn.nukkit.item.customitem.data.ItemCreativeCategory;
import cn.nukkit.item.food.Food;
import cn.nukkit.item.food.FoodNormal;
import cn.nukkit.math.Vector3;
import cn.nukkit.plugin.Plugin;
import com.penguin.App;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static cn.nukkit.network.protocol.CameraShakePacket.CameraShakeAction.ADD;
import static cn.nukkit.network.protocol.CameraShakePacket.CameraShakeType.ROTATIONAL;

public class Fire_Pepper_Item extends ItemCustomEdible {

    private FoodNormal food;

    public Fire_Pepper_Item() {
        super("np:lajiao", "绝云椒椒", "lajiao");
    }

    private static Food registerFood(Food addRelative) {
        return addRelative;
    }

    public Map.Entry<Plugin, Food> getFood() {
        return Map.entry(App.getMain(),
                new food1().addRelative("np:lajiao", 0, App.getMain())
                        .setEatingTick(40)
                        .setRestoreFood(2)
                        .setRestoreSaturation(0.5f));
    }

    @Override
    public CustomItemDefinition getDefinition() {
        return CustomItemDefinition
                .edibleBuilder(this, ItemCreativeCategory.ITEMS)
                .allowOffHand(true)
                .handEquipped(false)
                .foil(true)
                .build();
    }

    @Override
    public boolean canAlwaysEat() {
        return true;
    }

    @Override
    public int getTier() {
        return Item.CARROT;
    }

    @Override
    public boolean onClickAir(@NotNull Player player, Vector3 directionVector) {
        //在onUse方法使用前会先执行onClickAir，返回true时才可以吃。
        return true;
    }

    @Override
    public boolean onUse(@NotNull Player player, int ticksUsed) {
        player.fireTicks = 160;
        player.shakeCamera(0.5F, 3F, ROTATIONAL, ADD);
        return super.onUse(player, ticksUsed);
    }

    @Override
    public boolean isUnbreakable() {
        return true;
    }


    @Override
    public int getMaxStackSize() {
        return 32;
    }
}
