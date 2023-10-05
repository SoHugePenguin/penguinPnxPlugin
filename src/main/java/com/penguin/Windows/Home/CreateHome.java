package com.penguin.Windows.Home;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;
import com.penguin.util.SkyWorldZipFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class CreateHome extends FormWindowSimple {
    static String text;
    static FormWindowSimple home;

    public CreateHome() {
        super("§a家园生成器", text);
    }

    public static FormWindowSimple getWindowCreateHome(Player p) {
        File file = new File("worlds");
        File world_file = new File(file, p.getName() + "的家园");
        if (!world_file.exists()) {
            world_file.mkdirs();
        }
        for (int i = 0; i < p.getServer().getLevels().size(); i++) {
            if (i == p.getServer().getLevels().size() - 1) {
                Path world_data = Paths.get(Server.getInstance().getDataPath() + "worlds\\" + p.getName() + "的家园\\" + "SkyBlock_Home_Event.yml");
                if (!Files.exists(world_data)) {
                    text = "§6你还没有属于你自己的独有家园！\n请在下面几个选项中选出你喜欢的主题家园！";
                    home = new CreateHome();
                    home.addButton(new ElementButton("初始小岛", new ElementButtonImageData("path", "textures/ui/sunset_pending_keyart")));
                } else {
                    text = "§6你已经有了一个家园了，暂时不能多建奥！";
                    home = new CreateHome();
                    break;
                }
            }
        }
        return home;
    }

    public static FormWindowSimple CreateWorld(Player p) {
        for (int i = 0; i < p.getServer().getLevels().size(); i++) {
            if (i == p.getServer().getLevels().size() - 1) {
                Path world_data = Paths.get(Server.getInstance().getDataPath() + "worlds\\" + p.getName() + "的家园\\" + "SkyBlock_Home_Event.yml");
                if (!Files.exists(world_data)) {
                    try {
                        Files.copy(Objects.requireNonNull(CreateHome.class.getClassLoader().getResourceAsStream("SkyBlock_Home_Event.yml")), world_data);
                        SkyWorldZipFile.Zip_Compress("penguin_plugin\\SkyBlock_World\\SkyBlock_Home.zip", "worlds\\" + p.getName() + "的家园");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        File[] listFiles = new File("worlds").listFiles();
        assert listFiles != null;
        for (File wFolder : listFiles) {
            if (wFolder.isDirectory() && wFolder.getName().equals(p.getName() + "的家园")) {
                p.getServer().loadLevel(wFolder.getName());
                text = "§6恭喜你成功创建了一个独属你自己的家园！\n§a查看并使用你的新家园吧！\n\n\n\n§g成功创建--§6" + wFolder.getName() + "§g的世界！";
                home.addButton(new ElementButton("返回", new ElementButtonImageData("path", "textures/ui/back")));
                home = new CreateHome();
            }
        }
        return home;
    }
}
