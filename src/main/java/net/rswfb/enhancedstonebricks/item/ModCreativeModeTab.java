package net.rswfb.enhancedstonebricks.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.rswfb.enhancedstonebricks.EnhancedStonebricks;

import java.util.function.Supplier;

public class ModCreativeModeTab {
	public static final String TUTORIAL_TAB_STRING = "EnhancedStonebricks";
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
			DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EnhancedStonebricks.MODID);
	public static final Supplier<CreativeModeTab> TUTORIAL_TAB = CREATIVE_MODE_TABS.register("tutorial_tab",
		()-> CreativeModeTab.builder().icon(()->new ItemStack(ModItems.RUBY.get()))
			.title(Component.translatable(TUTORIAL_TAB_STRING))
			.displayItems((pParameters, pOutput) -> {
				// items
				pOutput.accept(ModItems.RUBY.get());
				// blocks
				pOutput.accept(ModItems.IRON_STONEBRICK.get());
				pOutput.accept(ModItems.CARBONIZED_STONEBRICK.get());
			}).build());

	public static void register(IEventBus eventBus) { CREATIVE_MODE_TABS.register(eventBus); }
}
