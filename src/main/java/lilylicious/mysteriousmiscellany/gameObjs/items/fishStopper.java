package lilylicious.mysteriousmiscellany.gameObjs.items;

import com.google.common.collect.ImmutableMap;
import lilylicious.mysteriousmiscellany.config.MMConfig;
import lilylicious.mysteriousmiscellany.gameObjs.ObjHandler;
import lilylicious.mysteriousmiscellany.utils.MMLogger;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.model.ISmartVariant;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import net.minecraft.block.BlockStone;
import net.minecraft.block.BlockSilverfish;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;

public class FishStopper extends ItemMM {

    public FishStopper() {
        super(0F, 4F, EnumHelper.addToolMaterial("fishStopper", 0, MMConfig.durability, 2.0F, 1F, 0), new HashSet<Block>());
        this.setUnlocalizedName("fishstopper");
        this.setMaxStackSize(1);
        this.setMaxDamage(MMConfig.durability);
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int invSlot, boolean par5) {


        EntityPlayer player = (EntityPlayer)entity;
        if(MMConfig.AOEMode){

            Iterable<BlockPos> iterable = findBox(player, MMConfig.destroyRadius);

            for (BlockPos blockPos : iterable) {
                IBlockState blockState = player.worldObj.getBlockState(blockPos);

                if (blockState.getBlock() == Blocks.MONSTER_EGG) {
                    if(replaceBlock(player, blockPos)){
                        stack.damageItem(1, player);
                    }
                }
            }
        }        
    }

    @Nonnull
    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing sideHit, float px, float py, float pz)
    {

        if (world.isRemote)
        {
            return EnumActionResult.SUCCESS;
        }

        RayTraceResult rtr = rayTrace(player.worldObj, player, player.isSneaking());

        if (rtr.getBlockPos() != null && !rtr.getBlockPos().equals(pos))
        {
            pos = rtr.getBlockPos();
            sideHit = rtr.sideHit;
        }
        
        if(replaceBlock(player, pos)){
            stack.damageItem(1, player);
        }
        
        return EnumActionResult.SUCCESS;
    }
    
    private boolean replaceBlock(EntityPlayer player, BlockPos pos){

        IBlockState blockState = player.worldObj.getBlockState(pos);
        if(blockState.getBlock() == Blocks.MONSTER_EGG &&
                !FMLCommonHandler.instance().getMinecraftServerInstance().isBlockProtected(player.worldObj, pos, player)){
            
            BlockSilverfish.EnumType type = ((BlockSilverfish.EnumType)blockState.getProperties().get(BlockSilverfish.VARIANT));
            IBlockState newBlockState = getNewBlock(type);
            
            BlockSnapshot before = BlockSnapshot.getBlockSnapshot(player.worldObj, pos);
            player.worldObj.setBlockState(pos, newBlockState);
            BlockEvent.PlaceEvent evt = new BlockEvent.PlaceEvent(before, Blocks.AIR.getDefaultState(), player);
            MinecraftForge.EVENT_BUS.post(evt);
            if (evt.isCanceled())
            {
                player.worldObj.restoringBlockSnapshots = true;
                before.restore(true, false);
                player.worldObj.restoringBlockSnapshots = false;
                return false;
            }

            return true;
        }
        return false;
    }
    
    private IBlockState getNewBlock(BlockSilverfish.EnumType type){

        return type.getModelBlock();
        
    }
        

    @SideOnly(Side.CLIENT)
    public void renderOverlay(RenderWorldLastEvent evt, EntityPlayerSP player) {

        Set<BlockPos> coordinates = findBlocks(player, MMConfig.highlightRadius);
        renderOutlines(evt, player, coordinates, 200, 230, 180);
    }

    private Iterable<BlockPos> findBox(EntityPlayer player, int radius){
        Iterable<BlockPos> iterable = null;

        int x = (int) Math.floor(player.posX);
        int y = (int) (player.posY - player.getYOffset());
        int z = (int) Math.floor(player.posZ);
        BlockPos pos = new BlockPos(x, y, z);

        return pos.getAllInBox(pos.add(-radius, -radius, -radius), pos.add(radius, radius, radius));
    }
    
    private Set<BlockPos> findBlocks(EntityPlayerSP player, int radius) {
       
        Set<BlockPos> coordinates = new HashSet<BlockPos>();

        Iterable<BlockPos> iterable = findBox(player, radius);

        for (BlockPos blockPos : iterable) {
            IBlockState blockState = player.worldObj.getBlockState(blockPos);

            if (blockState.getBlock() == Blocks.MONSTER_EGG) {
                
                coordinates.add(blockPos);
            }
        }

        return coordinates;
    }

    protected static void renderOutlines(RenderWorldLastEvent evt, EntityPlayerSP p, Set<BlockPos> coordinates, int r, int g, int b) {
        double doubleX = p.lastTickPosX + (p.posX - p.lastTickPosX) * evt.getPartialTicks();
        double doubleY = p.lastTickPosY + (p.posY - p.lastTickPosY) * evt.getPartialTicks();
        double doubleZ = p.lastTickPosZ + (p.posZ - p.lastTickPosZ) * evt.getPartialTicks();

        RenderHelper.disableStandardItemLighting();
        Minecraft.getMinecraft().entityRenderer.disableLightmap();
        GlStateManager.disableDepth();
        GlStateManager.disableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.disableAlpha();
        GlStateManager.depthMask(false);

        GlStateManager.pushMatrix();
        GlStateManager.translate(-doubleX, -doubleY, -doubleZ);

        renderOutlines(coordinates, r, g, b, 4);

        GlStateManager.popMatrix();

        RenderHelper.enableStandardItemLighting();
        Minecraft.getMinecraft().entityRenderer.enableLightmap();
        GlStateManager.enableDepth();
        GlStateManager.enableLighting();
        GlStateManager.enableAlpha();
        GlStateManager.depthMask(true);
        GlStateManager.enableTexture2D();
    }

    private static void renderOutlines(Set<BlockPos> coordinates, int r, int g, int b, int thickness) {
        Tessellator tessellator = Tessellator.getInstance();

        VertexBuffer buffer = tessellator.getBuffer();
        buffer.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION_COLOR);

