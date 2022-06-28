package io.github.mi1ax.terrariastuff.init;

import io.github.mi1ax.terrariastuff.items.IceMirror;
import io.github.mi1ax.terrariastuff.items.WandOfSparking;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemInit {
    public static final Item ICE_MIRROR = new IceMirror(new FabricItemSettings()
            .group(ItemGroup.TOOLS)
            .maxCount(1)
    );

    public static final Item WAND_OF_SPARKING = new WandOfSparking(new FabricItemSettings()
            .group(ItemGroup.COMBAT)
            .maxCount(1)
    );
}
