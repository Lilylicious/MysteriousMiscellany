package lilylicious.mysteriousmiscellany.gameObjs.tiles;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import lilylicious.mysteriousmiscellany.gameObjs.recipes.InfuserRecipe;
import lilylicious.mysteriousmiscellany.registry.InfuserRecipeRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

import static net.minecraft.block.BlockLog.LOG_AXIS;

public class TileKnowledgeInfuser extends TileEntity implements ITickable, IEnergyReceiver {

    private int craftingTicks = 0;
    private int ePower = 0;
    private final EnergyStorage storage = new EnergyStorage(30000);
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
        else if (craftingTicks > 0 && storage.getEnergyStored() >= recipe.getRfPowerCost()) {
            craftingTicks--;

            if (!getWorld().isRemote)
                storage.extractEnergy(recipe.getRfPowerCost(), false);
        }
        if (!getWorld().isRemote) {
            if (InfuserRecipeRegistry.blockValid(blockAbove()) && recipe.getePowerCost() <= getEnchantingPower() && craftingTicks <= 0) {
                if (recipe.getSourceBlock() instanceof BlockLog)
                    getWorld().setBlockState(getPos().up(), recipe.getResultBlock().getDefaultState().withProperty(LOG_AXIS, getWorld().getBlockState(getPos().up()).getValue(LOG_AXIS)), 2);
                else
                    getWorld().setBlockState(getPos().up(), recipe.getResultBlock().getDefaultState(), 2);
                getWorld().playEvent(2001, getPos().up(), Block.getStateId(recipe.getResultBlock().getDefaultState()));
            }
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

    public InfuserRecipe getRecipe() {
        return recipe;
    }

    @Override
    public boolean canConnectEnergy(EnumFacing from) {
        return true;
    }

    @Override
    public int getEnergyStored(EnumFacing from) {
        return storage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(EnumFacing from) {
        return storage.getMaxEnergyStored();
    }

    @Override
    public int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate) {
        return storage.receiveEnergy(maxReceive, simulate);
    }
}
