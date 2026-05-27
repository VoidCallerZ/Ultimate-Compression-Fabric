package com.voidcallerz.uc;

import com.voidcallerz.uc.registry.UCBlocks;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer; // Updated import
import net.minecraft.world.level.FoliageColor;

public class UCClientSetup {

    public static void registerClientSetup() {
        for (String name : UCBlocks.LEAVES_MATERIALS) {
            for (int tier = 0; tier < 2; tier++) {
                String registryName = (tier == 0 ? "compressed_" : "double_compressed_") + name;
                var block = UCBlocks.ALL_BLOCKS.get(registryName);
                if (block != null) {
                    // Fixed: Changed from RenderType to ChunkSectionLayer
                    BlockRenderLayerMap.putBlock(block, ChunkSectionLayer.CUTOUT);

                    // Register biome foliage tint for placed blocks
                    ColorProviderRegistry.BLOCK.register(
                        (state, level, pos, tintIndex) ->
                            level != null && pos != null
                                ? BiomeColors.getAverageFoliageColor(level, pos)
                                : FoliageColor.FOLIAGE_DEFAULT,
                        block
                    );
                }
            }
        }
    }
}