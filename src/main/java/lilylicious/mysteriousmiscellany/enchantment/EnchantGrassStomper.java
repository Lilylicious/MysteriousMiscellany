package lilylicious.mysteriousmiscellany.enchantment;

import lilylicious.mysteriousmiscellany.utils.WorldHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class EnchantGrassStomper extends EnchantmentBase {

    EnchantGrassStomper() {
        super(Rarity.COMMON, EnumEnchantmentType.ARMOR_FEET, new EntityEquipmentSlot[]{EntityEquipmentSlot.FEET});
        setName("grassstomping");
        setRegistryName("grassstomping");
    }

    @SubscribeEvent
    public void onPlayerUpdate(TickEvent.PlayerTickEvent event) {

        EntityPlayer player = event.player;
        ItemStack boots = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
        Block target;

        if(boots.isEmpty() || !(boots.getItem() instanceof ItemArmor))
            return;


        if (!player.getEntityWorld().isRemote && EnchantmentHelper.getMaxEnchantmentLevel(this, player) > 0) {
            Iterable<BlockPos> blocks = WorldHelper.findBox(player.getPosition(), 3);

            for (BlockPos blockPos : blocks) {
                if (player.getEntityWorld().getBlockState(blockPos).getBlock() instanceof BlockBush) {
                    target = player.getEntityWorld().getBlockState(blockPos).getBlock();

                    if (target instanceof BlockTallGrass
                            || target instanceof BlockDoublePlant
                            && !target.toString().toLowerCase().contains("botania"))
                        player.getEntityWorld().destroyBlock(blockPos, false);
                    }
                }
            }
        }


    }
}
