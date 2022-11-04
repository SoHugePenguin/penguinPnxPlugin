package Minecraft.nukkit.cn.shygard.nukkit.testPlugin.Windows.SaveBuilder;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;

import java.io.File;

public class FileList extends FormWindowSimple {
    public FileList() {
        super("--|||建筑列表|||--", "");
    }

    public static FormWindowSimple Window_File_List(Player p, String file) {
        FormWindowSimple home = new FileList();
        File[] listFiles = new File("penguin_plugin","Builder_Save\\" + file).listFiles();
        assert listFiles != null;
        for (File wFolder : listFiles) {
            home.addButton(new ElementButton(wFolder.getName(), new ElementButtonImageData("path", "textures/ui/book3")));
        }
        return home;
    }
}