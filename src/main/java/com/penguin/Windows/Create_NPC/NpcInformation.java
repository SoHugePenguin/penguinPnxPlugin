package com.penguin.Windows.Create_NPC;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.window.FormWindowSimple;
import com.penguin.Entity.BaseNpc;

public class NpcInformation extends FormWindowSimple {
    public NpcInformation(String text) {
        super("§cNPC§6管理系统", text);
    }

    public static FormWindowSimple getNpc_information(Player p) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < NpcBaseEntity.PlayerList.size(); i++) {
            if (p.getName().equals(NpcBaseEntity.PlayerList.get(i))) {
                BaseNpc baseNpc = NpcBaseEntity.PlayerTargetEntityList.get(i);

                text.append("UUID序列号：").append(baseNpc.namedTag.get("account")).
                        append("\n姓名：").append(baseNpc.getName()).
                        append("\n坐标：").append(baseNpc.getLocation()).
                        append("\n模型大小：").append(baseNpc.scale).
                        append("\n皮肤：").append(baseNpc.config.get("skin")).
                        append("\n碰撞箱  高：").append(baseNpc.getHeight()).append("  宽：").append(baseNpc.getWidth()).
                        append("\n手持物：").append(baseNpc.getInventory().getItemInHand().getNamespaceId());
            }
        }
        FormWindowSimple home = new NpcInformation(text.toString());
        home.addButton(new ElementButton("确定"));
        return home;
    }
}
