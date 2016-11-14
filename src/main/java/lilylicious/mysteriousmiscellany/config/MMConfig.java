package lilylicious.mysteriousmiscellany.config;

import lilylicious.mysteriousmiscellany.utils.MMLogger;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class MMConfig
{
	public static boolean enableDebugLog;
	
	public static void init(File configFile)
	{
		Configuration config = new Configuration(configFile);
		try
		{
			config.load();
			
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