package lilylicious.mysteriousmiscellany.gameObjs.tiles;

import lilylicious.mysteriousmiscellany.utils.WorldHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class TileEnchantmentAir extends TileEntity implements ITickable {

    @Override
    public void update() {
        EntityPlayer player = this.getWorld().getClosestPlayer(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 32, false);
        Iterable<BlockPos> iterable = WorldHelper.findBox(this.getPos(), 5);

        if (player != null && player.getDistance(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ()) < 10) {
            for (BlockPos pos : iterable) {
                if (this.getWorld().getBlockState(pos).getBlock() == Blocks.ENCHANTING_TABLE) {
                    return;
                }
            }
        }
        WorldHelper.replaceBlock(player, this.getWorld(), this.getPos(), this.getBlockType(), Blocks.AIR.getDefaultState());
    }
}
