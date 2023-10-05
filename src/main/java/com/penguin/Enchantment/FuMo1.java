/**
 * @Project MC
 * @File FuMo1
 * @Time 2023/2/27 11:52
 * @ToDo
 * @Author SoHugePenguin
 */
package com.penguin.Enchantment;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.item.enchantment.EnchantmentType;
import cn.nukkit.utils.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class FuMo1 extends Enchantment {
    public FuMo1() {
        super(Objects.requireNonNull(Identifier.of("np", "penguin_fumo1")),
                "秒杀",
                Rarity.VERY_RARE, EnchantmentType.SWORD);
    }

    public FuMo1(@NotNull Identifier identifier, String name, Rarity rarity, @NotNull EnchantmentType type) {
        super(identifier, name, rarity, type);
    }

    @Override
    public void doAttack(Entity attacker, Entity entity) {
        //打别人的效果
        Player player = (Player) attacker;
        player.sendMessage("你攻击了 " + entity.getName() + " , 该实体直接被秒杀");
        entity.kill();
        super.doAttack(attacker, entity);
    }

    @Override
    public void doPostAttack(Entity attacker, Entity entity) {
        //被打时的效果
        super.doPostAttack(attacker, entity);
    }
}
