package lilylicious.mysteriousmiscellany.gameObjs.tiles;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import lilylicious.mysteriousmiscellany.gameObjs.ObjHandler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.ForgeHooks;

public class TileEnchantmentGenerator extends TileEntity implements IEnergyProvider, ITickable {

    public final EnergyStorage storage = new EnergyStorage(10000);
    private int power = 0;
    private int generated = 0;

    @Override
    public void update() {

        if (isWorking()) {

            if (!this.getWorld().isRemote) {
                power = 0;
                for (int j = -1; j <= 1; ++j) {
                    for (int k = -1; k <= 1; ++k) {
                        if (j != 0 || k != 0) {
                            power += ForgeHooks.getEnchantPower(this.getWorld(), this.getPos().add(k * 2, 0, j * 2));
                            power += ForgeHooks.getEnchantPower(this.getWorld(), this.getPos().add(k * 2, 1, j * 2));
                            if (k != 0 && j != 0) {
                                power += ForgeHooks.getEnchantPower(this.getWorld(), this.getPos().add(k * 2, 0, j));
                                power += ForgeHooks.getEnchantPower(this.getWorld(), this.getPos().add(k * 2, 1, j));
                                power += ForgeHooks.getEnchantPower(this.getWorld(), this.getPos().add(k, 0, j * 2));
                                power += ForgeHooks.getEnchantPower(this.getWorld(), this.getPos().add(k, 1, j * 2));
                            }
                        }
                    }
                }


                //Thank you Blubberbub for figuring out this equation!
                if (power > 0)
                    generated = (int) (Math.pow(2, (Math.log(((double) power) / 32) / Math.log(9))) * 10);

            }

            if (power > 0) {
                this.storage.receiveEnergy(generated, false);
            }

            if (!this.getWorld().isRemote && this.storage.getEnergyStored() > 0) {
                for (EnumFacing side : EnumFacing.VALUES) {
                    if (this.getWorld().getTileEntity(this.getPos().add(side.getDirectionVec())) != null) {
                        TileEntity tileDestination = this.getWorld().getTileEntity(this.getPos().add(side.getDirectionVec()));

                        if (this instanceof IEnergyProvider && tileDestination instanceof IEnergyReceiver) {
                            IEnergyProvider from = this;
                            IEnergyReceiver to = (IEnergyReceiver) tileDestination;

                            int extract = from.extractEnergy(side, 40, true);

                            if (extract > 0 && to.canConnectEnergy(side.getOpposite())) {
                                int result = to.receiveEnergy(side.getOpposite(), extract, false);
                                from.extractEnergy(side, result, false);
                            }
                        }
                    }
                }
            }

        }
    }

    @Override
    public boolean canConnectEnergy(EnumFacing from) {
        return true;
    }

    @Override
    public int getEnergyStored(EnumFacing from) {
        return this.storage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(EnumFacing from) {
        return this.storage.getMaxEnergyStored();
    }

    @Override
    public int extractEnergy(EnumFacing from, int maxExtract, boolean simulate) {
        return this.storage.extractEnergy(maxExtract, simulate);
    }

    public int getEnergyPerTick() {
        return generated;
    }

    public boolean isWorking() {
        return this.getWorld().getBlockState(this.getPos().add(0, -1, 0)).getBlock() != ObjHandler.enchantingGenerator
                && this.getWorld().getBlockState(this.getPos().add(0, 1, 0)).getBlock() != ObjHandler.enchantingGenerator
                && this.getWorld().getBlockState(this.getPos().add(1, 0, 0)).getBlock() != ObjHandler.enchantingGenerator
                && this.getWorld().getBlockState(this.getPos().add(0, 0, 1)).getBlock() != ObjHandler.enchantingGenerator
                && this.getWorld().getBlockState(this.getPos().add(-1, 0, 0)).getBlock() != ObjHandler.enchantingGenerator
                && this.getWorld().getBlockState(this.getPos().add(0, 0, -1)).getBlock() != ObjHandler.enchantingGenerator;
    }

}
