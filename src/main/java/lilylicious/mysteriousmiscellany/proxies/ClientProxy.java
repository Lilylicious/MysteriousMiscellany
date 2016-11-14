package lilylicious.mysteriousmiscellany.proxies;

import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraft.entity.player.EntityPlayer;

public class ClientProxy implements IProxy
{
	@Override
	public EntityPlayer getClientPlayer()
	{
		return FMLClientHandler.instance().getClientPlayerEntity();
	}
}

