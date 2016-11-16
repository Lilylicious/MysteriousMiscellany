package lilylicious.mysteriousmiscellany.gameObjs;

import lilylicious.mysteriousmiscellany.MMCore;
import lilylicious.mysteriousmiscellany.gameObjs.blocks.CompressedBookshelf;
import lilylicious.mysteriousmiscellany.gameObjs.blocks.DoubleCompressedBookshelf;
import lilylicious.mysteriousmiscellany.gameObjs.blocks.EnchantmentAir;
import lilylicious.mysteriousmiscellany.gameObjs.items.EnchantBooster;
import lilylicious.mysteriousmiscellany.gameObjs.items.FishStopper;
import lilylicious.mysteriousmiscellany.gameObjs.items.InfusedFishStopper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ObjHandler {

    public static final Item fishStopper = new FishStopper();
    public static final Item infusedFishStopper = new InfusedFishStopper();
    public static final Item enchantBooster = new EnchantBooster();

    public static final Block enchantmentAir = new EnchantmentAir();
    public static final Block compressedBookshelf = new CompressedBookshelf();
    public static final Block doubleCompressedBookshelf = new DoubleCompressedBookshelf();

    public static void register() {
        //Items
        GameRegistry.register(fishStopper, new ResourceLocation(MMCore.MODID, fishStopper.getUnlocalizedName()));
        GameRegistry.register(infusedFishStopper, new ResourceLocation(MMCore.MODID, infusedFishStopper.getUnlocalizedName()));
        GameRegistry.register(enchantBooster, new ResourceLocation(MMCore.MODID, enchantBooster.getUnlocalizedName()));

        //Blocks
        GameRegistry.register(enchantmentAir, new ResourceLocation(MMCore.MODID, enchantmentAir.getUnlocalizedName()));
        registerBlockWithItem(compressedBookshelf, compressedBookshelf.getUnlocalizedName());
        registerBlockWithItem(doubleCompressedBookshelf, doubleCompressedBookshelf.getUnlocalizedName());
    }

    public static void addRecipes() {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(fishStopper), "SES", "ESE", "SES", 'S', "stone", 'E', "gemEmerald"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(infusedFishStopper), "RER", "EFE", "RER", 'F', new ItemStack(fishStopper, 1, OreDictionary.WILDCARD_VALUE), 'R', "blockRedstone", 'E', "gemEmerald"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(enchantBooster), "BBB", "BEB", "BBB", 'B', doubleCompressedBookshelf, 'E', "gemEmerald"));


        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(compressedBookshelf), "BBB", "BBB", "BBB", 'B', new ItemStack(Blocks.BOOKSHELF)));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(doubleCompressedBookshelf), "BBB", "BBB", "BBB", 'B', compressedBookshelf));
    }


    private static void registerObj(IForgeRegistryEntry<?> o, String name) {
        GameRegistry.register(o, new ResourceLocation(MMCore.MODID, name));
    }

    private static void registerBlockWithItem(Block b, String name) {
        registerObj(b, name);
        registerObj(new ItemBlock(b), name);
    }

}
