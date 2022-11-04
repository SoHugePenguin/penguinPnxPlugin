package Minecraft.nukkit.cn.shygard.nukkit.testPlugin.Windows.SaveBuilder;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.math.Vector3;
import cn.nukkit.utils.Config;

import java.io.File;
import java.util.ArrayList;

import static Minecraft.nukkit.cn.shygard.nukkit.testPlugin.Main_PluginBase.undo_map;

public class Paste_Build extends FormWindowSimple {
    static ArrayList<Block> save;
    public Paste_Build(String string, String con) {
        super(string, con);
        save = new ArrayList<>();
    }

    public static FormWindowSimple Build(String button){
        FormWindowSimple home = new Paste_Build("|建筑操作|","你正在控制名为 §6"+ button +" §r的建筑\n§b粘贴将会粘贴在你的脚下（人体模型两格子第二格子）(即当前坐标)");
        home.addButton(new ElementButton("生成建筑" , new ElementButtonImageData("path","textures/ui/save")));
        home.addButton(new ElementButton("水平旋转90°并生成建筑"));
        home.addButton(new ElementButton("水平旋转180°并生成建筑"));
        home.addButton(new ElementButton("水平旋转270°并生成建筑"));
        home.addButton(new ElementButton("水平X镜像并生成建筑"));
        home.addButton(new ElementButton("水平Z镜像并生成建筑"));
        home.addButton(new ElementButton("垂直Y镜像并生成建筑"));
        home.addButton(new ElementButton("垂直X旋转90°并生成建筑"));
        home.addButton(new ElementButton("垂直Z旋转90°并生成建筑"));
        home.addButton(new ElementButton("删除建筑", new ElementButtonImageData("path","textures/ui/icon_trash")));
        home.addButton(new ElementButton("关闭"));
        return home;
    }

