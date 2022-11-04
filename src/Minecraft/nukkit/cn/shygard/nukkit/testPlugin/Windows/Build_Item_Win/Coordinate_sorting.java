package Minecraft.nukkit.cn.shygard.nukkit.testPlugin.Windows.Build_Item_Win;

import cn.nukkit.math.Vector3;

import java.util.ArrayList;

public class Coordinate_sorting {
    public static ArrayList<Vector3> sorting(ArrayList<Vector3> v){
        double data;
        if (v.get(1).x < v.get(0).x) {
            data = v.get(1).x;
            v.get(1).x = v.get(0).x;
            v.get(0).x = data;
        }
        if (v.get(1).y < v.get(0).y) {
            data = v.get(1).y;
            v.get(1).y = v.get(0).y;
            v.get(0).y = data;
        }
        if (v.get(1).z < v.get(0).z) {
            data = v.get(1).z;
            v.get(1).z = v.get(0).z;
            v.get(0).z = data;
    }
        return v;
    }
}
