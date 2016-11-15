package lilylicious.mysteriousmiscellany.events;

import lilylicious.mysteriousmiscellany.gameObjs.items.FishStopper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderEvents {
    
    @SubscribeEvent
    public void renderWorldLastEvent(RenderWorldLastEvent evt){
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP p = mc.thePlayer;
        ItemStack itemStack = null;

        for(ItemStack s : p.inventory.mainInventory){
            if(s != null && s.getItem() instanceof FishStopper){
                itemStack = s;
            }
        }

        ItemStack offHandItem = p.inventory.offHandInventory[0];
        if(offHandItem != null && offHandItem.getItem() instanceof FishStopper){
            itemStack = offHandItem;
        }
        if (itemStack == null) {
            return;
        }

        FishStopper fishStopper = (FishStopper) itemStack.getItem();
        fishStopper.renderOverlay(evt, p);

    }
}
