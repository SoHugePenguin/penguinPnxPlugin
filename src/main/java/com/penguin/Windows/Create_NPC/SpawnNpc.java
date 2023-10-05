package com.penguin.Windows.Create_NPC;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.utils.Config;
import com.penguin.Entity.BaseNpc;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class SpawnNpc {


    public static void Spawn_Npc(Player p) {
        Config npc_config = new Config("penguin_plugin/Npc_config/npcBase.yml", 2);
        npc_config.set("npcNumber", npc_config.getInt("npcNumber") + 1);
        npc_config.save();

        BaseNpc human = new BaseNpc(p.getLocation().getChunk(), Entity.getDefaultNBT(p.getPosition())
                .putString("account", "null")
                .putCompound("Skin", (new CompoundTag())));

        human.namedTag.putString("account", human.getUniqueId().toString());
        File[] fileList = new File("penguin_plugin/Npc_config").listFiles();
        assert fileList != null;

        human.setSkin(p.getSkin());

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

        human.config.set("uuid", human.getUniqueId().toString());
        human.config.save();

        p.sendMessage("§o§a>>>您成功召唤了一个初始NPC！");
    }
}
