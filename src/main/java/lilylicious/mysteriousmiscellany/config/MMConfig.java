package lilylicious.mysteriousmiscellany.config;

import lilylicious.mysteriousmiscellany.utils.MMLogger;
import net.minecraftforge.common.config.Configuration;
import scala.Int;

import java.io.File;

public class MMConfig
{
	public static boolean enableDebugLog;
	public static boolean AOEMode;
	
	public static int highlightRadius;
	public static int destroyRadius;
    public static int durability;
	
	public static void init(File configFile)
	{
		Configuration config = new Configuration(configFile);
		try
		{
			config.load();
			
			//Difficulty
			AOEMode = config.getBoolean("AOEMode", "difficulty", false, "Transform blocks automatically");
			
			//Values (default, min, max)
			highlightRadius = config.getInt("highlightRadius", "values", 5, 1, 64, "Radius of highlight");
			destroyRadius = config.getInt("destroyRadius", "values", 3, 1, 64, "Radius of autoreplace");
            durability = config.getInt("durability", "values", 128, 0, Int.MaxValue(), "Durability of the tool. 0 for unlimited.");

			//Misc
			enableDebugLog = config.getBoolean("debugLogging", "misc", false, "Enable a more verbose debug logging");

			MMLogger.logInfo("Loaded configuration file.");
		} catch (Exception e)
		{
			MMLogger.logFatal("Caught exception while loading config file!");
			e.printStackTrace();
		} finally
		{
			if (config.hasChanged())
			{
				config.save();
			}
		}
	}
}