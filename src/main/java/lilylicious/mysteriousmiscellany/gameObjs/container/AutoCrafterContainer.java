package lilylicious.mysteriousmiscellany.gameObjs.container;

import lilylicious.mysteriousmiscellany.gameObjs.tiles.TileAutoCrafter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class AutoCrafterContainer extends Container{

    private final TileAutoCrafter tile;

    public AutoCrafterContainer(InventoryPlayer invPlayer, TileAutoCrafter tile)
    {
        this.tile = tile;

        IItemHandler inv = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

        //Input grid, ID's 0
        //for (int y = 0; y < 3; y++)
            //for (int x = 0; x < 3; x++)
                this.addSlotToContainer(new SlotItemHandler(inv, 0, 80, 13));

        //Output grid, ID's 1-10
        for (int y = 0; y < 2; y++)
            for (int x = 0; x < 5; x++)
                this.addSlotToContainer(new SlotItemHandler(inv, 1 + x + y * 5, 44 + x * 18, 56 + y * 18){
                    @Override
                    public boolean isItemValid(ItemStack par1ItemStack) {
                        return false;
                    }
                });

        //Player Inventory 18-44
        for(int y = 0; y < 3; y++)
            for(int x = 0; x < 9; x++)
                this.addSlotToContainer(new Slot(invPlayer, x + y * 9 + 9, 8 + x * 18, 104 + y * 18));

        //Player Hotbar, slot 0-8, ID's 44-53,
        for (int x = 0; x < 9; x++)
            this.addSlotToContainer(new Slot(invPlayer, x, 8 + x * 18, 161));
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex)
    {
        Slot slot = this.getSlot(slotIndex);

        if (slot == null || !slot.getHasStack())
        {
            return ItemStack.EMPTY;
        }

        ItemStack stack = slot.getStack();
        ItemStack newStack = stack.copy();

        if (slotIndex < 11)
        {
            if (!this.mergeItemStack(stack, 11, this.inventorySlots.size(), false))
            {
                return ItemStack.EMPTY;
            }
        }
        else if (!this.mergeItemStack(stack, 0, 1, false))
        {
            return ItemStack.EMPTY;
        }

        if (stack.getCount() == 0)
        {
            slot.putStack(ItemStack.EMPTY);
        }
        else
        {
            slot.onSlotChanged();
        }

        return newStack;
    }

    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer player)
    {
        return player.getDistanceSq(tile.getPos().getX() + 0.5, tile.getPos().getY() + 0.5, tile.getPos().getZ() + 0.5) <= 64.0;
    }

}
