package com.voidcallerz.uc.registry;

import net.fabricmc.fabric.api.registry.FuelValueEvents;

public class UCFuelHandler {

    public static void register() {
        FuelValueEvents.BUILD.register((builder, context) -> {
            builder.add(UCItemRegistry.ALL_ITEMS.get("compressed_coal"),      14400);
            builder.add(UCItemRegistry.ALL_ITEMS.get("compressed_blaze_rod"), 11200);
        });
    }
}