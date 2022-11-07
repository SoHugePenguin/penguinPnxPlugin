package Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Tool;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.math.Vector3;

public class round_tool {
    public static void round(Player p, String[] args) {
        double difference_x = 0,difference_z = 0;
        int radius = Integer.parseInt(args[0]);
        int block_id = Integer.parseInt(args[1]);
        if (radius <= 120) {
            Vector3 vector3 = new Vector3();
            if (p.getLocation().x < 0) {
                difference_x = 1;
            }
            if (p.getLocation().z < 0) {
                difference_z = 1;
            }
            vector3.x = (int) p.getLocation().x - difference_x + 0.5;
            vector3.z = (int) p.getLocation().z - difference_z + 0.5;
            p.sendMessage(String.valueOf(vector3));
            double x,z;
            for (double i = -radius - 0.5; i < radius + 0.5; i += 0.2f) {
                for (double j = -radius - 0.5; j < radius + 0.5; j += 0.2f) {
                        if (i * i + j * j <= radius * radius) {
                            x = vector3.x + i;
                            z = vector3.z + j;
                            if (args.length == 2) {
                                if (!p.level.getBlock(new Vector3(x, p.getLocation().y, z)).equals(Block.get(block_id, 0))) {
                                    p.getLevel().setBlock(new Vector3(x, p.getLocation().y, z), Block.get(block_id));
                                }
                            } else if (args.length == 3) {
                                int data = Integer.parseInt(args[2]);
                                if (!p.level.getBlock(new Vector3(x, p.getLocation().y, z)).equals(Block.get(block_id, data))) {
                                    p.getLevel().setBlock(new Vector3(x, p.getLocation().y, z), Block.get(block_id, data));
                                }
                            } else if (args.length == 4) {
                                int data = Integer.parseInt(args[2]);
                                if (args[3].equals("keep")) {
                                    if (p.level.getBlock(new Vector3(x, p.getLocation().y, z)).getId() == 0) {
                                        p.getLevel().setBlock(new Vector3(x, p.getLocation().y, z), Block.get(block_id, data));
                                    }
                                } else {
                                    p.sendMessage("参数有误，目前有效参数有keep。");
                                    return;
                                }
                            }
                    }
                }
            }
        } else p.sendMessage("半径最高不能大于120格！请重新输入！");
    }
}
