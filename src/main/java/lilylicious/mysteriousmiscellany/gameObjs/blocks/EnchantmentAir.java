package lilylicious.mysteriousmiscellany.gameObjs.blocks;

import lilylicious.mysteriousmiscellany.gameObjs.tiles.TileEnchantmentAir;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class EnchantmentAir extends BlockAir implements ITileEntityProvider {

    public EnchantmentAir() {
        super();
        setUnlocalizedName("enchantmentair");
    }

    @Nonnull
    @Override
    public Block setUnlocalizedName(@Nonnull String message) {
        return super.setUnlocalizedName("mm_" + message);
    }

    @Override
    public float getEnchantPowerBonus(World world, BlockPos pos) {
        return 15;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEnchantmentAir();
    }
}
