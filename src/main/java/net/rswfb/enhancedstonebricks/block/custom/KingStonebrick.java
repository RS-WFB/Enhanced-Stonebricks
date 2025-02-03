package net.rswfb.enhancedstonebricks.block.custom;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class KingStonebrick extends Block {
	public KingStonebrick() {
		super(Properties.ofFullCopy(Blocks.STONE_BRICKS).requiresCorrectToolForDrops().strength(2.5F).lightLevel((level) -> 8));
	}
}
