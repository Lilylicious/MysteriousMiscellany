package lilylicious.mysteriousmiscellany.gameObjs;

import lilylicious.mysteriousmiscellany.MMCore;
import lilylicious.mysteriousmiscellany.gameObjs.blocks.*;
import lilylicious.mysteriousmiscellany.gameObjs.items.*;
import lilylicious.mysteriousmiscellany.gameObjs.tiles.TileEnchantmentAir;
import lilylicious.mysteriousmiscellany.gameObjs.tiles.TileEnchantmentGenerator;
import lilylicious.mysteriousmiscellany.gameObjs.tiles.TileIceSpreader;
import lilylicious.mysteriousmiscellany.gameObjs.tiles.TileSpawnPreventer;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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
    public static final Item waterStopper = new WaterStopper();

    public static final Block enchantmentAir = new EnchantmentAir();
    public static final Block compressedBookshelf = new CompressedBookshelf();
    public static final Block doubleCompressedBookshelf = new DoubleCompressedBookshelf();
    public static final Block enchantingGenerator = new EnchantmentGenerator();
    public static final Block iceSpreader = new IceSpreader();
    public static final Block spawnPreventer = new SpawnPreventer();

    public static void register() {
        //Items
        GameRegistry.register(fishStopper, new ResourceLocation(MMCore.MODID, fishStopper.getUnlocalizedName()));
        GameRegistry.register(infusedFishStopper, new ResourceLocation(MMCore.MODID, infusedFishStopper.getUnlocalizedName()));
        GameRegistry.register(enchantBooster, new ResourceLocation(MMCore.MODID, enchantBooster.getUnlocalizedName()));
        GameRegistry.register(waterStopper, new ResourceLocation(MMCore.MODID, waterStopper.getUnlocalizedName()));

        //Blocks
        GameRegistry.register(enchantmentAir, new ResourceLocation(MMCore.MODID, enchantmentAir.getUnlocalizedName()));
        registerBlockWithItem(compressedBookshelf, compressedBookshelf.getUnlocalizedName());
        registerBlockWithItem(doubleCompressedBookshelf, doubleCompressedBookshelf.getUnlocalizedName());
        registerBlockWithItem(enchantingGenerator, enchantingGenerator.getUnlocalizedName());
        registerBlockWithItem(iceSpreader, iceSpreader.getUnlocalizedName());
        registerBlockWithItem(spawnPreventer, spawnPreventer.getUnlocalizedName());

        //Tiles
        GameRegistry.registerTileEntity(TileEnchantmentAir.class, "TileEnchantmentAir");
        GameRegistry.registerTileEntity(TileEnchantmentGenerator.class, "TileEnchantmentGenerator");
        GameRegistry.registerTileEntity(TileIceSpreader.class, "TileIceSpreader");
        GameRegistry.registerTileEntity(TileSpawnPreventer.class, "TileSpawnPreventer");
    }

    public static void addRecipes() {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(fishStopper), "SES", "ESE", "SES", 'S', "stone", 'E', "gemEmerald"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(infusedFishStopper), "RER", "EFE", "RER", 'F', new ItemStack(fishStopper, 1, OreDictionary.WILDCARD_VALUE), 'R', "blockRedstone", 'E', "gemEmerald"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(enchantBooster), "BBB", "BEB", "BBB", 'B', doubleCompressedBookshelf, 'E', "gemEmerald"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(waterStopper), "OBO", "BDB", "OBO", 'B', Items.BUCKET, 'D', "gemDiamond", 'O', Blocks.OBSIDIAN));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(compressedBookshelf), "BBB", "BBB", "BBB", 'B', Blocks.BOOKSHELF));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(doubleCompressedBookshelf), "BBB", "BBB", "BBB", 'B', compressedBookshelf));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(enchantingGenerator), "BDB", "ETE", "BOB", 'B', Blocks.BOOKSHELF, 'T', Blocks.ENCHANTING_TABLE, 'E', "gemEmerald", 'D', Blocks.DIAMOND_BLOCK, 'O', Blocks.OBSIDIAN));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(iceSpreader), "OIO", "IEI", "OIO", 'O', Blocks.OBSIDIAN, 'I', Blocks.ICE, 'E', "gemEmerald"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(spawnPreventer), "PDT", "EBE", "SGZ", 'P', Items.ENDER_PEARL, 'D', Blocks.DIAMOND_BLOCK, 'T', Items.GHAST_TEAR, 'E', "gemEmerald", 'B', Items.BONE, 'S', Items.STRING, 'G', Items.GUNPOWDER, 'Z', Items.ROTTEN_FLESH));
    }


    private static void registerObj(IForgeRegistryEntry<?> o, String name) {
        GameRegistry.register(o, new ResourceLocation(MMCore.MODID, name));
    }

    private static void registerBlockWithItem(Block b, String name) {
        registerObj(b, name);
        registerObj(new ItemBlock(b), name);
    }

}
