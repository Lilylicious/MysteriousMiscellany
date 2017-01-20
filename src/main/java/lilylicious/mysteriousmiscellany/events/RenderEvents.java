package lilylicious.mysteriousmiscellany.events;

import lilylicious.mysteriousmiscellany.gameObjs.items.FishStopper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderEvents {

    @SubscribeEvent
    public void renderWorldLastEvent(RenderWorldLastEvent evt) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP p = mc.player;
        ItemStack itemStack = ItemStack.EMPTY;

        for (ItemStack s : p.inventory.mainInventory) {
            if (!s.isEmpty() && s.getItem() instanceof FishStopper) {
                itemStack = s;
            }
        }

        ItemStack offHandItem = p.inventory.offHandInventory.get(0);
        if (!offHandItem.isEmpty() && offHandItem.getItem() instanceof FishStopper) {
            itemStack = offHandItem;
        }

        if (itemStack.isEmpty()) {
            return;
        }

        FishStopper fishStopper = (FishStopper) itemStack.getItem();
        fishStopper.renderOverlay(evt, p);
    }


}
