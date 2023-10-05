package com.penguin.Windows.Create_NPC;

import cn.nukkit.Player;
import cn.nukkit.utils.Config;
import com.penguin.Entity.BaseNpc;

import java.io.File;

public class NpcSettingBaseDelete {
    public static void Npc_Delete(Player p) {
        for (int i = 0; i < NpcBaseEntity.PlayerList.size(); i++) {
            if (p.getName().equals(NpcBaseEntity.PlayerList.get(i))) {
                BaseNpc baseNpc = NpcBaseEntity.PlayerTargetEntityList.get(i);
                baseNpc.alive = false;
                Config config;
                File[] file = new File("penguin_plugin/Npc_config").listFiles();
                assert file != null;
                for (File Folder : file) {
                    config = new Config(Folder, 2);
                    if (!Folder.getName().contains("npcBase") && baseNpc.namedTag.get("account").toString().contains(config.getString("uuid"))) {
                        if (Folder.delete()) {
                            p.sendMessage("§o§8Deleted the file: " + Folder.getName());
                            Config base = new Config("penguin_plugin/Npc_config/npcBase.yml", 2);
                            int value = base.getInt("npcNumber") - 1;
                            base.set("npcNumber", value);
                            base.save();
                            p.getServer().doAutoSave();
                        }
                    }
                }
                p.sendToast("§4删除成功！", "§c你成功删除了该npc");
            }
        }
    }
}
