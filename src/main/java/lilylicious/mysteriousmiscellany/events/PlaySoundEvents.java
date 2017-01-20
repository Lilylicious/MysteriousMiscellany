package lilylicious.mysteriousmiscellany.events;

import lilylicious.mysteriousmiscellany.config.MMConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlaySoundEvents {

    @SubscribeEvent
    public void playSoundEvent(PlaySoundEvent evt) {
        EntityPlayer player = Minecraft.getMinecraft().player;

        //MMLogger.logInfo(evt.getName());

        if(MMConfig.preventServerWideBossSounds){

            if(evt.getName().equals("entity.enderdragon.death") && player.dimension != 1)
                evt.setResultSound(null);

            if(evt.getName().equals("entity.wither.spawn") && player.dimension != 1)
                evt.setResultSound(null);
        }


        if(evt.getName().equals("block.portal.ambient") && MMConfig.preventPortalSounds)
            evt.setResultSound(null);

        if(evt.getName().equals("block.portal.travel") && MMConfig.preventPortalArrivalSounds)
            evt.setResultSound(null);

        if(evt.getName().equals("entity.villager.ambient") && MMConfig.preventVillagerHhms)
            evt.setResultSound(null);

        if(evt.getName().equals("entity.cat.ambient") && MMConfig.preventCatMeows)
            evt.setResultSound(null);


        if(evt.getName().startsWith("entity.") && MMConfig.preventAnimalSteps){
            if(evt.getName().endsWith("cow.step")
                    || evt.getName().endsWith("sheep.step")
                    || evt.getName().endsWith("chicken.step")
                    || evt.getName().endsWith("pig.step")){
                evt.setResultSound(null);
            }
        }

        if(MMConfig.preventChestSounds && (evt.getName().equals("block.chest.open") || evt.getName().equals("block.chest.close")))
            evt.setResultSound(null);

        if(MMConfig.preventDoorSounds){
            if(evt.getName().equals("block.wooden_door.open"))
                evt.setResultSound(null);

            if(evt.getName().equals("block.wooden_door.close"))
                evt.setResultSound(null);

            if(evt.getName().equals("block.iron_door.open"))
                evt.setResultSound(null);

            if(evt.getName().equals("block.iron_door.close"))
                evt.setResultSound(null);
        }

        //Wither ambient: entity.wither.ambient
        //Wither spawn: entity.wither.spawn
        //Wither shoot "woosh": entity.wither.shoot
        //Wither death: entity.wither.death

    }
}
