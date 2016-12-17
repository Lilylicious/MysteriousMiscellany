package lilylicious.mysteriousmiscellany.events;

import lilylicious.mysteriousmiscellany.MMCore;
import lilylicious.mysteriousmiscellany.utils.MMLogger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
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

        if(autorunActive){
            KeyBinding.setKeyBindState(forward, !stopFood(player));
        }
        else if (toggled) {
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

    private boolean stopFood(EntityPlayer player){
        if(player.getFoodStats().getFoodLevel() <= 10){
            autorunActive = false;
            return true;
        }
        return false;
    }
}
