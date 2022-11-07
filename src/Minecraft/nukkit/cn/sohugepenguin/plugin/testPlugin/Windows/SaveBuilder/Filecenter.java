package Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Windows.SaveBuilder;

import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;

import java.io.File;

public class Filecenter extends FormWindowSimple {
    public Filecenter() {
        super("\uE100op用建筑储存器\uE100", "尊敬的管理员\n你正在管理服务器的建筑储存，不懂的请看介绍");
    }
    public static FormWindowSimple Crate_Files(){
        FormWindowSimple home = new Filecenter();
        File[] listFiles = new File("penguin_plugin","Builder_Save").listFiles();
        assert listFiles != null;
        home.addButton(new ElementButton("新建文件夹", new ElementButtonImageData("path","textures/ui/create")));
        for (File wFolder : listFiles) {
            home.addButton(new ElementButton(wFolder.getName(), new ElementButtonImageData("path","textures/ui/file")));
        }
        return home;
    }
}