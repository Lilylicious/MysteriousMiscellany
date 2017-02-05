package lilylicious.mysteriousmiscellany.gameObjs.recipes;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class InWorldDyeRecipe {
    private ItemStack interactItem;
    private List<IBlockState> sourceBlockStates = new ArrayList<>();
    private IBlockState resultState;

    public InWorldDyeRecipe(ItemStack interactItem, List<IBlockState> sourceBlockStates, IBlockState resultState) {
        this.interactItem = interactItem;
        this.sourceBlockStates = sourceBlockStates;
        this.resultState = resultState;
    }

    public ItemStack getInteractItem() {
        return interactItem;
    }

    public void setInteractItem(ItemStack interactItem) {
        this.interactItem = interactItem;
    }

    public List<IBlockState> getSourceBlockStates() {
        return sourceBlockStates;
    }

    public void setSourceBlockStates(List<IBlockState> sourceBlockStates) {
        this.sourceBlockStates = sourceBlockStates;
    }

    public IBlockState getResultState() {
        return resultState;
    }

    public void setResultState(IBlockState resultState) {
        this.resultState = resultState;
    }
}
