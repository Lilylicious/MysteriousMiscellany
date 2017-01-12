package lilylicious.mysteriousmiscellany.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Enchantments {

    public static void init() {
        Enchantment grassStomping = new EnchantGrassStomper();
        MinecraftForge.EVENT_BUS.register(grassStomping);
        GameRegistry.register(grassStomping);
    }
}
