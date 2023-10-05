package com.penguin;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockAir;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.player.*;
import cn.nukkit.event.server.DataPacketReceiveEvent;
import cn.nukkit.form.window.FormWindow;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.item.Item;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;
import cn.nukkit.level.Sound;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.network.protocol.StopSoundPacket;
import com.penguin.Entity.Car1;
import com.penguin.Items.Tools.BuildGod_Item;
import com.penguin.Windows.Build_Item_Win.Build_Menu;
import com.penguin.Windows.Build_Item_Win.FillType.*;
import com.penguin.Windows.Create_NPC.*;
import com.penguin.Windows.Home.*;
import com.penguin.Windows.Personal.Personal_System;
import com.penguin.Windows.SaveBuilder.*;
import com.penguin.Windows.Setting.Setting;
import com.penguin.Windows.Shop.Shop;
import com.penguin.Windows.Socail.Social_Contact;
import com.penguin.Windows.Socail.addFriend;
import com.penguin.Windows.Socail.friendApply;
import com.penguin.Windows.Socail.myFriends;
import com.penguin.Windows.Teleport.Teleport_Menu;
import com.penguin.Windows.Teleport.Worlds_teleport;
import com.penguin.Windows.WorldMenuWindow;
import com.penguin.util.SoundSend;
import com.penguin.util.WoodAxe;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.penguin.App.build_map;
import static com.penguin.App.undo_map;

//监听事件严禁加static，否则崩溃！
public class ServerListener implements Listener {
    static final ArrayList<String> p_list = new ArrayList<>();
    static final ArrayList<String> p_get_Data = new ArrayList<>();
    static final ArrayList<String> p_get_text = new ArrayList<>();
    static final ArrayList<String> p_get_file = new ArrayList<>();
    int idr = 0;

    public ServerListener() {
    }

