package lilylicious.mysteriousmiscellany.gameObjs.items;

import lilylicious.mysteriousmiscellany.gameObjs.ObjHandler;
import lilylicious.mysteriousmiscellany.utils.WorldHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class EnchantBooster extends ItemMM {


    private List<BlockPos> savedPositions = new ArrayList<>();

    public EnchantBooster() {
        this.setUnlocalizedName("enchantBooster");
        this.setMaxStackSize(1);
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int invSlot, boolean par5) {


        EntityPlayer player = (EntityPlayer) entity;
        BlockPos playerPos = player.getPosition();

        Iterable<BlockPos> iterable = WorldHelper.findBox(playerPos, 5);

        boolean foundTable = false;
        boolean foundAir = false;

        for (BlockPos blockPos : iterable) {
            IBlockState blockState = world.getBlockState(blockPos);

            if (blockState.getBlock() == Blocks.ENCHANTING_TABLE && !foundTable) {

                foundTable = true;
                List<BlockPos> blockList;

                blockList = WorldHelper.findEligibleBlocksEnchanting(blockPos);

                //Make a list of blockPos's to check, check each for being air, if you find one then make that enchantingpower 15
                //Save that blockPos, when that is far enough away then reset that block to air
                for (BlockPos pos : blockList) {
                    IBlockState state = world.getBlockState(pos);

                    if (state.getBlock() == Blocks.AIR) {
                        savedPositions.add(pos);
                        foundAir = true;
                    }

                }
            }

        }
        if (foundAir) {
            for (BlockPos pos : savedPositions) {
                WorldHelper.replaceBlock(player, player.worldObj, pos, Blocks.AIR, ObjHandler.enchantmentAir.getDefaultState());
            }
        }
        if (!foundTable) {
            savedPositions.clear();
        }
    }

}
