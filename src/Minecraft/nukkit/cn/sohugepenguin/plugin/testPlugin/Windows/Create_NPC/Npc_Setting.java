package Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Windows.Create_NPC;

import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;

public class Npc_Setting extends FormWindowSimple {
    public Npc_Setting() {
        super("§cNPC§6管理系统", "§o§b高自定义NPC系统\n§6你可以在这里生成/修改你的NPC");
    }

    public static FormWindowSimple getNpc_Setting() {
        FormWindowSimple home = new Npc_Setting();
        home.addButton(new ElementButton("§a基础设置", new ElementButtonImageData("path", "textures/ui/mashup_PaintBrush")));
        home.addButton(new ElementButton("§6皮肤设置", new ElementButtonImageData("path", "textures/ui/setting")));
        home.addButton(new ElementButton("§b行为设置", new ElementButtonImageData("path", "textures/ui/icon_recipe_equipment")));
        home.addButton(new ElementButton("返回", new ElementButtonImageData("path", "textures/ui/back")));
        return home;
    }
}
