package lilylicious.mysteriousmiscellany.gameObjs.tiles;

import lilylicious.mysteriousmiscellany.config.MMConfig;
import lilylicious.mysteriousmiscellany.gameObjs.blocks.IceSpreader;
import lilylicious.mysteriousmiscellany.utils.Predicates;
import lilylicious.mysteriousmiscellany.utils.WorldHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class TileIceSpreader extends TileEntity implements ITickable {

    @Override
    public void update() {

        if (getWorld().isRemote)
            return;

        int radius = MMConfig.iceSpreaderRadius;
        Iterable<BlockPos> iterable = WorldHelper.findBox(this.getPos(), radius);

        for (BlockPos blockPos : iterable) {
            IBlockState blockState = this.getWorld().getBlockState(blockPos);

            IBlockState targetState = Blocks.AIR.getDefaultState();

            if (this.getPos().getX() - blockPos.getX() == -radius || this.getPos().getX() - blockPos.getX() == radius
                    || this.getPos().getZ() - blockPos.getZ() == -radius || this.getPos().getZ() - blockPos.getZ() == radius
                    || this.getPos().getY() - blockPos.getY() == -radius || this.getPos().getY() - blockPos.getY() == radius)
                targetState = Blocks.ICE.getDefaultState();

            if (blockPos != getPos() && Predicates.IS_WATER.test(blockState.getBlock()) || Predicates.ICE_NOT_ICE.test(blockState.getBlock(), targetState.getBlock()))
                getWorld().setBlockState(blockPos, targetState);
        }
    }

}
