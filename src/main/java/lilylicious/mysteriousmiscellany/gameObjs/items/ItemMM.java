package lilylicious.mysteriousmiscellany.gameObjs.items;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTool;

import javax.annotation.Nonnull;
import java.util.Set;

public class ItemMM extends ItemTool {

    protected ItemMM(float attackDamageIn, float attackSpeedIn, ToolMaterial materialIn, Set<Block> effectiveBlocksIn) {
        super(attackDamageIn, attackSpeedIn, materialIn, effectiveBlocksIn);
    }

    @Nonnull
    @Override
    public Item setUnlocalizedName(@Nonnull String message)
    {
        return super.setUnlocalizedName("mm_" + message);
    }
    
}
