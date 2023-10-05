package com.penguin.Windows.Create_NPC;

import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;

public class NpcMenu extends FormWindowSimple {
    public NpcMenu() {
        super("§cNPC§6管理系统", "§o§b高自定义NPC系统\n§6你可以在这里生成/修改你的NPC");
    }

    public static FormWindowSimple getNpc_Menu() {
        FormWindowSimple home = new NpcMenu();
        home.addButton(new ElementButton("§6生成一个NPC", new ElementButtonImageData("path", "textures/ui/mashup_PaintBrush")));
        home.addButton(new ElementButton("§o§cNPC管理", new ElementButtonImageData("path", "textures/ui/setting")));
        home.addButton(new ElementButton("返回", new ElementButtonImageData("path", "textures/ui/back")));
        return home;
    }
}
