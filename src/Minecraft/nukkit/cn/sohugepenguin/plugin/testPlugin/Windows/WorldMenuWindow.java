package Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Windows;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;

import static Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Main_PluginBase.date_show;


public class WorldMenuWindow extends FormWindowSimple {

    public WorldMenuWindow() {
        super("§l§6§ka§ca§ba§da§aa§4a§7a§1a §r§m§g| WORLD MENU |§l§6§ka§1a§7a§aa§da§ba§ca§6a", "§l§a世界时间："+date_show +" §l§6我的世界、无尽可能！");
    }

    public static FormWindowSimple getWindowSimple(Player p){
        FormWindowSimple windowSimple = new WorldMenuWindow();
        windowSimple.addButton(new ElementButton("§a传送系统", new ElementButtonImageData("path","textures/ui/Teleport")));
        windowSimple.addButton(new ElementButton("§6家园系统", new ElementButtonImageData("path","textures/ui/update_world_chunks")));
        windowSimple.addButton(new ElementButton("§c商店系统", new ElementButtonImageData("path","textures/ui/shop")));
        windowSimple.addButton(new ElementButton("§d社交系统", new ElementButtonImageData("path","textures/ui/world_friends")));
        windowSimple.addButton(new ElementButton("§g个人系统", new ElementButtonImageData("path","textures/ui/world_player")));
        windowSimple.addButton(new ElementButton(" §b设 置", new ElementButtonImageData("path","textures/ui/setting")));
        if(p.isOp()){
            windowSimple.addButton(new ElementButton("§o§6建筑存储器", new ElementButtonImageData("path","textures/ui/build")));
            windowSimple.addButton(new ElementButton("§o§6NPC生成器", new ElementButtonImageData("path","textures/ui/book2")));
        }
        return windowSimple;
    }
}
