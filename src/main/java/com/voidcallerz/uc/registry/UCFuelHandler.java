package com.voidcallerz.uc.registry;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.world.level.block.Block;

import java.util.Map;

/**
 * Registers fuel burn times for compressed fuel items and blocks.
 * Compressed = 9x vanilla, double compressed = 81x vanilla.
 */
public class UCFuelHandler {

    private static final Map<String, Integer> FUEL_BLOCKS = Map.ofEntries(
        Map.entry("oak_log",         300),
        Map.entry("spruce_log",      300),
        Map.entry("birch_log",       300),
        Map.entry("jungle_log",      300),
        Map.entry("acacia_log",      300),
        Map.entry("dark_oak_log",    300),
        Map.entry("mangrove_log",    300),
        Map.entry("cherry_log",      300),
        Map.entry("bamboo_block",    300),
        Map.entry("oak_planks",      300),
        Map.entry("spruce_planks",   300),
        Map.entry("birch_planks",    300),
        Map.entry("jungle_planks",   300),
        Map.entry("acacia_planks",   300),
        Map.entry("dark_oak_planks", 300),
        Map.entry("mangrove_planks", 300),
        Map.entry("cherry_planks",   300),
        Map.entry("crimson_planks",  300),
        Map.entry("warped_planks",   300),
        Map.entry("coal_block",      16000),
        Map.entry("white_wool",      100),
        Map.entry("orange_wool",     100),
        Map.entry("magenta_wool",    100),
        Map.entry("light_blue_wool", 100),
        Map.entry("yellow_wool",     100),
        Map.entry("lime_wool",       100),
        Map.entry("pink_wool",       100),
        Map.entry("gray_wool",       100),
        Map.entry("light_gray_wool", 100),
        Map.entry("cyan_wool",       100),
        Map.entry("purple_wool",     100),
        Map.entry("blue_wool",       100),
        Map.entry("brown_wool",      100),
        Map.entry("green_wool",      100),
        Map.entry("red_wool",        100),
        Map.entry("black_wool",      100)
    );

    public static void register() {
        // Item fuels
        FuelRegistry.INSTANCE.add(UCItemRegistry.ALL_ITEMS.get("compressed_coal"),      14400);
        FuelRegistry.INSTANCE.add(UCItemRegistry.ALL_ITEMS.get("compressed_blaze_rod"), 11200);
        FuelRegistry.INSTANCE.add(UCItemRegistry.ALL_ITEMS.get("compressed_stick"),     900);

        // Block fuels — both tiers
        for (Map.Entry<String, Integer> entry : FUEL_BLOCKS.entrySet()) {
            String material = entry.getKey();
            int vanillaBurn = entry.getValue();

            Block compressed = UCBlocks.ALL_BLOCKS.get("compressed_" + material);
            if (compressed != null) {
                FuelRegistry.INSTANCE.add(compressed, vanillaBurn * 9);
            }

            Block doubleCompressed = UCBlocks.ALL_BLOCKS.get("double_compressed_" + material);
            if (doubleCompressed != null) {
                FuelRegistry.INSTANCE.add(doubleCompressed, vanillaBurn * 81);
            }
        }
    }
}