package com.voidcallerz.uc.registry;

import com.voidcallerz.uc.ModConstants;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class UCCreativeTabs {

    public static void register() {
        ResourceKey<CreativeModeTab> key = ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(),
            Identifier.fromNamespaceAndPath(ModConstants.MOD_ID, "uc_tab")
        );

        CreativeModeTab tab = CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
            .title(Component.translatable("itemGroup.uc"))
            .icon(() -> UCBlocks.ALL_BLOCKS.values().stream()
                .findFirst()
                .map(b -> new ItemStack(b))
                .orElse(ItemStack.EMPTY))
            .displayItems((params, output) -> {
                UCItems.ALL_ITEMS.values().forEach(output::accept);
                UCItemRegistry.ALL_ITEMS.values().forEach(output::accept);
                output.accept(UCItemRegistry.COMPRESSION_CATALYST);
                UCOres.ALL_ORE_BLOCKS.values().forEach(b -> output.accept(new ItemStack(b)));
                UCEquipment.ALL_EQUIPMENT.values().forEach(output::accept);
            })
            .build();

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, key, tab);
    }
}