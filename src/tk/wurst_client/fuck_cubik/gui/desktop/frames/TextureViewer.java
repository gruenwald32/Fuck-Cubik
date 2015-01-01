package tk.wurst_client.fuck_cubik.gui.desktop.frames;

import java.awt.FlowLayout;

import tk.wurst_client.fuck_cubik.Main;
import tk.wurst_client.fuck_cubik.textureviewer.TextureViewerOptionsPanel;
import tk.wurst_client.fuck_cubik.textureviewer.TextureViewerPanel;

public class TextureViewer extends AbstractFrame
{
	public TextureViewerPanel viewer;
	public TextureViewerOptionsPanel options;
	
	public TextureViewer()
	{
		super("Texture Viewer", true, false, false);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		viewer = new TextureViewerPanel();
		this.add(viewer);
		options = new TextureViewerOptionsPanel();
		this.add(options);
		if(Main.isSmallScreen)
		{
			this.setSize(400, 200);
			this.setLocation(0, 400);
		}else
		{
			this.setSize(600, 200);
			this.setLocation(0, 600);
		}
	}
}
