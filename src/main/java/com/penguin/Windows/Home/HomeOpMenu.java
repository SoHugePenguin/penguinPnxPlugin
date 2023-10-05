package com.penguin.Windows.Home;

import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;

public class HomeOpMenu extends FormWindowSimple {
    public HomeOpMenu() {
        super("OP·家园管理", "尊敬的管理员\n你正在管理服务器所有已有家园维度。请选择你的操作；");
    }

    public static FormWindowSimple getWindowHomeOpMenu() {
        FormWindowSimple home = new HomeOpMenu();
        ElementButtonImageData elementButtonImageData = new ElementButtonImageData("path", "textures/ui/icon_recipe_nature");
        home.addButton(new ElementButton("列出所有家园", elementButtonImageData));
        home.addButton(new ElementButton("返回", new ElementButtonImageData("path", "textures/ui/back")));
        return home;
    }
}
