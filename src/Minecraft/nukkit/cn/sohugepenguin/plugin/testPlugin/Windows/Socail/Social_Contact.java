package Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Windows.Socail;

import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;

public class Social_Contact extends FormWindowSimple{
    public Social_Contact() {
        super("社交系统", "在这里结交新的朋友，并与他们互动！");
    }

    public static FormWindowSimple getWindowSocial_Contact(){
        FormWindowSimple home = new Social_Contact();
        home.addButton(new ElementButton("我的好友", new ElementButtonImageData("path","textures/ui/world_friends")));
        home.addButton(new ElementButton("好友申请表", new ElementButtonImageData("path","textures/ui/icon_book_writable")));
        home.addButton(new ElementButton("社交开放权限设置", new ElementButtonImageData("path","textures/ui/setting")));
        home.addButton(new ElementButton("返回", new ElementButtonImageData("path","textures/ui/back")));
        return home;
    }
}
