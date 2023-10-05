package com.penguin.Windows.Socail;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;
import org.jetbrains.annotations.NotNull;

public class Social_Contact extends FormWindowSimple {
    public static final int WORLDS = 0x661BA07;

    public Social_Contact() {
        super("社交系统", "在这里结交新的朋友，并与他们互动！");
    }

    public static void getWindowSocial_Contact(@NotNull Player p) {
        FormWindowSimple home = new Social_Contact();
        home.addButton(new ElementButton("§a添加好友", new ElementButtonImageData("path", "textures/ui/book3")));
        home.addButton(new ElementButton("§g我的好友", new ElementButtonImageData("path", "textures/ui/world_friends")));
        home.addButton(new ElementButton("§b好友申请  §6有0条好友申请", new ElementButtonImageData("path", "textures/ui/icon_book_writable")));
        home.addButton(new ElementButton("返回", new ElementButtonImageData("path", "textures/ui/back")));
        p.showFormWindow(home, WORLDS);
    }
}
