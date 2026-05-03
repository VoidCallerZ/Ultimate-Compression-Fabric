package com.voidcallerz.uc.registry;

import com.voidcallerz.uc.ModConstants;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
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
            BlockItem item = new BlockItem(block, new Item.Properties());
            Registry.register(BuiltInRegistries.ITEM,
                ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, name), item);
            ALL_ITEMS.put(name, item);
        }
    }
}