//        GlStateManager.color(r / 255.0f, g / 255.0f, b / 255.0f);
        GL11.glLineWidth(thickness);

        for (BlockPos coordinate : coordinates) {
            float x = coordinate.getX();
            float y = coordinate.getY();
            float z = coordinate.getZ();

            renderHighLightedBlocksOutline(buffer, x, y, z, r / 255.0f, g / 255.0f, b / 255.0f, 1.0f); // .02f
        }
        tessellator.draw();
    }

    public static void renderHighLightedBlocksOutline(VertexBuffer buffer, float mx, float my, float mz, float r, float g, float b, float a) {
        buffer.pos(mx, my, mz).color(r, g, b, a).endVertex();
        buffer.pos(mx + 1, my, mz).color(r, g, b, a).endVertex();
        buffer.pos(mx, my, mz).color(r, g, b, a).endVertex();
        buffer.pos(mx, my + 1, mz).color(r, g, b, a).endVertex();
        buffer.pos(mx, my, mz).color(r, g, b, a).endVertex();
        buffer.pos(mx, my, mz + 1).color(r, g, b, a).endVertex();
        buffer.pos(mx + 1, my + 1, mz + 1).color(r, g, b, a).endVertex();
        buffer.pos(mx, my + 1, mz + 1).color(r, g, b, a).endVertex();
        buffer.pos(mx + 1, my + 1, mz + 1).color(r, g, b, a).endVertex();
        buffer.pos(mx + 1, my, mz + 1).color(r, g, b, a).endVertex();
        buffer.pos(mx + 1, my + 1, mz + 1).color(r, g, b, a).endVertex();
        buffer.pos(mx + 1, my + 1, mz).color(r, g, b, a).endVertex();

        buffer.pos(mx, my + 1, mz).color(r, g, b, a).endVertex();
        buffer.pos(mx, my + 1, mz + 1).color(r, g, b, a).endVertex();
        buffer.pos(mx, my + 1, mz).color(r, g, b, a).endVertex();
        buffer.pos(mx + 1, my + 1, mz).color(r, g, b, a).endVertex();

        buffer.pos(mx + 1, my, mz).color(r, g, b, a).endVertex();
        buffer.pos(mx + 1, my, mz + 1).color(r, g, b, a).endVertex();
        buffer.pos(mx + 1, my, mz).color(r, g, b, a).endVertex();
        buffer.pos(mx + 1, my + 1, mz).color(r, g, b, a).endVertex();

        buffer.pos(mx, my, mz + 1).color(r, g, b, a).endVertex();
        buffer.pos(mx + 1, my, mz + 1).color(r, g, b, a).endVertex();
        buffer.pos(mx, my, mz + 1).color(r, g, b, a).endVertex();
        buffer.pos(mx, my + 1, mz + 1).color(r, g, b, a).endVertex();
    }
}
