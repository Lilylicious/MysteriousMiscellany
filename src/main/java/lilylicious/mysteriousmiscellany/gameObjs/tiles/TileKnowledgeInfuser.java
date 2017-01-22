package lilylicious.mysteriousmiscellany.gameObjs.tiles;

import lilylicious.mysteriousmiscellany.gameObjs.recipes.InfuserRecipe;
import lilylicious.mysteriousmiscellany.registry.InfuserRecipeRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;

public class TileKnowledgeInfuser extends TileEntity implements ITickable {

    private int craftingTicks = 0;
    private int ePower = 0;
    InfuserRecipe recipe;

    public TileKnowledgeInfuser() {
    }

    @Override
    public void update() {

        ePower = findEnchantingPower();

        if (InfuserRecipeRegistry.blockValid(blockAbove()))
            recipe = InfuserRecipeRegistry.getRecipe(blockAbove());
        else {
            craftingTicks = 0;
            return;
        }

        if (craftingTicks <= 0 && recipe != null && recipe.getePowerCost() <= getEnchantingPower())
            craftingTicks = recipe.getTickCost() / getTimeDivisor();
        else if (craftingTicks > 0)
            craftingTicks--;

        if (!getWorld().isRemote && InfuserRecipeRegistry.blockValid(blockAbove()) && recipe.getePowerCost() <= getEnchantingPower() && craftingTicks <= 0) {
            getWorld().setBlockState(getPos().up(), recipe.getResultBlock().getStateFromMeta(getWorld().getBlockState(getPos().up()).getBlock().getMetaFromState(getWorld().getBlockState(getPos().up()))), 2);
            getWorld().playEvent(2001, getPos().up(), Block.getStateId(recipe.getResultBlock().getDefaultState()));
        }

    }


    private int findEnchantingPower() {
        int enchantingPower = 0;

        for (BlockPos pos : BlockPos.getAllInBoxMutable(getPos().add(-1, 0, -1), getPos().add(1, 0, 1))) {
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

    private Block blockAbove() {
        return getWorld().getBlockState(getPos().up()).getBlock();
    }

    public InfuserRecipe getRecipe(){
        return recipe;
    }
}
