package com.voidcallerz.uc;

import com.voidcallerz.uc.registry.UCBlocks;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class UCClientSetup {

    private static final BlockTintSource FOLIAGE_TINT = new BlockTintSource() {
        @Override
        public int color(BlockState state) {
            return FoliageColor.FOLIAGE_DEFAULT;
        }

        @Override
        public int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos) {
            return BiomeColors.getAverageFoliageColor(level, pos);
        }
    };

    public static void registerClientSetup() {
        for (String name : UCBlocks.LEAVES_MATERIALS) {
            for (int tier = 0; tier < 2; tier++) {
                String registryName = (tier == 0 ? "compressed_" : "double_compressed_") + name;
                var block = UCBlocks.ALL_BLOCKS.get(registryName);
                if (block != null) {
                    BlockColorRegistry.register(List.of(FOLIAGE_TINT), block);
                }
            }
        }
    }
}