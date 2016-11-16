package lilylicious.mysteriousmiscellany.gameObjs.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class EnchantmentAir extends BlockAir {

    public EnchantmentAir(){
        super();
        setUnlocalizedName("enchantmentAir");
    }
    
    @Nonnull
    @Override
    public Block setUnlocalizedName(@Nonnull String message)
    {
        return super.setUnlocalizedName("mm_" + message);
    }
    
    @Override
    public float getEnchantPowerBonus(World world, BlockPos pos) {
        return 15;
    }

}
