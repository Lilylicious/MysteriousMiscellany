package lilylicious.mysteriousmiscellany.integration.JEI;

import lilylicious.mysteriousmiscellany.gameObjs.ObjHandler;
import mezz.jei.api.*;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

@JEIPlugin
public class MMJeiPlugin extends BlankModPlugin {

    @Override
    public void register(@Nonnull IModRegistry registry)
    {
        List<ItemStack> stainedGlass = new ArrayList<>();
        List<ItemStack> stainedGlassPanes = new ArrayList<>();
        List<ItemStack> hardenedClay = new ArrayList<>();
        Blocks.STAINED_GLASS.getSubBlocks(Item.getItemFromBlock(Blocks.STAINED_GLASS), null, stainedGlass);
        Blocks.STAINED_GLASS_PANE.getSubBlocks(Item.getItemFromBlock(Blocks.STAINED_GLASS_PANE), null, stainedGlassPanes);
        Blocks.STAINED_HARDENED_CLAY.getSubBlocks(Item.getItemFromBlock(Blocks.STAINED_HARDENED_CLAY), null, hardenedClay);

        registry.addDescription(stainedGlass, "jei.dyerecipes");
        registry.addDescription(stainedGlassPanes, "jei.dyerecipes");
        registry.addDescription(hardenedClay, "jei.dyerecipes");

    }

}