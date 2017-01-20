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
            return ItemStack.EMPTY;
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
        output = CraftingManager.getInstance().findMatchingRecipe(inv, world);

        if (!output.isEmpty()) {
            ItemStack newInput = input.copy();
            newInput.splitStack(1);
            if (newInput.getCount() <= 0)
                newInput = ItemStack.EMPTY;


            while (!output.isEmpty() && attempts++ < 5) {
                for (int k = 0; k < outputSlots.getSlots(); k++) {

                    ItemStack inSlot = outputSlots.getStackInSlot(k);

                    if (inSlot.isEmpty()) {
                        inputSlot.setStackInSlot(0, newInput);
                        outputSlots.setStackInSlot(k, output);
                        output = ItemStack.EMPTY;
                    } else if (inSlot.isItemEqual(output) && ItemStack.areItemStackTagsEqual(inSlot, output) && inSlot.isStackable() && inSlot.getCount() < inSlot.getMaxStackSize()) {
                        ItemStack copy = inSlot.copy();
                        copy.setCount(copy.getCount() + output.getCount());

                        if (!(k == 8 && copy.getCount() > copy.getMaxStackSize())) {
                            if (copy.getCount() > copy.getMaxStackSize()) {
                                ItemStack overflow = copy.copy();
                                overflow.setCount(copy.getCount() - copy.getMaxStackSize());
                                copy.setCount(copy.getMaxStackSize());
                                output = overflow;
                            } else
                                output = ItemStack.EMPTY;

                            inputSlot.setStackInSlot(0, newInput);
                            outputSlots.setStackInSlot(k, copy);
                        }
                    }
                    if (output.isEmpty()){
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
