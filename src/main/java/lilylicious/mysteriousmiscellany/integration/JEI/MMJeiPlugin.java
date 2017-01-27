package lilylicious.mysteriousmiscellany.integration.JEI;

import lilylicious.mysteriousmiscellany.config.MMConfig;
import lilylicious.mysteriousmiscellany.gameObjs.ObjHandler;
import lilylicious.mysteriousmiscellany.gameObjs.items.UnHoe;
import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

@JEIPlugin
public class MMJeiPlugin extends BlankModPlugin {

    @Override
    public void register(@Nonnull IModRegistry registry) {
        NonNullList<ItemStack> stainedGlass = NonNullList.create();
        NonNullList<ItemStack> stainedGlassPanes = NonNullList.create();
        NonNullList<ItemStack> hardenedClay = NonNullList.create();
        NonNullList<ItemStack> carpets = NonNullList.create();
        NonNullList<ItemStack> wool = NonNullList.create();
        NonNullList<ItemStack> unHoes = NonNullList.create();

        unHoes.add(new ItemStack(ObjHandler.woodUnHoe));
        unHoes.add(new ItemStack(ObjHandler.stoneUnHoe));
        unHoes.add(new ItemStack(ObjHandler.ironUnHoe));
        unHoes.add(new ItemStack(ObjHandler.goldUnHoe));
        unHoes.add(new ItemStack(ObjHandler.diamondUnHoe));

        Blocks.STAINED_GLASS.getSubBlocks(Item.getItemFromBlock(Blocks.STAINED_GLASS), null, stainedGlass);
        Blocks.STAINED_GLASS_PANE.getSubBlocks(Item.getItemFromBlock(Blocks.STAINED_GLASS_PANE), null, stainedGlassPanes);
        Blocks.STAINED_HARDENED_CLAY.getSubBlocks(Item.getItemFromBlock(Blocks.STAINED_HARDENED_CLAY), null, hardenedClay);
        Blocks.CARPET.getSubBlocks(Item.getItemFromBlock(Blocks.CARPET), null, carpets);
        Blocks.WOOL.getSubBlocks(Item.getItemFromBlock(Blocks.WOOL), null, wool);
        stainedGlass.add(new ItemStack(Blocks.GLASS));
        stainedGlassPanes.add(new ItemStack(Blocks.GLASS_PANE));
        hardenedClay.add(new ItemStack(Blocks.HARDENED_CLAY));



        if(MMConfig.enableFishstopper)
            registry.addDescription(new ItemStack(ObjHandler.fishStopper), "jei.mysteriousmiscellany.fishStopper");
        if(MMConfig.enableInfusedFishStopper)
            registry.addDescription(new ItemStack(ObjHandler.infusedFishStopper), "jei.mysteriousmiscellany.infusedFishStopper");
        if(MMConfig.enableEnchantBooster)
            registry.addDescription(new ItemStack(ObjHandler.enchantBooster), "jei.mysteriousmiscellany.enchantBooster");
        if(MMConfig.enableWaterStopper)
            registry.addDescription(new ItemStack(ObjHandler.waterStopper), "jei.mysteriousmiscellany.waterStopper");
        if(MMConfig.enableCompressedBookshelf)
            registry.addDescription(new ItemStack(ObjHandler.compressedBookshelf), "jei.mysteriousmiscellany.compressedBookshelf");
        if(MMConfig.enableDoubleCompressedBookshelf)
            registry.addDescription(new ItemStack(ObjHandler.doubleCompressedBookshelf), "jei.mysteriousmiscellany.doubleCompressedBookshelf");
        if(MMConfig.enableEnchantingGenerator)
            registry.addDescription(new ItemStack(ObjHandler.enchantingGenerator), "jei.mysteriousmiscellany.enchantingGenerator");
        if (MMConfig.enableOceanAnnihalator)
            registry.addDescription(new ItemStack(ObjHandler.iceSpreader), "jei.mysteriousmiscellany.iceSpreader");
        if(MMConfig.enableSpawnPreventer)
            registry.addDescription(new ItemStack(ObjHandler.spawnPreventer), "jei.mysteriousmiscellany.spawnPreventer");
        if (MMConfig.enableDyeRecipes) {
            registry.addDescription(stainedGlass, "jei.mysteriousmiscellany.dyerecipes");
            registry.addDescription(stainedGlassPanes, "jei.mysteriousmiscellany.dyerecipes");
            registry.addDescription(hardenedClay, "jei.mysteriousmiscellany.dyerecipes");
            registry.addDescription(carpets, "jei.mysteriousmiscellany.dyerecipes");
            registry.addDescription(wool, "jei.mysteriousmiscellany.dyerecipes");
        }
        if(MMConfig.enableAutoCrafter)
            registry.addDescription(new ItemStack(ObjHandler.autoCrafter), "jei.mysteriousmiscellany.autoCrafter");
        if(MMConfig.enableUnHoe)
            registry.addDescription(unHoes, "jei.mysteriousmiscellany.unHoe");
        if(MMConfig.enableGrassStompingEnchant && MMConfig.enableStompingBoots)
            registry.addDescription(new ItemStack(ObjHandler.stompingBoots), "jei.mysteriousmiscellany.stompingboots");


        registry.addDescription(new ItemStack(ObjHandler.knowledgeInfuser), "jei.mysteriousmiscellany.knowledgeinfuser");
        registry.addRecipeCategories(new KnowledgeInfuserRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeHandlers(new KnowledgeInfuserRecipeHandler());
        registry.addRecipes(KnowledgeInfuserRecipeMaker.getRecipes());
        registry.addRecipeCategoryCraftingItem(new ItemStack(ObjHandler.knowledgeInfuser), KnowledgeInfuserRecipeCategory.NAME);

    }

}