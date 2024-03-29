package com.penguin.Windows.SaveBuilder;

import cn.nukkit.Player;
import cn.nukkit.math.Vector3;
import com.penguin.Windows.Build_Item_Win.Coordinate_sorting;
import com.penguin.pojo.BuildInfo;
import com.penguin.util.YmlTool;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static com.penguin.App.build_map;

public class Clone_Block_To_Yml {
    public static void clone_block(File yml, Player p) {
        long temp;
        boolean all_ok = false;

        StringBuilder Location_Collect;
        StringBuilder Block_Collect;
        ArrayList<Vector3> v = build_map.get(p.getName());

        if (v == null || v.size() != 2) return;

        Coordinate_sorting.sorting(v);
        long[] xyz1 = new long[]{(long) v.get(0).x, (long) v.get(0).y, (long) v.get(0).z};
        ArrayList<Long> lengthen = new ArrayList<>();
        lengthen.add((long) Math.abs(v.get(0).x - v.get(1).x) + 1L);
        lengthen.add((long) Math.abs(v.get(0).y - v.get(1).y) + 1L);
        lengthen.add((long) Math.abs(v.get(0).z - v.get(1).z) + 1L);

        if (lengthen.get(0) <= 1000L && lengthen.get(1) <= 1000L && lengthen.get(2) <= 1000L) {
            temp = lengthen.get(0) * lengthen.get(1) * lengthen.get(2);
            if (temp <= 20000000L) all_ok = true;
            else p.sendMessage("§m§c<Error>保存方块过多(" + temp + ">20000000块)");
        } else p.sendMessage("§m§c<Error>xyz的延申长度不得>1000");

        if (!all_ok) return;

        Long[] xyz2 = new Long[]{(long) v.get(1).x, (long) v.get(1).y, (long) v.get(1).z};
        p.sendMessage("§e坐标1 " + Arrays.toString(xyz1) + "\n§a坐标2 " + Arrays.toString(xyz2) + "\n已为你保存！");

        long x_fill = xyz1[0];
        long y_fill = xyz1[1];
        long z_fill = xyz1[2];

        Location_Collect = new StringBuilder();
        Block_Collect = new StringBuilder();
        while (true) {
            Vector3 vector_fill = new Vector3((double) x_fill, (double) y_fill, (double) z_fill);
            if (!(p.level.getBlock(vector_fill).getId() == 0)) {
                Location_Collect.append(x_fill - xyz1[0]).append(" ").append(y_fill - xyz1[1]).append(" ").append(z_fill - xyz1[2]).append(" ");
                Block_Collect.append(p.level.getBlock(vector_fill).getId()).append(" ").append(p.level.getBlock(vector_fill).getExactIntStorage()).append(" ");
            }
            if (x_fill >= xyz2[0]) {
                x_fill = xyz1[0] - 1L;
                ++y_fill;
            }
            if (y_fill > xyz2[1]) {
                y_fill = xyz1[1];
                ++z_fill;
            }
            if (z_fill > xyz2[2]) break;
            ++x_fill;
        }

        BuildInfo buildInfo = new BuildInfo();
        buildInfo.setLocation(Location_Collect.toString());
        buildInfo.setBlock(Block_Collect.toString());
        buildInfo.setMax_x(v.get(1).x - v.get(0).x + 1);
        buildInfo.setMax_y(v.get(1).y - v.get(0).y + 1);
        buildInfo.setMax_z(v.get(1).z - v.get(0).z + 1);
        YmlTool.updateYml(yml, buildInfo);
        p.sendTitle("§m§6保存成功！！！");
    }
}