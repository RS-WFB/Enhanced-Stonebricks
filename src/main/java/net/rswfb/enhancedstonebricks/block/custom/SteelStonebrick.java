package net.rswfb.enhancedstonebricks.block.custom;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class SteelStonebrick extends Block {
	public SteelStonebrick() {
		super(Properties.ofFullCopy(Blocks.STONE_BRICKS).requiresCorrectToolForDrops().strength(2F));
	}
}
