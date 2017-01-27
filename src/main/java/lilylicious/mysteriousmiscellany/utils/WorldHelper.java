package lilylicious.mysteriousmiscellany.utils;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.ArrayList;
import java.util.List;

public class WorldHelper {

    public static Iterable<BlockPos> findBox(BlockPos pos, int radius) {
        return BlockPos.getAllInBox(pos.add(-radius, -radius, -radius), pos.add(radius, radius, radius));
    }

    public static boolean replaceBlock(EntityPlayer player, World world, BlockPos pos, Block oldBlock, IBlockState newBlockState) {

        IBlockState blockState = world.getBlockState(pos);
        if (!world.isRemote && (oldBlock == null || blockState.getBlock() == oldBlock) &&
                !FMLCommonHandler.instance().getMinecraftServerInstance().isBlockProtected(world, pos, player)) {

            BlockSnapshot before = BlockSnapshot.getBlockSnapshot(world, pos);
            world.setBlockState(pos, newBlockState);
            BlockEvent.PlaceEvent evt = new BlockEvent.PlaceEvent(before, Blocks.AIR.getDefaultState(), player, EnumHand.MAIN_HAND);
            MinecraftForge.EVENT_BUS.post(evt);
            if (evt.isCanceled()) {
                world.restoringBlockSnapshots = true;
                before.restore(true, false);
                world.restoringBlockSnapshots = false;
                return false;
            }

            return true;
        }
        return false;
    }

    public static boolean replaceBlock(World world, BlockPos pos, Block oldBlock, IBlockState newBlockState) {

        IBlockState blockState = world.getBlockState(pos);
        if (!world.isRemote && (oldBlock == null || blockState.getBlock() == oldBlock)) {
            world.setBlockState(pos, newBlockState);
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
