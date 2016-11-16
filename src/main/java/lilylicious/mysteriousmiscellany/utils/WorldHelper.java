package lilylicious.mysteriousmiscellany.utils;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.ArrayList;
import java.util.List;

public class WorldHelper {

    public static Iterable<BlockPos> findBox(EntityPlayer player, int radius) {
        Iterable<BlockPos> iterable = null;

        int x = (int) Math.floor(player.posX);
        int y = (int) (player.posY - player.getYOffset());
        int z = (int) Math.floor(player.posZ);
        BlockPos pos = new BlockPos(x, y, z);

        return pos.getAllInBox(pos.add(-radius, -radius, -radius), pos.add(radius, radius, radius));
    }

    public static boolean replaceBlock(EntityPlayer player, BlockPos pos, Block oldBlock, IBlockState newBlockState) {

        IBlockState blockState = player.worldObj.getBlockState(pos);
        if (blockState.getBlock() == oldBlock &&
                !FMLCommonHandler.instance().getMinecraftServerInstance().isBlockProtected(player.worldObj, pos, player)) {

            BlockSnapshot before = BlockSnapshot.getBlockSnapshot(player.worldObj, pos);
            player.worldObj.setBlockState(pos, newBlockState);
            BlockEvent.PlaceEvent evt = new BlockEvent.PlaceEvent(before, Blocks.AIR.getDefaultState(), player);
            MinecraftForge.EVENT_BUS.post(evt);
            if (evt.isCanceled()) {
                player.worldObj.restoringBlockSnapshots = true;
                before.restore(true, false);
                player.worldObj.restoringBlockSnapshots = false;
                return false;
            }

            return true;
        }
        return false;
    }

    public static List<BlockPos> findEligibleBlocksEnchanting(BlockPos pos) {

        List<BlockPos> blockList = new ArrayList<>();

        for (int j = -1; j <= 1; ++j) {
            for (int k = -1; k <= 1; ++k) {
                if ((j != 0 || k != 0)) {
                    blockList.add(pos.add(k * 2, 0, j * 2));
                    blockList.add(pos.add(k * 2, 1, j * 2));
                    if (k != 0 && j != 0) {
                        blockList.add(pos.add(k * 2, 0, j));
                        blockList.add(pos.add(k * 2, 1, j));
                        blockList.add(pos.add(k, 0, j * 2));
                        blockList.add(pos.add(k, 1, j * 2));
                    }
                }
            }
        }

        return blockList;
    }

}
