package lilylicious.mysteriousmiscellany.gameObjs.items;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTool;

import javax.annotation.Nonnull;
import java.util.Set;

public class ToolMM extends ItemTool {

    protected ToolMM(float attackDamageIn, float attackSpeedIn, ToolMaterial materialIn, Set<Block> effectiveBlocksIn) {
        super(attackDamageIn, attackSpeedIn, materialIn, effectiveBlocksIn);
        this.setMaxStackSize(1);
    }

    @Nonnull
    @Override
    public Item setUnlocalizedName(@Nonnull String message) {
        return super.setUnlocalizedName("mm_" + message);
    }

}
