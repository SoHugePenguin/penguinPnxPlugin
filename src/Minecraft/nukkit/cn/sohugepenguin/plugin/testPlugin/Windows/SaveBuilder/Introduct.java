package Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Windows.SaveBuilder;

import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;

public class Introduct extends FormWindowSimple {
    public Introduct() {
        super("op用建筑储存器", "尊敬的管理员\n你正在管理服务器的建筑储存，不懂的请看介绍");
    }

    public static FormWindowSimple getIntroduction() {
        FormWindowSimple home = new Introduct();
        home.setTitle("§6建筑储存介绍");
        home.setContent("§b与创世神一起使用！§g选取好坐标1，坐标2后，可以使用储存功能保存到服务器已有的文件夹。\\n§c与此同时，你可以浏览服务器文件夹并且生成建筑在你的脚下。\\n§d注意！生成方向在XZ轴正方向延申，该操作不可逆！慎用！\"");
        home.addButton(new ElementButton("返回上级", new ElementButtonImageData("path", "textures/ui/back")));
        return home;
    }
}