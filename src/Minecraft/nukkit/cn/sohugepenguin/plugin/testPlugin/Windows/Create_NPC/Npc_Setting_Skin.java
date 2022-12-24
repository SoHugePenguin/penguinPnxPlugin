package Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Windows.Create_NPC;

import Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Entity.BaseNpc;
import cn.nukkit.Player;
import cn.nukkit.entity.data.Skin;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.utils.Config;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static cn.nukkit.entity.data.Skin.GEOMETRY_CUSTOM;

public class Npc_Setting_Skin extends FormWindowSimple {
    public Npc_Setting_Skin() {
        super("§cNpc皮肤管理", "§o§d你正在更换该NPC的皮肤\n§6请选择你要更换的皮肤！");
    }

    public static FormWindowSimple getNpc_Setting_Skin() {
        FormWindowSimple home = new Npc_Setting_Skin();
        File[] listFiles = new File("penguin_plugin", "skins").listFiles();
        assert listFiles != null;
        for (File Fold : listFiles) {
            home.addButton(new ElementButton(Fold.getName(),
                    new ElementButtonImageData("path", "textures/npc_skins/" + Fold.getName())));
        }
        return home;
    }

    public static void ChangeSkinSuccess(Player p, String text) throws IOException {
        for (int i = 0; i < Npc_HuTao.PlayerList.size(); i++) {
            if (p.getName().equals(Npc_HuTao.PlayerList.get(i))) {
                BaseNpc human = Npc_HuTao.PlayerTargetEntityList.get(i);
                Skin skin = new Skin();
                File file = new File("penguin_plugin", "skins");
                File skin_file = new File(file, text);
                BufferedImage image = ImageIO.read(skin_file);   //skinData的数据类型为RGBA  A为alpha
                skin.setSkinData(image);
                skin.setSkinColor("#FF0004");
                skin.setSkinId("skin");
                skin.setTrusted(true);
                skin.setSkinResourcePatch(GEOMETRY_CUSTOM);
                human.skin = skin;
                human.spawnToAll();
                p.getServer().updatePlayerListData(human.getUniqueId(), human.getId(), human.getName(), human.getSkin());
                p.getServer().removePlayerListData(human.getUniqueId());

                Config config;
                File[] fileList = new File("penguin_plugin/Npc_config").listFiles();
                assert fileList != null;
                for (File Folder : fileList) {
                    config = new Config(Folder, 2);
                    if (!Folder.getName().contains("npcBase") && human.namedTag.get("account").toString().contains(config.getString("uuid"))) {
                        config.set("skin", text);
                        config.save();
                        human.config = config;
                        p.sendToast("§6设置成功！", "§b你成功更新了该npc的皮肤！");
                    }
                }

                break;
            }
        }
    }
}
