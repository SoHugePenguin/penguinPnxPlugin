package Minecraft.nukkit.cn.shygard.nukkit.testPlugin.Windows.SaveBuilder.Utils;

import java.io.Serializable;
import java.util.ArrayList;

public class BO implements Serializable{
    static ArrayList<short[]> b;
    public static void setBlocks(ArrayList<short[]> b){
            BO.b = b;
        }

    public static ArrayList<short[]> getBlocks() {
        return b;
    }
}
