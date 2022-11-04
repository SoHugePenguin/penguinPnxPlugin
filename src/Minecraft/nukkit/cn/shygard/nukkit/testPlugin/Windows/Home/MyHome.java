package Minecraft.nukkit.cn.shygard.nukkit.testPlugin.Windows.Home;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindow;
import cn.nukkit.form.window.FormWindowSimple;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyHome extends FormWindowSimple {
    public MyHome() {
        super("我的家园", "欢迎回来！\n这是你的家园菜单！");
    }
    public static FormWindow MyHomeTest(Player p){
        FormWindowSimple home = new MyHome();
        home.addButton(new ElementButton("创建家园！", new ElementButtonImageData("path","textures/ui/icon_recipe_construction")));
        File[] listFiles = new File("worlds").listFiles();
        assert listFiles != null;
        int i =0;
        for (File Fold : listFiles) {
            if (Fold.getName().equals(p.getName()+"的家园")) {
                File[] list = new File("worlds").listFiles();
                assert list != null;
                for (File wFolder : listFiles) {
                    if (wFolder.isDirectory() && wFolder.getName().equals(p.getName() + "的家园")) {
                        p.getServer().loadLevel(wFolder.getName());
                        home.addButton(new ElementButton(p.getName() + "的家园", new ElementButtonImageData("path", "textures/ui/promo_wolf")));
                    }
                }
                break;
            } else if(i == p.getServer().getLevels().size() - 1){
                Path world_data = Paths.get(Server.getInstance().getDataPath() + "worlds\\" + p.getName() + "的家园\\" + "SkyBlock_Home_Event.yml");
                if (Files.exists(world_data)) {
                    home.addButton(new ElementButton(p.getName() + "的家园", new ElementButtonImageData("path", "textures/ui/promo_wolf")));
                }
            }
            i++;
        }
        home.addButton(new ElementButton("返回", new ElementButtonImageData("path","textures/ui/back")));
        return home;
    }
}
