package lilylicious.mysteriousmiscellany.gameObjs.gui;

import lilylicious.mysteriousmiscellany.MMCore;
import lilylicious.mysteriousmiscellany.gameObjs.container.AutoCrafterContainer;
import lilylicious.mysteriousmiscellany.gameObjs.tiles.TileAutoCrafter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GUIAutoCrafter extends GuiContainer{

    private static final ResourceLocation texture = new ResourceLocation(MMCore.MODID.toLowerCase(), "textures/gui/autocrafter.png");

    public GUIAutoCrafter(InventoryPlayer invPlayer, TileAutoCrafter tile)
    {
        super(new AutoCrafterContainer(invPlayer, tile));
        this.xSize = 176;
        this.ySize = 186;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        GlStateManager.color(1, 1, 1, 1);
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        this.drawTexturedModalRect((width - xSize) / 2, (height - ySize) / 2, 0, 0, xSize, ySize);
    }
}
