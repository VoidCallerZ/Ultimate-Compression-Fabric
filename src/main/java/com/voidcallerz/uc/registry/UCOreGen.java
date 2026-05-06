package com.voidcallerz.uc.registry;

import com.voidcallerz.uc.ModConstants;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

/**
 * Fabric ore generation — uses BiomeModifications API instead of JSON files.
 * Placed feature JSONs still needed in resources; this just injects them into biomes.
 */
public class UCOreGen {

    private static final String[] OVERWORLD_ORES = {
        "compressed_coal_ore",
        "compressed_iron_ore",
        "compressed_gold_ore",
        "compressed_copper_ore",
        "compressed_diamond_ore",
        "compressed_emerald_ore",
        "compressed_lapis_ore",
        "compressed_redstone_ore",
    };

    private static final String[] NETHER_ORES = {
        "compressed_nether_quartz_ore",
        "compressed_nether_gold_ore",
    };

    public static void register() {
        for (String name : OVERWORLD_ORES) {
            ResourceKey<PlacedFeature> key = ResourceKey.create(
                Registries.PLACED_FEATURE,
                ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, name));
            BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.IS_OVERWORLD),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                key);
        }
        for (String name : NETHER_ORES) {
            ResourceKey<PlacedFeature> key = ResourceKey.create(
                Registries.PLACED_FEATURE,
                ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, name));
            BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.IS_NETHER),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                key);
        }
    }
}