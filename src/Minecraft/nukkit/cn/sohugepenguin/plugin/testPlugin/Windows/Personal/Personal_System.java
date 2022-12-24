package Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Windows.Personal;

import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;

public class Personal_System extends FormWindowSimple {
    public Personal_System() {
        super("个人信息", "查看你的个人信息！");
    }

    public static FormWindowSimple getWindowPersonal_System() {
        FormWindowSimple home = new Personal_System();
        home.addButton(new ElementButton("经济", new ElementButtonImageData("path", "textures/ui/cake")));
        home.addButton(new ElementButton("个人事件记录", new ElementButtonImageData("path", "textures/ui/icon_book_writable")));
        home.addButton(new ElementButton("其他信息", new ElementButtonImageData("path", "textures/ui/icon_bookshelf")));
        home.addButton(new ElementButton("返回", new ElementButtonImageData("path", "textures/ui/back")));
        return home;
    }
}
