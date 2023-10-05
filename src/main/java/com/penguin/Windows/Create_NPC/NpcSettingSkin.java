package com.penguin.Windows.Create_NPC;

import cn.nukkit.Player;
import cn.nukkit.entity.data.Skin;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;
import com.penguin.Entity.BaseNpc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static cn.nukkit.entity.data.Skin.GEOMETRY_CUSTOM;

public class NpcSettingSkin extends FormWindowSimple {
    public NpcSettingSkin() {
        super("§cNpc皮肤管理", "§o§d你正在更换该NPC的皮肤\n§6请选择你要更换的皮肤！");
    }

    public static FormWindowSimple getNpc_Setting_Skin() {
        FormWindowSimple home = new NpcSettingSkin();
        File[] listFiles = new File("penguin_plugin", "skins").listFiles();
        assert listFiles != null;
        for (File Fold : listFiles) {
            home.addButton(new ElementButton(Fold.getName(),
                    new ElementButtonImageData("path", "textures/npc_skins/" + Fold.getName())));
        }
        return home;
    }

    public static void ChangeSkinSuccess(Player p, String text) throws IOException {
        for (int i = 0; i < NpcBaseEntity.PlayerList.size(); i++) {
            if (p.getName().equals(NpcBaseEntity.PlayerList.get(i))) {
                BaseNpc human = NpcBaseEntity.PlayerTargetEntityList.get(i);
                Skin skin = new Skin();
                File file = new File("penguin_plugin", "skins");
                File skin_file = new File(file, text);
                BufferedImage image = ImageIO.read(skin_file);   //skinData的数据类型为RGBA  A为alpha

                human.config.set("skin", text);
                System.out.println(human.config.toString());
                human.config.save();

                skin.setSkinData(image);
                skin.setSkinColor("#FF0004");
                skin.setTrusted(true);
                skin.setSkinResourcePatch(GEOMETRY_CUSTOM);
                human.setSkin(skin);
                human.spawnToAll();
                //客户端渲染更新
                p.getServer().updatePlayerListData(human.getUniqueId(), human.getId(), human.getName(), human.getSkin());
                p.getServer().removePlayerListData(human.getUniqueId());

                p.sendToast("§6设置成功！", "§b你成功更新了该npc的皮肤！");
                break;
            }
        }
    }
}
