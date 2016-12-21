package lilylicious.mysteriousmiscellany.events;

import lilylicious.mysteriousmiscellany.MMCore;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public class PlayerEvents {

    boolean autorunActive = false;
    boolean toggled;
    private KeyBinding TOGGLE_AUTORUN;

    /* Bindings collection to register at runtime */
    public PlayerEvents() {
        TOGGLE_AUTORUN = new KeyBinding("Toggle Autorun", Keyboard.KEY_NUMLOCK, MMCore.MODNAME);
        ClientRegistry.registerKeyBinding(TOGGLE_AUTORUN);
    }

    @SubscribeEvent
    public void onLivingUpdateEvent(LivingEvent.LivingUpdateEvent e) {

        if (!(e.getEntityLiving() instanceof EntityPlayer))
            return;

        EntityPlayer player = (EntityPlayer) e.getEntity();
        GameSettings gameSettings = Minecraft.getMinecraft().gameSettings;
        int forward = gameSettings.keyBindForward.getKeyCode();

        if (autorunActive) {
            KeyBinding.setKeyBindState(forward, !stopFood(player));
        } else if (toggled) {
            KeyBinding.setKeyBindState(forward, false);
            toggled = false;
        }
    }

    @SubscribeEvent
    public void onKeyInputEvent(InputEvent.KeyInputEvent e) {
        if (TOGGLE_AUTORUN.isPressed()) {
            toggled = true;
            autorunActive = !autorunActive;
        }
    }

    @SubscribeEvent
    public void onBonemealEvent(BonemealEvent evt) {
        IBlockState blockstate = evt.getBlock();

        if(blockstate.getBlock() instanceof BlockFlower){
            EntityItem ei = new EntityItem(evt.getWorld(), evt.getPos().getX(), evt.getPos().getY(), evt.getPos().getZ(), new ItemStack(evt.getBlock().getBlock()));
            if(!evt.getWorld().isRemote){
                evt.getWorld().spawnEntityInWorld(ei);
                evt.setResult(Event.Result.ALLOW);
            }
        }
    }

    private boolean stopFood(EntityPlayer player) {
        if (player.getFoodStats().getFoodLevel() <= 10) {
            autorunActive = false;
            return true;
        }
        return false;
    }
}
