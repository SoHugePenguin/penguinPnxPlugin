package com.penguin.util;

import cn.nukkit.Player;
import cn.nukkit.math.Vector3;
import cn.nukkit.plugin.PluginBase;

import java.util.ArrayList;

import static com.penguin.App.build_map;

public class WoodAxe extends PluginBase {

    public WoodAxe() {
    }

    public static void AddVector(Vector3 v, Player p) {
        ArrayList<Vector3> vector3s = build_map.get(p.getName());
        if (vector3s.isEmpty()) {
            vector3s.add(v);
            p.sendMessage("  §l§a已选择坐标A");
        } else if (vector3s.size() == 1) {
            vector3s.add(v);
            p.sendMessage("  §l§a已选择坐标B");
        } else if (vector3s.size() == 2) {
            vector3s = new ArrayList<>();
            p.sendMessage("  §l§c已重置坐标选取！");
        }
        build_map.put(p.getName(), vector3s);
    }
}
