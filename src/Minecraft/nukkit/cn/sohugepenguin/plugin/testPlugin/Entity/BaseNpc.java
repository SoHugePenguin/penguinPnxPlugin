package Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Entity;

import Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Main_PluginBase;
import cn.nukkit.Player;
import cn.nukkit.entity.EntityHuman;
import cn.nukkit.entity.custom.CustomEntity;
import cn.nukkit.entity.custom.CustomEntityDefinition;
import cn.nukkit.entity.data.Skin;
import cn.nukkit.item.Item;
import cn.nukkit.level.Sound;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.math.Vector3;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.potion.Effect;
import cn.nukkit.utils.Config;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Main_PluginBase.online_players;
import static Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Windows.Create_NPC.Npc_HuTao.HuTao_Windows;
import static cn.nukkit.entity.data.Skin.GEOMETRY_CUSTOM;

public class BaseNpc extends EntityHuman implements CustomEntity {
    public final static CustomEntityDefinition def = CustomEntityDefinition.builder().identifier("np:hutao")
            .summonable(true)
            .spawnEgg(false)
            .build();
    public static ArrayList<BaseNpc> onlineNpcList = new ArrayList<>();
    public boolean alive = true;
    public Config config;
    public Skin skin;

    public BaseNpc(FullChunk chunk, CompoundTag tag) {
        super(chunk, tag);
    }

    @Override
    public CustomEntityDefinition getDefinition() {
        return def;
    }

    @Override
    public String getOriginalName() {
        return "hutao";
    }

    @Override
    public void initEntity() {
        //获取服务器npc数据文件
        Config config;
        File[] file = new File("penguin_plugin/Npc_config").listFiles();
        assert file != null;
        for (File Folder : file) {
            config = new Config(Folder, 2);
            if (!Folder.getName().contains("npcBase") && this.namedTag.get("account").toString().contains(config.getString("uuid"))) {
                this.config = config;
            }
        }
        if (this.config == null) {
            this.config = new Config("penguin_plugin/Npc_config/npcBase.yml", 2);
        }
        this.setSkin(skin);
        super.initEntity();
        this.setMaxHealth(40);
        this.spawnToAll();
    }

    @Override
    public Skin getSkin() {
        if (skin == null) {
            skin = new Skin();
            BufferedImage image;
            //获取服务器npc数据文件
            Config config;
            File[] file = new File("penguin_plugin/Npc_config").listFiles();
            assert file != null;
            for (File Folder : file) {
                config = new Config(Folder, 2);
                if (!Folder.getName().contains("npcBase") && this.namedTag.get("account").toString().contains(config.getString("uuid"))) {
                    this.config = config;
                }
            }
            File f = new File("penguin_plugin/skins/" + (this.config.get("skin") != null ? this.config.get("skin") : "steve.png"));
            try {
                image = ImageIO.read(f);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            skin.setSkinData(image);
            skin.setSkinColor("#FF0004");
            skin.setSkinId("skin");
            skin.setTrusted(true);
            skin.setSkinResourcePatch(GEOMETRY_CUSTOM);
        }
        this.setSkin(skin);
        return skin;
    }

    @Override
    public void setSkin(Skin skin) {
        skin = this.skin;
        super.setSkin(skin);
    }

    @Override
    public void spawnToAll() {
        super.spawnToAll();
    }

    @Override
    public float getScale() {
        return super.getScale();
    }

    @Override
    public void setScale(float scale) {
        super.setScale(this.config.get("model_size") != null ? (float) this.config.getDouble("model_size") : 1f);
    }

    @Override
    public boolean isAlive() {
        if (this.getHealth() < 10) {
            this.health = 40;
        }
        boolean add = true;
        for (BaseNpc baseNpc : onlineNpcList) {
            if (baseNpc == this) {
                add = false;
                break;
            }
        }
        if (add) {
            onlineNpcList.add(this);
        }
        Map<UUID, Player> onlinePlayers = Main_PluginBase.getMain().getServer().getOnlinePlayers();
        Player p;
        UUID idr = null;
        this.addEffect((new Effect(11, "test", 0, 0, 0, false)).setDuration(20).setAmplifier(5).setAmbient(true));
        if (onlinePlayers.size() > 0) {
            double length = 200;
            for (UUID uuid : onlinePlayers.keySet()) {
                p = getServer().getOnlinePlayers().get(uuid);
                if (idr == null) {
                    idr = p.getUniqueId();
                }
                double dx = (p.x - this.x), dy = (p.y - this.y), dz = (p.z - this.z);
                double l = dx * dx + dy * dy + dz * dz;
                if (l < 200 && l < length) {
                    length = l;
                    idr = uuid;
                }
            }
            if (idr != null) {
                p = onlinePlayers.get(idr);
                double dx = this.x - p.x, dy = this.y - p.y, dz = this.z - p.z;
                double yaw = Math.asin(dx / Math.sqrt(dx * dx + dz * dz)) / Math.PI * 180.0D;
                double pitch = Math.round(Math.asin(dy / Math.sqrt(dx * dx + dz * dz + dy * dy)) / Math.PI * 180.0D);
                if (dz > 0.0D) yaw = -yaw + 180.0D;
                this.yaw = yaw;
                this.pitch = pitch;
            }
        }
        return alive;
    }

    @Override
    public boolean canBePushed() {
        return false;
    }

    @Override
    public boolean onInteract(Player p, Item item, Vector3 clickedPos) {
//        this.getServer().updatePlayerListData(this.getUniqueId(),this.getId(),this.getName(),this.getSkin());
        //updatePlayerListData是增加到ScoreboardList玩家列表中，皮肤刷新时需要用到，可以remove取消列表显示！
        this.spawnToAll();
        this.level.addSound(this, Sound.LAND_BONE_BLOCK);
        p.showFormWindow(HuTao_Windows(p, this));
        return true;
    }

    @Override
    public Item[] getDrops() {
        //取消掉落物防止kill @e
        List<Item> drops = new ArrayList<>();
        drops.add(Item.get(Item.AIR, 0, 0));
        return drops.toArray(new Item[0]);
    }

    @Override
    public float getGravity() {
        return 0.06f;
    }

    @Override
    public float getHeight() {
        return 2.0f;
    }

    @Override
    public float getWidth() {
        return 0.6f;
    }

    @Override
    public int getMaxHealth() {
        return 40;
    }

    @Override
    public boolean isNameTagAlwaysVisible() {
        return true;
    }

    @Override
    public boolean isNameTagVisible() {
        return true;
    }

    @Override
    public void setNameTagVisible(boolean value) {
        super.setNameTagVisible(true);
    }

    @Override
    public void setNameTagVisible() {
        super.setNameTagVisible();
    }

    @Override
    public boolean canClimb() {
        return true;
    }

    @Override
    public String getNameTag() {
        return this.config.get("name") != null ? this.config.getString("name") : "npc";
    }

    @Override
    public void setNameTag(String name) {
        super.setNameTag(this.config.get("name") != null ? this.config.getString("name") : "npc");
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}
