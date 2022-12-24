package Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Items.Edibles;

import cn.nukkit.Player;
import cn.nukkit.event.player.PlayerItemConsumeEvent;
import cn.nukkit.item.Item;
import cn.nukkit.item.customitem.CustomItemDefinition;
import cn.nukkit.item.customitem.ItemCustomEdible;
import cn.nukkit.item.customitem.data.ItemCreativeCategory;
import cn.nukkit.item.food.Food;
import cn.nukkit.item.food.FoodNormal;
import cn.nukkit.level.Sound;

import static cn.nukkit.network.protocol.CameraShakePacket.CameraShakeAction.ADD;
import static cn.nukkit.network.protocol.CameraShakePacket.CameraShakeType.ROTATIONAL;

public class Fire_Pepper_Item extends ItemCustomEdible {
    private FoodNormal food;

    public Fire_Pepper_Item() {
        super("np:lajiao", "绝云椒椒", "lajiao");
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
    public int getTier() {
        return Item.CARROT;
    }

    @Override
    public boolean canAlwaysEat() {
        return true;
    }

    @Override
    public int getEatTick() {
        return 40;
    }

    @Override
    public boolean onUse(Player player, int ticksUsed) {
        PlayerItemConsumeEvent consumeEvent = new PlayerItemConsumeEvent(player, this);
        if (ticksUsed < this.getEatTick()) {
            return false;
        } else {
            player.getServer().getPluginManager().callEvent(consumeEvent);
            if (consumeEvent.isCancelled()) {
                return false;
            } else {
                Food food = Food.getByRelative(this);
                player.completeUsingItem(this.getNetworkId(), 1);
                player.getLevel().addSound(player, Sound.RANDOM_BURP);
                if (!player.isCreative() && !player.isSpectator()) {
                    --this.count;
                    player.getInventory().setItemInHand(this);
                    //修改背包物品，此行代码不可缺少！
                    player.fireTicks = 160;
                    player.shakeCamera(0.5F, 3F, ROTATIONAL, ADD);
                    player.getFoodData().addFoodLevel(2, 1.5F);
                }
                return true;
            }
        }
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
