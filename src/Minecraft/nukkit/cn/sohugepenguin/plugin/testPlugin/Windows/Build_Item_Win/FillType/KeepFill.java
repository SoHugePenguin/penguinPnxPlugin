package Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Windows.Build_Item_Win.FillType;

import Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Windows.Build_Item_Win.Coordinate_sorting;
import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.form.element.Element;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.math.Vector3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Main_PluginBase.build_map;
import static Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Main_PluginBase.undo_map;

public class KeepFill extends FormWindowCustom {
    public KeepFill(List<Element> elements) {
        super("§6保持性填充", elements);
    }

    public static FormWindowCustom Keep_Fill() {
        List<Element> el = new ArrayList<>();
        el.add(new ElementLabel("§l§6请输入方块ID"));
        el.add(new ElementInput(" "));
        el.add(new ElementLabel("§l§6请输入方块特殊值"));
        el.add(new ElementInput(" "));
        return new KeepFill(el);
    }

    public static void KeepFill_do(int id, int special_id, Player player) {
        String text = "";
        if (Block.get(id, special_id).getName() != null) {
            if (Block.get(id, special_id).getName().contains("UNKNOWN")) {
                special_id = 0;
                player.sendMessage("-->§c没有这种特殊值的方块，已自动改为默认值0");
            }
//            从小到大排序
            ArrayList<Vector3> vector3s = Coordinate_sorting.sorting(build_map.get(player.getName()));

            long end_all = 0;
            if (vector3s.size() == 2) {
                long[] xyz1 = new long[]{(long) vector3s.get(0).x, (long) vector3s.get(0).y, (long) vector3s.get(0).z};
                end_all = 0L;
                ArrayList<Long> lengthen = new ArrayList<>();
                lengthen.add((long) Math.abs(vector3s.get(0).x - vector3s.get(1).x) + 1L);
                lengthen.add((long) Math.abs(vector3s.get(0).y - vector3s.get(1).y) + 1L);
                lengthen.add((long) Math.abs(vector3s.get(0).z - vector3s.get(1).z) + 1L);
                long temp;
                boolean all_ok = false;
                if (lengthen.get(0) <= 1000L && lengthen.get(1) <= 1000L && lengthen.get(2) <= 1000L) {
                    temp = lengthen.get(0) * lengthen.get(1) * lengthen.get(2);
                    if (temp <= 1000000L) {
                        all_ok = true;
                    } else {
                        player.sendMessage("§m§c<Error>填充方块过大(" + temp + "> 百万块)");
                    }
                } else {
                    player.sendMessage("§m§c<Error>xyz的延申长度不得>1000");
                }
                if (all_ok) {
                    Long[] xyz2 = new Long[]{(long) vector3s.get(1).x, (long) vector3s.get(1).y, (long) vector3s.get(1).z};
                    player.sendMessage("§e坐标1 " + Arrays.toString(xyz1) + "\n§a坐标2 " + Arrays.toString(xyz2) + " §6 属性: " + Block.get(id, special_id));
                    long x_fill = xyz1[0];
                    long y_fill = xyz1[1];
                    long z_fill = xyz1[2];
//                    刷新undo撤销
                    undo_map.put(player.getName(), new ArrayList<>());
                    ArrayList<Block> save = new ArrayList<>();
                    while (true) {
                        Vector3 vector_fill = new Vector3((double) x_fill, (double) y_fill, (double) z_fill);
                        if (player.getLevel().getBlock(vector_fill).getId() == 0) {
                            save.add(player.getLevel().getBlock(vector_fill));
                            player.level.setBlock(vector_fill, Block.get(id, special_id));
                            ++end_all;
                        }

                        if (x_fill >= xyz2[0]) {
                            x_fill = xyz1[0] - 1L;
                            ++y_fill;
                        }
                        if (y_fill > xyz2[1]) {
                            y_fill = xyz1[1];
                            ++z_fill;
                        }
                        if (z_fill > xyz2[2]) {
                            break;
                        }
                        ++x_fill;
                    }
                    text = "§a id " + id +
                            "\nspecial_id :" + special_id +
                            "\nplayer: " + player.getName() +
                            " §d§m已为你填充" + end_all + " §l§b块方块 §6";

                    undo_map.put(player.getName(), save);

                }
            }
        } else {
            text = "id " + id +
                    "\nspecial_id :" + special_id +
                    "\nplayer: " + player.getName() +
                    "\n§l§c你输入的方块ID不存在，填充失败！";
        }
        player.sendMessage(text);
    }
}
