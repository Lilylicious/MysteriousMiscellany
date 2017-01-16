package lilylicious.mysteriousmiscellany.gameObjs.tiles;

import lilylicious.mysteriousmiscellany.utils.ValidatedItemHandler;
import lilylicious.mysteriousmiscellany.utils.ValidatedItemHandler.RecipeType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RangedWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileAutoCrafter extends TileEntity implements ITickable {

    private final ItemStackHandler inventory = new ValidatedItemHandler(this, 18, RecipeType.AUTO_CRAFTER);

    private final RangedWrapper inputSlot = new RangedWrapper(inventory, 0, 1){
        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate)
        {
            return null;
        }
    };;
    private final RangedWrapper outputSlots = new RangedWrapper(inventory, 1, 11){
        @Override
        public ItemStack insertItem(int slot, ItemStack stack, boolean simulate)
        {
            return stack;
        }
    };

    private int tickCount = 0;
    private int attempts = 0;

    @Override
    public boolean hasCapability(@Nonnull Capability<?> cap, @Nullable EnumFacing side) {
        return cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(cap, side);
    }


    @Nonnull
    @Override
    public <T> T getCapability(@Nonnull Capability<T> cap, @Nullable EnumFacing side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (side == null) {
                return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inventory);
            } else if (side == EnumFacing.DOWN) {
                return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(outputSlots);
            } else {
                return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inputSlot);
            }
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        inventory.deserializeNBT(nbt);
    }

    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt = super.writeToNBT(nbt);
        nbt.merge(inventory.serializeNBT());
        return nbt;
    }

    @Override
    public void update() {

        InventoryCrafting inv = new InventoryCrafting(new Container() {
            @Override
            public boolean canInteractWith(EntityPlayer var1) {
                return false;
            }
        }, 3, 3);

        tickCount++;

        ItemStack output;

        ItemStack input = inputSlot.getStackInSlot(0);

        inv.setInventorySlotContents(0, input);
        output = CraftingManager.getInstance().findMatchingRecipe(inv, worldObj);

        if (output != null) {
            ItemStack newInput = input.copy();
            newInput.stackSize -= 1;
            if (newInput.stackSize <= 0)
                newInput = null;


            while (output != null && attempts++ < 5) {
                for (int k = 0; k < outputSlots.getSlots(); k++) {

                    ItemStack inSlot = outputSlots.getStackInSlot(k);

                    if (inSlot == null) {
                        inputSlot.setStackInSlot(0, newInput);
                        outputSlots.setStackInSlot(k, output);
                        output = null;
                    } else if (inSlot.isItemEqual(output) && ItemStack.areItemStackTagsEqual(inSlot, output) && inSlot.isStackable() && inSlot.stackSize < inSlot.getMaxStackSize()) {
                        ItemStack copy = inSlot.copy();
                        copy.stackSize += output.stackSize;

                        if (!(k == 8 && copy.stackSize > copy.getMaxStackSize())) {
                            if (copy.stackSize > copy.getMaxStackSize()) {
                                ItemStack overflow = copy.copy();
                                overflow.stackSize = copy.stackSize - copy.getMaxStackSize();
                                copy.stackSize = copy.getMaxStackSize();
                                output = overflow;
                            } else
                                output = null;

                            inputSlot.setStackInSlot(0, newInput);
                            outputSlots.setStackInSlot(k, copy);
                        }
                    }
                    if (output == null){
                        attempts = 0;
                        break;
                    }



                }
            }
            if(tickCount >= 40){
                attempts = 0;
                tickCount = 0;
            }
        }
    }
}
