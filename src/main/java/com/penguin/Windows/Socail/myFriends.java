/**
 * @Project MC
 * @File myFriends
 * @Time 2023/1/4 19:06
 * @ToDo
 * @Author SoHugePenguin
 */
package com.penguin.Windows.Socail;

import cn.nukkit.Player;
import cn.nukkit.form.window.FormWindowSimple;


public class myFriends extends FormWindowSimple {
    public static final int WORLDS = 0x661BA10;

    public myFriends(String title, String content) {
        super(title, content);
    }

    public static void my_Friends(Player p) {
        myFriends myFriends = new myFriends("我的好友", "在线0/100");
        p.showFormWindow(myFriends, WORLDS);
    }
}
