package com.voidcallerz.uc.registry;

import com.voidcallerz.uc.ModConstants;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.LinkedHashMap;
import java.util.Map;

public class UCItemRegistry {

    public static final Map<String, Item> ALL_ITEMS = new LinkedHashMap<>();
    public static Item COMPRESSION_CATALYST;

    private static final Object[][] ITEMS_LIST = {
        { "compressed_raw_iron",        0 },
        { "compressed_raw_gold",        0 },
        { "compressed_raw_copper",      0 },
        { "compressed_iron_ingot",      0 },
        { "compressed_gold_ingot",      0 },
        { "compressed_copper_ingot",    0 },
        { "compressed_netherite_ingot", 0 },
        { "compressed_diamond",         0 },
        { "compressed_emerald",         0 },
        { "compressed_amethyst_shard",  0 },
        { "compressed_quartz",          0 },
        { "compressed_lapis",           0 },
        { "compressed_redstone",        0 },
        { "compressed_flint",           0 },
        { "compressed_stick",           0 },
        { "compressed_leather",         0 },
        { "compressed_bone",            0 },
        { "compressed_string",          0 },
        { "compressed_feather",         0 },
        { "compressed_iron_nugget",     0 },
        { "compressed_gold_nugget",     0 },
        { "compressed_coal",            14400 },
        { "compressed_blaze_rod",       11200 },
        { "compressed_resin_clump",     0 },
    };

    public static void register() {
        // Compression catalyst — plain stacksTo(1) item
        // craftRemainder self-reference causes issues in 1.21.2
        // remainingItems in recipe JSON handles staying in grid instead
        ResourceKey<Item> catalystKey = ResourceKey.create(Registries.ITEM,
            ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, "compression_catalyst"));
        COMPRESSION_CATALYST = new Item(new Item.Properties().stacksTo(1).setId(catalystKey));
        Registry.register(BuiltInRegistries.ITEM,
            ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, "compression_catalyst"),
            COMPRESSION_CATALYST);

        // Standalone compressed items
        for (Object[] entry : ITEMS_LIST) {
            String name = (String) entry[0];
            ResourceKey<Item> key = ResourceKey.create(Registries.ITEM,
                ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, name));
            Item item = new Item(new Item.Properties().setId(key));
            Registry.register(BuiltInRegistries.ITEM,
                ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, name), item);
            ALL_ITEMS.put(name, item);
        }
    }
}