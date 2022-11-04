package Minecraft.nukkit.cn.shygard.nukkit.testPlugin.Windows.Home;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;

import java.io.File;

public class HomeList extends FormWindowSimple {
    public HomeList() {
        super("OP·家园管理", "尊敬的管理员\n你正在管理服务器所有已有家园维度。请选择你的操作；");
    }

    public static FormWindowSimple Home_List(Player p){
        FormWindowSimple home = new HomeList();
            File[] listFiles = new File("worlds").listFiles();
            assert listFiles != null;
            for (File wFolder : listFiles) {
                if (wFolder.isDirectory() && wFolder.getName().contains("的家园")) {
                    home.addButton(new ElementButton(wFolder.getName(), new ElementButtonImageData("path", "textures/ui/promo_wolf")));
                }
            }
        home.addButton(new ElementButton("返回", new ElementButtonImageData("path","textures/ui/back")));
        return home;
    }
}
