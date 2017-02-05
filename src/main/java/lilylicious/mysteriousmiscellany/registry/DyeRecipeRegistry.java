package lilylicious.mysteriousmiscellany.registry;

import lilylicious.mysteriousmiscellany.gameObjs.recipes.InWorldDyeRecipe;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class DyeRecipeRegistry {
    public static final List<InWorldDyeRecipe> inWorldDyeRecipes = new ArrayList<>();
    private static final List<IBlockState> glass = new ArrayList<>();
    private static final List<IBlockState> glassPanes = new ArrayList<>();
    private static final List<IBlockState> hardenedClays = new ArrayList<>();
    private static final List<IBlockState> carpets = new ArrayList<>();
    private static final List<IBlockState> wools = new ArrayList<>();

    public static void load() {
        loadBlockStates();

        for(int i = 0; i < 16; i++){
            addRecipe(new ItemStack(Items.DYE, 1, i), glass, Blocks.STAINED_GLASS.getStateFromMeta(15-i));
            addRecipe(new ItemStack(Items.DYE, 1, i), glassPanes, Blocks.STAINED_GLASS_PANE.getStateFromMeta(15-i));
            addRecipe(new ItemStack(Items.DYE, 1, i), hardenedClays, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(15-i));
            addRecipe(new ItemStack(Items.DYE, 1, i), carpets, Blocks.CARPET.getStateFromMeta(15-i));
            addRecipe(new ItemStack(Items.DYE, 1, i), wools, Blocks.WOOL.getStateFromMeta(15-i));
        }
    }


    public static void addRecipe(ItemStack interactItemStack, List<IBlockState> sourceBlockStates, IBlockState targetState) {
        inWorldDyeRecipes.add(new InWorldDyeRecipe(interactItemStack, sourceBlockStates, targetState));
    }

    private static void loadBlockStates() {

        glass.clear();
        glassPanes.clear();
        hardenedClays.clear();
        carpets.clear();
        wools.clear();

        glass.add(Blocks.GLASS.getDefaultState());
        glassPanes.add(Blocks.GLASS_PANE.getDefaultState());
        hardenedClays.add(Blocks.HARDENED_CLAY.getDefaultState());

        for (int i = 0; i < 16; i++) {
            glass.add(Blocks.STAINED_GLASS.getStateFromMeta(i));
            glassPanes.add(Blocks.STAINED_GLASS_PANE.getStateFromMeta(i));
            hardenedClays.add(Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(i));
            carpets.add(Blocks.CARPET.getStateFromMeta(i));
            wools.add(Blocks.WOOL.getStateFromMeta(i));
        }
    }

}
