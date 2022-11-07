package Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.dimension;

import cn.nukkit.level.biome.Biome;
import com.dfsek.terra.api.properties.Context;
import com.dfsek.terra.api.world.biome.PlatformBiome;

import java.util.Set;

public class MyFirstDimension extends Biome implements com.dfsek.terra.api.world.biome.Biome {
    @Override
    public String getName() {
        return "Forth_World";
    }

    @Override
    public float getBaseHeight() {
        return 10F;
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    public boolean canRain() {
        return true;
    }

    @Override
    public PlatformBiome getPlatformBiome() {
        return null;
    }

    @Override
    public int getColor() {
        return 1;
    }

    @Override
    public Set<String> getTags() {
        return null;
    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public String getID() {
        return null;
    }

    @Override
    public void setBaseHeight(float baseHeight) {
        super.setBaseHeight(0);
    }
}
