package lilylicious.mysteriousmiscellany.gameObjs.items;

import net.minecraft.item.Item;

import javax.annotation.Nonnull;

class ItemMM extends Item {

    @Nonnull
    @Override
    public Item setUnlocalizedName(@Nonnull String message) {
        return super.setUnlocalizedName("mm_" + message);
    }


}
