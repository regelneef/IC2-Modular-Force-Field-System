package mffs;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.InventoryPlayer;
import org.lwjgl.opengl.GL11;

public class GuiProjektorArea extends GuiContainer {

	private TileEntityAreaProjektor inventory;
	private String mode;

	public GuiProjektorArea(InventoryPlayer inventoryplayer, TileEntityAreaProjektor tileEntity_Cube_Projektor) {

		super(new ContainerProjektor(inventoryplayer, tileEntity_Cube_Projektor));
		inventory = tileEntity_Cube_Projektor;

	}

	@SuppressWarnings("unchecked")
	public void initGui() {

		controlList.add(new GuiButton(0, (width / 2) + 50, (height / 2) - 10, 10, 10, "+"));
		controlList.add(new GuiButton(1, (width / 2) + 10, (height / 2) - 10, 10, 10, "-"));
		controlList.add(new GuiButton(2, (width / 2) + 60, (height / 2) + 10, 10, 10, "+"));
		controlList.add(new GuiButton(3, (width / 2) + 0, (height / 2) + 10, 10, 10, "-"));

		super.initGui();
	}

	protected void actionPerformed(GuiButton guibutton) {

		if (!inventory.getActive()) {
			switch (guibutton.id) {

			case 0:
				if (inventory.getActive() == false) {

					if (inventory.getRadius() < inventory.getMaxradius()) {

						inventory.setRadius(inventory.getRadius() + 1);
					}
				}
				break;
			case 1:
				if (inventory.getActive() == false) {

					if (inventory.getRadius() > 4) {
						inventory.setRadius(inventory.getRadius() - 1);
					}

				}
				break;

			case 2:
				if (inventory.getActive() == false) {

					if (inventory.getmode_designe() < inventory.getMaxmode()) {

						inventory.setmode_designe((short) (inventory.getmode_designe() + 1));
					}
				}
				break;
			case 3:
				if (inventory.getActive() == false) {

					if (inventory.getmode_designe() > 1) {
						inventory.setmode_designe((short) (inventory.getmode_designe() - 1));
					}

				}
				break;

			}
		}
	}

	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

		int textur = mc.renderEngine.getTexture("/mffs_grafik/GUIid.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(textur);
		int w = (width - xSize) / 2;
		int k = (height - ySize) / 2;
		drawTexturedModalRect(w, k, 0, 0, xSize, ySize);
		int i1 = (69 * inventory.getLinkPower()) / inventory.getMaxlinkPower();
		drawTexturedModalRect(w + 93, k + 30, 176, 0, i1 + 1, 69);

	}

	protected void drawGuiContainerForegroundLayer() {

		switch (inventory.getmode_designe()) {
		case 1:
			mode = "Cube";
			break;
		case 2:
			mode = "Sphere";
			break;
		}

		fontRenderer.drawString("MFFS Area Projector", 20, 5, 0x404040);
		fontRenderer.drawString("Force Power", 10, 30, 0x404040);
		fontRenderer.drawString("available", 22, 40, 0x404040);
		fontRenderer.drawString("linked to generator:", 10, 60, 0x404040);
		fontRenderer.drawString("Frequency Card:", 10, 123, 0x404040);
		fontRenderer.drawString("radius :", 10, 75, 0x404040);
		fontRenderer.drawString("mode:", 10, 95, 0x404040);

		fontRenderer.drawString((new StringBuilder()).append(" ").append(inventory.getLinkPower()).toString(), 100, 45, 0x404040);
		fontRenderer.drawString((new StringBuilder()).append(" ").append(inventory.isLinkGenerator()).toString(), 120, 60, 0x404040);
		fontRenderer.drawString((new StringBuilder()).append(mode).toString(), 105, 95, 0x404040);
		fontRenderer.drawString((new StringBuilder()).append(inventory.getRadius()).toString(), 120, 75, 0x404040);

	}

}
