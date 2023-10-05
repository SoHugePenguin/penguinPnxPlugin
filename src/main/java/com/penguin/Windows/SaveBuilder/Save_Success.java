package com.penguin.Windows.SaveBuilder;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.math.Vector3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static com.penguin.App.build_map;


public class Save_Success extends FormWindowSimple {
    public Save_Success() {
        super("保存文件·菜单", "");
    }

    public static FormWindowSimple SaveSuccess(Player p, String File, String Text) throws IOException {
        FormWindowSimple simple = new Save_Success();

        ArrayList<Vector3> v = build_map.get(p.getName());

        if (v != null && v.size() == 2) {
            simple.setContent("§6 " + Text + " §a已成功保存至§6 " + File);
            File file = new File("penguin_plugin", "Builder_Save\\" + File);
            File yml = new File(file, Text + ".yml");
            if (!yml.exists()) {
                yml.createNewFile();
                Clone_Block_To_Yml.clone_block(yml, p);
            } else simple.setContent("§c[Error] " + Text + " 文件已经存在在 §6" + File + " §c中,请勿重名！");
        } else simple.setContent("§4[Error] 未选择两点坐标! 请使用创世神选好坐标再保存！");
        simple.addButton(new ElementButton("确定！"));
        return simple;
    }
}