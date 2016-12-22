package lilylicious.mysteriousmiscellany.events;

import net.minecraft.block.BlockFlower;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BonemealEvents {

    @SubscribeEvent
    public void onBonemealEvent(net.minecraftforge.event.entity.player.BonemealEvent evt) {
        IBlockState blockstate = evt.getBlock();

        if(blockstate.getBlock() instanceof BlockFlower){
            EntityItem ei = new EntityItem(evt.getWorld(), evt.getPos().getX(), evt.getPos().getY(), evt.getPos().getZ(), new ItemStack(evt.getBlock().getBlock()));
            if(!evt.getWorld().isRemote){
                evt.getWorld().spawnEntityInWorld(ei);
                evt.setResult(Event.Result.ALLOW);
            }
        }
    }
}
