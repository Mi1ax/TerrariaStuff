package io.github.mi1ax.terrariastuff.items;

import io.github.mi1ax.terrariastuff.init.SoundInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class IceMirror extends Item {
    public IceMirror(Settings settings) {
        super(settings);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof PlayerEntity player) {
            if (user instanceof ServerPlayerEntity) {
                BlockPos spawnPoint = ((ServerPlayerEntity) user).getSpawnPointPosition();
                if (spawnPoint != null) {
                    user.teleport(spawnPoint.getX(), spawnPoint.getY(), spawnPoint.getZ());
                }
            }

            // 20 ticks = 1 sec
            player.getItemCooldownManager().set(this, 60);

            for(int i = 0; i < 32; ++i) {
                world.addParticle(
                        ParticleTypes.PORTAL,
                        player.getX(),
                        player.getY() + player.getRandom().nextDouble() * 2.0D,
                        player.getZ(),
                        player.getRandom().nextGaussian(),
                        0.0D,
                        player.getRandom().nextGaussian());
            }

            world.playSound(player, player.getX(), player.getY(), player.getZ(),
                    SoundInit.ICE_MIRROR_USE, SoundCategory.PLAYERS, 1.0f, 1.0f);
        }
        return super.finishUsing(stack, world, user);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 30;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand);
        return super.use(world, user, hand);
    }
}
