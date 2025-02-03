package net.rswfb.enhancedstonebricks.world.structure.piece;

import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePieceAccessor;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.rswfb.enhancedstonebricks.ExampleMod;
import net.rswfb.enhancedstonebricks.world.structure.*;

import java.util.Map;

public class ModDesertRelicStructurePieces {
    static final ResourceLocation STRUCTURE_LOCATION_MOD_DESERT_RELIC_STRUCTURE = new ResourceLocation(ExampleMod.MODID,"mod_desert_relic_structure");
    static final Map<ResourceLocation, BlockPos> PIVOTS = ImmutableMap.of(
            STRUCTURE_LOCATION_MOD_DESERT_RELIC_STRUCTURE, new BlockPos(4, 0, 4)
    );
    static final Map<ResourceLocation, BlockPos> OFFSETS = ImmutableMap.of(
            STRUCTURE_LOCATION_MOD_DESERT_RELIC_STRUCTURE, BlockPos.ZERO
    );

    public static void addPieces(
            StructureTemplateManager pStructureTemplateManager, BlockPos pStartPos, Rotation pRotation, StructurePieceAccessor pPieces, RandomSource pRandom
    ) {
        pPieces.addPiece(new ModDesertRelicStructurePieces.ModDesertRelicStructurePiece(pStructureTemplateManager, STRUCTURE_LOCATION_MOD_DESERT_RELIC_STRUCTURE, pStartPos, pRotation, 0));
    }

    public static class ModDesertRelicStructurePiece extends TemplateStructurePiece {

        public ModDesertRelicStructurePiece(StructureTemplateManager pStructureTemplateManager, ResourceLocation pLocation, BlockPos pStartPos, Rotation pRotation, int pDown) {
            super(ModStructurePieceTypes.MOD_DESERT_RELIC_STRUCTURE_PIECE.get(), 0, pStructureTemplateManager, pLocation, pLocation.toString(),
                    makeSettings(pRotation, pLocation),
                    makePosition(pLocation, pStartPos, pDown)
            );
        }


        public ModDesertRelicStructurePiece(StructureTemplateManager pStructureTemplateManager, CompoundTag pTag) {
            super(ModStructurePieceTypes.MOD_DESERT_RELIC_STRUCTURE_PIECE.get(), pTag, pStructureTemplateManager,   resourceLocation -> makeSettings(Rotation.valueOf(pTag.getString("Rot")), resourceLocation));
        }

        private static StructurePlaceSettings makeSettings(Rotation pRotation, ResourceLocation pLocation) {
            return new StructurePlaceSettings()
                    .setRotation(pRotation)
                    .setMirror(Mirror.NONE)
                    .setRotationPivot(ModDesertRelicStructurePieces.PIVOTS.get(pLocation))
                    .addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK);
        }

        private static BlockPos makePosition(ResourceLocation pLocation, BlockPos pPos, int pDown) {
            return pPos.offset(ModDesertRelicStructurePieces.OFFSETS.get(pLocation)).below(pDown);
        }
        @Override
        protected void addAdditionalSaveData(StructurePieceSerializationContext pContext, CompoundTag pTag) {
            super.addAdditionalSaveData(pContext, pTag);
            pTag.putString("Rot", this.placeSettings.getRotation().name());
        }

        @Override
        protected void handleDataMarker(String pName, BlockPos pPos, ServerLevelAccessor pLevel, RandomSource pRandom, BoundingBox pBox) {
            if ("chest".equals(pName)) {
                pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(), 3);
                BlockEntity blockentity = pLevel.getBlockEntity(pPos.below());
                if (blockentity instanceof ChestBlockEntity) {
                    ((ChestBlockEntity)blockentity).setLootTable(BuiltInLootTables.IGLOO_CHEST, pRandom.nextLong());
                }
            }
        }

        @Override
        public void postProcess(
                WorldGenLevel pLevel,
                StructureManager pStructureManager,
                ChunkGenerator pGenerator,
                RandomSource pRandom,
                BoundingBox pBox,
                ChunkPos pChunkPos,
                BlockPos pPos
        ) {
            ResourceLocation resourceLocation = new ResourceLocation(this.templateName);
            StructurePlaceSettings structureplacesettings = makeSettings(this.placeSettings.getRotation(), resourceLocation);
            BlockPos blockpos = ModDesertRelicStructurePieces.OFFSETS.get(resourceLocation);
            BlockPos blockpos1 = this.templatePosition
                .offset(StructureTemplate.calculateRelativePosition(structureplacesettings, new BlockPos(3 - blockpos.getX(), 0, -blockpos.getZ())));
            int i = pLevel.getHeight(Heightmap.Types.WORLD_SURFACE_WG, blockpos1.getX(), blockpos1.getZ());
            BlockPos blockpos2 = this.templatePosition;
            this.templatePosition = this.templatePosition.offset(0, i - 90 - 1, 0);
            super.postProcess(pLevel, pStructureManager, pGenerator, pRandom, pBox, pChunkPos, pPos);
            this.templatePosition = blockpos2;
        }
    }
}