package lilylicious.mysteriousmiscellany.gameObjs.blocks;

import lilylicious.mysteriousmiscellany.gameObjs.ObjHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBookshelf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class CompressedBookshelf extends BlockBookshelf {

    public CompressedBookshelf() {
        super();
        setUnlocalizedName("compressedBookshelf");
        setHardness(1.5f);
    }

    @Nonnull
    @Override
    public Block setUnlocalizedName(@Nonnull String message) {
        return super.setUnlocalizedName("mm_" + message);
    }

    @Override
    public float getEnchantPowerBonus(World world, BlockPos pos) {
        return 9;
    }

    @Override
    public int quantityDropped(Random random) {
        return 1;
    }

    @Nullable
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(ObjHandler.compressedBookshelf);
    }
}
