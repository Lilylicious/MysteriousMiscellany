package lilylicious.mysteriousmiscellany.utils;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public final class Predicates {

    public static final Predicate<Block> IS_WATER = input -> input == Blocks.WATER || input == Blocks.FLOWING_WATER;

    public static final Predicate<Block> IS_DYEABLE = input -> input == Blocks.HARDENED_CLAY || input == Blocks.STAINED_GLASS || input == Blocks.STAINED_GLASS_PANE || input == Blocks.GLASS_PANE || input == Blocks.STAINED_HARDENED_CLAY || input == Blocks.GLASS || input == Blocks.CARPET || input == Blocks.WOOL;

    public static final BiPredicate<Block, Block> ICE_NOT_ICE = (current, target) -> current == Blocks.ICE && target != Blocks.ICE;
}
