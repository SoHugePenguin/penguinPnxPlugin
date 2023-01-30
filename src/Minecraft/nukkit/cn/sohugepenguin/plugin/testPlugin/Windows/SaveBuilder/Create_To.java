package Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Windows.SaveBuilder;

import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;

import java.io.File;


public class Create_To extends FormWindowSimple {
    public Create_To() {
        super("§m§b保存文件·菜单", "");
    }

    public static FormWindowSimple Create_Yml_To(String text) {
        FormWindowSimple home = new Create_To();
        if (text.equals("")) {
            home = new FormWindowSimple("错误！", "§l§c建筑储存不能没有名字！！！");
            home.addButton(new ElementButton("确定！"));
        } else {
            home.setContent("§a" + text + "\n§l§6  文件保存至>>>\n");
            File[] listFiles = new File("penguin_plugin", "Builder_Save").listFiles();
            assert listFiles != null;
            for (File wFolder : listFiles) {
                home.addButton(new ElementButton(wFolder.getName(), new ElementButtonImageData("path", "textures/ui/book3")));
            }
        }
        return home;
    }
}