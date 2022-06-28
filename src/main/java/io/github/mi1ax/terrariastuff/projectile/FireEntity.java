package io.github.mi1ax.terrariastuff.projectile;

import io.github.mi1ax.terrariastuff.EntitySpawnPacket;
import io.github.mi1ax.terrariastuff.TerrariaStuff;
import io.github.mi1ax.terrariastuff.TerrariaStuffClient;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.Packet;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class FireEntity extends ThrownItemEntity {

    public FireEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public FireEntity(World world, LivingEntity owner) {
        super(TerrariaStuff.FireSparkEntityType, owner, world);
    }

    public FireEntity(World world, double x, double y, double z) {
        super(TerrariaStuff.FireSparkEntityType, x, y, z, world);
    }

    private ParticleEffect getParticleParameters() {
        ItemStack itemStack = this.getItem();
        return itemStack.isEmpty() ? ParticleTypes.FLAME : new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack);
    }

    @Override
    public void handleStatus(byte status) {
        if (status == EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES) {
            ParticleEffect particleEffect = this.getParticleParameters();
            for (int i = 0; i < 32; ++i) {
                this.world.addParticle(
                        particleEffect,
                        this.getX(),
                        this.getY(),
                        this.getZ(),
                        this.random.nextGaussian() / 5.0D,
                        this.random.nextGaussian() / 5.0D,
                        this.random.nextGaussian() / 5.0D);
            }
        }
    }

    @Override
    protected Item getDefaultItem() {
        return Items.FIRE_CHARGE;
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return EntitySpawnPacket.create(this, TerrariaStuffClient.PacketID);
    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
    }

    // Called on collision with a block
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.world.isClient) {
            // TODO: Set on fire
            this.world.sendEntityStatus(this, EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES);
            this.kill();
        }
    }
}
