package com.voidcallerz.uc.registry;

import com.voidcallerz.uc.ModConstants;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import java.util.LinkedHashMap;
import java.util.Map;

public class UCOres {

    public static final Map<String, Block> ALL_ORE_BLOCKS = new LinkedHashMap<>();

    private static final Object[][] ORES = {
        { "compressed_coal_ore",         MapColor.DEEPSLATE, 0,  2 },
        { "compressed_iron_ore",         MapColor.DEEPSLATE, 0,  0 },
        { "compressed_gold_ore",         MapColor.DEEPSLATE, 0,  0 },
        { "compressed_copper_ore",       MapColor.DEEPSLATE, 0,  0 },
        { "compressed_diamond_ore",      MapColor.DEEPSLATE, 9, 21 },
        { "compressed_emerald_ore",      MapColor.DEEPSLATE, 9, 21 },
        { "compressed_lapis_ore",        MapColor.DEEPSLATE, 6, 15 },
        { "compressed_redstone_ore",     MapColor.DEEPSLATE, 3, 15 },
        { "compressed_nether_quartz_ore",MapColor.NETHER,    2,  5 },
        { "compressed_nether_gold_ore",  MapColor.NETHER,    0,  0 },
    };

    public static void register() {
        for (Object[] ore : ORES) {
            String   name  = (String)   ore[0];
            MapColor color = (MapColor) ore[1];
            int      xpMin = (int)      ore[2];
            int      xpMax = (int)      ore[3];

            boolean isNether = name.contains("nether");
            BlockBehaviour.Properties props = isNether
                ? BlockBehaviour.Properties.of()
                    .mapColor(color).sound(SoundType.NETHERRACK)
                    .strength(3.0f, 3.0f)
                : BlockBehaviour.Properties.of()
                    .mapColor(color).sound(SoundType.DEEPSLATE)
                    .strength(5.0f, 3.0f).requiresCorrectToolForDrops();

            Block block = (xpMin == 0 && xpMax == 0)
                ? new Block(props)
                : new DropExperienceBlock(UniformInt.of(xpMin, xpMax), props);

            Registry.register(BuiltInRegistries.BLOCK,
                ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, name), block);
            ALL_ORE_BLOCKS.put(name, block);

            // BlockItem
            BlockItem item = new BlockItem(block, new Item.Properties());
            Registry.register(BuiltInRegistries.ITEM,
                ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, name), item);
        }
    }
}