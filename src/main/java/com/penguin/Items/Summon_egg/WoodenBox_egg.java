package com.penguin.Items.Summon_egg;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.entity.Entity;
import cn.nukkit.item.customitem.CustomItemDefinition;
import cn.nukkit.item.customitem.ItemCustomTool;
import cn.nukkit.item.customitem.data.ItemCreativeCategory;
import cn.nukkit.math.Vector3;
import cn.nukkit.scheduler.TaskHandler;
import com.penguin.Entity.WoodenBox;
import org.jetbrains.annotations.NotNull;

public class WoodenBox_egg extends ItemCustomTool {
    TaskHandler handler;

    public WoodenBox_egg() {
        super("np:woodenbox_egg", "woodenBox_egg", "empty");
    }

    @Override
    public CustomItemDefinition getDefinition() {
        return CustomItemDefinition
                .toolBuilder(this, ItemCreativeCategory.NATURE)
                .creativeGroup("itemGroup.name.mobEgg")
                .allowOffHand(false)
                .handEquipped(true)
                .customBuild(nbt -> nbt.getCompound("components"));
    }
//    itemGroup.name.planks  //木板
//    itemGroup.name.walls  //墙
//    itemGroup.name.fence  //围墙
//    itemGroup.name.fenceGate  //围墙门
//    itemGroup.name.stairs  //楼梯
//    itemGroup.name.door  //门
//    itemGroup.name.glass  //玻璃
//    itemGroup.name.glassPane  //玻璃板
//    itemGroup.name.slab  //台阶
//    itemGroup.name.stoneBrick  //装饰石头
//    itemGroup.name.sandstone  //沙石
//    itemGroup.name.wool  //羊毛
//    itemGroup.name.woolCarpet  //羊毛地毯
//    itemGroup.name.concretePowder  //混凝土粉末
//    itemGroup.name.concrete  //混凝土
//    itemGroup.name.stainedClay  //陶瓦
//    itemGroup.name.glazedTerracotta  //带釉陶瓦
//    itemGroup.name.dye  //染料
//    itemGroup.name.ore  //矿石
//    itemGroup.name.stone  //石头
//    itemGroup.name.log  //原木
//    itemGroup.name.leaves  //树叶
//    itemGroup.name.sapling  //树苗
//    itemGroup.name.seed  //种子
//    itemGroup.name.crop  //农作物
//    itemGroup.name.grass  //地表植物
//    itemGroup.name.flower  //花
//    itemGroup.name.rawFood  //生食
//    itemGroup.name.cookedFood  //熟食
//    itemGroup.name.miscFood  //其他食物
//    itemGroup.name.mushroom  //蘑菇
//    itemGroup.name.monsterStoneEgg  //被虫蚀的石头
//    itemGroup.name.mobEgg  //生物蛋
//    itemGroup.name.helmet  //头盔
//    itemGroup.name.chestplate  //胸甲
//    itemGroup.name.leggings  //护腿
//    itemGroup.name.boots  //靴子
//    itemGroup.name.horseArmor  //马甲
//    itemGroup.name.sword  //剑
//    itemGroup.name.axe  //斧头
//    itemGroup.name.pickaxe  //镐
//    itemGroup.name.shovel  //锹
//    itemGroup.name.hoe  //锄头
//    itemGroup.name.arrow  //箭
//    itemGroup.name.potion  //药水
//    itemGroup.name.splashPotion  //喷溅药水
//    itemGroup.name.lingeringPotion  //滞留药水
//    itemGroup.name.bed  //床
//    itemGroup.name.anvil  //铁砧
//    itemGroup.name.chest  //箱子
//    itemGroup.name.shulkerBox  //潜影盒
//    itemGroup.name.record  //唱片
//    itemGroup.name.skull  //生物模型
//    itemGroup.name.boat  //船
//    itemGroup.name.rail  //铁轨
//    itemGroup.name.minecart  //矿车
//    itemGroup.name.pressurePlate  //压力板
//    itemGroup.name.trapdoor  //活板门
//    itemGroup.name.enchantedBook  //附魔书
//    itemGroup.name.banner  //旗帜
//    itemGroup.name.firework  //烟花火箭
//    itemGroup.name.fireworkStars  //烟火之星
//    itemGroup.name.coral  //珊瑚块
//    itemGroup.name.coral_decorations  //珊瑚装饰
//    itemGroup.name.buttons  //按钮
//    itemGroup.name.sign  //告示牌
//    itemGroup.name.wood  //树林
//    itemGroup.name.banner_pattern  //旗帜图案


    @Override
    public boolean onClickAir(@NotNull Player p, @NotNull Vector3 directionVector) {
        for (int i = 0; i < 5; i++) {
            if (p.getTargetBlock(10) != null && p.getTargetBlock(10).getId() != Block.AIR) {
                Entity mob = new WoodenBox(p.getLocation().getChunk(),
                        Entity.getDefaultNBT(p.getTargetBlock(10).getLocation().setY(p.getTargetBlock(10).getY() + 1))
                );
                mob.yaw += 100;
                break;
            }
        }
        return super.onClickAir(p, directionVector);
    }
}
