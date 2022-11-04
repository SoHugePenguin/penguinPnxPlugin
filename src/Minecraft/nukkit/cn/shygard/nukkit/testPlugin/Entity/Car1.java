package Minecraft.nukkit.cn.shygard.nukkit.testPlugin.Entity;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.EntityRideable;
import cn.nukkit.entity.custom.CustomEntity;
import cn.nukkit.entity.custom.CustomEntityDefinition;
import cn.nukkit.entity.data.ByteEntityData;
import cn.nukkit.entity.data.FloatEntityData;
import cn.nukkit.item.Item;
import cn.nukkit.level.Sound;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.math.BVector3;
import cn.nukkit.math.Vector3;
import cn.nukkit.nbt.tag.CompoundTag;

import static Minecraft.nukkit.cn.shygard.nukkit.testPlugin.Main_PluginBase.online_players;

public class Car1 extends Entity implements CustomEntity, EntityRideable {
    public final static CustomEntityDefinition def = CustomEntityDefinition.builder().identifier("np:car1")
            .summonable(true)
            .spawnEgg(true)
            .build();
    public double mx;
    public double my;

    @Override
    public CustomEntityDefinition getDefinition() {
        return def;
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }

    public Car1(FullChunk chunk, CompoundTag tag) {
        super(chunk, tag);
    }

    @Override
    public void initEntity(){
        super.initEntity();
        this.setMaxHealth(40);
        this.spawnToAll();
    }

    @Override
    public boolean isAlive() {
        if(this.scale != 0.5){
            this.setScale(0.5f);
        }
        if (online_players.size() > 0) {
            for (Player p : online_players) {
                if (p.riding == this) {
                    if (this.my != 0) {
                        Vector3 run = BVector3.fromLocation(this.getLocation(), this.my * 0.15).getPos();
                        Vector3 run2 = BVector3.fromLocation(this.getLocation(), 1).getPos();
                        if (this.getLevel().getBlock(new Vector3(this.getLocation().x + run2.x, p.getLocation().y + 1, this.getLocation().z + run2.z)).getId() != 0 &&
                                this.getLevel().getBlock(new Vector3(this.getLocation().x + run2.x, p.getLocation().y + 2, this.getLocation().z + run2.z)).getId() == 0) {
                            this.fastMove(0, 0.1, 0);
                        } else {
                            if (this.getLevel().getBlock(new Vector3(this.x, this.y - 0.15, this.z)).getId() == 0) {
                                this.fastMove(0, -0.15, 0);
                            } else if (this.getLevel().getBlock(new Vector3(this.x, this.y - 0.04, this.z)).getId() == 0) {
                                this.fastMove(0, -0.04, 0);
                            }
                        }
                        if (this.my > 0) {
                            this.fastMove(run.x, 0, run.z);
                        } else {
                            this.fastMove(0.5 * run.x, 0, 0.5 * run.z);
                        }
                        if (this.mx != 0) {
                            this.yaw -= 2 * mx;
                        }
                    }
                }
            }
        }
        if(this.passengers.size() == 0){
            if(this.getLevel().getBlock((int) this.x, (int) (this.y-0.15), (int) this.z).getId() == 0){
                this.fastMove(0 , -0.15 , 0);
            } else if (this.getLevel().getBlock((int) this.x, (int) (this.y-0.04), (int) this.z).getId() == 0) {
                this.fastMove(0 , -0.04 , 0);
            }
        }
        return super.isAlive();
    }

    @Override
    public boolean onInteract(Player p, Item item, Vector3 clickedPos) {
        this.setScale(0.5f);
        this.spawnToAll();
        boolean has_ride = false;
        if (online_players.size() > 0) {
            for (Player online_player : online_players) {
                if (online_player.riding == this) {
                    has_ride = true;
                    p.sendTip("这个座位有人了哦！");
                    p.sendMessage(String.valueOf(online_player));
                    p.sendMessage(String.valueOf(this.passengers.size()));
                    p.sendMessage(String.valueOf(this.passengers.get(0)));
                    boolean player = this.passengers.size() >= 1 && this.passengers.get(0) instanceof Player;
                    p.sendMessage(String.valueOf(player));
                }
            }
        }
        if(!has_ride){
            this.level.addSound(this, Sound.LAND_BONE_BLOCK);
            p.riding = this;
            byte b = 1;
            this.mountEntity(p,b);
            this.isControlling(p);
            p.sendTip("乘坐成功");
        }
        return true;
    }

    @Override
    public boolean mountEntity(Entity entity) {
        boolean player = this.passengers.size() >= 1 && this.passengers.get(0) instanceof Player;
        byte mode = 2;
        if (!player && (entity instanceof Player || this.passengers.size() == 0)) {
            mode = 1;
        }
        return super.mountEntity(entity, mode);
    }
@Override
    public boolean mountEntity(Entity entity, byte mode) {
        boolean r = super.mountEntity(entity, mode);
        if (entity.riding == this) {
            this.updatePassengers();
            entity.setDataProperty(new ByteEntityData(DATA_RIDER_ROTATION_LOCKED, 1));
            entity.setDataProperty(new FloatEntityData(DATA_RIDER_MAX_ROTATION, 45.0F));
            entity.setDataProperty(new FloatEntityData(DATA_RIDER_MIN_ROTATION, this.passengers.indexOf(entity) == 1 ? 0F : 1F));
            entity.setDataProperty(new FloatEntityData(DATA_RIDER_ROTATION_OFFSET, 0F));
            entity.setRotation(this.yaw, entity.pitch);
            entity.updateMovement();
        }
        return r;
    }

    @Override
    public float getHeight() {
        return 1.5f;
    }

    @Override
    public float getWidth() {
        return 2.0f;
    }

    @Override
    public float getGravity() {
        return 0.04F;
    }
}
