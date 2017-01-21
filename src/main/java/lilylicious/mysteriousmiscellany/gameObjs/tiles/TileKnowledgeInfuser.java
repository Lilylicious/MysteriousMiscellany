package lilylicious.mysteriousmiscellany.gameObjs.tiles;

import lilylicious.mysteriousmiscellany.registry.InfuserRecipeRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class TileKnowledgeInfuser extends TileEntity implements ITickable {


    private int tickCount = 0;
    private int baseCraftingTicks = 1200;
    private int craftingTicks = 0;
    private int ePower = 0;

    public TileKnowledgeInfuser() {
    }

    @Override
    public void update() {

        ePower = findEnchantingPower();

        if (craftingTicks <= 0 && InfuserRecipeRegistry.blockValid(blockAbove()))
            craftingTicks = baseCraftingTicks / getTimeDivisor();
        else if (craftingTicks > 0)
            craftingTicks--;

        if(!InfuserRecipeRegistry.blockValid(blockAbove())){
            craftingTicks = 0;
            return;
        }

        if (!getWorld().isRemote && InfuserRecipeRegistry.blockValid(blockAbove()) && craftingTicks <= 0)
            getWorld().setBlockState(getPos().up(), InfuserRecipeRegistry.getResult(blockAbove()).getStateFromMeta(getWorld().getBlockState(getPos().up()).getBlock().getMetaFromState(getWorld().getBlockState(getPos().up()))), 2);
    }


    private int findEnchantingPower() {
        int enchantingPower = 0;

        for (BlockPos pos : BlockPos.getAllInBoxMutable(getPos().add(-1, 1, -1), getPos().add(1, 1, 1))) {
            enchantingPower += getWorld().getBlockState(pos).getBlock().getEnchantPowerBonus(getWorld(), pos);
        }

        return enchantingPower;
    }

    public int getEnchantingPower() {
        return ePower;
    }

    public int getTimeDivisor() {
        int x = (int) (Math.pow(2, (Math.log(((double) ePower) / 8) / Math.log(9))) * 2);
        return x > 0 ? x : 1;
    }

    public int getCraftingTicks() {
        return craftingTicks;
    }

    public int getBaseTicks(){
        return baseCraftingTicks;
    }

    private Block blockAbove(){
        return getWorld().getBlockState(getPos().up()).getBlock();
    }

}
