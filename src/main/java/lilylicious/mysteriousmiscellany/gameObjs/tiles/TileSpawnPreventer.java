package lilylicious.mysteriousmiscellany.gameObjs.tiles;

import lilylicious.mysteriousmiscellany.config.MMConfig;
import lilylicious.mysteriousmiscellany.events.SpawnEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileSpawnPreventer extends TileEntity implements ITickable {


    private boolean addedToList;

    @Override
    public void update() {

        if (!addedToList)
            if (!SpawnEvents.spawnPreventers.contains(this))
                SpawnEvents.spawnPreventers.add(this);

        addedToList = true;

    }

    public int getRadiusSquared() {
        return (int) Math.pow(MMConfig.spawnPreventionRadius, 2);
    }

    @Override
    public void invalidate() {
        if (SpawnEvents.spawnPreventers.contains(this))
            SpawnEvents.spawnPreventers.remove(this);

        super.invalidate();
    }

}
