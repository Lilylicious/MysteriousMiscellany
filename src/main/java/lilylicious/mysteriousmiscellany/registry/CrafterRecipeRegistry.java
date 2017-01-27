package lilylicious.mysteriousmiscellany.registry;

import lilylicious.mysteriousmiscellany.utils.MMLogger;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CrafterRecipeRegistry {

    private static final List<Item> crafterItems = new ArrayList<>();

    public static void load() {
        crafterItems.clear();

        for (Object obj : CraftingManager.getInstance().getRecipeList()) {
            IRecipe recipe = (IRecipe) obj;
            if (recipe.getRecipeSize() == 1) {
                if (obj instanceof ShapedRecipes) {
                    crafterItems.add(((ShapedRecipes) obj).recipeItems[0].getItem());
                } else if (obj instanceof ShapelessRecipes) {
                    crafterItems.add(((ShapelessRecipes) obj).recipeItems.get(0).getItem());
                } else if (obj instanceof ShapedOreRecipe) {
                    crafterItems.addAll(getRecipeSources((ShapedOreRecipe)obj));
                } else if (obj instanceof ShapelessOreRecipe) {
                    crafterItems.addAll(getRecipeSources((ShapelessOreRecipe)obj));
                }
            }

        }

        MMLogger.logInfo("Loaded recipes");
    }

    public static boolean itemValid(Item item){
        if(item instanceof ItemMultiTexture)
            for(Item listItem : crafterItems)
                if(((ItemMultiTexture) item).getBlock() == Block.getBlockFromItem(listItem))
                    return true;
        return crafterItems.contains(item);
    }

    @SuppressWarnings("unchecked")
    private static List<Item> getRecipeSources(ShapedOreRecipe recipe) {
        for(Object obj : recipe.getInput()) {
            if(obj != null) {
                if(obj instanceof List) {
                    return Collections.singletonList(((ItemStack)((List) obj).get(0)).getItem());
                } else if(obj instanceof ItemStack) {
                    return Collections.singletonList(((ItemStack) obj).getItem());
                } else if(obj instanceof Block) {
                    return Collections.singletonList(Item.getItemFromBlock((Block)obj));
                } else if(obj instanceof Item) {
                    return Collections.singletonList((Item) obj);
                }
            }
        }
        return Collections.emptyList();
    }

    @SuppressWarnings("unchecked")
    private static List<Item> getRecipeSources(ShapelessOreRecipe recipe) {
        for(Object obj : recipe.getInput()) {
            if(obj != null) {
                if(obj instanceof List) {
                    return Collections.singletonList(((ItemStack)((List) obj).get(0)).getItem());
                } else if(obj instanceof ItemStack) {
                    return Collections.singletonList(((ItemStack) obj).getItem());
                } else if(obj instanceof Block) {
                    return Collections.singletonList(Item.getItemFromBlock((Block)obj));
                } else if(obj instanceof Item) {
                    return Collections.singletonList((Item) obj);
                }
            }
        }
        return Collections.emptyList();
    }
}
