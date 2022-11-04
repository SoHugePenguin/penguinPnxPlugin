package Minecraft.nukkit.cn.shygard.nukkit.testPlugin.Windows.Home;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;

public class Home extends FormWindowSimple {
    private static boolean isOp;
    public Home() {
        super("家园系统", "在这里管理你独有的家园吧！你可以在这里创建并游玩属于自己的世界！你还可以邀请你的朋友来你的家做客!还有更多的家园设置奥!");
    }
    public static FormWindowSimple getWindowHome(){
        FormWindowSimple home = new Home();
        home.addButton(new ElementButton("我的家园", new ElementButtonImageData("path","textures/ui/update_world_chunks")));
        home.addButton(new ElementButton("他人的家园", new ElementButtonImageData("path","textures/ui/other_home")));
        home.addButton(new ElementButton("家园权限设置", new ElementButtonImageData("path","textures/ui/setting")));
        if(isOp) home.addButton(new ElementButton("OP管理员·家园管理", new ElementButtonImageData("path","textures/ui/world_op")));
        home.addButton(new ElementButton("返回", new ElementButtonImageData("path","textures/ui/back")));
        return home;
    }

    public static FormWindowSimple OpHomeMenu(Player player){
        isOp = player.isOp();
        return getWindowHome();
    }
}
