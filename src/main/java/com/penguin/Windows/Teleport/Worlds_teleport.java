/**
 * @Project MC
 * @File Worlds_teleport
 * @Time 2022/12/25 3:09
 * @ToDo
 * @Author SoHugePenguin
 */
package com.penguin.Windows.Teleport;/*
 *MC -> Worlds_teleport
 *2022/12/25 3:09
 *ToDo: 多世界传送菜单
 *
 * @Author: SoHugePenguin
 */

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;

import java.io.File;

public class Worlds_teleport extends FormWindowSimple {
    public static final int WORLDS = 0x661BA06;

    public Worlds_teleport(String title, String content) {
        super(title, content);
    }

    public static void WorldTeleport(Player p) {
        Worlds_teleport window = new Worlds_teleport("\uE100§6世界传送菜单\uE100", "§b你可以传送以下世界:");

        File[] listFiles = new File("worlds").listFiles();
        assert listFiles != null;

        //主世界置顶
        window.addButton(new ElementButton("§6" + p.getServer().getDefaultLevel().getName(), new ElementButtonImageData("path", "textures/ui/server_logo")));

        for (File wFolder : listFiles) {
            if (wFolder.isDirectory() &&
                    !wFolder.getName().contains("的家园") &&
                    !wFolder.getName().equals(p.getServer().getDefaultLevel().getName())) {
                window.addButton(new ElementButton("§e" + wFolder.getName(), new ElementButtonImageData("path", "textures/ui/the_world")));
            }
        }
        if (p.isOp()) {
            for (File wFolder : listFiles) {
                if (wFolder.isDirectory() &&
                        wFolder.getName().contains("的家园") &&
                        !wFolder.getName().equals(p.getServer().getDefaultLevel().getName())) {
                    window.addButton(new ElementButton("§a" + wFolder.getName(), new ElementButtonImageData("path", "textures/ui/promo_wolf")));
                }
            }
        }
        p.showFormWindow(window, WORLDS);
    }
}
