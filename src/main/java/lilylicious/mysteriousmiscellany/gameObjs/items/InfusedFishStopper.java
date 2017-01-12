package lilylicious.mysteriousmiscellany.gameObjs.items;

import lilylicious.mysteriousmiscellany.config.MMConfig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

import javax.annotation.Nonnull;
import java.util.HashSet;

public class InfusedFishStopper extends FishStopper {

    public InfusedFishStopper() {
        super(0F, 4F, EnumHelper.addToolMaterial("fishStopper", 0, MMConfig.infusedStopperDurability, 2.0F, 1F, 0), new HashSet<>());
        this.setUnlocalizedName("infusedFishstopper");
        this.setMaxDamage(MMConfig.infusedStopperDurability);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
        if (!world.isRemote) {
            if (player.isSneaking() && !MMConfig.defaultAOEMode) {
                AOEMode = !AOEMode;
            }
        }

        return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
    }

}
