package lilylicious.mysteriousmiscellany.gameObjs.items;

import lilylicious.mysteriousmiscellany.config.MMConfig;
import lilylicious.mysteriousmiscellany.utils.Predicates;
import lilylicious.mysteriousmiscellany.utils.WorldHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

import javax.annotation.Nonnull;
import java.util.HashSet;

public class WaterStopper extends ToolMM {

    private boolean active;

    public WaterStopper() {
        super(0F, 4F, EnumHelper.addToolMaterial("waterStopper", 0, MMConfig.stopperDurability, 2.0F, 1F, 0), new HashSet<>());
        this.setUnlocalizedName("waterStopper");
        this.setMaxDamage(MMConfig.stopperDurability);
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int invSlot, boolean par5) {

        EntityPlayer player = (EntityPlayer) entity;

        if (active) {
            BlockPos playerPos = player.getPosition();
            int radius = 5;
            Iterable<BlockPos> iterable = WorldHelper.findBox(playerPos, radius);

            for (BlockPos blockPos : iterable) {
                IBlockState blockState = player.world.getBlockState(blockPos);
                IBlockState targetState = Blocks.AIR.getDefaultState();
                if (Predicates.IS_WATER.test(blockState.getBlock()) || Predicates.ICE_NOT_ICE.test(blockState.getBlock(), targetState.getBlock()))
                    WorldHelper.replaceBlock(player, world, blockPos, null, targetState);

            }
        }
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if (!world.isRemote) {
            if (player.isSneaking()) {
                active = !active;
            }
        }

        return ActionResult.newResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return active;
    }
}
