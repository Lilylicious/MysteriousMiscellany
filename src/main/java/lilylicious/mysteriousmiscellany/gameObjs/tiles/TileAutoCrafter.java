package lilylicious.mysteriousmiscellany.gameObjs.tiles;

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

    private final ItemStackHandler inventory = new ItemStackHandler(18);

    private final RangedWrapper inputSlots = new RangedWrapper(inventory, 0, 9);
    private final RangedWrapper outputSlots = new RangedWrapper(inventory, 9, 18);

    @Override
    public boolean hasCapability(@Nonnull Capability<?> cap, @Nullable EnumFacing side) {
        return cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(cap, side);
    }

    @Nonnull
    @Override
    public <T> T getCapability(@Nonnull Capability<T> cap, @Nullable EnumFacing side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inventory);
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

        ItemStack output;

        for (int i = 0; i < inputSlots.getSlots(); i++) {
            ItemStack input = inputSlots.getStackInSlot(i);

            inv.setInventorySlotContents(0, input);
            output = CraftingManager.getInstance().findMatchingRecipe(inv, worldObj);

            if (output != null) {
                ItemStack newInput = input.copy();
                newInput.stackSize -= 1;
                if (newInput.stackSize <= 0)
                    newInput = null;

                while (output != null) {
                    for (int k = 0; k < outputSlots.getSlots(); k++) {

                        ItemStack inSlot = outputSlots.getStackInSlot(k);

                        if (inSlot == null) {
                            inputSlots.setStackInSlot(i, newInput);
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

                                inputSlots.setStackInSlot(i, newInput);
                                outputSlots.setStackInSlot(k, copy);
                            }
                        }
                        if (output == null)
                            break;
                    }
                }

            }
        }
    }
}
