package net.rswfb.enhancedstonebricks.block.custom;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class LightningStruckLog extends ModFlammableRotatedPillarBlock {
	public LightningStruckLog() {
		super(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(5f).requiresCorrectToolForDrops());
	}
}
