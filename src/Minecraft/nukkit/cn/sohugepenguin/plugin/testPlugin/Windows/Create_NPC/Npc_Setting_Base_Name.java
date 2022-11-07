package Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Windows.Create_NPC;

import Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Entity.BaseNpc;
import cn.nukkit.Player;
import cn.nukkit.form.element.Element;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.utils.Config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Windows.Create_NPC.Npc_HuTao.PlayerTargetEntityList;


public class Npc_Setting_Base_Name extends FormWindowCustom {
    public Npc_Setting_Base_Name(List<Element> elements) {
        super("§6重命名npc", elements);
    }
    public static FormWindowCustom  Change_Npc_Name(){
        List<Element> el = new ArrayList<>();
        el.add(new ElementLabel("§l§6请输入新的名字！"));
        el.add(new ElementInput(" "));
        return new Npc_Setting_Base_Name(el);
    }
    public static void Npc_Name(Player p,String text){
        for (int i = 0; i < Npc_HuTao.PlayerList.size() ; i++) {
            if(p.getName().equals(Npc_HuTao.PlayerList.get(i))){
                Config config;
                File[] fileList = new File("penguin_plugin/Npc_config").listFiles();
                assert fileList != null;
                for(File Folder : fileList){
                    config = new Config(Folder,2);
                    BaseNpc baseNpc = PlayerTargetEntityList.get(i);
                    if(!Folder.getName().contains("npcBase") && baseNpc.namedTag.get("account").toString().contains(config.getString("uuid"))){
                        p.sendToast("§6设置成功！","§d你成功更新了该npc的命名！");
                        config.set("name",text);
                        config.save();
                        baseNpc.config = config;//重载配置文件
                        baseNpc.setNameTag("");//这里的setNameTag是多少都无所谓，只是一个触发的，以config返回值为准；
                    }
                }
                break;
            }
        }
    }
}