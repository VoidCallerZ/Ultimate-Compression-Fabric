package com.voidcallerz.uc;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;

public class UCFallingBlock extends FallingBlock {
    public UCFallingBlock(Properties props) {
        super(props);
    }

    @Override
    protected MapCodec<? extends FallingBlock> codec() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'codec'");
    }

    @Override
    public int getDustColor(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDustColor'");
    }
}
