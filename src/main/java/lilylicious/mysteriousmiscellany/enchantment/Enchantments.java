package lilylicious.mysteriousmiscellany.enchantment;

import lilylicious.mysteriousmiscellany.config.MMConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Enchantments {

    public static Enchantment grassStomping = new EnchantGrassStomper();

    public static void init() {

        if (MMConfig.enableGrassStompingEnchant) {
            MinecraftForge.EVENT_BUS.register(grassStomping);
            GameRegistry.register(grassStomping);
        }
    }
}
