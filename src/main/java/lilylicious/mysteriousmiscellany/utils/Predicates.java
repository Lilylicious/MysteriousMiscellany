package lilylicious.mysteriousmiscellany.utils;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public final class Predicates {

    public static final Predicate<Block> IS_WATER = input -> input == Blocks.WATER || input == Blocks.FLOWING_WATER;

    public static final BiPredicate<Block, Block> ICE_NOT_ICE = (current, target) -> current == Blocks.ICE && target != Blocks.ICE;
}
