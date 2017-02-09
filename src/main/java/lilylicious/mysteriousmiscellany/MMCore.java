package lilylicious.mysteriousmiscellany;

import lilylicious.mysteriousmiscellany.config.MMConfig;
import lilylicious.mysteriousmiscellany.enchantment.Enchantments;
import lilylicious.mysteriousmiscellany.events.BonemealEvents;
import lilylicious.mysteriousmiscellany.events.InteractEvents;
import lilylicious.mysteriousmiscellany.events.SpawnEvents;
import lilylicious.mysteriousmiscellany.gameObjs.ObjHandler;
import lilylicious.mysteriousmiscellany.proxies.IProxy;
import lilylicious.mysteriousmiscellany.registry.CrafterRecipeRegistry;
import lilylicious.mysteriousmiscellany.registry.DyeRecipeRegistry;
import lilylicious.mysteriousmiscellany.registry.InfuserRecipeRegistry;
import lilylicious.mysteriousmiscellany.utils.GuiHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import java.io.File;

@SuppressWarnings("unused")
@Mod(modid = MMCore.MODID, name = MMCore.MODNAME, version = MMCore.VERSION)
public class MMCore {

    public static final String MODID = "MysteriousMiscellany";
    public static final String MODNAME = "Mysterious Miscellany";
    public static final String VERSION = "1.10.2-1.5.3";

    @Mod.Instance(MODID)
    public static MMCore instance;

    @SidedProxy(clientSide = "lilylicious.mysteriousmiscellany.proxies.ClientProxy", serverSide = "lilylicious.mysteriousmiscellany.proxies.ServerProxy")
    private static IProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MMConfig.init(new File(event.getModConfigurationDirectory(), "MysteriousMiscellany.cfg"));

        ObjHandler.register();
        Enchantments.init();
        ObjHandler.addRecipes();

        NetworkRegistry.INSTANCE.registerGuiHandler(MMCore.instance, new GuiHandler());

        MinecraftForge.EVENT_BUS.register(new SpawnEvents());
        MinecraftForge.EVENT_BUS.register(new BonemealEvents());
        MinecraftForge.EVENT_BUS.register(new InteractEvents());
        proxy.registerClientOnlyEvents();
        proxy.registerModels();
    }

    @SuppressWarnings("EmptyMethod")
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        CrafterRecipeRegistry.load();
        InfuserRecipeRegistry.load();
        DyeRecipeRegistry.load();
    }

}