    @EventHandler
    public void Damage_Test(@NotNull EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player p) {
            double damage = 0.0d;
            String onGround = "";
            if (!event.getDamager().onGround) {
                onGround = "暴击！！";
                damage = (double) event.getFinalDamage() * 1.5;
            } else if (event.getDamager().onGround) {
                damage = event.getFinalDamage();
            }

            if (damage < 0.01) {
                damage = 0.0d;
            }
            p.sendActionBar("§l§c" + onGround + " §b- " + damage + "§4♥");
        }
    }

    @EventHandler
    public void PlayerJoin(@NotNull PlayerJoinEvent event) {
        Player p = event.getPlayer();
        App.access(p);

        Item item = Item.fromString("np:world_menu");
//        CompoundTag tag = item.getNamedTag();
        CompoundTag tag1 = new CompoundTag();
        tag1.putByte("minecraft:item_lock", 2);
        tag1.putByte("minecraft:keep_on_death", 1);
        item.setCompoundTag(tag1);

        for (int i = 0; i < p.getInventory().getSize(); i++) {
            if (p.getInventory().getItem(i).equals(item)) {
                break;
            }
            if (i == p.getInventory().getSize() - 1) {
                p.getInventory().addItem(item);
            }
        }
        p.sendToast("\uE100§gApotheosis Ultramarine\uE100-->§b极海群青", "\uE103\uE100§m§6欢迎来到鹅鹅服务器§c玩法：§b空岛生存§r/§a世界经济§r/§c趣味游戏§r/§d建筑休闲§r等；\uE102\uE103");

        //玩家进入后即生成一个空对象（创世神）
        build_map.put(p.getName(), new ArrayList<>());
        undo_map.put(p.getName(), new ArrayList<>());

        //加入游戏传送大厅
        p.teleport(p.getServer().getDefaultLevel().getSpawnLocation());
    }

    @EventHandler
    public void PlayerDieTeleport(@NotNull PlayerDeathEvent event) {
        //死亡一律传送主世界大厅
        Player p = event.getEntity().getPlayer();
        p.setSpawn(p.getServer().getDefaultLevel().getSpawnLocation());
        p.teleport(p.getServer().getDefaultLevel().getSpawnLocation());
    }

    @EventHandler // 物品互动识别
    public void ClickTest(@NotNull PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block target = event.getBlock();
        Item item = event.getItem();

        if (item == null) return;

        if (item.getNamespaceId().equals("np:world_menu") &&
                event.getAction().toString().contains("RIGHT_CLICK_AIR")) {
            player.getLevel().addSound(player, Sound.BREAK_DIRT_WITH_ROOTS);
            WorldMenuWindow.getWindowSimple(player);
        }

        if (item.getNamespaceId().equals("np:build_item")) {
            Block block = event.getBlock();
            if ((event.getAction().toString().contains("RIGHT_CLICK") ||
                    event.getAction().toString().contains("LEFT_CLICK")) &&
                    player.getLoginChainData().getDeviceOS() != 7) {
                if (!(block instanceof BlockAir)) WoodAxe.AddVector(target, player); // 移动端
            } else if (event.getAction().toString().contains("LEFT_CLICK") &&
                    player.getLoginChainData().getDeviceOS() == 7)
                WoodAxe.AddVector(target, player); // PC端
        }
    }

    @EventHandler
    public void PlayerBreak(@NotNull BlockBreakEvent e) {
        Player p = e.getPlayer();
        Item item = e.getPlayer().getInventory().getItemInHand();
        if (item instanceof BuildGod_Item) {
            Block block = e.getBlock();
            if (!(block instanceof BlockAir)) e.setCancelled(true);
            p.onChunkChanged(p.getChunk());
        }
    }

    @EventHandler // 坐骑前后按键判定（赛车）
    public void wasd_test(@NotNull DataPacketReceiveEvent event) {
        if (event.getPacket().offset == 30 &&
                event.getPlayer().riding != null &&
                event.getPlayer().riding.getName().equals("np:car1")) {
            String[] list = event.getPacket().toString().split("=");
            StringBuilder x = new StringBuilder();
            StringBuilder y = new StringBuilder();
            boolean empty = true;
            for (String s : list) {
                for (int j = 0; j < s.length(); j++) {
                    if ("-1234567890.".contains(s.substring(j, j + 1))) {
                        if (empty) {
                            x.append(s.charAt(j));
                        } else {
                            y.append(s.charAt(j));
                        }
                    }
                }
                if (!x.isEmpty()) {
                    empty = false;
                }
                if (!y.isEmpty()) {
                    break;
                }
            }
            Car1 car1 = (Car1) event.getPlayer().riding;
            if (Double.parseDouble(x.toString()) > 0) {
                car1.mx = 1d;
            } else if (Double.parseDouble(x.toString()) < 0) {
                car1.mx = -1d;
            }
            if (Double.parseDouble(y.toString()) > 0) {
                car1.my = 1d;
            } else if (Double.parseDouble(y.toString()) < 0) {
                car1.my = -1d;
            }
        }
    }

    @EventHandler // 玩家退出事件
    public void ExitGameEvent(@NotNull PlayerQuitEvent event) {
        Player p = event.getPlayer();
        p.removeTag("noUseStab");
    }

    @EventHandler
    public void ChatSetting(@NotNull PlayerChatEvent event) {
        Player p = event.getPlayer();
        String is_op = "§b<Player>";
        if (p.isOp()) is_op = "§6<管理>";
        String device = "§d|移动端|";
        if (p.getLoginChainData().getDeviceOS() == 7) device = "§d|PC端|";
        event.setMessage(device + " §b[" + p.getSocketAddress().getHostName() + "]\n " + is_op + " §a『在" + p.getLevelName() + "』 :§r " + event.getMessage());
    }

    @EventHandler
    public void Build_Item_Menu(@NotNull PlayerFormRespondedEvent event) {
        Player p = event.getPlayer();
        if (!(event.getWindow().getResponse() == null) && event.getWindow() instanceof FormWindowSimple simple) {
            int page = simple.getResponse().getClickedButtonId();
            if (simple instanceof Build_Menu) {
                switch (page) {
                    case 0 -> p.showFormWindow(CommonFill.common_fill());
                    case 1 -> p.showFormWindow(KeepFill.Keep_Fill());
                    case 2 -> p.showFormWindow(ReplaceFill.Replace_Fill());
                    case 3 -> p.showFormWindow(RandomFill.Random_Filll());
                    case 4 -> UndoFill.undo_Fill(p);
                }
            }
        }

        if (!(event.getWindow().getResponse() == null) && event.getWindow() instanceof FormWindowCustom form) {
            if (form instanceof CommonFill) {

                if (!form.getResponse().getInputResponse(1).isEmpty() &&
                        !form.getResponse().getInputResponse(3).isEmpty() &&
                        Integer_Test(form.getResponse().getInputResponse(1), p) &&
                        Integer_Test(form.getResponse().getInputResponse(3), p)) {
                    if (build_map.get(p.getName()).size() == 2) {
                        CommonFill.common_fill_do(Integer.parseInt(form.getResponse().getInputResponse(1)),
                                Integer.parseInt(form.getResponse().getInputResponse(3)), p);
                    } else p.sendMessage("§l§c坐标选取不完整,请检查!");
                } else p.sendMessage("  <不合法的ID值>");

            } else if (form instanceof KeepFill) {
                if (!form.getResponse().getInputResponse(1).isEmpty() &&
                        !form.getResponse().getInputResponse(3).isEmpty() &&
                        Integer_Test(form.getResponse().getInputResponse(1), p) &&
                        Integer_Test(form.getResponse().getInputResponse(3), p)) {
                    if (build_map.get(p.getName()).size() == 2) {
                        KeepFill.KeepFill_do(Integer.parseInt(form.getResponse().getInputResponse(1)),
                                Integer.parseInt(form.getResponse().getInputResponse(3)), p);
                    } else p.sendMessage("§l§c坐标选取不完整,请检查!");
                } else p.sendMessage("  <不合法的ID值>");

            } else if (form instanceof ReplaceFill) {
                if (!form.getResponse().getInputResponse(1).isEmpty() &&
                        !form.getResponse().getInputResponse(3).isEmpty() &&
                        !form.getResponse().getInputResponse(5).isEmpty() &&
                        !form.getResponse().getInputResponse(7).isEmpty() &&
                        Integer_Test(form.getResponse().getInputResponse(1), p) &&
                        Integer_Test(form.getResponse().getInputResponse(3), p) &&
                        Integer_Test(form.getResponse().getInputResponse(5), p) &&
                        Integer_Test(form.getResponse().getInputResponse(7), p)) {
                    if (build_map.get(p.getName()).size() == 2) {
                        ReplaceFill.ReplaceFill_do(Integer.parseInt(form.getResponse().getInputResponse(1)),
                                Integer.parseInt(form.getResponse().getInputResponse(3)),
                                Integer.parseInt(form.getResponse().getInputResponse(5)),
                                Integer.parseInt(form.getResponse().getInputResponse(7)), p);
                    } else p.sendMessage("§l§c坐标选取不完整,请检查!");
                } else p.sendMessage("  <不合法的ID值>");

            } else if (form instanceof RandomFill) {
                if (!form.getResponse().getInputResponse(1).isEmpty() &&
                        !form.getResponse().getInputResponse(2).isEmpty() &&
                        !form.getResponse().getInputResponse(4).isEmpty() &&
                        !form.getResponse().getInputResponse(5).isEmpty() &&
                        !form.getResponse().getInputResponse(7).isEmpty() &&
                        !form.getResponse().getInputResponse(8).isEmpty() &&
                        !form.getResponse().getInputResponse(10).isEmpty() &&
                        !form.getResponse().getInputResponse(11).isEmpty() &&
                        !form.getResponse().getInputResponse(13).isEmpty() &&
                        !form.getResponse().getInputResponse(14).isEmpty() &&
                        Integer_Test(form.getResponse().getInputResponse(1), p) &&
                        Integer_Test(form.getResponse().getInputResponse(2), p) &&
                        Integer_Test(form.getResponse().getInputResponse(4), p) &&
                        Integer_Test(form.getResponse().getInputResponse(5), p) &&
                        Integer_Test(form.getResponse().getInputResponse(7), p) &&
                        Integer_Test(form.getResponse().getInputResponse(8), p) &&
                        Integer_Test(form.getResponse().getInputResponse(10), p) &&
                        Integer_Test(form.getResponse().getInputResponse(11), p) &&
                        Integer_Test(form.getResponse().getInputResponse(13), p) &&
                        Integer_Test(form.getResponse().getInputResponse(14), p)) {
                    if (build_map.get(p.getName()).size() == 2) {
                        RandomFill.Random(Integer.parseInt(form.getResponse().getInputResponse(1)),
                                Integer.parseInt(form.getResponse().getInputResponse(2)),
                                Integer.parseInt(form.getResponse().getInputResponse(4)),
                                Integer.parseInt(form.getResponse().getInputResponse(5)),
                                Integer.parseInt(form.getResponse().getInputResponse(7)),
                                Integer.parseInt(form.getResponse().getInputResponse(8)),
                                Integer.parseInt(form.getResponse().getInputResponse(10)),
                                Integer.parseInt(form.getResponse().getInputResponse(11)),
                                Integer.parseInt(form.getResponse().getInputResponse(13)),
                                Integer.parseInt(form.getResponse().getInputResponse(14)), p);
                    } else p.sendMessage("§l§c坐标选取不完整,请检查!");
                } else if (!form.getResponse().getInputResponse(1).isEmpty() &&
                        !form.getResponse().getInputResponse(2).isEmpty() &&
                        !form.getResponse().getInputResponse(4).isEmpty() &&
                        !form.getResponse().getInputResponse(5).isEmpty() &&
                        !form.getResponse().getInputResponse(7).isEmpty() &&
                        !form.getResponse().getInputResponse(8).isEmpty() &&
                        !form.getResponse().getInputResponse(10).isEmpty() &&
                        !form.getResponse().getInputResponse(11).isEmpty() &&
                        Integer_Test(form.getResponse().getInputResponse(1), p) &&
                        Integer_Test(form.getResponse().getInputResponse(2), p) &&
                        Integer_Test(form.getResponse().getInputResponse(4), p) &&
                        Integer_Test(form.getResponse().getInputResponse(5), p) &&
                        Integer_Test(form.getResponse().getInputResponse(7), p) &&
                        Integer_Test(form.getResponse().getInputResponse(8), p) &&
                        Integer_Test(form.getResponse().getInputResponse(10), p) &&
                        Integer_Test(form.getResponse().getInputResponse(11), p)) {
                    if (build_map.get(p.getName()).size() == 2) {
                        RandomFill.Random(Integer.parseInt(form.getResponse().getInputResponse(1)),
                                Integer.parseInt(form.getResponse().getInputResponse(2)),
                                Integer.parseInt(form.getResponse().getInputResponse(4)),
                                Integer.parseInt(form.getResponse().getInputResponse(5)),
                                Integer.parseInt(form.getResponse().getInputResponse(7)),
                                Integer.parseInt(form.getResponse().getInputResponse(8)),
                                Integer.parseInt(form.getResponse().getInputResponse(10)),
                                Integer.parseInt(form.getResponse().getInputResponse(11)), p);
                    } else p.sendMessage("§l§c坐标选取不完整,请检查!");
                } else if (!form.getResponse().getInputResponse(1).isEmpty() &&
                        !form.getResponse().getInputResponse(2).isEmpty() &&
                        !form.getResponse().getInputResponse(4).isEmpty() &&
                        !form.getResponse().getInputResponse(5).isEmpty() &&
                        !form.getResponse().getInputResponse(7).isEmpty() &&
                        !form.getResponse().getInputResponse(8).isEmpty() &&
                        Integer_Test(form.getResponse().getInputResponse(1), p) &&
                        Integer_Test(form.getResponse().getInputResponse(2), p) &&
                        Integer_Test(form.getResponse().getInputResponse(4), p) &&
                        Integer_Test(form.getResponse().getInputResponse(5), p) &&
                        Integer_Test(form.getResponse().getInputResponse(7), p) &&
                        Integer_Test(form.getResponse().getInputResponse(8), p)) {
                    if (build_map.get(p.getName()).size() == 2) {
                        RandomFill.Random(Integer.parseInt(form.getResponse().getInputResponse(1)),
                                Integer.parseInt(form.getResponse().getInputResponse(2)),
                                Integer.parseInt(form.getResponse().getInputResponse(4)),
                                Integer.parseInt(form.getResponse().getInputResponse(5)),
                                Integer.parseInt(form.getResponse().getInputResponse(7)),
                                Integer.parseInt(form.getResponse().getInputResponse(8)), p);
                    } else p.sendMessage("§l§c坐标选取不完整,请检查!");
                } else if (!form.getResponse().getInputResponse(1).isEmpty() &&
                        !form.getResponse().getInputResponse(2).isEmpty() &&
                        !form.getResponse().getInputResponse(4).isEmpty() &&
                        !form.getResponse().getInputResponse(5).isEmpty() &&
                        Integer_Test(form.getResponse().getInputResponse(1), p) &&
                        Integer_Test(form.getResponse().getInputResponse(2), p) &&
                        Integer_Test(form.getResponse().getInputResponse(4), p) &&
                        Integer_Test(form.getResponse().getInputResponse(5), p)) {
                    if (build_map.get(p.getName()).size() == 2) {
                        RandomFill.Random(Integer.parseInt(form.getResponse().getInputResponse(1)),
                                Integer.parseInt(form.getResponse().getInputResponse(2)),
                                Integer.parseInt(form.getResponse().getInputResponse(4)),
                                Integer.parseInt(form.getResponse().getInputResponse(5)), p);
                    } else p.sendMessage("§l§c坐标选取不完整,请检查!");
                } else if (!form.getResponse().getInputResponse(1).isEmpty() &&
                        !form.getResponse().getInputResponse(2).isEmpty() &&
                        Integer_Test(form.getResponse().getInputResponse(1), p) &&
                        Integer_Test(form.getResponse().getInputResponse(2), p)) {
                    if (build_map.get(p.getName()).size() == 2) {
                        RandomFill.Random(Integer.parseInt(form.getResponse().getInputResponse(1)),
                                Integer.parseInt(form.getResponse().getInputResponse(2)), p);
                    } else p.sendMessage("§l§c坐标选取不完整,请检查!");
                } else p.sendMessage("  <不合法的ID值>");
            }
        }
    }

    @EventHandler
    public void Back(@NotNull PlayerFormRespondedEvent e) {
        Player p = e.getPlayer();
        if (!(e.getWindow().getResponse() == null) && e.getWindow() instanceof FormWindowSimple w) {
            if ((w instanceof Home || w instanceof Personal_System ||
                    w instanceof opSaveBuilder || w instanceof Setting || w instanceof Shop ||
                    w instanceof Social_Contact || w instanceof Teleport_Menu || w instanceof NpcMenu) &&
                    w.getResponse().getClickedButton().getText().equals("返回")) {
                WorldMenuWindow.getWindowSimple(p);
            } else if ((w instanceof MyHome || w instanceof HomeOpMenu || w instanceof HomeList) &&
                    w.getResponse().getClickedButton().getText().equals("返回")) {
                p.showFormWindow(Home.OpHomeMenu(p));
            } else if (w instanceof Introduct && w.getResponse().getClickedButton().getText().equals("返回上级")) {
                p.showFormWindow(opSaveBuilder.getWindow_op_save_builder());
            }
        }
    }

    @EventHandler
    public void onWindowsListener(@NotNull PlayerFormRespondedEvent event) {
        int page;
        Player p = event.getPlayer();
        if (event.getWindow().getResponse() != null && event.getWindow() instanceof FormWindowSimple simple) {
            page = simple.getResponse().getClickedButtonId();
            if (simple instanceof WorldMenuWindow) {
                switch (page) {
                    case 0 -> p.showFormWindow(Teleport_Menu.getWindowTeleport_Menu());
                    case 1 -> p.showFormWindow(Home.OpHomeMenu(p));
                    case 2 -> p.showFormWindow(Shop.getWindowShop());
                    case 3 -> Social_Contact.getWindowSocial_Contact(p);
                    case 4 -> p.showFormWindow(Personal_System.getWindowPersonal_System());
                    case 5 -> p.showFormWindow(Setting.getWindowSetting());
                    case 6 -> p.showFormWindow(opSaveBuilder.getWindow_op_save_builder());
                    case 7 -> p.showFormWindow(NpcMenu.getNpc_Menu());
                }
            }

            if (simple instanceof Teleport_Menu) {
                switch (page) {
                    case 0 -> {
                        p.sendToast("§6[传送]", "§g" + p.getName() + ",§a欢迎回到主世界！");
                        p.teleport(p.getServer().getDefaultLevel().getSpawnLocation());
                    }
                    case 1 -> Worlds_teleport.WorldTeleport(p);
                }
            }

            if (event.getFormID() == Worlds_teleport.WORLDS) {
                //颜色符号需过滤
                String worldName = simple.getResponse().getClickedButton().getText().substring(2);
                //如果世界没有加载，则载入
                if (!p.getServer().isLevelLoaded(worldName)) {
                    p.getServer().loadLevel(worldName);
                }
                Level level = p.getServer().getLevelByName(worldName);
                if (level != null) {//颜色符号需过滤
                    Position position = level.getSpawnLocation();
                    //修复传送后卡地下
                    while (true) {
                        if (level.getBlock(position).getId() == Block.AIR) {
                            break;
                        } else {
                            position.y++;
                        }
                    }
                    p.teleport(position);
                    p.sendToast("§e[传送]", "    §b你已传送至 " + level.getName());
                }
            }

            if (event.getFormID() == Social_Contact.WORLDS) {
                switch (simple.getResponse().getClickedButtonId()) {
                    case 0 -> addFriend.add(p);
                    case 1 -> myFriends.my_Friends(p);
                    case 2 -> friendApply.frined_Apply(p);
                }
            }

            if (simple instanceof Home) {
                switch (page) {
                    case 0 -> p.showFormWindow(MyHome.MyHomeTest(p));
                    case 1 -> p.sendMessage("别人的家园");
                    case 2 -> p.sendMessage("家园权限设置");
                    case 3 -> p.showFormWindow(HomeOpMenu.getWindowHomeOpMenu());
                }
            } else if (simple instanceof MyHome) {
                switch (page) {
                    case 0 -> p.showFormWindow(CreateHome.getWindowCreateHome(p));
                    case 1 -> {
                        p.sendToast("§6[家园]", "§g" + p.getName() + ",§a欢迎回家！§d要愉快的玩耍奥！");
                        p.teleport(p.getServer().getLevelByName(p.getName() + "的家园").getSpawnLocation());
                    }
                }
            } else if (simple instanceof CreateHome) {
                switch (page) {
                    case 0 -> p.showFormWindow(CreateHome.CreateWorld(p));
                    case 1 -> p.sendMessage("§a敬请期待");
                }
            } else if (simple instanceof HomeOpMenu) {
                if (page == 0) {
                    HomeList.Home_List(p);
                }
            } else if (simple instanceof HomeList) {
                if (!simple.getButtons().get(page).getText().contains("返回")) {
                    p.showFormWindow(Managing_Homes.Managing_List(((FormWindowSimple) event.getWindow()).getButtons().get(page).getText()));
                }
            } else if (simple instanceof Managing_Homes) {
                switch (page) {
                    case 0 -> {
                        Managing_Homes.loadWorld(p, simple.getTitle());
                        p.sendToast("§6[传送]", "§c尊敬的管理员，欢迎来到§a" + simple.getTitle());
                    }
                    case 1 -> p.sendMessage("还在做。。");
                    case 2 -> p.sendMessage("还在做呢。。");
                }
            }

            if (simple instanceof opSaveBuilder) {
                switch (page) {
                    case 0 -> p.showFormWindow(SaveBuild.Create_Yml());
                    case 1 -> p.showFormWindow(Filecenter.Crate_Files());
                    case 2 -> p.showFormWindow(Introduct.getIntroduction());
                }
            } else if (simple instanceof Filecenter) {
                if (page == 0) {
                    p.showFormWindow(CreateFile.Window_Crate_Files(p));
                } else {
                    p.showFormWindow(FileList.Window_File_List(p, ((FormWindowSimple) event.getWindow()).getButtons().get(page).getText()));
                    p_get_file.set(idr, "Builder_Save\\" + ((FormWindowSimple) event.getWindow()).getButtons().get(page).getText());
                }
            }

            if (simple instanceof NpcMenu) {
                switch (page) {
                    case 0 -> SpawnNpc.Spawn_Npc(p);
                    case 1 -> p.sendMessage("右键NPC即可管理！");
                }
            }
        } else if (event.getWindow() instanceof FormWindowCustom form) {
            if (event.getFormID() == addFriend.WORLDS) {
                String playerName = form.getResponse().getInputResponse(1);
                Player player = p.getServer().getPlayer(playerName);
                if (player == null || !playerName.equals(player.getName())) {
                    p.sendMessage("玩家不存在");
                } else {
                    p.sendMessage(String.valueOf(p.getViewDistance()));
//                    player.setViewDistance(2);
                }
            }
        }
    }

    @EventHandler
    public void data_Listener(@NotNull PlayerFormRespondedEvent dataListener) throws IOException {
        Player p = dataListener.getPlayer();
        if (!(dataListener.getWindow().getResponse() == null) && dataListener.getWindow() instanceof FormWindowSimple simple) {
            FormWindow window = dataListener.getWindow();
            if (!p_list.isEmpty()) {
                for (int i = 0; i < p_list.size(); i++) {
                    if (p_list.get(i).equals(p.getName())) {
                        p_get_Data.set(i, dataListener.getWindow().getJSONData());
                        idr = i;
                    } else if (i == p_list.size() - 1) {
                        p_list.add(p.getName());
                        p_get_Data.add(dataListener.getWindow().getJSONData());
                        p_get_text.add("");
                        p_get_file.add("");
                    }
                }
            } else {
                p_list.add(p.getName());
                p_get_Data.add(dataListener.getWindow().getJSONData());
                p_get_text.add("");
                p_get_file.add("");
            }
            if (window instanceof Create_To) {
                p.showFormWindow(Save_Success.SaveSuccess(p, simple.getButtons().get(simple.getResponse().getClickedButtonId()).getText(), get_data(p_get_text.get(idr))));
            }
            if (window instanceof FileList) {
                p.showFormWindow(Paste_Build.Build(simple.getButtons().get(simple.getResponse().getClickedButtonId()).getText()));
                p_get_text.set(idr, simple.getButtons().get(simple.getResponse().getClickedButtonId()).getText());
                p_get_file.set(idr, "penguin_plugin\\" + p_get_file.get(idr) + "\\" + simple.getButtons().get(simple.getResponse().getClickedButtonId()).getText());
            }
            if (window instanceof Paste_Build) {
                int id = simple.getResponse().getClickedButtonId();
                if (id >= 0 && id < 9) {
                    Paste_Build.PasteBuild(p, p_get_file.get(idr), id);
                }
                if (id == 9) {
                    p.showFormWindow(Paste_Build.DeleteBuild(p_get_file.get(idr)));
                }
            }
        } else if (dataListener.getWindow() instanceof FormWindowCustom window) {
            if (!p_list.isEmpty()) {
                for (int i = 0; i < p_list.size(); i++) {
                    if (p_list.get(i).equals(p.getName())) {
                        p_get_Data.set(i, dataListener.getWindow().getJSONData());
                        idr = i;
                    }
                }
            } else {
                p_list.add(p.getName());
                p_get_Data.add(dataListener.getWindow().getJSONData());
                p_get_text.add("");
                p_get_file.add("");
            }
            if (window instanceof CreateFile) {
                if (p_get_Data.get(idr).length() > 313) {
                    p_get_text.set(idr, p_get_Data.get(idr).substring(314));
                    p.showFormWindow(CreateFile.Super_File(get_data(p_get_text.get(idr))));
                }
            }
            if (window instanceof SaveBuild) {
                if (p_get_Data.get(idr).length() > 305) {
                    p_get_text.set(idr, p_get_Data.get(idr).substring(306));
                    p.showFormWindow(Create_To.Create_Yml_To(get_data(p_get_text.get(idr))));
                }
            }
            if (window instanceof NpcSettingBaseName) {
                if (p_get_Data.get(idr).length() > 198) {
                    p_get_text.set(idr, p_get_Data.get(idr).substring(199));
                    NpcSettingBaseName.Npc_Name(p, get_data(p_get_text.get(idr)));
                }
            }
            if (window instanceof NpcSettingBaseModel) {
                if (p_get_Data.get(idr).length() > 205) {
                    p_get_text.set(idr, p_get_Data.get(idr).substring(206));
                    NpcSettingBaseModel.Npc_Model(p, get_data(p_get_text.get(idr)));
                }
            }
        }
    }

    @EventHandler
    public void Npc_Listener(@NotNull PlayerFormRespondedEvent NpcListener) throws IOException {
        Player p = NpcListener.getPlayer();
        if (!(NpcListener.getWindow().getResponse() == null) && NpcListener.getWindow() instanceof FormWindowSimple simple) {
            int page;
            page = simple.getResponse().getClickedButtonId();
            FormWindow window = NpcListener.getWindow();
            if (window instanceof NpcBaseEntity) {
                switch (page) {
                    case 0 -> {
                        Random ran = new Random();
                        int i = ran.nextInt(3) + 1;
                        StopSoundPacket packet = new StopSoundPacket();
                        packet.stopAll = true;
                        List<Player> targets = new ArrayList<>();
                        targets.add(p);
                        packet.name = "";
                        Server.broadcastPacket(targets, packet);
                        SoundSend.playSound(p, "hutao" + i, 1, 1);
                    }
                    case 1 -> p.showFormWindow(NpcSetting.getNpc_Setting());
                    case 2 -> p.showFormWindow(NpcInformation.getNpc_information(p));
                }
            } else if (window instanceof NpcSetting) {
                switch (page) {
                    case 0 -> p.showFormWindow(NpcSettingBase.getNpc_Setting_Base());
                    case 1 -> p.showFormWindow(NpcSettingSkin.getNpc_Setting_Skin());
                    case 2 -> p.sendMessage("行为设置");
                }
            } else if (window instanceof NpcSettingBase) {
                switch (page) {
                    case 0 -> p.showFormWindow(NpcSettingBaseName.Change_Npc_Name());
                    case 1 -> p.showFormWindow(NpcSettingBaseModel.Change_Npc_Model());
                    case 2 -> p.showFormWindow(NpcSettingBaseEquipment.Change_Npc_Equipment());
                    case 3 -> NpcSettingBaseDelete.Npc_Delete(p);
                }
            } else if (window instanceof NpcSettingSkin) {
                NpcSettingSkin.ChangeSkinSuccess(p, ((NpcSettingSkin) window).getResponse().getClickedButton().getText());
            } else if (window instanceof NpcSettingBaseEquipment) {
                if (page == 0) NpcSettingBaseEquipment.Clone_Equipment(p);
            }
        }
    }

    private String get_data(@NotNull String text) {
        String New_File_Name = "";
        for (int i = 1; i < text.length() - 1; i++) {
            int intIndex = text.substring(0, i).indexOf("}");
            if (intIndex != -1) {
                New_File_Name = text.substring(0, i - 2);
                break;
            }
        }
        return New_File_Name;
    }


    //    判定是否带有非法字符
    private boolean Integer_Test(String text, Player p) {
        int text_long = text.length();
        if (text.length() > 4) {
            p.sendMessage("<错误> 字符串长度不应过长！");
            return false;
        }
        while (text_long > 0) {
            if (!"0123456789".contains(text.substring(text_long - 1, text_long))) {
                return false;
            }
            text_long--;
        }
        return true;
    }
}
