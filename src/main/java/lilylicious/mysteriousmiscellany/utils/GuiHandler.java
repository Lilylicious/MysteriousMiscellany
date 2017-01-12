package lilylicious.mysteriousmiscellany.utils;

import com.google.common.collect.ImmutableSet;
import lilylicious.mysteriousmiscellany.gameObjs.container.AutoCrafterContainer;
import lilylicious.mysteriousmiscellany.gameObjs.gui.GUIAutoCrafter;
import lilylicious.mysteriousmiscellany.gameObjs.tiles.TileAutoCrafter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import java.util.Set;

public class GuiHandler implements IGuiHandler {

    //IDs for non-tile GUIs
    private static final Set<Integer> ITEM_IDS = ImmutableSet.of(

    );

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        TileEntity tile = !ITEM_IDS.contains(ID) ? world.getTileEntity(new BlockPos(x, y, z)) : null;

        switch (ID)
        {
            case Constants.AUTOCRAFTER_GUI:
                if (tile != null && tile instanceof TileAutoCrafter)
                    return new AutoCrafterContainer(player.inventory, (TileAutoCrafter) tile);
                break;
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tile = !ITEM_IDS.contains(ID) ? world.getTileEntity(new BlockPos(x, y, z)) : null;

        switch (ID)
        {
            case Constants.AUTOCRAFTER_GUI:
                if (tile != null && tile instanceof TileAutoCrafter)
                    return new GUIAutoCrafter(player.inventory, (TileAutoCrafter) tile);
                break;

        }

        return null;
    }

}
