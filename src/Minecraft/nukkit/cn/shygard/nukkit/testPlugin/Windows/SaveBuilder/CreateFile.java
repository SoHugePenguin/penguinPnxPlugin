package Minecraft.nukkit.cn.shygard.nukkit.testPlugin.Windows.SaveBuilder;

import cn.nukkit.Player;
import cn.nukkit.form.element.Element;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowSimple;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CreateFile extends FormWindowCustom {
    public CreateFile(List<Element> elements) {
        super("§6创建文件夹", elements);
    }
    public static FormWindowCustom Window_Crate_Files(Player p){
        List<Element> el = new ArrayList<>();
        el.add(new ElementLabel("§l§6请命名你的新文件夹！"));
        el.add(new ElementLabel("§l§b如果重名，将会生成副本！\n§g名字不要太奇怪了，低概率可能会创建失败"));
        el.add(new ElementInput(" "));
        return new CreateFile(el);
    }

    public static FormWindowSimple Super_File(String text){
        FormWindowSimple home = new FormWindowSimple("§6创建文件夹","");
        File file = new File("penguin_plugin","Builder_Save");
        File world_file = new File(file, text);
        if(text.equals("")){
            home.setContent("§l§c文件夹不能没有名字！！！");
        }else {
            if (!world_file.exists()) {
                home.setContent("§你成功创建了名为 "+ text +" 的文件夹！");
                world_file.mkdirs();
            }else home.setContent("§c名为 §6" + text + " §c的文件夹已经存在，请不要重名！");
        }
        home.addButton(new ElementButton("确定！"));
        return home;
    }
}