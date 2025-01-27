package superlord.prehistoricfauna.compat;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FenceBlock;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

public class HedgeBlock extends FenceBlock {
	public static final ITag<Block> HEDGES = BlockTags.createOptional(new ResourceLocation("quark", "hedges"));
	private static final BooleanProperty EXTEND = BooleanProperty.create("extend");

	public HedgeBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.getDefaultState().with(EXTEND, false));
	}
	
	@Override
	public boolean canConnect(BlockState state, boolean isSideSolid, Direction direction) {
		return state.getBlock().isIn(HEDGES);
	}
	
	@Override
	public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable) {
		return facing == Direction.UP && !state.get(WATERLOGGED) && plantable.getPlantType(world, pos) == PlantType.PLAINS;
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return super.getStateForPlacement(context).with(EXTEND, context.getWorld().getBlockState(context.getPos().down()).getBlock().isIn(HEDGES));
	}

	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (stateIn.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
		}

		return facing == Direction.DOWN ? stateIn.with(EXTEND, facingState.getBlock().isIn(HEDGES)) : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}
	
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(EXTEND);
	}
}