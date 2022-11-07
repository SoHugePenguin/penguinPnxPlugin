package Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Windows.Create_NPC;

import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;

public class Npc_Setting_Base extends FormWindowSimple {
    public Npc_Setting_Base() {
        super("§cNPC§6管理系统", "§o§b高自定义NPC系统\n§6你可以在这里生成/修改你的NPC");
    }
    public static FormWindowSimple getNpc_Setting_Base(){
        FormWindowSimple home = new Npc_Setting_Base();
        home.addButton(new ElementButton("§6名字设置", new ElementButtonImageData("path","textures/ui/mashup_PaintBrush")));
        home.addButton(new ElementButton("§6模型大小设置", new ElementButtonImageData("path","textures/ui/setting")));
        home.addButton(new ElementButton("§6装备/手持物设置", new ElementButtonImageData("path","textures/ui/setting")));
        home.addButton(new ElementButton("§c删除NPC", new ElementButtonImageData("path","textures/ui/setting")));
        home.addButton(new ElementButton("返回", new ElementButtonImageData("path","textures/ui/back")));
        return home;
    }
}
