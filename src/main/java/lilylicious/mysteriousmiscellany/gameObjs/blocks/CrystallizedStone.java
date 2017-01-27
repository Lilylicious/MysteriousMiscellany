package lilylicious.mysteriousmiscellany.gameObjs.blocks;

import com.google.common.base.Predicate;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CrystallizedStone extends Block {

    public CrystallizedStone(){
        super(Material.ROCK);
        this.setHarvestLevel("pickaxe", 0);
        this.setHardness(1.5F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.STONE);
        setUnlocalizedName("crystallizedstone");
    }

    @Override
    public float getEnchantPowerBonus(World world, BlockPos pos)
    {
        return 8;
    }

    @Nonnull
    @Override
    public Block setUnlocalizedName(@Nonnull String message) {
        return super.setUnlocalizedName("mm_" + message);
    }
}
