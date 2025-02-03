package net.rswfb.enhancedstonebricks.block.custom;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class NetherRelicBlock extends Block {
    public NetherRelicBlock(){
        super(Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops());
    }
}
