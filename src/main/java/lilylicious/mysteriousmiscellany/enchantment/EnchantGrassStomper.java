package lilylicious.mysteriousmiscellany.enchantment;

import lilylicious.mysteriousmiscellany.utils.WorldHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class EnchantGrassStomper extends EnchantmentBase {

    EnchantGrassStomper() {
        super(Rarity.COMMON, EnumEnchantmentType.ARMOR_FEET, new EntityEquipmentSlot[]{EntityEquipmentSlot.FEET});
        setName("grassStomping");
        setRegistryName("grassStomping");
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        EntityPlayer player = event.player;
        ItemStack boots = null;
        Block target;

        for (ItemStack stack : player.getArmorInventoryList()) {
            if (stack != null && player.getEntityWorld().isRemote && stack.getItem() instanceof ItemArmor && ((ItemArmor) stack.getItem()).getEquipmentSlot().ordinal() == 2)
                boots = stack;
        }


        if (boots != null && EnchantmentHelper.getMaxEnchantmentLevel(this, player) > 0) {
            Iterable<BlockPos> blocks = WorldHelper.findBox(player.getPosition(), 3);

            for (BlockPos blockPos : blocks) {
                if (player.getEntityWorld().getBlockState(blockPos).getBlock() instanceof BlockBush) {
                    target = player.getEntityWorld().getBlockState(blockPos).getBlock();

                    if (target instanceof BlockTallGrass
                            || target instanceof BlockDoublePlant)
                        player.getEntityWorld().destroyBlock(blockPos, false);

                }
            }
        }


    }
}
