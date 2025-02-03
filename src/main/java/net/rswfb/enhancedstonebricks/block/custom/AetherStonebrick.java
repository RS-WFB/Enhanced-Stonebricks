package net.rswfb.enhancedstonebricks.block.custom;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;

public class AetherStonebrick extends Block {
    public AetherStonebrick() {
        super(Properties.ofFullCopy(Blocks.STONE_BRICKS).requiresCorrectToolForDrops().lightLevel((level) -> 12));
    }
}