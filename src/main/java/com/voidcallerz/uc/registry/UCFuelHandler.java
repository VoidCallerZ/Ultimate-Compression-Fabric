package com.voidcallerz.uc.registry;

import net.fabricmc.fabric.api.registry.FuelRegistryEvents;

/**
 * Registers fuel burn times for compressed fuel items.
 * Fabric 1.21.2+ uses FuelRegistryEvents instead of FuelRegistry.INSTANCE.
 */
public class UCFuelHandler {

    public static void register() {
        FuelRegistryEvents.BUILD.register((builder, context) -> {
            builder.add(UCItemRegistry.ALL_ITEMS.get("compressed_coal"),      14400);
            builder.add(UCItemRegistry.ALL_ITEMS.get("compressed_blaze_rod"), 11200);
        });
    }
}