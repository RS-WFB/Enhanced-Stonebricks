package net.rswfb.enhancedstonebricks.block.custom;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class CarbonizedStonebrick extends Block {
	public CarbonizedStonebrick() {
		super(Properties.ofFullCopy(Blocks.STONE_BRICKS).requiresCorrectToolForDrops());
	}
}
