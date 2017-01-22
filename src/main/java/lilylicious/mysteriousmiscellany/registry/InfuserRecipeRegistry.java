package lilylicious.mysteriousmiscellany.registry;

import lilylicious.mysteriousmiscellany.gameObjs.ObjHandler;
import lilylicious.mysteriousmiscellany.gameObjs.recipes.InfuserRecipe;
import lilylicious.mysteriousmiscellany.utils.MMLogger;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InfuserRecipeRegistry {

    private static final List<InfuserRecipe> infuserRecipes = new ArrayList<>();

    public static void load() {
        infuserRecipes.clear();

        infuserRecipes.add(new InfuserRecipe(Blocks.LOG, ObjHandler.crystallizedLog, 0, 1));
        infuserRecipes.add(new InfuserRecipe(Blocks.SAND, Blocks.BRICK_BLOCK, 100, 2));

        MMLogger.logInfo("Loaded recipes");
    }


    public static boolean blockValid(Block block) {
        for(InfuserRecipe recipe : infuserRecipes)
            if(recipe.getSourceBlock() == block)
                return true;

        return false;
    }

    public static Block getResult(Block block) {
        for(InfuserRecipe recipe : infuserRecipes)
            if(recipe.getSourceBlock() == block)
                return recipe.getResultBlock();

        return null;
    }

    public static int getePowerCost(Block block){
        for(InfuserRecipe recipe : infuserRecipes)
            if(recipe.getSourceBlock() == block)
                return recipe.getePowerCost();

        return 0;
    }

    public static InfuserRecipe getRecipe(Block block){
        for(InfuserRecipe recipe : infuserRecipes)
            if(recipe.getSourceBlock() == block)
                return recipe;

        return null;
    }
}
