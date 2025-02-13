package net.rswfb.enhancedstonebricks.data;

import net.rswfb.enhancedstonebricks.block.ModBlocks;
import net.rswfb.enhancedstonebricks.client.KeyBindings;
import net.rswfb.enhancedstonebricks.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.rswfb.enhancedstonebricks.item.custom.Crucibles;

public class ModZhCnLanguageProvider extends LanguageProvider {
    public ModZhCnLanguageProvider(PackOutput output, String modid) {
        super(output, modid, "zh_cn");
    }

    @Override
    protected void addTranslations() {
        this.add(ModItems.TEST_ITEM.get(), "测试物品");
        this.add(ModItems.AETHER.get(), "以太");
        this.add(ModItems.STEEL_INGOT.get(),"钢锭");
        this.add(ModItems.BADGE.get(),"徽章");
        this.add(ModItems.AETHER_BADGE.get(),"以太徽章");
        this.add(ModItems.ELEMENTAL_CORE.get(), "元素核心");
        this.add(ModItems.CORE_OF_DESERT.get(),"沙漠核心");
        this.add(ModItems.CORE_OF_DESTRUCTION.get(),"毁灭核心");
        this.add(ModItems.CORE_OF_END.get(),"末地核心");
        this.add(ModItems.CORE_OF_NETHER.get(),"下界核心");
        this.add(ModItems.CORE_OF_OCEAN.get(),"海洋核心");
        this.add(ModItems.CORE_OF_RELIC.get(), "遗迹核心");
        this.add(ModItems.CORE_OF_SOUL.get(),"灵魂核心");
        this.add(ModItems.CORE_OF_VITALITY.get(),"生命核心");
        this.add(ModItems.STORM_INGOT.get(),"风暴锭");
        this.add(ModItems.ASTRAL_INGOT.get(),"星辉锭");

        this.add(ModItems.STEEL_SWORD.get(),"钢剑");
        this.add(ModItems.STEEL_PICKAXE.get(),"钢镐");
        this.add(ModItems.STEEL_HELMET.get(),"钢制头盔");
        this.add(ModItems.STEEL_CHESTPLATE.get(),"钢制胸甲");
        this.add(ModItems.STEEL_LEGGINGS.get(),"钢制护腿");
        this.add(ModItems.STEEL_BOOTS.get(),"钢制靴子");

        this.add(ModItems.REQUIEM.get(),"烬灵挽歌");

        this.add(ModBlocks.IronStonebrick.get(),"嵌铁石砖");
        this.add(ModBlocks.CarbonizedStonebrick.get(),"碳化石砖");
        this.add(ModBlocks.SteelStonebrick.get(),"钢化石砖");
        this.add(ModBlocks.SteelBlock.get(),"钢块");
        this.add(ModBlocks.KingStonebrick.get(),"君主石砖");
        this.add(ModBlocks.QueenStonebrick.get(),"王后石砖");
        this.add(ModBlocks.CourtierStonebrick.get(), "臣子石砖");
        this.add(ModBlocks.GoldStonebrick.get(), "镶金石砖");
        this.add(ModBlocks.AetherStonebrick.get(),"以太石砖");

        this.add(ModBlocks.LightningStruckLog.get(),"雷击木");

        this.add(ModBlocks.DesertRelicBlock.get(), "沙漠遗物方块");
        this.add(ModBlocks.EndRelicBlock.get(), "End Relic Block");
        this.add(ModBlocks.NetherRelicBlock.get(), "Nether Relic Block");
        this.add(ModBlocks.OceanRelicBlock.get(), "Ocean Relic Block");
        this.add(ModBlocks.SoulRelicBlock.get(), "Soul Relic Block");
        this.add(ModBlocks.VitalityRelicBlock.get(), "Vitality Relic Block");

        this.add(ModBlocks.StonebrickPortalBlock.get(), "Stonebrick Portal Block");

        this.add(KeyBindings.KEY_ARMOR_EFFECT, "套装效果");

        ModItems.ITEMS_SUPPLIER.stream().filter(obj -> (obj.get() instanceof Crucibles)).forEach(obj -> {
            this.add(obj.get(), "Crucible_with_lid");
        });
    }
}
