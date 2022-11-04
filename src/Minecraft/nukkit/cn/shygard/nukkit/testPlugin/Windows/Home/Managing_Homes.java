package Minecraft.nukkit.cn.shygard.nukkit.testPlugin.Windows.Home;

import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;

public class Managing_Homes extends FormWindowSimple {
    public Managing_Homes() {
        super("", "");
    }

    public static FormWindowSimple Managing_List(String s){
        FormWindowSimple home = new Managing_Homes();
        home.setTitle(s);
        home.setContent("§6你正在管理 §b§o"+ s +"\n请合理操作！");
        home.addButton(new ElementButton("§6传送至该家园" , new ElementButtonImageData("path","textures/ui/the_world")));
        home.addButton(new ElementButton("§b查看详细信息" , new ElementButtonImageData("path","textures/ui/icon_book_writable")));
        home.addButton(new ElementButton("§d删除该家园 §o§c(§4严禁乱用§c)" , new ElementButtonImageData("path","textures/ui/icon_trash")));
        home.addButton(new ElementButton("返回", new ElementButtonImageData("path","textures/ui/back")));
        return home;
    }
}
