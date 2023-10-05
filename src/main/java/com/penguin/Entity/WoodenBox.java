/**
 * @Project MC
 * @File WoodenBox
 * @Time 2023/1/24 23:08
 * @ToDo
 * @Author SoHugePenguin
 */
package com.penguin.Entity;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.custom.CustomEntity;
import cn.nukkit.entity.custom.CustomEntityDefinition;
import cn.nukkit.item.Item;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.math.Vector3;
import cn.nukkit.nbt.tag.CompoundTag;
import org.jetbrains.annotations.NotNull;

public class WoodenBox extends Entity implements CustomEntity {
    public final static CustomEntityDefinition def = CustomEntityDefinition.builder().identifier("np:wooden_box")
            .summonable(true)
            .spawnEgg(false)
            .build();

    public WoodenBox(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @NotNull
    @Override
    public String getName() {
        return "Wooden_box";
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
    public void initEntity() {
        super.initEntity();
        this.setMaxHealth(40);
        this.spawnToAll();
    }

    @Override
    public boolean isAlive() {
        return super.isAlive();
    }

    @Override
    public boolean onInteract(@NotNull Player player, Item item, Vector3 clickedPos) {
        player.riding = this;
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
