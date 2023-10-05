/**
 * @Project MC
 * @File friendApply
 * @Time 2023/1/4 18:59
 * @ToDo
 * @Author SoHugePenguin
 */
package com.penguin.Windows.Socail;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;
import org.jetbrains.annotations.NotNull;

public class friendApply extends FormWindowSimple {
    public static final int WORLDS = 0x661BA09;

    public friendApply(String title, String content) {
        super(title, content);
    }

    public static void frined_Apply(@NotNull Player p) {
        friendApply friendApply = new friendApply("好友申请", "以下玩家想要申请你为好友：");
        friendApply.addButton(new ElementButton("返回", new ElementButtonImageData("path", "textures/ui/back")));
        p.showFormWindow(friendApply, WORLDS);
    }
}
