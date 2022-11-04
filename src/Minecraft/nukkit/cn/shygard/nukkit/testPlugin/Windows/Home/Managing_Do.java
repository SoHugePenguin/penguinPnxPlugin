package Minecraft.nukkit.cn.shygard.nukkit.testPlugin.Windows.Home;

import cn.nukkit.Player;

public class Managing_Do{
    public static void Managing(Player p , String home){
        for (int i = 1; i < p.getServer().getLevels().size() + 1; i++) {
                if (!p.getServer().isLevelLoaded(home)) {
                    p.getServer().loadLevel(home);
                }
                p.teleport(p.getServer().getLevelByName(home).getSpawnLocation());
            }
    }
}
