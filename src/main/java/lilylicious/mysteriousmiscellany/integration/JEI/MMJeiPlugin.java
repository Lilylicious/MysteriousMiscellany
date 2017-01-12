package lilylicious.mysteriousmiscellany.integration.JEI;

import lilylicious.mysteriousmiscellany.config.MMConfig;
import lilylicious.mysteriousmiscellany.gameObjs.ObjHandler;
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
        Blocks.STAINED_GLASS.getSubBlocks(Item.getItemFromBlock(Blocks.STAINED_GLASS), null, stainedGlass);
        Blocks.STAINED_GLASS_PANE.getSubBlocks(Item.getItemFromBlock(Blocks.STAINED_GLASS_PANE), null, stainedGlassPanes);
        Blocks.STAINED_HARDENED_CLAY.getSubBlocks(Item.getItemFromBlock(Blocks.STAINED_HARDENED_CLAY), null, hardenedClay);

        if (MMConfig.enableDyeRecipes) {
            registry.addDescription(stainedGlass, "jei.dyerecipes");
            registry.addDescription(stainedGlassPanes, "jei.dyerecipes");
            registry.addDescription(hardenedClay, "jei.dyerecipes");
        }

        if (MMConfig.enableIceSpreader)
            registry.addDescription(new ItemStack(ObjHandler.iceSpreader), "jei.iceSpreader");

        if(MMConfig.enableAutoCrafter)
            registry.addDescription(new ItemStack(ObjHandler.autoCrafter), "jei.autoCrafter");

    }

}