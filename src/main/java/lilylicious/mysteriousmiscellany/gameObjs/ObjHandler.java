package lilylicious.mysteriousmiscellany.gameObjs;

import lilylicious.mysteriousmiscellany.MMCore;
import lilylicious.mysteriousmiscellany.gameObjs.blocks.CompressedBookshelf;
import lilylicious.mysteriousmiscellany.gameObjs.blocks.DoubleCompressedBookshelf;
import lilylicious.mysteriousmiscellany.gameObjs.blocks.EnchantmentAir;
import lilylicious.mysteriousmiscellany.gameObjs.blocks.EnchantmentGenerator;
import lilylicious.mysteriousmiscellany.gameObjs.items.EnchantBooster;
import lilylicious.mysteriousmiscellany.gameObjs.items.FishStopper;
import lilylicious.mysteriousmiscellany.gameObjs.items.InfusedFishStopper;
import lilylicious.mysteriousmiscellany.gameObjs.tiles.TileEnchantmentAir;
import lilylicious.mysteriousmiscellany.gameObjs.tiles.TileEnchantmentGenerator;
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
    public static final Block enchantingGenerator = new EnchantmentGenerator();

    public static void register() {
        //Items
        GameRegistry.register(fishStopper, new ResourceLocation(MMCore.MODID, fishStopper.getUnlocalizedName()));
        GameRegistry.register(infusedFishStopper, new ResourceLocation(MMCore.MODID, infusedFishStopper.getUnlocalizedName()));
        GameRegistry.register(enchantBooster, new ResourceLocation(MMCore.MODID, enchantBooster.getUnlocalizedName()));

        //Blocks
        GameRegistry.register(enchantmentAir, new ResourceLocation(MMCore.MODID, enchantmentAir.getUnlocalizedName()));
        registerBlockWithItem(compressedBookshelf, compressedBookshelf.getUnlocalizedName());
        registerBlockWithItem(doubleCompressedBookshelf, doubleCompressedBookshelf.getUnlocalizedName());
        registerBlockWithItem(enchantingGenerator, enchantingGenerator.getUnlocalizedName());

        //Tiles
        GameRegistry.registerTileEntity(TileEnchantmentAir.class, "TileEnchantmentAir");
        GameRegistry.registerTileEntity(TileEnchantmentGenerator.class, "TileEnchantmentGenerator");
    }

    public static void addRecipes() {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(fishStopper), "SES", "ESE", "SES", 'S', "stone", 'E', "gemEmerald"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(infusedFishStopper), "RER", "EFE", "RER", 'F', new ItemStack(fishStopper, 1, OreDictionary.WILDCARD_VALUE), 'R', "blockRedstone", 'E', "gemEmerald"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(enchantBooster), "BBB", "BEB", "BBB", 'B', doubleCompressedBookshelf, 'E', "gemEmerald"));


        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(compressedBookshelf), "BBB", "BBB", "BBB", 'B', Blocks.BOOKSHELF));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(doubleCompressedBookshelf), "BBB", "BBB", "BBB", 'B', compressedBookshelf));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(enchantingGenerator), "BDB", "ETE", "BOB", 'B', Blocks.BOOKSHELF, 'T', Blocks.ENCHANTING_TABLE, 'E', "gemEmerald", 'D', Blocks.DIAMOND_BLOCK, 'O', Blocks.OBSIDIAN));
    }


    private static void registerObj(IForgeRegistryEntry<?> o, String name) {
        GameRegistry.register(o, new ResourceLocation(MMCore.MODID, name));
    }

    private static void registerBlockWithItem(Block b, String name) {
        registerObj(b, name);
        registerObj(new ItemBlock(b), name);
    }

}
