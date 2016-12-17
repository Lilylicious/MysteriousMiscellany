package lilylicious.mysteriousmiscellany.gameObjs.blocks;

import lilylicious.mysteriousmiscellany.gameObjs.tiles.TileSpawnPreventer;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class SpawnPreventer extends Block implements ITileEntityProvider{


    public SpawnPreventer() {
        super(Material.ROCK);
    }


    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileSpawnPreventer();
    }
}
