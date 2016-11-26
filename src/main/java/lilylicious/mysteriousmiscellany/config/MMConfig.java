package lilylicious.mysteriousmiscellany.config;

import lilylicious.mysteriousmiscellany.utils.MMLogger;
import net.minecraftforge.common.config.Configuration;
import scala.Int;

import java.io.File;

public class MMConfig {
    public static boolean enableDebugLog;
    public static boolean defaultAOEMode;

    public static int highlightRadius;
    public static int destroyRadius;
    public static int stopperDurability;
    public static int infusedStopperDurability;

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