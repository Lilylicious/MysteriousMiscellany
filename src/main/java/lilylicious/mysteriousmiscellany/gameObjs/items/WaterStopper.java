package lilylicious.mysteriousmiscellany.gameObjs.items;

import lilylicious.mysteriousmiscellany.config.MMConfig;
import lilylicious.mysteriousmiscellany.utils.WorldHelper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;

public class WaterStopper extends ToolMM {

    private boolean active;
    private int radius = 5;

    public WaterStopper() {
        super(0F, 4F, EnumHelper.addToolMaterial("waterStopper", 0, MMConfig.stopperDurability, 2.0F, 1F, 0), new HashSet<Block>());
        this.setUnlocalizedName("waterStopper");
        this.setMaxDamage(MMConfig.stopperDurability);
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int invSlot, boolean par5) {

        EntityPlayer player = (EntityPlayer) entity;

        if (active) {
            int x = (int) Math.floor(player.posX);
            int y = (int) (player.posY - player.getYOffset());
            int z = (int) Math.floor(player.posZ);
            BlockPos playerPos = new BlockPos(x, y, z);
            Iterable<BlockPos> iterable = WorldHelper.findBox(playerPos, radius);

            for (BlockPos blockPos : iterable) {
                IBlockState blockState = player.worldObj.getBlockState(blockPos);
                IBlockState targetState = Blocks.AIR.getDefaultState();

                /*
                //Doesn't really produce the effect desired
                if(playerPos.getX() - blockPos.getX() == -radius || playerPos.getX() - blockPos.getX() == radius
                        || playerPos.getZ() - blockPos.getZ() == -radius || playerPos.getZ() - blockPos.getZ() == radius
                        || playerPos.getY() - blockPos.getY() == -radius || playerPos.getY() - blockPos.getY() == radius)
                    targetState = Blocks.ICE.getDefaultState();
                */
                if (blockState.getBlock() == Blocks.WATER || blockState.getBlock() == Blocks.FLOWING_WATER || (blockState.getBlock() == Blocks.ICE && targetState.getBlock() != Blocks.ICE))
                    WorldHelper.replaceBlock(player, world, blockPos, null, targetState);

            }
        }
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
        if (!world.isRemote) {
            if (player.isSneaking()) {
                active = !active;
            }
        }

        return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return active;
    }
}
