package WayofTime.alchemicalWizardry.client;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import WayofTime.alchemicalWizardry.ModItems;
import WayofTime.alchemicalWizardry.common.items.armour.BoundArmour;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class POVArmourModelWrapper extends ModelRenderer 
{
    private final ModelRenderer armModel;
    private final ResourceLocation resource;

    public POVArmourModelWrapper(ModelBase model) 
    {
        super(model);
        ItemStack plateStack = new ItemStack(ModItems.boundPlate);
        ModelBiped bipedModel = ((BoundArmour)ModItems.boundPlate).getArmorModel(Minecraft.getMinecraft().thePlayer, plateStack, 1);
        armModel = bipedModel.bipedRightArm;
        resource = new ResourceLocation(((BoundArmour)ModItems.boundPlate).getArmorTexture(plateStack, Minecraft.getMinecraft().thePlayer, 1, "POV"));
        addBox(0, 0, 0, 0, 0, 0); //Adds in a blank box as it's required in certain cases such as rendering arrows in entities
    }

    @Override
    public void render(float partialTicks) 
    {
//        if (ClientEventHandler.currentEvent != null && ClientEventHandler.currentPartsData != null && ClientEventHandler.currentPlayerTexture != null) 
        {
//            PartInfo info = ClientEventHandler.currentPartsData.getPartInfo(partType);
//            if (info != null && info.hasPart) 
            {
            	GL11.glPushMatrix();
//            	GL11.glTranslated(0.3, -.1, 0);
            	Minecraft.getMinecraft().renderEngine.bindTexture(resource);
                armModel.render(partialTicks);

                GL11.glPopMatrix();
                Minecraft.getMinecraft().renderEngine.bindTexture(ClientEventHandler.currentPlayerTexture);
            }
        }
    }
}
