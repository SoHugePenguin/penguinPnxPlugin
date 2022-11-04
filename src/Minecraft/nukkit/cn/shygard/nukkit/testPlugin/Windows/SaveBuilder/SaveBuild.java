package Minecraft.nukkit.cn.shygard.nukkit.testPlugin.Windows.SaveBuilder;

import cn.nukkit.form.element.Element;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.window.FormWindowCustom;

import java.util.ArrayList;
import java.util.List;


public class SaveBuild extends FormWindowCustom {
    public SaveBuild(List<Element> elements) {
        super("保存文件·菜单", elements);
    }
    public static FormWindowCustom Create_Yml(){
        List<Element> el = new ArrayList();
        el.add(new ElementLabel("§l§6输入建筑名称"));
        el.add(new ElementLabel("§l§b禁止重名，否则无法创建！\n§g名字不要太奇怪了，低概率可能会创建失败"));
        el.add(new ElementInput(" "));
        return new SaveBuild(el);
    }
}