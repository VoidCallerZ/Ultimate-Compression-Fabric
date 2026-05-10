package com.voidcallerz.uc;

import com.voidcallerz.uc.registry.*;
import com.mojang.logging.LogUtils;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;

public class UltimateCompression implements ModInitializer {

    private static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public void onInitialize() {
        // ORDER MATTERS in Fabric — each step depends on the previous ones
        // 1. Blocks first — items reference blocks
        UCBlocks.register();
        // 2. Block items — references blocks
        UCItems.register();
        // 3. Standalone items — no dependencies
        UCItemRegistry.register();
        // 4. Ore blocks and their items
        UCOres.register();
        // 5. Tools and armor — references armor materials and items
        UCEquipment.register();
        // 6. Creative tab — references all of the above
        UCCreativeTabs.register();
        // 7. Fuel handler — references items
        UCFuelHandler.register();
        // 8. Ore generation — injects placed features into overworld biomes
        UCOreGen.register();
        // 9. Armor effects — registers tick event callback
        UCArmorEffects.register();

        LOGGER.info("{} is loading — {} tiers, auto-registration active.",
            ModConstants.MOD_NAME, ModConstants.TIER_COUNT);
    }
}