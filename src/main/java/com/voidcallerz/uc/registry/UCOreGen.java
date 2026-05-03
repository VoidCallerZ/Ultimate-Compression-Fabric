package com.voidcallerz.uc.registry;

import com.voidcallerz.uc.ModConstants;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.*;

/**
 * Fabric ore generation — uses BiomeModifications API instead of JSON files.
 * Configured and placed features are still registered via DatapackBuiltinEntries
 * but biome injection is done in code.
 */
public class UCOreGen {

    private static final Object[][] ORE_CONFIGS = {
        { "compressed_coal_ore",     3, 2, -64, -32 },
        { "compressed_iron_ore",     3, 2, -64, -32 },
        { "compressed_gold_ore",     2, 1, -64, -32 },
        { "compressed_copper_ore",   3, 2, -64, -16 },
        { "compressed_diamond_ore",  1, 1, -64, -48 },
        { "compressed_emerald_ore",  1, 1, -64, -32 },
        { "compressed_lapis_ore",    2, 1, -64, -32 },
        { "compressed_redstone_ore", 2, 2, -64, -32 },
    };

    public static void register() {
        for (Object[] ore : ORE_CONFIGS) {
            String oreName = (String) ore[0];
            int veinSize   = (int)    ore[1];
            int count      = (int)    ore[2];
            int minY       = (int)    ore[3];
            int maxY       = (int)    ore[4];

            ResourceKey<PlacedFeature> key = ResourceKey.create(
                Registries.PLACED_FEATURE,
                ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, oreName));

            BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.IS_OVERWORLD),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                key
            );
        }
    }
}