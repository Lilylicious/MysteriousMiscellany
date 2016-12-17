package lilylicious.mysteriousmiscellany.gameObjs.tiles;

import lilylicious.mysteriousmiscellany.config.MMConfig;
import lilylicious.mysteriousmiscellany.utils.WorldHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

import java.util.UUID;

public class TileIceSpreader extends TileEntity implements ITickable {

    private UUID owner;
    private int radius = 16;

    public TileIceSpreader(UUID owner){
        this.owner = owner;
    }

    @Override
    public void update() {

        if(owner == null)
            owner = this.getWorld().getClosestPlayer(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 32, false).getUniqueID();

        if(owner != null){
            Iterable<BlockPos> iterable = WorldHelper.findBox(this.getPos(), radius);

            for (BlockPos blockPos : iterable) {
                IBlockState blockState = this.getWorld().getBlockState(blockPos);

                IBlockState targetState = Blocks.AIR.getDefaultState();

                //if((blockPos.getX() == -radius+1 || blockPos.getX() == radius-1) || (blockPos.getZ() == -radius+1 || blockPos.getZ() == radius-1) || (blockPos.getY() == -radius+1 || blockPos.getY() == radius-1))
                  if(this.getPos().getX() - blockPos.getX() == -radius || this.getPos().getX() - blockPos.getX() == radius
                          || this.getPos().getZ() - blockPos.getZ() == -radius || this.getPos().getZ() - blockPos.getZ() == radius
                          || this.getPos().getY() - blockPos.getY() == -radius || this.getPos().getY() - blockPos.getY() == radius)
                    targetState = Blocks.ICE.getDefaultState();

                if (blockState.getBlock() == Blocks.WATER || blockState.getBlock() == Blocks.FLOWING_WATER || (blockState.getBlock() == Blocks.ICE && targetState.getBlock() != Blocks.ICE)){
                    WorldHelper.replaceBlock(this.getWorld().getPlayerEntityByUUID(owner), this.getWorld(), blockPos, null, targetState);
                }

            }
        }

    }

}
