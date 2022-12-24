package Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Windows.Create_NPC;

import Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Entity.BaseNpc;
import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.window.FormWindowSimple;


public class Npc_Setting_Base_Equipment extends FormWindowSimple {
    public Npc_Setting_Base_Equipment() {
        super("§6npc装备物品管理", "可以直接复制你现在身上的装备和双手物品到NPC上，主手如果不能正常显示请重新进入服务器！");
    }

    public static FormWindowSimple Change_Npc_Equipment() {
        FormWindowSimple home = new Npc_Setting_Base_Equipment();
        home.addButton(new ElementButton("复制自己的手持物/装备到NPC"));
        return home;
    }

    public static void Clone_Equipment(Player p) {
        BaseNpc baseNpc;
        for (int i = 0; i < Npc_HuTao.PlayerList.size(); i++) {
            if (p.getName().equals(Npc_HuTao.PlayerList.get(i))) {
                baseNpc = Npc_HuTao.PlayerTargetEntityList.get(i);
                baseNpc.getInventory().setItemInHand(p.getInventory().getItemInHand());
                for (int j = 0; j < 4; j++) {
                    baseNpc.getInventory().setArmorItem(j, p.getInventory().getArmorItem(j));
                }
                baseNpc.getOffhandInventory().setItem(0, p.getOffhandInventory().getItem(0));
                p.sendToast("§6设置成功！", "§a你成功更新了该npc的装备列表！");
            }
        }
    }
}