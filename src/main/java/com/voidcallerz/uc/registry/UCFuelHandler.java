package com.voidcallerz.uc.registry;

import net.fabricmc.fabric.api.registry.FuelRegistry;

/**
 * Registers fuel burn times for compressed fuel items.
 * For Fabric 1.21.1, use FuelRegistry.INSTANCE (FuelRegistryEvents is 1.21.2+)
 */
public class UCFuelHandler {

    public static void register() {
        FuelRegistry.INSTANCE.add(UCItemRegistry.ALL_ITEMS.get("compressed_coal"),      14400);
        FuelRegistry.INSTANCE.add(UCItemRegistry.ALL_ITEMS.get("compressed_blaze_rod"), 11200);
    }
}