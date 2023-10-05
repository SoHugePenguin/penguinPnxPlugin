package com.penguin.Items.Summon_egg;

import cn.nukkit.math.Vector3;

import java.util.ArrayList;

public class anchor_information {
    public final static ArrayList<String> name = new ArrayList<>();
    public final static ArrayList<Vector3> vector3 = new ArrayList<>();

    public static void anchor_info(String name, Vector3 vector3) {
        anchor_information.name.add(name);
        anchor_information.vector3.add(vector3);
    }
}
