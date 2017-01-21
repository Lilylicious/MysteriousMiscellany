package lilylicious.mysteriousmiscellany.gameObjs.blocks;

import lilylicious.mysteriousmiscellany.gameObjs.tiles.TileIceSpreader;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.UUID;

public class IceSpreader extends Block implements ITileEntityProvider {

        private UUID owner;
    public IceSpreader() {
            super(Material.GLASS);
            this.setHarvestLevel("pickaxe", 0);
            this.setHardness(1.5F);
            this.setResistance(10.0F);
            this.setSoundType(SoundType.GLASS);
            setUnlocalizedName("iceSpreader");
        }

        @Nonnull
        @Override
        public Block setUnlocalizedName(@Nonnull String message) {
            return super.setUnlocalizedName("mm_" + message);
        }

        @Override
        public TileEntity createNewTileEntity(World worldIn, int meta) {
            return new TileIceSpreader(owner);
        }

        @Override
        public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
            owner = placer.getUniqueID();
        }
}
