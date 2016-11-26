package lilylicious.mysteriousmiscellany.events;

import lilylicious.mysteriousmiscellany.gameObjs.ObjHandler;
import lilylicious.mysteriousmiscellany.utils.WorldHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class BlockEvents {

    @SubscribeEvent
    public void blockBroken(BlockEvent.BreakEvent evt) {

        if (evt != null) {
            if (evt.getState().getBlock() == Blocks.ENCHANTING_TABLE) {

                List<BlockPos> blockList = new ArrayList<>();

                blockList = WorldHelper.findEligibleBlocksEnchanting(evt.getPos());

                for (BlockPos pos : blockList) {
                    WorldHelper.replaceBlock(evt.getPlayer(), pos, ObjHandler.enchantmentAir, Blocks.AIR.getDefaultState());
                }
            }
        }
    }
}
