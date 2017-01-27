package lilylicious.mysteriousmiscellany.integration.JEI;

import lilylicious.mysteriousmiscellany.gameObjs.recipes.InfuserRecipe;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KnowledgeInfuserRecipeWrapper extends BlankRecipeWrapper {

    public InfuserRecipe recipe;

    public KnowledgeInfuserRecipeWrapper(InfuserRecipe recipe) {
        this.recipe = recipe;
    }
    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInput(ItemStack.class, new ItemStack(recipe.getSourceBlock()));
        ingredients.setOutput(ItemStack.class, new ItemStack(recipe.getResultBlock()));
    }

    @Nullable
    @Override
    public List<String> getTooltipStrings(int mouseX, int mouseY) {
        if (mouseX >= 32 && mouseX <= 51 && mouseY >= 22 && mouseY <= 30)
        {
            return Collections.singletonList(I18n.format("jei.mysteriousmiscellany.minimumepower", recipe.getePowerCost()));
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof KnowledgeInfuserRecipeWrapper)) {
            return false;
        }

        KnowledgeInfuserRecipeWrapper other = (KnowledgeInfuserRecipeWrapper) obj;

            if (!ItemStack.areItemStacksEqual(new ItemStack(recipe.getSourceBlock()), new ItemStack(other.recipe.getSourceBlock()))) {
                return false;

        }

        return ItemStack.areItemStacksEqual(new ItemStack(recipe.getResultBlock()), new ItemStack(other.recipe.getResultBlock()));
    }

    @Override
    public String toString() {
        return recipe.getSourceBlock() + " = " + recipe.getResultBlock();
    }
}
