package superlord.prehistoricfauna.compat;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class PFBookshelfBlock extends Block {
	
	public PFBookshelfBlock(Properties properties) {
		super(properties);
	}

	@Override
	public float getEnchantPowerBonus(BlockState state, IWorldReader world, BlockPos pos) {
		return 1.0F;
	}
	
}
