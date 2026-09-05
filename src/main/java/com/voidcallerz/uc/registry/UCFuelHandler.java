package com.voidcallerz.uc.registry;

import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Registers fuel burn times for compressed fuel items and blocks.
 * Fabric 1.21.2+ uses FuelRegistryEvents instead of FuelRegistry.INSTANCE.
 *
 * Burn times are scaled from the vanilla counterpart:
 * compressed = 9x, double compressed = 81x.
 *
 * Mirrors the block/item set used by the NeoForge UCFuelHandler.
 */
public class UCFuelHandler {

    // Item fuels: registry name -> burn time in ticks (already scaled 9x).
    private static final Map<String, Integer> FUEL_ITEMS = new LinkedHashMap<>();

    // Block fuels: material name -> VANILLA burn time.
    // Both tiers are derived from this, so list the vanilla value only once.
    private static final Map<String, Integer> FUEL_BLOCKS = new LinkedHashMap<>();

    static {
        // --- Items (vanilla coal 1600, blaze rod ~2400, stick 100) ---
        FUEL_ITEMS.put("compressed_coal",      14400);
        FUEL_ITEMS.put("compressed_blaze_rod", 11200);
        FUEL_ITEMS.put("compressed_stick",       900);

        // --- Logs (vanilla 300) ---
        FUEL_BLOCKS.put("oak_log",         300);
        FUEL_BLOCKS.put("spruce_log",      300);
        FUEL_BLOCKS.put("birch_log",       300);
        FUEL_BLOCKS.put("jungle_log",      300);
        FUEL_BLOCKS.put("acacia_log",      300);
        FUEL_BLOCKS.put("dark_oak_log",    300);
        FUEL_BLOCKS.put("mangrove_log",    300);
        FUEL_BLOCKS.put("cherry_log",      300);
        FUEL_BLOCKS.put("bamboo_block",    300);

        // --- Planks (vanilla 300) ---
        FUEL_BLOCKS.put("oak_planks",      300);
        FUEL_BLOCKS.put("spruce_planks",   300);
        FUEL_BLOCKS.put("birch_planks",    300);
        FUEL_BLOCKS.put("jungle_planks",   300);
        FUEL_BLOCKS.put("acacia_planks",   300);
        FUEL_BLOCKS.put("dark_oak_planks", 300);
        FUEL_BLOCKS.put("mangrove_planks", 300);
        FUEL_BLOCKS.put("cherry_planks",   300);
        FUEL_BLOCKS.put("crimson_planks",  300);
        FUEL_BLOCKS.put("warped_planks",   300);

        // --- Coal block (vanilla 16000) ---
        FUEL_BLOCKS.put("coal_block",    16000);

        // --- Wool (vanilla 100) ---
        FUEL_BLOCKS.put("white_wool",      100);
        FUEL_BLOCKS.put("orange_wool",     100);
        FUEL_BLOCKS.put("magenta_wool",    100);
        FUEL_BLOCKS.put("light_blue_wool", 100);
        FUEL_BLOCKS.put("yellow_wool",     100);
        FUEL_BLOCKS.put("lime_wool",       100);
        FUEL_BLOCKS.put("pink_wool",       100);
        FUEL_BLOCKS.put("gray_wool",       100);
        FUEL_BLOCKS.put("light_gray_wool", 100);
        FUEL_BLOCKS.put("cyan_wool",       100);
        FUEL_BLOCKS.put("purple_wool",     100);
        FUEL_BLOCKS.put("blue_wool",       100);
        FUEL_BLOCKS.put("brown_wool",      100);
        FUEL_BLOCKS.put("green_wool",      100);
        FUEL_BLOCKS.put("red_wool",        100);
        FUEL_BLOCKS.put("black_wool",      100);
    }

    public static void register() {
        FuelRegistryEvents.BUILD.register((builder, context) -> {
            // Items
            for (Map.Entry<String, Integer> entry : FUEL_ITEMS.entrySet()) {
                Item item = UCItemRegistry.ALL_ITEMS.get(entry.getKey());
                if (item != null) {
                    builder.add(item, entry.getValue());
                }
            }

            // Blocks — compressed 9x, double compressed 81x
            for (Map.Entry<String, Integer> entry : FUEL_BLOCKS.entrySet()) {
                String material    = entry.getKey();
                int    vanillaBurn = entry.getValue();

                Block compressed = UCBlocks.ALL_BLOCKS.get("compressed_" + material);
                if (compressed != null) {
                    builder.add(compressed, vanillaBurn * 9);
                }

                Block doubleCompressed = UCBlocks.ALL_BLOCKS.get("double_compressed_" + material);
                if (doubleCompressed != null) {
                    builder.add(doubleCompressed, vanillaBurn * 81);
                }
            }
        });
    }
}