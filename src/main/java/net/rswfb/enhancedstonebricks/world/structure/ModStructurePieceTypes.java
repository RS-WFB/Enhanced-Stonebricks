package net.rswfb.enhancedstonebricks.world.structure;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rswfb.enhancedstonebricks.ExampleMod;
import net.rswfb.enhancedstonebricks.world.structure.piece.ModDesertRelicStructurePieces;

import java.util.Locale;

public class ModStructurePieceTypes {

    public static final DeferredRegister<StructurePieceType> STRUCTURE_PIECE_TYPES = DeferredRegister.create(Registries.STRUCTURE_PIECE, ExampleMod.MODID);
    public static final DeferredHolder<StructurePieceType, StructurePieceType> MOD_DESERT_RELIC_STRUCTURE_PIECE  = registerPieceType("mod_desert_relic_structure_piece", ModDesertRelicStructurePieces.ModDesertRelicStructurePiece::new);
    public static final DeferredHolder<StructurePieceType, StructurePieceType> MOD_END_RELIC_STRUCTURE_PIECE  = registerPieceType("mod_end_relic_structure_piece", ModDesertRelicStructurePieces.ModDesertRelicStructurePiece::new);
    public static final DeferredHolder<StructurePieceType, StructurePieceType> MOD_NETHER_RELIC_STRUCTURE_PIECE  = registerPieceType("mod_nether_relic_structure_piece", ModDesertRelicStructurePieces.ModDesertRelicStructurePiece::new);
    public static final DeferredHolder<StructurePieceType, StructurePieceType> MOD_OCEAN_RELIC_STRUCTURE_PIECE  = registerPieceType("mod_ocean_relic_structure_piece", ModDesertRelicStructurePieces.ModDesertRelicStructurePiece::new);
    public static final DeferredHolder<StructurePieceType, StructurePieceType> MOD_SOUL_RELIC_STRUCTURE_PIECE  = registerPieceType("mod_soul_relic_structure_piece", ModDesertRelicStructurePieces.ModDesertRelicStructurePiece::new);
    public static final DeferredHolder<StructurePieceType, StructurePieceType> MOD_VITALITY_RELIC_STRUCTURE_PIECE  = registerPieceType("mod_vitality_relic_structure_piece", ModDesertRelicStructurePieces.ModDesertRelicStructurePiece::new);
    private static DeferredHolder<StructurePieceType, StructurePieceType> registerPieceType(String name, StructurePieceType.StructureTemplateType structurePieceType) {
        return STRUCTURE_PIECE_TYPES.register(name.toLowerCase(Locale.ROOT), () -> structurePieceType);
    }

    public static void register(IEventBus eventBus){
        STRUCTURE_PIECE_TYPES.register(eventBus);
    }
}
