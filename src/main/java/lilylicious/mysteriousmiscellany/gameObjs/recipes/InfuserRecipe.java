package lilylicious.mysteriousmiscellany.gameObjs.recipes;

import lilylicious.mysteriousmiscellany.config.MMConfig;
import net.minecraft.block.Block;

public class InfuserRecipe {

    private final Block sourceBlock;
    private final Block resultBlock;
    private final int tickCost;
    private final int ePowerCost;
    private final int rfPowerCost;

    public InfuserRecipe(Block source, Block result, int enchantingPowerCost, int rfPowerCost, int tickMultiplier){
        sourceBlock = source;
        resultBlock = result;
        ePowerCost = enchantingPowerCost;
        tickCost = MMConfig.baseTickCost * tickMultiplier;
        this.rfPowerCost = rfPowerCost;
    }

    public Block getSourceBlock() {
        return sourceBlock;
    }

    public Block getResultBlock() {
        return resultBlock;
    }

    public int getTickCost() {
        return tickCost;
    }

    public int getePowerCost() {
        return ePowerCost;
    }

    public int getRfPowerCost() {
        return rfPowerCost;
    }
}
