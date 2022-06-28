package io.github.mi1ax.terrariastuff.init;

import io.github.mi1ax.terrariastuff.TerrariaStuff;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class SoundInit {
    public static final SoundEvent ICE_MIRROR_USE
            = new SoundEvent(
                    new Identifier(TerrariaStuff.MOD_ID, "ice_mirror_use")
    );

    public static final SoundEvent WAND_OF_SPARKING_USE
            = new SoundEvent(
                    new Identifier(TerrariaStuff.MOD_ID, "wand_of_sparking_use")
    );
}
