package lilylicious.mysteriousmiscellany.gameObjs.recipes;

import net.minecraft.block.*;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class DyeRecipes implements IRecipe {

    private ItemStack output = ItemStack.EMPTY;

    @Override
    public boolean matches(@Nonnull InventoryCrafting inv, @Nonnull World world) {
        List<ItemStack> itemsToDye = new ArrayList<>();
        List<ItemStack> dyes = new ArrayList<>();
        List<ItemStack> sand = new ArrayList<>();
        boolean stainedGlass = false;
        boolean stainedGlassPane = false;
        boolean hardenedClay = false;
        boolean carpet = false;
        boolean wool = false;

        for (int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack input = inv.getStackInSlot(i);

            //Removes the need for nullchecks in every if statement
            if (input.isEmpty()) {
                continue;
            } else if (input.getItem() instanceof ItemDye)
                dyes.add(input);
            else if (Block.getBlockFromItem(input.getItem()) instanceof BlockSand)
                sand.add(input);
            else if (Block.getBlockFromItem(input.getItem()) instanceof BlockStainedGlass || Block.getBlockFromItem(input.getItem()) instanceof BlockGlass) {
                stainedGlass = true;
                itemsToDye.add(input);
            }
            else if (Block.getBlockFromItem(input.getItem()) instanceof BlockStainedGlassPane || Block.getBlockFromItem(input.getItem()) instanceof BlockPane) {
                stainedGlassPane = true;
                itemsToDye.add(input);
            }
            else if (Block.getBlockFromItem(input.getItem()) instanceof BlockHardenedClay || Block.getBlockFromItem(input.getItem()) == Blocks.STAINED_HARDENED_CLAY) {
                hardenedClay = true;
                itemsToDye.add(input);
            }
            else if (Block.getBlockFromItem(input.getItem()) instanceof BlockCarpet) {
                carpet = true;
                itemsToDye.add(input);
            }
            else if (Block.getBlockFromItem(input.getItem()) == Blocks.WOOL) {
                wool = true;
                itemsToDye.add(input);
            }
        }

        if ((sand.isEmpty() && dyes.isEmpty() || dyes.size() > 1 || sand.size() > 1) || itemsToDye.size() == 0) {
            return false;
        }

        if(countTrue(stainedGlass, stainedGlassPane, hardenedClay, carpet, wool, !dyes.isEmpty(), !sand.isEmpty()) > 2)
            return false;

        if(!dyes.isEmpty()){
            if(stainedGlass)
                output = new ItemStack(Blocks.STAINED_GLASS, itemsToDye.size(), 15-dyes.get(0).getMetadata());
            else if(stainedGlassPane)
                output = new ItemStack(Blocks.STAINED_GLASS_PANE, itemsToDye.size(), 15-dyes.get(0).getMetadata());
            else if(hardenedClay)
                output = new ItemStack(Blocks.STAINED_HARDENED_CLAY, itemsToDye.size(), 15-dyes.get(0).getMetadata());
            else if(carpet)
                output = new ItemStack(Blocks.CARPET, itemsToDye.size(), 15-dyes.get(0).getMetadata());
            else if(wool)
                output = new ItemStack(Blocks.WOOL, itemsToDye.size(), 15-dyes.get(0).getMetadata());
        }
        else if (!sand.isEmpty()){
            if(stainedGlass)
                output = new ItemStack(Blocks.GLASS, itemsToDye.size());
            else if(stainedGlassPane)
                output = new ItemStack(Blocks.GLASS_PANE, itemsToDye.size());
            else if(hardenedClay)
                output = new ItemStack(Blocks.HARDENED_CLAY, itemsToDye.size());
            else if(carpet)
                output = new ItemStack(Blocks.CARPET, itemsToDye.size());
            else if(wool)
                output = new ItemStack(Blocks.WOOL, itemsToDye.size());
        }

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
    public NonNullList<ItemStack> getRemainingItems(@Nonnull InventoryCrafting inv) {
        return ForgeHooks.defaultRecipeGetRemainingItems(inv);
    }

    private int countTrue(boolean... booleans){
        int sum = 0;
        for (boolean b : booleans) {
            if (b) {
                sum++;
            }
        }
        return sum;
    }
}
