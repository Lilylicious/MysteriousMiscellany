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

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

@JEIPlugin
public class MMJeiPlugin extends BlankModPlugin {

    @Override
    public void register(@Nonnull IModRegistry registry) {
        List<ItemStack> stainedGlass = new ArrayList<>();
        List<ItemStack> stainedGlassPanes = new ArrayList<>();
        List<ItemStack> hardenedClay = new ArrayList<>();
        List<ItemStack> carpets = new ArrayList<>();
        List<ItemStack> wool = new ArrayList<>();
        List<ItemStack> unHoes = new ArrayList<>();

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
            registry.addDescription(new ItemStack(ObjHandler.fishStopper), "jei.fishStopper");
        if(MMConfig.enableInfusedFishStopper)
            registry.addDescription(new ItemStack(ObjHandler.infusedFishStopper), "jei.infusedFishStopper");
        if(MMConfig.enableEnchantBooster)
            registry.addDescription(new ItemStack(ObjHandler.enchantBooster), "jei.enchantBooster");
        if(MMConfig.enableWaterStopper)
            registry.addDescription(new ItemStack(ObjHandler.waterStopper), "jei.waterStopper");
        if(MMConfig.enableCompressedBookshelf)
            registry.addDescription(new ItemStack(ObjHandler.compressedBookshelf), "jei.compressedBookshelf");
        if(MMConfig.enableDoubleCompressedBookshelf)
            registry.addDescription(new ItemStack(ObjHandler.doubleCompressedBookshelf), "jei.doubleCompressedBookshelf");
        if(MMConfig.enableEnchantingGenerator)
            registry.addDescription(new ItemStack(ObjHandler.enchantingGenerator), "jei.enchantingGenerator");
        if (MMConfig.enableOceanAnnihalator)
            registry.addDescription(new ItemStack(ObjHandler.iceSpreader), "jei.iceSpreader");
        if(MMConfig.enableSpawnPreventer)
            registry.addDescription(new ItemStack(ObjHandler.spawnPreventer), "jei.spawnPreventer");
        if (MMConfig.enableDyeRecipes) {
            registry.addDescription(stainedGlass, "jei.dyerecipes");
            registry.addDescription(stainedGlassPanes, "jei.dyerecipes");
            registry.addDescription(hardenedClay, "jei.dyerecipes");
            registry.addDescription(carpets, "jei.dyerecipes");
            registry.addDescription(wool, "jei.dyerecipes");
        }
        if(MMConfig.enableAutoCrafter)
            registry.addDescription(new ItemStack(ObjHandler.autoCrafter), "jei.autoCrafter");
        if(MMConfig.enableUnHoe)
            registry.addDescription(unHoes, "jei.unHoe");
        if(MMConfig.enableGrassStompingEnchant && MMConfig.enableStompingBoots)
            registry.addDescription(new ItemStack(ObjHandler.stompingBoots), "jei.stompingboots");


        registry.addRecipeCategories(new KnowledgeInfuserRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeHandlers(new KnowledgeInfuserRecipeHandler());
        registry.addRecipes(KnowledgeInfuserRecipeMaker.getRecipes());
        registry.addRecipeCategoryCraftingItem(new ItemStack(ObjHandler.knowledgeInfuser), KnowledgeInfuserRecipeCategory.NAME);

    }

}