package com.voidcallerz.uc.registry;

import com.voidcallerz.uc.ModConstants;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.LinkedHashMap;
import java.util.Map;

public class UCItems {

    public static final Map<String, Item> ALL_ITEMS = new LinkedHashMap<>();

    /**
     * Burnable block materials -> VANILLA burn time in ticks.
     * Compressed is 9x this value, double compressed 81x, so the vanilla
     * number is listed once and both tiers derive from it.
     */
    private static final Map<String, Integer> FUEL_BLOCKS = new LinkedHashMap<>();

    static {
        // Logs (vanilla 300)
        FUEL_BLOCKS.put("oak_log",         300);
        FUEL_BLOCKS.put("spruce_log",      300);
        FUEL_BLOCKS.put("birch_log",       300);
        FUEL_BLOCKS.put("jungle_log",      300);
        FUEL_BLOCKS.put("acacia_log",      300);
        FUEL_BLOCKS.put("dark_oak_log",    300);
        FUEL_BLOCKS.put("mangrove_log",    300);
        FUEL_BLOCKS.put("cherry_log",      300);
        FUEL_BLOCKS.put("bamboo_block",    300);
        FUEL_BLOCKS.put("poplar_log",      300);

        // Planks (vanilla 300)
        FUEL_BLOCKS.put("oak_planks",      300);
        FUEL_BLOCKS.put("spruce_planks",   300);
        FUEL_BLOCKS.put("birch_planks",    300);
        FUEL_BLOCKS.put("jungle_planks",   300);
        FUEL_BLOCKS.put("acacia_planks",   300);
        FUEL_BLOCKS.put("dark_oak_planks", 300);
        FUEL_BLOCKS.put("mangrove_planks", 300);
        FUEL_BLOCKS.put("cherry_planks",   300);
        FUEL_BLOCKS.put("poplar_planks",   300);

        // Coal block (vanilla 16000)
        FUEL_BLOCKS.put("coal_block",    16000);

        // Wool (vanilla 100)
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

    /**
     * Burn time for a compressed block item, or 0 if it isn't a fuel.
     * Reads the tier off the registry name so the map only lists materials.
     */
    private static int burnTimeFor(String registryName) {
        for (Map.Entry<String, Integer> e : FUEL_BLOCKS.entrySet()) {
            if (registryName.equals("compressed_" + e.getKey())) {
                return e.getValue() * 9;
            }
            if (registryName.equals("double_compressed_" + e.getKey())) {
                return e.getValue() * 81;
            }
        }
        return 0;
    }

    public static void register() {
        for (Map.Entry<String, Block> entry : UCBlocks.ALL_BLOCKS.entrySet()) {
            String name  = entry.getKey();
            Block  block = entry.getValue();

            ResourceKey<Item> key = ResourceKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath(ModConstants.MOD_ID, name));

            // In 1.21.2, BlockItem.getDescriptionId() uses the item's setId key
            // which gives "item.uc.x" instead of "block.uc.x".
            // overrideDescription() forces it to use the block's translation key.
            Item.Properties props = new Item.Properties()
                .setId(key)
                .overrideDescription(block.getDescriptionId());

            // 26.3: fuel is the minecraft:cooking_fuel component, not a registry
            int burnTime = burnTimeFor(name);
            if (burnTime > 0) {
                props = props.component(DataComponents.COOKING_FUEL,
                    UCItemRegistry.fuel(burnTime));
            }

            BlockItem item = new BlockItem(block, props);
            Registry.register(BuiltInRegistries.ITEM,
                Identifier.fromNamespaceAndPath(ModConstants.MOD_ID, name), item);
            ALL_ITEMS.put(name, item);
        }
    }
}