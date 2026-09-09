package com.voidcallerz.uc;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;

public class UCFallingBlock extends FallingBlock {
    public UCFallingBlock(Properties props) {
        super(props);
    }

    @Override
    public int getDustColor(BlockState blockState, BlockGetter level, BlockPos pos) {
        throw new UnsupportedOperationException("Unimplemented method 'getDustColor'");
    }
}
