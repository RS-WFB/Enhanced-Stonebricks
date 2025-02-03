package net.rswfb.enhancedstonebricks.block.custom;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;

public class DesertRelicBlock extends Block {
    public DesertRelicBlock() {
        super(Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops());
    }
}
