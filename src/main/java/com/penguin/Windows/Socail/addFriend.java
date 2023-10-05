/**
 * @Project MC
 * @File addFriend
 * @Time 2023/1/4 18:49
 * @ToDo
 * @Author SoHugePenguin
 */
package com.penguin.Windows.Socail;

import cn.nukkit.Player;
import cn.nukkit.form.element.Element;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.window.FormWindowCustom;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class addFriend extends FormWindowCustom {
    public static final int WORLDS = 0x661BA08;

    public addFriend(List<Element> elements) {
        super("§6添加好友", elements);
    }

    public static void add(@NotNull Player p) {
        List<Element> el = new ArrayList<>();
        el.add(new ElementLabel("§l§6请输入玩家名！"));
        el.add(new ElementInput(" "));
        p.showFormWindow(new addFriend(el), WORLDS);
    }
}
