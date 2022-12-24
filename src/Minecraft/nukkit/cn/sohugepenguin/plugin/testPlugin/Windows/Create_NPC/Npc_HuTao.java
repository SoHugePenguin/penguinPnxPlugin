package Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Windows.Create_NPC;

import Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Entity.BaseNpc;
import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;

import java.util.ArrayList;

public class Npc_HuTao extends FormWindowSimple {
    public static ArrayList<String> PlayerList = new ArrayList<>();
    public static ArrayList<BaseNpc> PlayerTargetEntityList = new ArrayList<>();

    public Npc_HuTao() {
        super("NPC:往生堂堂主·胡桃", "你好哦！我是胡桃，胡了的胡，桃子的桃哦~");
    }

    public static FormWindowSimple HuTao_Windows(Player player, BaseNpc entity) {
        if (PlayerList.size() > 0) {
            for (int i = 0; i < PlayerList.size(); i++) {
                if (player.getName().equals(PlayerList.get(i))) {
                    PlayerTargetEntityList.set(i, entity);
                    break;
                } else if (i == PlayerList.size() - 1) {
                    PlayerList.add(player.getName());
                    PlayerTargetEntityList.add(entity);
                }
            }
        } else {
            PlayerList.add(player.getName());
            PlayerTargetEntityList.add(entity);
        }
        FormWindowSimple home = new Npc_HuTao();
        home.addButton(new ElementButton("唱一首歌", new ElementButtonImageData("path", "textures/ui/icon_recipe_nature")));
        if (player.isOp())
            home.addButton(new ElementButton("修改NPC", new ElementButtonImageData("path", "textures/ui/setting")));
        if (player.isOp())
            home.addButton(new ElementButton("查看NPC详细信息", new ElementButtonImageData("path", "textures/ui/icon_book_writable")));
        return home;
    }
}
