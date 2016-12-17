package lilylicious.mysteriousmiscellany.gameObjs.blocks;

import lilylicious.mysteriousmiscellany.gameObjs.tiles.TileSpawnPreventer;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class SpawnPreventer extends Block implements ITileEntityProvider{


    public SpawnPreventer() {
        super(Material.ROCK);
        setUnlocalizedName("spawnPreventer");
        this.setHarvestLevel("pickaxe", 0);
        this.setHardness(1.5F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.STONE);
    }

    @Nonnull
    @Override
    public Block setUnlocalizedName(@Nonnull String message) {
        return super.setUnlocalizedName("mm_" + message);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileSpawnPreventer();
    }
}
