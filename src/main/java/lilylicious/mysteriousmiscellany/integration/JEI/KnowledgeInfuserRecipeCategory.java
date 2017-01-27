package lilylicious.mysteriousmiscellany.integration.JEI;

import lilylicious.mysteriousmiscellany.gameObjs.ObjHandler;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.recipe.BlankRecipeCategory;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class KnowledgeInfuserRecipeCategory extends BlankRecipeCategory<KnowledgeInfuserRecipeWrapper> {

    public static final String NAME = "mysteriousmiscellany.knowledgeinfuser";

    private static final ItemStack INFUSER = new ItemStack(ObjHandler.knowledgeInfuser);

    public IDrawable background;

    public KnowledgeInfuserRecipeCategory(IGuiHelper helper) {
        background = helper.createDrawable(new ResourceLocation("mysteriousmiscellany", "textures/gui/infuser.png"), 0, 0, 96, 60);
    }

    @Nonnull
    @Override
    public String getUid() {
        return NAME;
    }

    @Nonnull
    @Override
    public String getTitle() {
        return I18n.format("tile.mm_knowledgeinfuser.name");
    }

    @Nonnull
    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull KnowledgeInfuserRecipeWrapper recipeWrapper) {
        recipeLayout.getItemStacks().init(0, true, 4, 18);
        recipeLayout.getItemStacks().set(0, recipeWrapper.getInputs());

        recipeLayout.getItemStacks().init(1, false, 66, 18);
        recipeLayout.getItemStacks().set(1, recipeWrapper.getOutputs());
    }
}
