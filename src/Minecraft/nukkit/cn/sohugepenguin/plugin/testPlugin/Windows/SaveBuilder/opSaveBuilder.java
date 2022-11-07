package Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Windows.SaveBuilder;

import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;

public class opSaveBuilder extends FormWindowSimple {
    public opSaveBuilder() {
        super("op用建筑储存器", "尊敬的管理员\n你正在管理服务器的建筑储存，不懂的请看介绍");
    }
    public static FormWindowSimple getWindow_op_save_builder(){
        FormWindowSimple home = new opSaveBuilder();
        home.addButton(new ElementButton("储存建筑文件", new ElementButtonImageData("path","textures/ui/save")));
        home.addButton(new ElementButton("文件夹目录", new ElementButtonImageData("path","textures/ui/book2")));
        home.addButton(new ElementButton("功能介绍", new ElementButtonImageData("path","textures/ui/book1")));
        home.addButton(new ElementButton("返回", new ElementButtonImageData("path","textures/ui/back")));
        return home;
    }
}