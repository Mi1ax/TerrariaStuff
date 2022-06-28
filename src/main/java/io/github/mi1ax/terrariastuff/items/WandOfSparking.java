package io.github.mi1ax.terrariastuff.items;

import io.github.mi1ax.terrariastuff.init.SoundInit;
import io.github.mi1ax.terrariastuff.projectile.FireEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class WandOfSparking extends Item {
    public WandOfSparking(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        if (!world.isClient) {
            var fireballEntity = new FireEntity(world, playerEntity);
            fireballEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(),
                    0.0f, 2.0f, 1.0f);
            world.spawnEntity(fireballEntity);
        }

        world.playSound(
                playerEntity,
                playerEntity.getX(),
                playerEntity.getY(),
                playerEntity.getZ(),
                SoundInit.WAND_OF_SPARKING_USE,
                SoundCategory.PLAYERS, 1.0f, 1.0f);

        playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        return TypedActionResult.consume(playerEntity.getStackInHand(hand));
    }
}
