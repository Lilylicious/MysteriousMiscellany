package lilylicious.mysteriousmiscellany.events;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BonemealEvents {

    @SubscribeEvent
    public void onBonemealEvent(net.minecraftforge.event.entity.player.BonemealEvent evt) {
        Block block = evt.getBlock().getBlock();

        if(block.toString().toLowerCase().contains("botania"))
            return;

        if (block instanceof BlockFlower) {
            ItemStack stack = new ItemStack(block, 1, block.getMetaFromState(evt.getBlock()));
            EntityItem ei = new EntityItem(evt.getWorld(), evt.getPos().getX(), evt.getPos().getY(), evt.getPos().getZ(), stack);
            if (!evt.getWorld().isRemote) {
                evt.getWorld().spawnEntityInWorld(ei);
                evt.setResult(Event.Result.ALLOW);
            }
        }
    }
}
