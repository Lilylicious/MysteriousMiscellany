package lilylicious.mysteriousmiscellany.proxies;

import net.minecraft.entity.player.EntityPlayer;

public interface IProxy
{
	EntityPlayer getClientPlayer();

	void registerModels();
}
