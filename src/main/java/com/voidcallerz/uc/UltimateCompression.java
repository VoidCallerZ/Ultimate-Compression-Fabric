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
        // 5. Armor materials MUST be registered before equipment
        //    ArmorItem holds a Holder<ArmorMaterial> — null if not registered yet
        UCArmorMaterials.register();
        // 6. Tools and armor — references armor materials and items
        UCEquipment.register();
        // 7. Creative tab — references all of the above
        UCCreativeTabs.register();
        // 8. Fuel handler — references items
        UCFuelHandler.register();
        // 9. Ore generation — injects placed features into overworld biomes
        UCOreGen.register();
        // 10. Armor effects — registers tick event callback
        UCArmorEffects.register();

        LOGGER.info("{} is loading — {} tiers, auto-registration active.",
            ModConstants.MOD_NAME, ModConstants.TIER_COUNT);
    }
}