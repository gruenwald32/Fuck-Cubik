package tk.wurst_client.fuck_cubik.gui.textureviewer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class TextureViewerPanel extends JPanel
{
	public boolean showGrid = true;
	public boolean showNumbers = true;
	public File texture;
	
	public TextureViewerPanel()
	{
		this.setSize(160, 160);
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
			g.drawImage(ImageIO.read(texture), 4, 4, 128, 128, null);
		}catch(Exception e)
		{
			g.fillRect(4, 4, 128, 128);
		}
		if(showGrid)
		{
			g.setColor(Color.black);
			g.setFont(new Font("Monospaced", Font.PLAIN, 12));
			for(int i = 0; i <= 128; i += 8)
			{
				g.drawLine(i + 4, 4, i + 4, showNumbers ?i % 64 == 0 ? 140 : i % 32 == 0 ? 136 : 132 : 132);
				g.drawLine(4, i + 4, showNumbers ? i % 64 == 0 ? 140 : i % 32 == 0 ? 136 : 132 : 132, i + 4);
				if(i % 32 == 0 && showNumbers)
				{
					g.drawString(Integer.toString(i / 8), i + 4 - 3 * (i / 80 + 1), 152);
					g.drawString(Integer.toString(i / 8), 144, i + 8);
				}
			}
		}
	}
}
