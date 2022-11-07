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


public class Npc_Setting_Base_Model extends FormWindowCustom {
    public Npc_Setting_Base_Model(List<Element> elements) {
        super("§6修改npc模型大小", elements);
    }
    public static FormWindowCustom  Change_Npc_Model(){
        List<Element> el = new ArrayList<>();
        el.add(new ElementLabel("§l§6请输入模型缩放比例！"));
        el.add(new ElementInput(" "));
        return new Npc_Setting_Base_Model(el);
    }
    public static void Npc_Model(Player p,String text){
        for (int i = 0; i < Npc_HuTao.PlayerList.size() ; i++) {
            if(p.getName().equals(Npc_HuTao.PlayerList.get(i))){
                Config config;
                File[] fileList = new File("penguin_plugin/Npc_config").listFiles();
                assert fileList != null;
                for(File Folder : fileList){
                    config = new Config(Folder,2);
                    BaseNpc baseNpc = PlayerTargetEntityList.get(i);
                    if(!Folder.getName().contains("npcBase") && baseNpc.namedTag.get("account").toString().contains(config.getString("uuid"))){
                        if(Float.parseFloat(text)>=0.1 && Float.parseFloat(text)<=10){
                            for (int j = 0; j <Npc_HuTao.PlayerList.size() ; j++) {
                                if(p.getName().equals(Npc_HuTao.PlayerList.get(j))){
                                    config.set("model_size",Float.parseFloat(text));
                                    config.save();
                                    baseNpc.config = config;//重载配置文件
                                    double bound = baseNpc.boundingBox.getMaxY() - baseNpc.boundingBox.getMinY();
                                    baseNpc.boundingBox.setMaxY(baseNpc.boundingBox.getMinY() + bound * baseNpc.scale);
                                    //自动配置眼睛位置
                                    PlayerTargetEntityList.get(j).setScale(1f); //这里的setScale是多少都无所谓，只是一个触发的，以config返回值为准；
                                    p.getServer().updatePlayerListData(PlayerTargetEntityList.get(j).getUniqueId(), PlayerTargetEntityList.get(j).getId(), PlayerTargetEntityList.get(j).getName(), PlayerTargetEntityList.get(j).getSkin());
                                }
                            }
                            p.sendToast("§6设置成功！","§a你成功更新了该npc的缩放比例！");
                        }else p.sendMessage("§c你只能填写0.1~10范围的缩放比例！初始值为1");
                    }
                }
                break;
            }
        }
    }
}