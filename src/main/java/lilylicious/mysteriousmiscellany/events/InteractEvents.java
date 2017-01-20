package lilylicious.mysteriousmiscellany.events;

import lilylicious.mysteriousmiscellany.utils.Predicates;
import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class InteractEvents {

    @SubscribeEvent
    public void onInteractEvent(RightClickBlock e) {
        Block block = e.getWorld().getBlockState(e.getPos()).getBlock();
        ItemStack dyeStack = e.getEntityPlayer().getHeldItem(EnumHand.MAIN_HAND);
        IBlockState result;

        boolean stainedGlass = false;
        boolean stainedGlassPane = false;
        boolean hardenedClay = false;
        boolean carpet = false;
        boolean wool = false;

        if(dyeStack.isEmpty() || block == null)
            return;

        if(!e.getWorld().isRemote && Predicates.IS_DYEABLE.test(block)){
            if(!(dyeStack.getItem() instanceof ItemDye))
                return;

            if (block instanceof BlockStainedGlass || block instanceof BlockGlass) {
                stainedGlass = true;
            }
            else if (block instanceof BlockStainedGlassPane || block instanceof BlockPane) {
                stainedGlassPane = true;
            }
            else if (block instanceof BlockHardenedClay || block == Blocks.STAINED_HARDENED_CLAY) {
                hardenedClay = true;
            }
            else if (block instanceof BlockCarpet) {
                carpet = true;
            }
            else if (block == Blocks.WOOL) {
                wool = true;
            }
            else
                return;

            if(stainedGlass)
                result = Blocks.STAINED_GLASS.getStateFromMeta(15-dyeStack.getMetadata());
            else if(stainedGlassPane)
                result = Blocks.STAINED_GLASS_PANE.getStateFromMeta(15-dyeStack.getMetadata());
            else if(hardenedClay)
                result = Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(15-dyeStack.getMetadata());
            else if(carpet)
                result = Blocks.CARPET.getStateFromMeta(15-dyeStack.getMetadata());
            else if(wool)
                result = Blocks.WOOL.getStateFromMeta(15-dyeStack.getMetadata());
            else
                return;

            if(e.getWorld().getBlockState(e.getPos()) == result)
                return;

            dyeStack.splitStack(1);
            e.getWorld().setBlockState(e.getPos(), result);
        }
    }
}
