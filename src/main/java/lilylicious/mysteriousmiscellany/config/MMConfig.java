package lilylicious.mysteriousmiscellany.config;

import lilylicious.mysteriousmiscellany.utils.MMLogger;
import net.minecraftforge.common.config.Configuration;
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