package tk.wurst_client.fuck_cubik.textureviewer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import tk.wurst_client.fuck_cubik.error.ErrorMessage;
import tk.wurst_client.fuck_cubik.files.ResourceManager;

public class TextureViewerPanel extends JPanel
{
	public boolean showGrid = true;
	public boolean showNumbers = true;
	public InputStream input;
	public BufferedImage texture;
	
	public TextureViewerPanel()
	{
		this.setSize(160, 160);
		setMinimumSize(this.getSize());
		setMaximumSize(this.getSize());
		setPreferredSize(this.getSize());
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		g.setColor(Color.white);
		if(texture == null)
			try
			{
				texture = ImageIO.read(input);
			}catch(Exception e)
			{
				try
				{
					texture = ImageIO.read(ResourceManager.getStream(ResourceManager.MISSING_TEXTURE));
				}catch(Exception e1)
				{	
					
				}
			}
		try
		{
			if(texture == null)
				throw new NullPointerException();
			g.drawImage(texture, 4, 4, 128, 128, null);
		}catch(Exception e)
		{
			g.setColor(new Color(1F, 0F, 0.5F));
			g.fillRect(4, 4, 128, 128);
		}
		g.setColor(Color.black);
		g.setFont(new Font("Monospaced", Font.PLAIN, 12));
		for(int i = 0; i <= 128; i += showGrid ? 8 : 128)
		{
			g.drawLine(i + 4, 4, i + 4, showGrid && showNumbers ? i % 64 == 0 ? 140 : i % 32 == 0 ? 136 : 132 : 132);
			g.drawLine(4, i + 4, showGrid && showNumbers ? i % 64 == 0 ? 140 : i % 32 == 0 ? 136 : 132 : 132, i + 4);
			if(i % 32 == 0 && showGrid && showNumbers)
			{
				g.drawString(Integer.toString(i / 8), i + 4 - 3 * (i / 80 + 1), 152);
				g.drawString(Integer.toString(i / 8), 144, i + 8);
			}
		}
	}
	
	public void setInput(File file)
	{
		try
		{
			if(file != null)
				input = new FileInputStream(file);
			else
				input = null;
		}catch(Exception e)
		{
			new ErrorMessage("loading texture file", e);
			input = null;
		}
		texture = null;
		repaint();
	}
}
