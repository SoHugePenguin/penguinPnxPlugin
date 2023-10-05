package com.penguin.util;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.math.Vector3;

public class BallTool {
    public static void ball(Player p, String[] args) {
        double difference_x = 0, difference_y = 0, difference_z = 0;
        int radius = Integer.parseInt(args[0]);
        int block_id = Integer.parseInt(args[1]);
        if (radius <= 50) {
            Vector3 vector3 = new Vector3();
            if (p.getLocation().x < 0) {
                difference_x = 1;
            }
            if (p.getLocation().y < 0) {
                difference_y = 1;
            }
            if (p.getLocation().z < 0) {
                difference_z = 1;
            }
            vector3.x = (int) p.getLocation().x - difference_x + 0.5;
            vector3.y = (int) p.getLocation().y - difference_y + 0.5;
            vector3.z = (int) p.getLocation().z - difference_z + 0.5;
            //传送至上方，避免卡在球体中间//
            p.teleport(new Vector3(vector3.x, vector3.y + radius + 2, vector3.z));
            double x, y, z;
            for (float i = -radius - 1; i < radius + 1; i += 1f) {
                for (float j = -radius - 1; j < radius + 1; j += 1f) {
                    for (float k = -radius - 1; k < radius + 1; k += 1f) {
                        if (i * i + j * j + k * k <= radius * radius) {
                            x = vector3.x + i;
                            y = vector3.y + j;
                            z = vector3.z + k;
                            if (args.length == 2) {
                                if (!p.level.getBlock(new Vector3(x, y, z)).equals(Block.get(block_id, 0))) {
                                    p.getLevel().setBlock(new Vector3(x, y, z), Block.get(block_id));
                                }
                            } else if (args.length == 3) {
                                int data = Integer.parseInt(args[2]);
                                if (!p.level.getBlock(new Vector3(x, y, z)).equals(Block.get(block_id, data))) {
                                    p.getLevel().setBlock(new Vector3(x, y, z), Block.get(block_id, data));
                                }
                            } else if (args.length == 4) {
                                int data = Integer.parseInt(args[2]);
                                if (args[3].equals("keep")) {
                                    if (p.level.getBlock(new Vector3(x, y, z)).getId() == 0) {
                                        p.getLevel().setBlock(new Vector3(x, y, z), Block.get(block_id, data));
                                    }
                                } else {
                                    p.sendMessage("参数有误，目前有效参数有keep。");
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        } else p.sendMessage("半径最高不能大于50格！请重新输入！");
    }
}
