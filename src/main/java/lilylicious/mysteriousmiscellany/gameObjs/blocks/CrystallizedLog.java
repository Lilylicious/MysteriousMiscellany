package lilylicious.mysteriousmiscellany.gameObjs.blocks;

import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CrystallizedLog extends BlockLog {

    public static final PropertyEnum<BlockPlanks.EnumType> VARIANT = PropertyEnum.<BlockPlanks.EnumType>create("variant", BlockPlanks.EnumType.class, new Predicate<BlockPlanks.EnumType>()
    {
        public boolean apply(@Nullable BlockPlanks.EnumType p_apply_1_)
        {
            return p_apply_1_.getMetadata() < 4;
        }
    });

    public CrystallizedLog(){
        super();
        setUnlocalizedName("crystallizedlog");
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(LOG_AXIS, EnumAxis.values()[meta]);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    @SuppressWarnings("incomplete-switch")
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(LOG_AXIS).ordinal();
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, LOG_AXIS);
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
