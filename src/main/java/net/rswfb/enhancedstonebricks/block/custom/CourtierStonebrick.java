package net.rswfb.enhancedstonebricks.block.custom;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class CourtierStonebrick extends Block {
	public CourtierStonebrick() {
		super(Properties.ofFullCopy(Blocks.STONE_BRICKS).requiresCorrectToolForDrops().strength(2F).lightLevel((level) -> 6));
	}
}
