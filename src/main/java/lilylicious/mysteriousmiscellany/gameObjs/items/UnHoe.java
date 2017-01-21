package lilylicious.mysteriousmiscellany.gameObjs.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class UnHoe extends ItemHoe{

    public UnHoe(ToolMaterial material) {
        super(material);

        this.setUnlocalizedName("mm_unhoe_" + material.name().toLowerCase());
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!player.canPlayerEdit(pos.offset(facing), facing, player.getActiveItemStack()))
        {
            return EnumActionResult.FAIL;
        }
        else
        {
            int hook = net.minecraftforge.event.ForgeEventFactory.onHoeUse(player.getActiveItemStack(), player, world, pos);
            if (hook != 0) return hook > 0 ? EnumActionResult.SUCCESS : EnumActionResult.FAIL;

            IBlockState iblockstate = world.getBlockState(pos);
            Block block = iblockstate.getBlock();

            if (facing != EnumFacing.DOWN && world.isAirBlock(pos.up()))
            {
                if (block == Blocks.FARMLAND)
                {
                    this.setBlock(player.getActiveItemStack(), player, world, pos, Blocks.DIRT.getDefaultState());
                    return EnumActionResult.SUCCESS;
                }
                else if (block == Blocks.GRASS || block == Blocks.GRASS_PATH)
                {
                    this.setBlock(player.getActiveItemStack(), player, world, pos, Blocks.DIRT.getDefaultState());
                    return EnumActionResult.SUCCESS;
                }
            }

            return EnumActionResult.PASS;
        }
    }

}
