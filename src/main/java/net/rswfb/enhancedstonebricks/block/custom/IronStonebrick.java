package net.rswfb.enhancedstonebricks.block.custom;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class IronStonebrick extends Block {
	public IronStonebrick() {
		super(Properties.ofFullCopy(Blocks.STONE).requiresCorrectToolForDrops().strength(1.5F, 6F));
	}
}
