package net.rswfb.enhancedstonebricks.block.custom;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class VitalityRelicBlock extends Block {
    public VitalityRelicBlock(){
        super(Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops());
    }
}
