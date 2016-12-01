package lilylicious.mysteriousmiscellany;

import lilylicious.mysteriousmiscellany.config.MMConfig;
import lilylicious.mysteriousmiscellany.enchantment.Enchantments;
import lilylicious.mysteriousmiscellany.events.RenderEvents;
import lilylicious.mysteriousmiscellany.gameObjs.ObjHandler;
import lilylicious.mysteriousmiscellany.proxies.IProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

@Mod(modid = MMCore.MODID, name = MMCore.MODNAME, version = MMCore.VERSION)
public class MMCore {

    public static final String MODID = "MysteriousMiscellany";
    public static final String MODNAME = "Mysterious Miscellany";
    public static final String VERSION = "1.10.2-1.2.1";

    @Mod.Instance(MODID)
    public static MMCore instance;

    @SidedProxy(clientSide = "lilylicious.mysteriousmiscellany.proxies.ClientProxy", serverSide = "lilylicious.mysteriousmiscellany.proxies.ServerProxy")
    public static IProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MMConfig.init(new File(event.getModConfigurationDirectory(), "MysteriousMiscellany.cfg"));

        MinecraftForge.EVENT_BUS.register(new RenderEvents());

        ObjHandler.register();
        Enchantments.init();
        ObjHandler.addRecipes();

        proxy.registerModels();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

    }

}
