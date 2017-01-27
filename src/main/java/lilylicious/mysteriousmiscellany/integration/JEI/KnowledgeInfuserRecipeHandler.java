package lilylicious.mysteriousmiscellany.integration.JEI;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

import javax.annotation.Nonnull;

public class KnowledgeInfuserRecipeHandler implements IRecipeHandler<KnowledgeInfuserRecipeWrapper> {

    @Nonnull
    @Override
    public Class<KnowledgeInfuserRecipeWrapper> getRecipeClass() {
        return KnowledgeInfuserRecipeWrapper.class;
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid(@Nonnull KnowledgeInfuserRecipeWrapper recipe) {
        return KnowledgeInfuserRecipeCategory.NAME;
    }

    @Nonnull
    @Override
    public IRecipeWrapper getRecipeWrapper(@Nonnull KnowledgeInfuserRecipeWrapper recipe) {
        return recipe;
    }

    @Override
    public boolean isRecipeValid(@Nonnull KnowledgeInfuserRecipeWrapper recipe) {
        return true;
    }
}
