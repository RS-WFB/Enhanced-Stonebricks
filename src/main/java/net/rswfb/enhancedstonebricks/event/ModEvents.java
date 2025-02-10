package net.rswfb.enhancedstonebricks.event;

import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.ProjectileImpactEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.rswfb.enhancedstonebricks.EnhancedStonebricks;
import net.rswfb.enhancedstonebricks.item.ModItems;
import net.rswfb.enhancedstonebricks.item.custom.Requiem;
import net.rswfb.enhancedstonebricks.network.packet.SoulFireSyncPacket;
import net.rswfb.enhancedstonebricks.network.packet.SyncPacket;
import net.rswfb.enhancedstonebricks.network.packet.SyncTexturePacket;

import static net.rswfb.enhancedstonebricks.event.utils.Funcs.summonDragonFireball;

@Mod.EventBusSubscriber(modid = EnhancedStonebricks.MODID)
public class ModEvents {
    private static int counter1 = 1;

    @SubscribeEvent
    public static void onArrowFired(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof AbstractArrow arrow) {
            if (arrow.getOwner() instanceof Player player) {
                ItemStack bow = player.getUseItem();
                if (bow.getItem() == ModItems.REQUIEM.get()) {
                    arrow.getPersistentData().putBoolean("soul_fire", true);
                    SyncPacket packet = new SyncPacket(arrow.getId(), "soul_fire" ,true);
                    PacketDistributor.PLAYER.with((ServerPlayer) player).send(packet);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onEntityJoin(TickEvent.LevelTickEvent event) {
        if (Minecraft.getInstance().level != null) {
            for (Entity entity : Minecraft.getInstance().level.entitiesForRendering()) {
                CompoundTag nbt = entity.getPersistentData();
                if (entity.getPersistentData().getBoolean("soul_fire")) {
                    Level level = entity.level();
                    if (entity instanceof AbstractArrow arrow) {
                        if (level.isClientSide) {
                            CompoundTag isg = arrow.getPersistentData().getCompound("inGround");
                            arrow.addAdditionalSaveData(isg);
                            if (!isg.getBoolean("inGround")) {
                                for (int i = 0; i < 2; i++) {
                                    double offset = i * 0.1;
                                    level.addParticle(
                                            ParticleTypes.SOUL_FIRE_FLAME,
                                            arrow.getX() - arrow.getDeltaMovement().x * offset,
                                            arrow.getY() - arrow.getDeltaMovement().y * offset,
                                            arrow.getZ() - arrow.getDeltaMovement().z * offset,
                                            0, 0, 0
                                    );
                                }
                            }
                        }
                    }
                }
            };
        }
    }
    @SubscribeEvent
    public static void onProjectileImpact(ProjectileImpactEvent event) {
        Projectile projectile = event.getProjectile();
        if (projectile instanceof AbstractArrow arrow && event.getRayTraceResult() instanceof EntityHitResult entityHit) {
            Entity target = entityHit.getEntity();
            Level level = arrow.level();
            LivingEntity owner = (LivingEntity) arrow.getOwner();
            ItemStack stack = owner.getMainHandItem();
            if (!level.isClientSide) {
                CompoundTag nbt = stack.getOrCreateTag();
                counter1 = nbt.getInt("counter");
                if (target instanceof LivingEntity livingTarget && arrow.getPersistentData().getBoolean("is_soul_crit")) {
                    if (counter1 % 3 == 2) {

                        nbt.putInt(Requiem.ENERGIZE_STATE_TAG, 1);
                        SyncTexturePacket packet = new SyncTexturePacket(owner.getId(), Requiem.ENERGIZE_STATE_TAG, 1);
                        PacketDistributor.PLAYER.with((ServerPlayer) owner).send(packet);
                        level.playSound(null, owner.getX(), owner.getY(), owner.getZ(),SoundEvents.SOUL_ESCAPE,
                                SoundSource.PLAYERS,
                                4.5F,
                                1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + 0.8F * 0.5F);
                    }
                    if (counter1 % 3 == 0 && counter1 != 0) {
                        summonDragonFireball(level, target, owner);
                        nbt.putInt(Requiem.ENERGIZE_STATE_TAG, 0);
                        SyncTexturePacket packet = new SyncTexturePacket(owner.getId(), Requiem.ENERGIZE_STATE_TAG, 0);
                        PacketDistributor.PLAYER.with((ServerPlayer) owner).send(packet);
                    }
                    counter1 ++;
                    nbt.putInt("counter", counter1);
                    SyncTexturePacket packet2 = new SyncTexturePacket(owner.getId(), "counter", counter1);
                    PacketDistributor.PLAYER.with((ServerPlayer) owner).send(packet2);
                }
            }
        }
    }
    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        ServerPlayer player = (ServerPlayer) event.getEntity();
        ServerLevel level = (ServerLevel) player.level();

        level.getAllEntities().forEach(entity -> {
            if (entity instanceof AbstractArrow arrow &&
                    arrow.getPersistentData().getBoolean("soul_fire")) {
                SoulFireSyncPacket packet = new SoulFireSyncPacket(arrow.getId(), true);
                PacketDistributor.PLAYER.with(player).send(packet);
            }
        });
    }
}
