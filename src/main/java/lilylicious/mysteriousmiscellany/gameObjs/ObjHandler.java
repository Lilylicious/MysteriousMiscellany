package lilylicious.mysteriousmiscellany.gameObjs;

import lilylicious.mysteriousmiscellany.MMCore;
import lilylicious.mysteriousmiscellany.config.MMConfig;
import lilylicious.mysteriousmiscellany.gameObjs.blocks.*;
import lilylicious.mysteriousmiscellany.gameObjs.items.*;
import lilylicious.mysteriousmiscellany.gameObjs.recipes.DyeRecipes;
import lilylicious.mysteriousmiscellany.gameObjs.tiles.*;
import lilylicious.mysteriousmiscellany.utils.MMLogger;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ObjHandler {

    public static final Item fishStopper = new FishStopper();
    public static final Item infusedFishStopper = new InfusedFishStopper();
    public static final Item enchantBooster = new EnchantBooster();
    public static final Item waterStopper = new WaterStopper();

    public static final Item woodUnHoe = new UnHoe(Item.ToolMaterial.WOOD);
    public static final Item stoneUnHoe = new UnHoe(Item.ToolMaterial.STONE);
    public static final Item ironUnHoe = new UnHoe(Item.ToolMaterial.IRON);
    public static final Item goldUnHoe = new UnHoe(Item.ToolMaterial.GOLD);
    public static final Item diamondUnHoe = new UnHoe(Item.ToolMaterial.DIAMOND);

    public static final Item stompingBoots = new StompingBoots();

    public static final Block enchantmentAir = new EnchantmentAir();
    public static final Block compressedBookshelf = new CompressedBookshelf();
    public static final Block doubleCompressedBookshelf = new DoubleCompressedBookshelf();
    public static final Block enchantingGenerator = new EnchantmentGenerator();
    public static final Block iceSpreader = new IceSpreader();
    public static final Block spawnPreventer = new SpawnPreventer();
    public static final Block autoCrafter = new AutoCrafter();
    public static final Block knowledgeInfuser = new KnowledgeInfuser();
    public static final Block crystallizedLog = new CrystallizedLog();
    public static final Block crystallizedPlanks = new CrystallizedPlanks();

    public static void register() {
        //Items
        GameRegistry.register(fishStopper, new ResourceLocation(MMCore.MODID, fishStopper.getUnlocalizedName()));
        GameRegistry.register(infusedFishStopper, new ResourceLocation(MMCore.MODID, infusedFishStopper.getUnlocalizedName()));
        GameRegistry.register(enchantBooster, new ResourceLocation(MMCore.MODID, enchantBooster.getUnlocalizedName()));
        GameRegistry.register(waterStopper, new ResourceLocation(MMCore.MODID, waterStopper.getUnlocalizedName()));

        GameRegistry.register(woodUnHoe, new ResourceLocation(MMCore.MODID, woodUnHoe.getUnlocalizedName()));
        GameRegistry.register(stoneUnHoe, new ResourceLocation(MMCore.MODID, stoneUnHoe.getUnlocalizedName()));
        GameRegistry.register(ironUnHoe, new ResourceLocation(MMCore.MODID, ironUnHoe.getUnlocalizedName()));
        GameRegistry.register(goldUnHoe, new ResourceLocation(MMCore.MODID, goldUnHoe.getUnlocalizedName()));
        GameRegistry.register(diamondUnHoe, new ResourceLocation(MMCore.MODID, diamondUnHoe.getUnlocalizedName()));

        GameRegistry.register(stompingBoots, new ResourceLocation(MMCore.MODID, stompingBoots.getUnlocalizedName()));

        //Blocks
        GameRegistry.register(enchantmentAir, new ResourceLocation(MMCore.MODID, enchantmentAir.getUnlocalizedName()));
        registerBlockWithItem(compressedBookshelf, compressedBookshelf.getUnlocalizedName());
        registerBlockWithItem(doubleCompressedBookshelf, doubleCompressedBookshelf.getUnlocalizedName());
        registerBlockWithItem(enchantingGenerator, enchantingGenerator.getUnlocalizedName());
        registerBlockWithItem(iceSpreader, iceSpreader.getUnlocalizedName());
        registerBlockWithItem(spawnPreventer, spawnPreventer.getUnlocalizedName());
        registerBlockWithItem(autoCrafter, autoCrafter.getUnlocalizedName());
        registerBlockWithItem(knowledgeInfuser, knowledgeInfuser.getUnlocalizedName());
        registerBlockWithItem(crystallizedLog, crystallizedLog.getUnlocalizedName());
        registerBlockWithItem(crystallizedPlanks, crystallizedPlanks.getUnlocalizedName());

        //Tiles
        GameRegistry.registerTileEntity(TileEnchantmentAir.class, "TileEnchantmentAir");
        GameRegistry.registerTileEntity(TileEnchantmentGenerator.class, "TileEnchantmentGenerator");
        GameRegistry.registerTileEntity(TileIceSpreader.class, "TileIceSpreader");
        GameRegistry.registerTileEntity(TileSpawnPreventer.class, "TileSpawnPreventer");
        GameRegistry.registerTileEntity(TileAutoCrafter.class, "TileAutoCrafter");
        GameRegistry.registerTileEntity(TileKnowledgeInfuser.class, "TileKnowledgeInfuser");
    }

    public static void addRecipes() {
        if(MMConfig.enableFishstopper)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(fishStopper), "SES", "ESE", "SES", 'S', "stone", 'E', "gemEmerald"));
        if(MMConfig.enableInfusedFishStopper)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(infusedFishStopper), "RER", "EFE", "RER", 'F', new ItemStack(fishStopper, 1, OreDictionary.WILDCARD_VALUE), 'R', "blockRedstone", 'E', "gemEmerald"));
        if(MMConfig.enableEnchantBooster)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(enchantBooster), "BBB", "BEB", "BBB", 'B', doubleCompressedBookshelf, 'E', "gemEmerald"));
        if(MMConfig.enableWaterStopper)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(waterStopper), "OBO", "BDB", "OBO", 'B', Items.BUCKET, 'D', "gemDiamond", 'O', Blocks.OBSIDIAN));
        if(MMConfig.enableCompressedBookshelf)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(compressedBookshelf), "BBB", "BBB", "BBB", 'B', Blocks.BOOKSHELF));
        if(MMConfig.enableDoubleCompressedBookshelf)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(doubleCompressedBookshelf), "BBB", "BBB", "BBB", 'B', compressedBookshelf));
        if(MMConfig.enableEnchantingGenerator)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(enchantingGenerator), "BDB", "ETE", "BOB", 'B', Blocks.BOOKSHELF, 'T', Blocks.ENCHANTING_TABLE, 'E', "gemEmerald", 'D', Blocks.DIAMOND_BLOCK, 'O', Blocks.OBSIDIAN));
        if(MMConfig.enableOceanAnnihalator)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(iceSpreader), "OIO", "IEI", "OIO", 'O', Blocks.OBSIDIAN, 'I', Blocks.ICE, 'E', "gemEmerald"));
        if(MMConfig.enableSpawnPreventer)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(spawnPreventer), "PDT", "EBE", "SGZ", 'P', Items.ENDER_PEARL, 'D', Blocks.DIAMOND_BLOCK, 'T', Items.GHAST_TEAR, 'E', "gemEmerald", 'B', Items.BONE, 'S', Items.STRING, 'G', Items.GUNPOWDER, 'Z', Items.ROTTEN_FLESH));
        if(MMConfig.enableDyeRecipes)
            GameRegistry.addRecipe(new DyeRecipes());
        if(MMConfig.enableAutoCrafter)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(autoCrafter), "RSR", "SCS", "RSR", 'C', Blocks.CRAFTING_TABLE, 'S', "stone", 'R', "blockRedstone"));
        if(MMConfig.enableUnHoe){
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(woodUnHoe), "XSX", "XSX", "XMM", 'S', "stickWood", 'M', "plankWood"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(stoneUnHoe), "XSX", "XSX", "XMM", 'S', "stickWood", 'M', "cobblestone"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ironUnHoe), "XSX", "XSX", "XMM", 'S', "stickWood", 'M', "ingotIron"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(goldUnHoe), "XSX", "XSX", "XMM", 'S', "stickWood", 'M', "ingotGold"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(diamondUnHoe), "XSX", "XSX", "XMM", 'S', "stickWood", 'M', "gemDiamond"));
        }
        if(MMConfig.enableGrassStompingEnchant){

            if(MMConfig.enableStompingBoots){
                GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(stompingBoots), "OXO", "XBX", "OSO", 'S', Items.SHEARS, 'O', Blocks.OBSIDIAN, 'B', Items.IRON_BOOTS));
            }
        }

        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(crystallizedPlanks, 4), crystallizedLog));

    }


    private static void registerObj(IForgeRegistryEntry<?> o, String name) {
        GameRegistry.register(o, new ResourceLocation(MMCore.MODID, name));
    }

    private static void registerBlockWithItem(Block b, String name) {
        registerObj(b, name);
        registerObj(new ItemBlock(b), name);
    }

}
