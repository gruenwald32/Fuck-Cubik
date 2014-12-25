package tk.wurst_client.fuck_cubik.gui.textureviewer;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class TextureViewerPanel extends JPanel
{
	public boolean showGrid = true;
	public File texture;
	
	public TextureViewerPanel()
	{
		this.setSize(128, 128);
		this.setMinimumSize(this.getSize());
		this.setMaximumSize(this.getSize());
		this.setPreferredSize(this.getSize());
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		g.setColor(Color.white);
		try
		{
			g.drawImage(ImageIO.read(texture), 0, 0, 128, 128, null);
		}catch(Exception e)
		{
			g.fillRect(0, 0, 128, 128);
		}
		if(showGrid)
		{
			g.setColor(Color.black);
			for(int i = 0; i <= 128; i += 8)
			{
				g.drawLine(i, 0, i, 128);
				g.drawLine(0, i, 128, i);
			}
		}
	}
}
