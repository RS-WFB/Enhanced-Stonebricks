package net.rswfb.enhancedstonebricks;

import com.mojang.logging.LogUtils;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;
import net.rswfb.enhancedstonebricks.block.ModBlocks;
import net.rswfb.enhancedstonebricks.entity.ModEntityTypes;
import net.rswfb.enhancedstonebricks.item.ModCreativeModeTab;
import net.rswfb.enhancedstonebricks.item.ModItems;
import net.rswfb.enhancedstonebricks.network.NetworkHandler;
import net.rswfb.enhancedstonebricks.network.handler.ClientPacketHandler;
import net.rswfb.enhancedstonebricks.network.packet.SoulFireSyncPacket;
import net.rswfb.enhancedstonebricks.world.structure.ModStructurePieceTypes;
import net.rswfb.enhancedstonebricks.world.structure.ModStructureType;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(EnhancedStonebricks.MODID)
public class EnhancedStonebricks
{
	public static final String MODID = "enhancedstonebricks";
	public static final Logger LOGGER = LogUtils.getLogger();

	// gty
	// gty
	public EnhancedStonebricks(IEventBus modEventBus)
	{
		modEventBus.addListener(this::commonSetup);
		// Items
		ModItems.register(modEventBus);
		// Blocks
		ModBlocks.register(modEventBus);
		// Item group
		ModCreativeModeTab.register(modEventBus);
		// World
		ModStructureType.register(modEventBus);
		ModStructurePieceTypes.register(modEventBus);

		ModEntityTypes.register(modEventBus);

		NeoForge.EVENT_BUS.register(this);

	}
	private void commonSetup(final FMLCommonSetupEvent event)
	{
	}

	@SubscribeEvent
	public void onServerStarting(ServerStartingEvent event)
	{
	}
	@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientModEvents
	{
		@SubscribeEvent
		public static void onClientSetup(FMLClientSetupEvent event)
		{
		}

		// 监听箭矢移动事件（生成粒子）
	}
}
