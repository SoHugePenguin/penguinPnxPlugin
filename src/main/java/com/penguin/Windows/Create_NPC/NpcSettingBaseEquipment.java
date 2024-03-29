package com.penguin.Windows.Create_NPC;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.window.FormWindowSimple;
import com.penguin.Entity.BaseNpc;


public class NpcSettingBaseEquipment extends FormWindowSimple {
    public NpcSettingBaseEquipment() {
        super("§6npc装备物品管理", "可以直接复制你现在身上的装备和双手物品到NPC上，主手如果不能正常显示请重新进入服务器！");
    }

    public static FormWindowSimple Change_Npc_Equipment() {
        FormWindowSimple home = new NpcSettingBaseEquipment();
        home.addButton(new ElementButton("复制自己的手持物/装备到NPC"));
        return home;
    }

    public static void Clone_Equipment(Player p) {
        BaseNpc baseNpc;
        for (int i = 0; i < NpcBaseEntity.PlayerList.size(); i++) {
            if (p.getName().equals(NpcBaseEntity.PlayerList.get(i))) {
                baseNpc = NpcBaseEntity.PlayerTargetEntityList.get(i);
                baseNpc.getInventory().setItemInHand(p.getInventory().getItemInHand());
                for (int j = 0; j < 4; j++) {
                    baseNpc.getInventory().setArmorItem(j, p.getInventory().getArmorItem(j));
                }
                baseNpc.getOffhandInventory().setItem(0, p.getOffhandInventory().getItem(0));
                baseNpc.getInventory().sendHeldItem(p);
                baseNpc.getInventory().sendHeldItem(p); // 客户端刷新
                baseNpc.getOffhandInventory().sendSlot(0, p);
                p.sendToast("§6设置成功！", "§a你成功更新了该npc的装备列表！");
            }
        }
    }
}