package Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Windows.Create_NPC;

import Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Entity.BaseNpc;
import cn.nukkit.Player;
import cn.nukkit.utils.Config;

import java.io.File;

public class Npc_Setting_Base_Delete {
    public static void Npc_Delete(Player p) {
        for (int i = 0; i < Npc_HuTao.PlayerList.size(); i++) {
            if (p.getName().equals(Npc_HuTao.PlayerList.get(i))) {
                BaseNpc baseNpc = Npc_HuTao.PlayerTargetEntityList.get(i);
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
