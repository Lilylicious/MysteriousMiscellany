package lilylicious.mysteriousmiscellany.proxies;

import lilylicious.mysteriousmiscellany.events.PlaySoundEvents;
import lilylicious.mysteriousmiscellany.events.PlayerEvents;
import lilylicious.mysteriousmiscellany.events.RenderEvents;
import lilylicious.mysteriousmiscellany.gameObjs.ObjHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ClientProxy implements IProxy {
    @Override
    public EntityPlayer getClientPlayer() {
        return FMLClientHandler.instance().getClientPlayerEntity();
    }

    @Override
    public void registerClientOnlyEvents() {
        MinecraftForge.EVENT_BUS.register(new RenderEvents());
        MinecraftForge.EVENT_BUS.register(new PlaySoundEvents());
        MinecraftForge.EVENT_BUS.register(new PlayerEvents());
    }

    @Override
    public void registerModels() {
        registerItem(ObjHandler.fishStopper);
        registerItem(ObjHandler.infusedFishStopper);
        registerItem(ObjHandler.enchantBooster);
        registerItem(ObjHandler.waterStopper);

        registerItem(ObjHandler.woodUnHoe);
        registerItem(ObjHandler.stoneUnHoe);
        registerItem(ObjHandler.ironUnHoe);
        registerItem(ObjHandler.goldUnHoe);
        registerItem(ObjHandler.diamondUnHoe);

        registerBlock(ObjHandler.compressedBookshelf);
        registerBlock(ObjHandler.doubleCompressedBookshelf);
        registerBlock(ObjHandler.enchantingGenerator);
        registerBlock(ObjHandler.iceSpreader);
        registerBlock(ObjHandler.spawnPreventer);
        registerBlock(ObjHandler.autoCrafter);
    }

    private void registerItem(Item i) {
        registerItem(i, 0);
    }

    private void registerItem(Item i, int meta) {
        String name = ForgeRegistries.ITEMS.getKey(i).toString();
        ModelLoader.setCustomModelResourceLocation(i, meta, new ModelResourceLocation(name, "inventory"));
    }

    private void registerBlock(Block b) {
        String name = ForgeRegistries.BLOCKS.getKey(b).toString();
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(b), 0, new ModelResourceLocation(name, "inventory"));
    }
}

