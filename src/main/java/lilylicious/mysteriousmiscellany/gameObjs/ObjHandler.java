package lilylicious.mysteriousmiscellany.gameObjs;

import lilylicious.mysteriousmiscellany.MMCore;
import lilylicious.mysteriousmiscellany.gameObjs.items.FishStopper;
import lilylicious.mysteriousmiscellany.utils.MMLogger;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ObjHandler {
    
    public static final Item fishStopper = new FishStopper();

    public static void register() {
        GameRegistry.register(fishStopper, new ResourceLocation(MMCore.MODID, fishStopper.getUnlocalizedName()));
    }
    
    public static void addRecipes(){
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(fishStopper), "SES", "ESE", "SES", 'S', "stone", 'E', "gemEmerald"));
    }
    
}
