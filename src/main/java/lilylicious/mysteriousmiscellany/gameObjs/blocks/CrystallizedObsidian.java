package lilylicious.mysteriousmiscellany.gameObjs.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class CrystallizedObsidian extends Block {

    public CrystallizedObsidian(){
        super(Material.ROCK);
        this.setHarvestLevel("pickaxe", 1);
        this.setHardness(10.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.STONE);
        setUnlocalizedName("crystallizedobsidian");
    }

    @Override
    public float getEnchantPowerBonus(World world, BlockPos pos)
    {
        return 72;
    }

    @Nonnull
    @Override
    public Block setUnlocalizedName(@Nonnull String message) {
        return super.setUnlocalizedName("mm_" + message);
    }
}
