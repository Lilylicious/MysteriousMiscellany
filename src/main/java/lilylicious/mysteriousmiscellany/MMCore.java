package lilylicious.mysteriousmiscellany;

import lilylicious.mysteriousmiscellany.proxies.IProxy;
import net.minecraft.init.Blocks;
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
    public static final String VERSION = "@VERSION@";

    @Mod.Instance(MODID)
    public static MMCore instance;

    @SidedProxy(clientSide = "lilylicious.mysteriousmiscellany.proxies.ClientProxy", serverSide = "moze_intel.projecte.proxies.ServerProxy")
    public static IProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        //TODO: check how to do single file config without dir
        //MMConfig.init(new File(CONFIG_DIR, "ThaumicEquivalency.cfg"));

        //proxy.registerClientOnlyEvents();
    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        // some example code
        System.out.println("DIRT BLOCK >> " + Blocks.DIRT.getUnlocalizedName());
    }

}
