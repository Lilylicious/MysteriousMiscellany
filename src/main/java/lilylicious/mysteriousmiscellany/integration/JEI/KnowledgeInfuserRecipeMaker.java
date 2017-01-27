package lilylicious.mysteriousmiscellany.integration.JEI;

import lilylicious.mysteriousmiscellany.gameObjs.recipes.InfuserRecipe;
import lilylicious.mysteriousmiscellany.registry.InfuserRecipeRegistry;

import java.util.ArrayList;
import java.util.List;

public class KnowledgeInfuserRecipeMaker {
    public static List<KnowledgeInfuserRecipeWrapper> getRecipes() {
        List<KnowledgeInfuserRecipeWrapper> recipes = new ArrayList<>();

        for (InfuserRecipe recipe : InfuserRecipeRegistry.getInfuserRecipes())
            recipes.add(new KnowledgeInfuserRecipeWrapper(recipe));


        return recipes;
    }
}