    public static void PasteBuild(Player p,String button,int way){

        //                    刷新undo撤销
        undo_map.put(p.getName(),new ArrayList<>());

        FormWindowSimple home = new FormWindowSimple("|建筑生成|", "你已生成名为 §6" + button + " §r的建筑");
        Config file = new Config(button);
        String location = (String) file.get("location");
        String block = (String) file.get("block");
        String[] location_list = location.split(" ");
        String[] block_list = block.split(" ");
        double x = p.getX(), y = p.getY(), z = p.getZ();
        double max_x = (double) file.get("max_x"),max_y = (double) file.get("max_y"),max_z = (double) file.get("max_z");
        int j = 0;
        home.addButton(new ElementButton("确认"));
        p.showFormWindow(home);
        int data = 0;
        switch (way){
            //正常放置
            case 0 ->{
                for (int i = 0; i < location_list.length; i += 3) {
                    Vector3 vector3 = new Vector3(Double.parseDouble(location_list[i]) + x,
                            Double.parseDouble(location_list[i + 1]) + y,
                            Double.parseDouble(location_list[i + 2]) + z);
                    save.add(p.getLevel().getBlock(vector3));
                    p.getLevel().setBlock(vector3, Block.get(Integer.parseInt(block_list[j]), Integer.parseInt(block_list[j + 1])));
                    j += 2;
                }
                undo_map.put(p.getName(),save);
            }
            //水平旋转90度
            case 1 ->{
                for (int i = 0; i < location_list.length; i += 3) {
                    Vector3 vector3 = new Vector3(max_z - Double.parseDouble(location_list[i+2]) + x,
                            Double.parseDouble(location_list[i + 1]) + y,
                            Double.parseDouble(location_list[i]) + z);
                    Block b = Block.get(Integer.parseInt(block_list[j]));

                    save.add(p.getLevel().getBlock(vector3));

                    if(FullBlockTest(b)){
                        p.getLevel().setBlock(vector3, Block.get(Integer.parseInt(block_list[j]), Integer.parseInt(block_list[j + 1])));
                    }
                    if(b.getName().contains("Stair")){
                        switch (Integer.parseInt(block_list[j + 1])){
                            case 0 -> data = 2;
                            case 1 -> data = 3;
                            case 2 -> data = 1;
                            case 3 -> data = 0;
                            case 4 -> data = 6;
                            case 5 -> data = 7;
                            case 6 -> data = 5;
                            case 7 -> data = 4;
                        }
                        p.getLevel().setBlock(vector3, Block.get(Integer.parseInt(block_list[j]), data));
                    } else if (b.getName().contains("Fence Gate")) {
                        switch (Integer.parseInt(block_list[j + 1])){
                            case 0, 2 -> data = 1;
                            case 1, 3 -> data = 2;
                            case 4, 6 -> data = 5;
                            case 5, 7 -> data = 4;
                        }
                        p.getLevel().setBlock(vector3, Block.get(Integer.parseInt(block_list[j]), data));
                    }else if (b.getName().contains("Ladder")) {
                        switch (Integer.parseInt(block_list[j + 1])){
                            case 2 -> data = 5;
                            case 3 -> data = 4;
                            case 4 -> data = 2;
                            case 5 -> data = 3;
                        }
                        p.getLevel().setBlock(vector3, Block.get(Integer.parseInt(block_list[j]), data));
                    }else p.getLevel().setBlock(vector3, Block.get(Integer.parseInt(block_list[j]), Integer.parseInt(block_list[j + 1])));
                    j += 2;
                }

                undo_map.put(p.getName(),save);

            }
            //水平旋转180度
            case 2 ->{
                for (int i = 0; i < location_list.length; i += 3) {
                    Vector3 vector3 = new Vector3(max_x - Double.parseDouble(location_list[i]) + x,
                            Double.parseDouble(location_list[i + 1]) + y,
                            max_z - Double.parseDouble(location_list[i + 2]) + z);
                    Block b = Block.get(Integer.parseInt(block_list[j]));

                    save.add(p.getLevel().getBlock(vector3));

                    if(FullBlockTest(b)){
                        p.getLevel().setBlock(vector3, Block.get(Integer.parseInt(block_list[j]), Integer.parseInt(block_list[j + 1])));
                    }
                    if (b.getName().contains("Stair")) {
                        switch (Integer.parseInt(block_list[j + 1])) {
                            case 2 -> data = 3;
                            case 3 -> data = 2;
                            case 7 -> data = 6;
                            case 6 -> data = 7;
                        }
                        p.getLevel().setBlock(vector3, Block.get(Integer.parseInt(block_list[j]), data));
                    } else if (b.getName().contains("Fence Gate")) {
                        switch (Integer.parseInt(block_list[j + 1])) {
                            case 0, 2 -> data = 1;
                            case 1, 3 -> data = 2;
                            case 4, 6 -> data = 5;
                            case 5, 7 -> data = 4;
                        }
                    } else if (b.getName().contains("Ladder")) {
                        switch (Integer.parseInt(block_list[j + 1])) {
                            case 2 -> data = 5;
                            case 3 -> data = 4;
                            case 4 -> data = 2;
                            case 5 -> data = 3;
                        }
                        j += 2;
                    }else p.getLevel().setBlock(vector3, Block.get(Integer.parseInt(block_list[j]), Integer.parseInt(block_list[j + 1])));
                }

                undo_map.put(p.getName(),save);

            }
            //水平旋转270度
            case 3 ->{
                for (int i = 0; i < location_list.length; i += 3) {
                    Vector3 vector3 = new Vector3(Double.parseDouble(location_list[i + 2]) + x,
                            Double.parseDouble(location_list[i + 1]) + y,
                            max_x - Double.parseDouble(location_list[i]) + z);
                    Block b = Block.get(Integer.parseInt(block_list[j]));

                    save.add(p.getLevel().getBlock(vector3));

                    p.getLevel().setBlock(vector3, Block.get(Integer.parseInt(block_list[j]), Integer.parseInt(block_list[j + 1])));
                    j += 2;
                }

                undo_map.put(p.getName(),save);

            }
            //X镜像
            case 4 ->{
                for (int i = 0; i < location_list.length; i += 3) {
                    Vector3 vector3 = new Vector3(max_x - Double.parseDouble(location_list[i]) + x,
                            Double.parseDouble(location_list[i + 1]) + y,
                            Double.parseDouble(location_list[i + 2]) + z);
                    Block b = Block.get(Integer.parseInt(block_list[j]));

                    save.add(p.getLevel().getBlock(vector3));

                    p.getLevel().setBlock(vector3, Block.get(Integer.parseInt(block_list[j]), Integer.parseInt(block_list[j + 1])));
                    j += 2;
                }

                undo_map.put(p.getName(),save);

            }
            //Z镜像
            case 5 ->{
                p.sendMessage("Z镜像翻转成功！");
                for (int i = 0; i < location_list.length; i += 3) {
                    Vector3 vector3 = new Vector3(Double.parseDouble(location_list[i]) + x,
                            Double.parseDouble(location_list[i + 1]) + y,
                            max_z - Double.parseDouble(location_list[i + 2]) + z);
                    Block b = Block.get(Integer.parseInt(block_list[j]));

                    save.add(p.getLevel().getBlock(vector3));

                    if(FullBlockTest(b)){
                        p.getLevel().setBlock(vector3, Block.get(Integer.parseInt(block_list[j]), Integer.parseInt(block_list[j + 1])));
                    }
                    if (b.getName().contains("Stair")) {
                        switch (Integer.parseInt(block_list[j + 1])) {
                            case 2 -> data = 3;
                            case 3 -> data = 2;
                            case 7 -> data = 6;
                            case 6 -> data = 7;
                            default -> data = Integer.parseInt(block_list[j+1]);
                        }
                        p.getLevel().setBlock(vector3, Block.get(Integer.parseInt(block_list[j]), data));
                    } else if (b.getName().contains("Fence Gate")) {
                        switch (Integer.parseInt(block_list[j + 1])) {
                            case 0 -> data = 2;
                            case 2 -> data = 0;
                            case 4 -> data = 6;
                            case 6 -> data = 4;
                            default -> data = Integer.parseInt(block_list[j+1]);
                        }
                        p.getLevel().setBlock(vector3, Block.get(Integer.parseInt(block_list[j]), data));
                    } else if (b.getName().contains("Ladder")) {
                        switch (Integer.parseInt(block_list[j + 1])) {
                            case 3 -> data = 2;
                            case 2 -> data = 3;
                            default -> data = Integer.parseInt(block_list[j+1]);
                        }
                        p.getLevel().setBlock(vector3, Block.get(Integer.parseInt(block_list[j]), data));
                    }else p.getLevel().setBlock(vector3, Block.get(Integer.parseInt(block_list[j]), Integer.parseInt(block_list[j + 1])));
                    j += 2;
                }

                undo_map.put(p.getName(),save);

            }
            //Y镜像
            case 6 ->{
                for (int i = 0; i < location_list.length; i += 3) {
                    Vector3 vector3 = new Vector3(Double.parseDouble(location_list[i]) + x,
                            max_y - Double.parseDouble(location_list[i + 1]) + y,
                            Double.parseDouble(location_list[i + 2]) + z);
                    Block b = Block.get(Integer.parseInt(block_list[j]));

                    save.add(p.getLevel().getBlock(vector3));

                    if(FullBlockTest(b)){
                        p.getLevel().setBlock(vector3, Block.get(Integer.parseInt(block_list[j]), Integer.parseInt(block_list[j + 1])));
                    }
                    if (b.getName().contains("Stair")) {
                        switch (Integer.parseInt(block_list[j + 1])) {
                            case 2 -> data = 3;
                            case 3 -> data = 2;
                            case 7 -> data = 6;
                            case 6 -> data = 7;
                            default -> data = Integer.parseInt(block_list[j+1]);
                        }
                        p.getLevel().setBlock(vector3, Block.get(Integer.parseInt(block_list[j]), data));
                    } else p.getLevel().setBlock(vector3, Block.get(Integer.parseInt(block_list[j]), Integer.parseInt(block_list[j + 1])));
                    j += 2;
                }

                undo_map.put(p.getName(),save);

            }
            //垂直X旋转90度
            case 7 ->{
                for (int i = 0; i < location_list.length; i += 3) {
                    Vector3 vector3 = new Vector3(Double.parseDouble(location_list[i + 1]) + x,
                            Double.parseDouble(location_list[i]) + y,
                            Double.parseDouble(location_list[i + 2]) + z);
                    Block b = Block.get(Integer.parseInt(block_list[j]));

                    save.add(p.getLevel().getBlock(vector3));

                    p.getLevel().setBlock(vector3, Block.get(Integer.parseInt(block_list[j]), Integer.parseInt(block_list[j + 1])));
                    j += 2;
                }

                undo_map.put(p.getName(),save);

            }
            //垂直Z旋转90度
            case 8 ->{
                for (int i = 0; i < location_list.length; i += 3) {
                    Vector3 vector3 = new Vector3(Double.parseDouble(location_list[i]) + x,
                            Double.parseDouble(location_list[i + 2]) + y,
                            Double.parseDouble(location_list[i + 1]) + z);
                    Block b = Block.get(Integer.parseInt(block_list[j]));

                    save.add(p.getLevel().getBlock(vector3));

                    p.getLevel().setBlock(vector3, Block.get(Integer.parseInt(block_list[j]), Integer.parseInt(block_list[j + 1])));
                    j += 2;
                }

                undo_map.put(p.getName(),save);

            }
        }
    }

    public static FormWindowSimple DeleteBuild(String button){
        FormWindowSimple home = new FormWindowSimple("|建筑删除|","你已经删除 §6" + button +" §r的建筑");
        File file = new File(button);
        file.delete();
        home.addButton(new ElementButton("确认"));
        return home;
    }

    private static boolean FullBlockTest(Block b){
        return b.isFullBlock() ||
                b.getName().contains("Wall") ||
                (b.getName().contains("Fence") && !b.getName().contains("Gate")) ||
                b.getName().contains("Pane") ||
                b.getName().contains("Scaffold") ||
                b.getName().contains("Slab") ||
                b.getName().contains("Iron Bars") ||
                b.getName().contains("Carpet") ||
                b.getName().contains("Cactus") ||
                b.getName().contains("Portal Frame") ||
                b.getName().contains("Cobweb") ||
                b.getName().contains("Mushroom") ||
                b.getName().contains("Pointed Drip") ||
                b.getName().contains("Candle") ||
                b.getName().contains("Cauldron") ||
                b.getName().contains("Composter") ||
                b.getName().contains("Sapling") ||
                b.getName().contains("Brewing");
    }

}