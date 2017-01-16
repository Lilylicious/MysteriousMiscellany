package lilylicious.mysteriousmiscellany.utils;

import lilylicious.mysteriousmiscellany.registry.CrafterRecipeRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.ItemStackHandler;

public class ValidatedItemHandler extends ItemStackHandler {

    public final TileEntity tileEntity;
    public final RecipeType recipeType;

    public ValidatedItemHandler(TileEntity tileEntity, int size, RecipeType recipeType) {
        super(size);
        this.tileEntity = tileEntity;
        this.recipeType = recipeType;
    }

    @Override
    protected void onContentsChanged(int slot) {
        tileEntity.markDirty();
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {

        if(!isItemValid(stack)) {
            return stack;
        }
        return super.insertItem(slot, stack, simulate);
    }

    public boolean isItemValid(ItemStack itemStack) {

        boolean valid = true;

        switch (recipeType){
            case AUTO_CRAFTER:
                valid = CrafterRecipeRegistry.itemValid(itemStack.getItem());
        }

        return valid;
    }

    public enum RecipeType {
        AUTO_CRAFTER
    }
}

