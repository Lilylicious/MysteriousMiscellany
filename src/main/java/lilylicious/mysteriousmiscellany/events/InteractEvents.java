package lilylicious.mysteriousmiscellany.events;

import lilylicious.mysteriousmiscellany.gameObjs.recipes.InWorldDyeRecipe;
import lilylicious.mysteriousmiscellany.registry.DyeRecipeRegistry;
import lilylicious.mysteriousmiscellany.utils.MMLogger;
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
        IBlockState sourceBlockState = e.getWorld().getBlockState(e.getPos());
        ItemStack interactStack = e.getEntityPlayer().getHeldItem(EnumHand.MAIN_HAND);

        for(InWorldDyeRecipe recipe : DyeRecipeRegistry.inWorldDyeRecipes){
            if(e.getWorld().isRemote || e.getWorld().getBlockState(e.getPos()) == recipe.getResultState())
                continue;

            if(recipe.getInteractItem().isItemEqual(interactStack) && recipe.getSourceBlockStates().contains(sourceBlockState)){
                interactStack.splitStack(1);
                e.getWorld().setBlockState(e.getPos(), recipe.getResultState());
            }
        }
    }
}
