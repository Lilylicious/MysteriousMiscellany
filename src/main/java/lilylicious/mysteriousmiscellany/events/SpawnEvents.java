package lilylicious.mysteriousmiscellany.events;

import lilylicious.mysteriousmiscellany.gameObjs.tiles.TileSpawnPreventer;
import lilylicious.mysteriousmiscellany.utils.MMLogger;
import net.minecraft.entity.EnumCreatureType;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.Iterator;

public class SpawnEvents {

    public static ArrayList<TileSpawnPreventer> spawnPreventers = new ArrayList<>();

    @SubscribeEvent
    public void onEntitySpawnCheck(LivingSpawnEvent.CheckSpawn evt) {

        if (evt.getResult() == Event.Result.ALLOW || evt.getResult() == Event.Result.DENY)
            return;
        if (evt.getEntityLiving().isCreatureType(EnumCreatureType.MONSTER, false)) {
            Iterator<TileSpawnPreventer> it = spawnPreventers.iterator();
            while (it.hasNext()) {
                TileSpawnPreventer preventer = it.next();
                if (preventer.isInvalid())
                    it.remove();
                else if (preventer.getWorld().provider.getDimension() == evt.getEntity().world.provider.getDimension() && preventer.getDistanceSq(evt.getEntity().posX, evt.getEntity().posY, evt.getEntity().posZ) <= preventer.getRadiusSquared()) {
                    evt.setResult(Event.Result.DENY);

                    ///Replacing this with a single log of a spawn preventer existing
                    //MMLogger.logDebug("Prevented spawn of " + evt.getEntity().getName());
                }

            }
        }
    }
}
