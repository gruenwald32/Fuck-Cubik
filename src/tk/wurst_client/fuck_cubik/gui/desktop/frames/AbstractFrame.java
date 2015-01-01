package tk.wurst_client.fuck_cubik.gui.desktop.frames;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import tk.wurst_client.fuck_cubik.files.ResourceManager;

public abstract class AbstractFrame extends JInternalFrame
{
	public AbstractFrame()
	{
		this("", false, false, false, false);
	}
	
	public AbstractFrame(String title, boolean resizable, boolean closable, boolean maximizable)
	{
		this(title, resizable, closable, maximizable, false);
	}
	
	public AbstractFrame(String title, boolean resizable, boolean closable)
	{
		this(title, resizable, closable, false, false);
	}
	
	public AbstractFrame(String title, boolean resizable)
	{
		this(title, resizable, false, false, false);
	}
	
	public AbstractFrame(String title)
	{
		this(title, false, false, false, false);
	}
	
	public AbstractFrame(String title, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable)
	{
		super(title, resizable, closable, maximizable, iconifiable);
		setBackground(new Color(240, 240, 240));
		setLayout(new BorderLayout());
		setFrameIcon(new ImageIcon(getClass().getResource("/" + ResourceManager.ICON_IMAGE)));
		this.show();
	}
}
