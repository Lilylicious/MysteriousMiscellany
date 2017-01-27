package lilylicious.mysteriousmiscellany.gameObjs.blocks;

import lilylicious.mysteriousmiscellany.gameObjs.tiles.TileKnowledgeInfuser;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.Random;

public class KnowledgeInfuser extends Block implements ITileEntityProvider {

    public KnowledgeInfuser() {
        super(Material.ROCK);
        this.setHarvestLevel("pickaxe", 0);
        this.setHardness(1.5F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.STONE);
        setUnlocalizedName("knowledgeinfuser");
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing par6, float par7, float par8, float par9) {
        if (!world.isRemote) {
            TileKnowledgeInfuser infuser = (TileKnowledgeInfuser) world.getTileEntity(pos);
            player.sendMessage(new TextComponentString("Total EP: " + infuser.getEnchantingPower()));
            player.sendMessage(new TextComponentString("TimeDivisor: " + infuser.getTimeDivisor()));
            player.sendMessage(new TextComponentString("Ticks left: " + infuser.getCraftingTicks()));
            player.sendMessage(new TextComponentString("RF Stored: " + infuser.getEnergyStored(null)));
            return true;
        }
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        TileKnowledgeInfuser infuser = (TileKnowledgeInfuser) worldIn.getTileEntity(pos);

        for (int i = -1; i <= 1; ++i) {
            for (int j = -1; j <= 1; ++j) {


                if (infuser.getCraftingTicks() > 0) {
                    float speed = 1 + (float) Math.log(infuser.getRecipe().getTickCost() / infuser.getCraftingTicks());

                    worldIn.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, (double) pos.getX() + 0.5D, (double) pos.getY() + 2.0D, (double) pos.getZ() + 0.5D, (double) (speed * (float) i + rand.nextFloat()) - 0.5D, (double) (speed * (float) 1 - rand.nextFloat() - 1.0F), speed * (double) ((float) j + rand.nextFloat()) - 0.5D, new int[0]);
                }
            }
        }
    }

    @Nonnull
    @Override
    public Block setUnlocalizedName(@Nonnull String message) {
        return super.setUnlocalizedName("mm_" + message);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileKnowledgeInfuser();
    }

}
