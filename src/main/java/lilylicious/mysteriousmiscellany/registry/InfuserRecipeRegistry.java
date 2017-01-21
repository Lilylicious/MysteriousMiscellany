package lilylicious.mysteriousmiscellany.registry;

import lilylicious.mysteriousmiscellany.gameObjs.ObjHandler;
import lilylicious.mysteriousmiscellany.utils.MMLogger;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import java.util.HashMap;

public class InfuserRecipeRegistry {

    private static final HashMap<Block, Block> infuserItems = new HashMap<>();

    public static void load() {
        infuserItems.clear();

        infuserItems.put(Blocks.LOG, ObjHandler.crystallizedLog);
        infuserItems.put(Blocks.SAND, Blocks.BRICK_BLOCK);

        MMLogger.logInfo("Loaded recipes");
    }


    public static boolean blockValid(Block block) {
        return infuserItems.containsKey(block);
    }

    public static Block getResult(Block block) {
        return infuserItems.get(block);
    }
}
