package com.voidcallerz.uc.registry;

import com.voidcallerz.uc.ModConstants;
import net.minecraft.core.Registry;
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

    public static void register() {
        for (Map.Entry<String, Block> entry : UCBlocks.ALL_BLOCKS.entrySet()) {
            String name  = entry.getKey();
            Block  block = entry.getValue();

            ResourceKey<Item> key = ResourceKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath(ModConstants.MOD_ID, name));

            // In 1.21.2, BlockItem.getDescriptionId() uses the item's setId key
            // which gives "item.uc.x" instead of "block.uc.x".
            // overrideDescription() forces it to use the block's translation key.
            BlockItem item = new BlockItem(block,
                new Item.Properties()
                    .setId(key)
                    .overrideDescription(block.getDescriptionId()));
            Registry.register(BuiltInRegistries.ITEM,
                Identifier.fromNamespaceAndPath(ModConstants.MOD_ID, name), item);
            ALL_ITEMS.put(name, item);
        }
    }
}