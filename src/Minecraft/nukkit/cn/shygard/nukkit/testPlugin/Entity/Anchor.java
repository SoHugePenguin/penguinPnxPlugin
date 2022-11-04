package Minecraft.nukkit.cn.shygard.nukkit.testPlugin.Entity;

import Minecraft.nukkit.cn.shygard.nukkit.testPlugin.Items.Summon_egg.anchor_information;
import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.custom.CustomEntity;
import cn.nukkit.entity.custom.CustomEntityDefinition;
import cn.nukkit.item.Item;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.math.Vector3;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.utils.Utils;
import org.jetbrains.annotations.NotNull;

import static Minecraft.nukkit.cn.shygard.nukkit.testPlugin.Items.Summon_egg.anchor_information.anchor_info;

public class Anchor extends Entity implements CustomEntity {
    public final static CustomEntityDefinition def = CustomEntityDefinition.builder().identifier("np:anchor")
            .summonable(true)
            .spawnEgg(true)
            .build();

    public Anchor(FullChunk chunk, CompoundTag tag) {
        super(chunk, tag);
    }
    boolean a;
    double dx,dy,dz;

    @NotNull
    @Override
    public String getName() {
        return "Anchor";
    }

    @Override
    public CustomEntityDefinition getDefinition() {
        return def;
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }

    @Override
    public void initEntity(){
        super.initEntity();
        this.setMaxHealth(40);
        this.spawnToAll();
        this.dx = this.x;
        this.dy = this.y;
        this.dz = this.z;
    }

    @Override
    public boolean isAlive() {
        if(anchor_information.name == null || !anchor_information.name.contains(this.getNameTag())){
            anchor_info(this.getNameTag() ,  this.getLocation());
        }
        if(Utils.rand(1,50) == 1) {
            if (this.a) {
                this.y += 0.1;
                this.a = false;
            } else {
                this.y -= 0.1;
                this.a = true;
            }
        }
        if(Utils.rand(1,80) >40){
            this.yaw += 0.1;
        }else this.yaw-= 0.1;

        if(Math.abs(this.dx - this.x) > 0.2 || Math.abs(this.dy - this.y) > 0.2 || Math.abs(this.dz - this.z) > 0.2){
            System.out.println("qwq?");
            this.x = this.dx;
            this.y = this.dy;
            this.z = this.dz;
        }


        this.dx = this.x;
        this.dy = this.y;
        this.dz = this.z;
        return true;
    }

    @Override
    public boolean onInteract(Player player, Item item, Vector3 clickedPos) {
        return super.onInteract(player, item, clickedPos);
    }


    @Override
    public float getHeight() {
        return 3f;
    }

    @Override
    public float getWidth() {
        return 0.4f;
    }

    @Override
    public float getGravity() {
        return 0.04F;
    }
}
