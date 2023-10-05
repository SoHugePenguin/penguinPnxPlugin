package com.penguin.Windows.Setting;

import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;

public class Setting extends FormWindowSimple {
    public Setting() {
        super("系统设置", "为你的玩法手感和习惯对你的游戏做调整以适应喜好；");
    }

    public static FormWindowSimple getWindowSetting() {
        FormWindowSimple home = new Setting();
        home.addButton(new ElementButton("设置1", new ElementButtonImageData("path", "textures/ui/setting")));
        home.addButton(new ElementButton("设置2", new ElementButtonImageData("path", "textures/ui/setting")));
        home.addButton(new ElementButton("设置3", new ElementButtonImageData("path", "textures/ui/setting")));
        home.addButton(new ElementButton("返回", new ElementButtonImageData("path", "textures/ui/back")));
        return home;
    }
}
