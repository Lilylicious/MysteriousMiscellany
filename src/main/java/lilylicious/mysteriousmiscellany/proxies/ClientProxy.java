package lilylicious.mysteriousmiscellany.proxies;

import lilylicious.mysteriousmiscellany.gameObjs.ObjHandler;
import lilylicious.mysteriousmiscellany.gameObjs.items.FishStopper;
import lilylicious.mysteriousmiscellany.utils.MMLogger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ClientProxy implements IProxy
{
	@Override
	public EntityPlayer getClientPlayer()
	{
		return FMLClientHandler.instance().getClientPlayerEntity();
	}

	@Override
	public void registerModels()
	{
		registerItem(ObjHandler.fishStopper);
	}

	private void registerItem(Item i)
	{
		registerItem(i, 0);
	}

	private void registerItem(Item i, int meta)
	{
		String name = ForgeRegistries.ITEMS.getKey(i).toString();
		ModelLoader.setCustomModelResourceLocation(i, meta, new ModelResourceLocation(name, "inventory"));
	}
}

