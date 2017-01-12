package lilylicious.mysteriousmiscellany.config;

import lilylicious.mysteriousmiscellany.gameObjs.recipes.DyeRecipes;
import lilylicious.mysteriousmiscellany.utils.MMLogger;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import scala.Int;

import java.io.File;

public class MMConfig {
    public static boolean enableDebugLog;
    public static boolean defaultAOEMode;

    public static boolean preventServerWideBossSounds;
    public static boolean preventPortalSounds;
    public static boolean preventPortalArrivalSounds;
    public static boolean preventVillagerHhms;
    public static boolean preventCatMeows;
    public static boolean preventAnimalSteps;
    public static boolean preventChestSounds;
    public static boolean preventDoorSounds;

    public static int highlightRadius;
    public static int destroyRadius;
    public static int stopperDurability;
    public static int infusedStopperDurability;
    public static int spawnPreventionRadius;
    public static int iceSpreaderRadius;

    public static boolean enableFishstopper;
    public static boolean enableInfusedFishStopper;
    public static boolean enableEnchantBooster;
    public static boolean enableWaterStopper;
    public static boolean enableCompressedBookshelf;
    public static boolean enableDoubleCompressedBookshelf;
    public static boolean enableEnchantingGenerator;
    public static boolean enableIceSpreader;
    public static boolean enableSpawnPreventer;
    public static boolean enableAutoCrafter;
    public static boolean enableDyeRecipes;

    public static void init(File configFile) {
        Configuration config = new Configuration(configFile);
        try {
            config.load();

            //Difficulty
            defaultAOEMode = config.getBoolean("AOEMode", "difficulty", false, "Make AOEMode default for both fishstoppers");

            //Values (default, min, max)
            highlightRadius = config.getInt("highlightRadius", "values", 5, 1, 64, "Radius of highlight");
            destroyRadius = config.getInt("destroyRadius", "values", 3, 1, 64, "Radius of autoreplace");
            stopperDurability = config.getInt("stopperDurability", "values", 128, 0, Int.MaxValue(), "Durability of the tool. 0 for unlimited.");
            infusedStopperDurability = config.getInt("infusedStopperDurability", "values", 256, 0, Int.MaxValue(), "Durability of the tool. 0 for unlimited.");
            spawnPreventionRadius = config.getInt("spawnPreventionRadius", "values", 32, 0, 128, "Radius for spawn prevention.");
            spawnPreventionRadius = config.getInt("iceSpreaderRadius", "values", 16, 0, 128, "Radius for the ice spreader");

            //Sounds
            preventServerWideBossSounds = config.getBoolean("preventServerWideBossSounds", "sounds", true, "Prevent dragons from playing death sounds outside the end.");
            preventPortalSounds = config.getBoolean("preventPortalSounds", "sounds", false, "Prevent the nether portal from making noises.");
            preventPortalArrivalSounds = config.getBoolean("preventPortalArrivalSounds", "sounds", false, "Prevent the nether portal from making an ungodly loud noise upon arrival.");
            preventVillagerHhms = config.getBoolean("preventVillagerHhms", "sounds", false, "Prevent villagers from hmhing");
            preventAnimalSteps = config.getBoolean("preventAnimalSteps", "sounds", false, "Prevent animals from making walky sounds.");
            preventCatMeows = config.getBoolean("preventCatMeows", "sounds", false, "Prevent cats from meowing.");
            preventChestSounds = config.getBoolean("preventChestSounds", "sounds", false, "Prevent chest sounds.");
            preventDoorSounds = config.getBoolean("preventDoorSounds", "sounds", false, "Prevent door sounds.");

            //Misc
            enableDebugLog = config.getBoolean("debugLogging", "misc", false, "Enable a more verbose debug logging");

            //Items
            enableFishstopper = config.getBoolean("enableFishstopper", "items", true, "Enable the fishstopper");
            enableInfusedFishStopper = config.getBoolean("enableInfusedFishStopper", "items", true, "Enable the infused fishstopper");
            enableEnchantBooster = config.getBoolean("enableEnchantBooster", "items", true, "Enable the enchantbooster");
            enableWaterStopper = config.getBoolean("enableWaterStopper", "items", true, "Enable the water stopper");
            enableCompressedBookshelf = config.getBoolean("enableCompressedBookshelf", "items", true, "Enable the compressed bookshelf.");
            enableDoubleCompressedBookshelf = config.getBoolean("enableDoubleCompressedBookshelf", "items", true, "Enable the double compressed bookshelf.");
            enableEnchantingGenerator = config.getBoolean("enableEnchantingGenerator", "items", true, "Enable the enchanting generator");
            enableIceSpreader = config.getBoolean("enableOceanAnnihalator", "items", true, "Enable the ocean annihalator");
            enableSpawnPreventer = config.getBoolean("enableSpawnPreventer", "items", true, "Enable the spawn preventer");
            enableAutoCrafter = config.getBoolean("enableAutoCrafter", "items", true, "Enable the auto crafter");
            enableDyeRecipes = config.getBoolean("enableDyeRecipes", "items", true, "Enable the dye recipes");

            MMLogger.logInfo("Loaded configuration file.");
        } catch (Exception e) {
            MMLogger.logFatal("Caught exception while loading config file!");
            e.printStackTrace();
        } finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }
}