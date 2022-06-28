package io.github.mi1ax.terrariastuff;

import io.github.mi1ax.terrariastuff.init.ItemInit;
import io.github.mi1ax.terrariastuff.init.SoundInit;
import io.github.mi1ax.terrariastuff.projectile.FireEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TerrariaStuff implements ModInitializer {
    public static final String MOD_ID = "terraria-stuff";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final EntityType<FireEntity> FireSparkEntityType = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "fire_spark"),
            FabricEntityTypeBuilder.<FireEntity>create(SpawnGroup.MISC, FireEntity::new)
                    .dimensions(EntityDimensions.fixed(0.10F, 0.10F))
                    .trackRangeBlocks(4).trackedUpdateRate(10)
                    .build()
    );

    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier(TerrariaStuff.MOD_ID,
                "ice_mirror"), ItemInit.ICE_MIRROR);
        Registry.register(Registry.ITEM, new Identifier(TerrariaStuff.MOD_ID,
                "wand_of_sparking"), ItemInit.WAND_OF_SPARKING);

        Registry.register(Registry.SOUND_EVENT, SoundInit.ICE_MIRROR_USE.getId(), SoundInit.ICE_MIRROR_USE);
        Registry.register(Registry.SOUND_EVENT, SoundInit.WAND_OF_SPARKING_USE.getId(), SoundInit.WAND_OF_SPARKING_USE);
    }
}
