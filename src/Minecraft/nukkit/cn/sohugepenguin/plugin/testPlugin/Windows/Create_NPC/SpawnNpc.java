package Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Windows.Create_NPC;

import Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Entity.BaseNpc;
import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.item.Item;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.utils.Config;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class SpawnNpc {
    static BaseNpc human;

    public static void Spawn_Npc(Player p) {
        human = new BaseNpc(p.getLocation().getChunk(), Entity.getDefaultNBT(p.getPosition())
                .putString("account", "null")
                .putCompound("Skin", (new CompoundTag()))
        );
        human.namedTag.putString("account", human.getUniqueId().toString());
        File[] fileList = new File("penguin_plugin/Npc_config").listFiles();
        assert fileList != null;
        boolean is_ok = true;
        for (File Folder : fileList) {
            Config config = new Config(Folder, 2);
            if (!Folder.getName().contains("npcBase") && config.get("x").equals(human.x) && config.get("y").equals(human.y) && config.get("z").equals(human.z)) {
                p.sendMessage("§c npc不能与其他npc坐标重叠！请重新放置！");
                is_ok = false;
                break;
            }
        }
        if (is_ok) {
            Config npc_config = new Config("penguin_plugin/Npc_config/npcBase.yml", 2);
            Config npc = new Config("penguin_plugin/Npc_config/npc" + npc_config.getInt("npcNumber") + ".yml", 2);
            npc.set("uuid", human.getUniqueId().toString());
            npc.set("world", human.getLevel().getName());
            npc.set("x", human.x);
            npc.set("y", human.y);
            npc.set("z", human.z);
            npc.set("name", "npc" + npc_config.getInt("npcNumber"));
            npc.set("skin", "steve.png");
            npc.set("model_size", 1);
            npc.set("eye_height", 1.8f);
            npc_config.set("npcNumber", npc_config.getInt("npcNumber") + 1);
            npc.save();
            npc_config.save();
            human.getInventory().setItemInHand(Item.get(40));
            human.spawnTo(p);
            human.spawnToAll();
            //生成实体需要重新发包才能显示正常//
            final int[] t = {2};
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                public void run() {
                    if (t[0] == 2) {
                        human.y++;
                        t[0]--;
                    } else if (t[0] == 1) {
                        human.y--;
                        t[0]--;
                    }
                }
            };
            timer.schedule(task, 2500, 2500);
            //延迟2秒执行,并且每隔2秒定时执行
            //timer.schedule(task,2000);//延迟2秒执行
            p.sendMessage("§o§a>>>您成功召唤了一个初始NPC！");
        }
    }
}
