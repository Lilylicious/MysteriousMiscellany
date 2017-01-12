package lilylicious.mysteriousmiscellany.gameObjs.blocks;

import lilylicious.mysteriousmiscellany.gameObjs.tiles.TileEnchantmentGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
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

public class EnchantmentGenerator extends Block implements ITileEntityProvider {

    public EnchantmentGenerator() {
        super(Material.ROCK);
        this.setHarvestLevel("pickaxe", 0);
        this.setHardness(1.5F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.STONE);
        this.setDefaultState(this.blockState.getBaseState());
        setUnlocalizedName("enchantingGenerator");
    }

    @Nonnull
    @Override
    public Block setUnlocalizedName(@Nonnull String message) {
        return super.setUnlocalizedName("mm_" + message);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEnchantmentGenerator();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack stack, EnumFacing par6, float par7, float par8, float par9) {
        if (!world.isRemote) {
            TileEnchantmentGenerator generator = (TileEnchantmentGenerator) world.getTileEntity(pos);
            if (!generator.isWorking())
                player.addChatMessage(new TextComponentString("Adjacent block interfering!"));
            else {
                player.addChatMessage(new TextComponentString(generator.getEnergyStored(null) + "/" + generator.getMaxEnergyStored(null) + " RF"));
                player.addChatMessage(new TextComponentString(generator.getEnergyPerTick() + " RF/Tick"));
            }
            return true;
        }
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        super.randomDisplayTick(stateIn, worldIn, pos, rand);

        for (int i = -2; i <= 2; ++i) {
            for (int j = -2; j <= 2; ++j) {
                if (i > -2 && i < 2 && j == -1) {
                    j = 2;
                }

                if (rand.nextInt(16) == 0) {
                    for (int k = 0; k <= 1; ++k) {
                        BlockPos blockpos = pos.add(i, k, j);

                        if (net.minecraftforge.common.ForgeHooks.getEnchantPower(worldIn, blockpos) > 0) {
                            if (!worldIn.isAirBlock(pos.add(i / 2, 0, j / 2))) {
                                break;
                            }

                            worldIn.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, (double) pos.getX() + 0.5D, (double) pos.getY() + 2.0D, (double) pos.getZ() + 0.5D, (double) ((float) i + rand.nextFloat()) - 0.5D, (double) ((float) k - rand.nextFloat() - 1.0F), (double) ((float) j + rand.nextFloat()) - 0.5D);
                        }
                    }
                }
            }
        }
    }

}
