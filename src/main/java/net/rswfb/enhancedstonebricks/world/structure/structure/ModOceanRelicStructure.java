package net.rswfb.enhancedstonebricks.world.structure.structure;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.rswfb.enhancedstonebricks.world.structure.ModStructureType;
import net.rswfb.enhancedstonebricks.world.structure.piece.ModOceanRelicStructurePieces;

import java.util.Optional;

public class ModOceanRelicStructure extends Structure {

    public static final Codec<ModOceanRelicStructure> CODEC = simpleCodec(ModOceanRelicStructure::new);

    public ModOceanRelicStructure(StructureSettings pSettings) {
        super(pSettings);
    }

    @Override
    protected Optional<GenerationStub> findGenerationPoint(GenerationContext pContext) {
        return onTopOfChunkCenter(pContext, Heightmap.Types.WORLD_SURFACE_WG, structurePiecesBuilder -> this.generatePieces(structurePiecesBuilder, pContext));
    }

    private void generatePieces(StructurePiecesBuilder pBuilder, Structure.GenerationContext pContext) {
        ChunkPos chunkpos = pContext.chunkPos();
        WorldgenRandom worldgenrandom = pContext.random();
        BlockPos blockpos = new BlockPos(chunkpos.getMinBlockX(), 90, chunkpos.getMinBlockZ());
        Rotation rotation = Rotation.getRandom(worldgenrandom);
        ModOceanRelicStructurePieces.addPieces(pContext.structureTemplateManager(), blockpos, rotation, pBuilder, worldgenrandom);
    }

    @Override
    public StructureType<?> type() {
        return ModStructureType.MOD_OCEAN_RELIC_STRUCTURE.get();
    }
}
