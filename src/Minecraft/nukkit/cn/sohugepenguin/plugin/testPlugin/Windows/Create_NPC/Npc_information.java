package Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Windows.Create_NPC;

import Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Entity.BaseNpc;
import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.window.FormWindowSimple;

public class Npc_information extends FormWindowSimple {
    public Npc_information(String text) {
        super("§cNPC§6管理系统", text);
    }

    public static FormWindowSimple getNpc_information(Player p) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < Npc_HuTao.PlayerList.size(); i++) {
            if (p.getName().equals(Npc_HuTao.PlayerList.get(i))) {
                BaseNpc baseNpc = Npc_HuTao.PlayerTargetEntityList.get(i);

                text.append("UUID序列号：").append(baseNpc.namedTag.get("account")).
                        append("\n姓名：").append(baseNpc.getName()).
                        append("\n坐标：").append(baseNpc.getLocation()).
                        append("\n模型大小：").append(baseNpc.scale).
                        append("\n皮肤：").append(baseNpc.config.get("skin")).
                        append("\n碰撞箱  高：").append(baseNpc.getHeight()).append("  宽：").append(baseNpc.getWidth()).
                        append("\n手持物：").append(baseNpc.getInventory().getItemInHand().getNamespaceId());
            }
        }
        FormWindowSimple home = new Npc_information(text.toString());
        home.addButton(new ElementButton("确定"));
        return home;
    }
}
