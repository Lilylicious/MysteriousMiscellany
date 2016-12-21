package lilylicious.mysteriousmiscellany.gameObjs.recipes;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHardenedClay;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.block.BlockStainedGlassPane;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class DyeRecipes implements IRecipe {

    private ItemStack output;

    @Override
    public boolean matches(@Nonnull InventoryCrafting inv, @Nonnull World world) {
        List<ItemStack> itemsToDye = new ArrayList<>();
        ItemStack dye = null;
        boolean stainedGlass = false;
        boolean stainedGlassPane = false;
        boolean hardenedClay = false;


        for (int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack input = inv.getStackInSlot(i);

            if (input == null) {
                continue;
            } else if (input.getItem() instanceof ItemDye)
                dye = input;
            else if (Block.getBlockFromItem(input.getItem()) instanceof BlockStainedGlass) {
                stainedGlass = true;
                itemsToDye.add(input);
            }
            else if (Block.getBlockFromItem(input.getItem()) instanceof BlockStainedGlassPane) {
                stainedGlassPane = true;
                itemsToDye.add(input);
            }
            else if (Block.getBlockFromItem(input.getItem()) instanceof BlockHardenedClay || Block.getBlockFromItem(input.getItem()) == Blocks.STAINED_HARDENED_CLAY) {
                hardenedClay = true;
                itemsToDye.add(input);
            }
        }

        if (dye == null || itemsToDye.size() == 0) {
            return false;
        }

        if((stainedGlass && stainedGlassPane) || (stainedGlass && hardenedClay) || (stainedGlassPane && hardenedClay))
            return false;

        if(stainedGlass)
            output = new ItemStack(Blocks.STAINED_GLASS, itemsToDye.size(), 15-dye.getMetadata());
        else if(stainedGlassPane)
            output = new ItemStack(Blocks.STAINED_GLASS_PANE, itemsToDye.size(), 15-dye.getMetadata());
        else if(hardenedClay)
            output = new ItemStack(Blocks.STAINED_HARDENED_CLAY, itemsToDye.size(), 15-dye.getMetadata());

        return true;
    }

    @Override
    public ItemStack getCraftingResult(@Nonnull InventoryCrafting var1) {
        return output.copy();
    }

    @Override
    public int getRecipeSize() {
        return 9;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return output;
    }

    @Nonnull
    @Override
    public ItemStack[] getRemainingItems(@Nonnull InventoryCrafting inv) {
        return ForgeHooks.defaultRecipeGetRemainingItems(inv);
    }
}
