package Minecraft.nukkit.cn.shygard.nukkit.testPlugin.Windows.Teleport;

import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;

public class Teleport_Menu extends FormWindowSimple {
    public Teleport_Menu() {
        super("传送系统", "便捷传送\n请选择你要传送的目的地！");
    }
    public static FormWindowSimple getWindowTeleport_Menu(){
        FormWindowSimple home = new Teleport_Menu();
        home.addButton(new ElementButton("回到主世界出生地", new ElementButtonImageData("path","textures/ui/icon_spring")));
        home.addButton(new ElementButton("玩家互传", new ElementButtonImageData("path","textures/ui/promo_creeper")));
        home.addButton(new ElementButton("返回", new ElementButtonImageData("path","textures/ui/back")));
        return home;
    }
}
