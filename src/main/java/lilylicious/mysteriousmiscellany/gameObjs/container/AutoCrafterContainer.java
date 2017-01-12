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

        //Input grid, ID's 0-8
        for (int y = 0; y < 3; y++)
            for (int x = 0; x < 3; x++)
                this.addSlotToContainer(new SlotItemHandler(inv, x + y * 3, 17 + x * 18, 27 + y * 18));

        //Output grid, ID's 9-17
        for (int y = 0; y < 3; y++)
            for (int x = 0; x < 3; x++)
                this.addSlotToContainer(new SlotItemHandler(inv, 9 + x + y * 3, 107 + x * 18, 27 + y * 18));

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
            return null;
        }

        ItemStack stack = slot.getStack();
        ItemStack newStack = stack.copy();

        if (slotIndex < 18)
        {
            if (!this.mergeItemStack(stack, 18, this.inventorySlots.size(), false))
            {
                return null;
            }
        }
        else if (!this.mergeItemStack(stack, 0, 8, false))
        {
            return null;
        }

        if (stack.stackSize == 0)
        {
            slot.putStack(null);
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
