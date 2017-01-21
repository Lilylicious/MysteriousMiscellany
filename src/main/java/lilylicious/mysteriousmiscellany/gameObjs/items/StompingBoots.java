package lilylicious.mysteriousmiscellany.gameObjs.items;

import lilylicious.mysteriousmiscellany.config.MMConfig;
import lilylicious.mysteriousmiscellany.enchantment.Enchantments;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class StompingBoots extends ItemArmor {

    public StompingBoots() {
        super(ArmorMaterial.IRON, 2, EntityEquipmentSlot.FEET);
        setUnlocalizedName("stompingboots");

    }

    @Override
    public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn)
    {
        if(MMConfig.enableGrassStompingEnchant)
            stack.addEnchantment(Enchantments.grassStomping, 1);
    }

    @Nonnull
    @Override
    public Item setUnlocalizedName(@Nonnull String message) {
        return super.setUnlocalizedName("mm_" + message);
    }
}
