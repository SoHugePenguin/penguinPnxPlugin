package com.penguin.Windows.Build_Item_Win;

import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;

public class Build_Menu extends FormWindowSimple {

    public Build_Menu() {
        super("创世神菜单", "    选择你要使用填充方式！");
    }

    public static Build_Menu getBuildWindow() {
        Build_Menu home = new Build_Menu();
        home.addButton(new ElementButton("普通填充", new ElementButtonImageData("path", "textures/ui/create")));
        home.addButton(new ElementButton("仅对空气填充", new ElementButtonImageData("path", "textures/ui/create")));
        home.addButton(new ElementButton("方块替换", new ElementButtonImageData("path", "textures/ui/create")));
        home.addButton(new ElementButton("百分比随机填充", new ElementButtonImageData("path", "textures/ui/create")));
        home.addButton(new ElementButton("撤回上一次填充", new ElementButtonImageData("path", "textures/ui/create")));
        return home;
    }
}
