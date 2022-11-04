package Minecraft.nukkit.cn.shygard.nukkit.testPlugin.Tool;

import cn.nukkit.Player;
import cn.nukkit.math.Vector3;
import cn.nukkit.plugin.PluginBase;

import java.util.ArrayList;

import static Minecraft.nukkit.cn.shygard.nukkit.testPlugin.Main_PluginBase.build_map;

public class WoodAxe extends PluginBase {
    public static ArrayList<Player> player_access = new ArrayList<>();
    public static ArrayList<Vector3[]> player_access_vector3s = new ArrayList<>();
    public static int identifier = 0;
    public WoodAxe() {
    }
    
    public static void AddVector(Vector3 v , Player p){
        ArrayList<Vector3> vector3s = build_map.get(p.getName());
        if(vector3s.size()==0){
            vector3s.add(v);
            p.sendMessage("  §l§a已选择坐标A");
        } else if (vector3s.size()==1) {
            vector3s.add(v);
            p.sendMessage("  §l§a已选择坐标B");
        } else if (vector3s.size()==2) {
            vector3s = new ArrayList<>();
            p.sendMessage("  §l§c已重置坐标选取！");
        }
        build_map.put(p.getName(),vector3s);
    }
}
