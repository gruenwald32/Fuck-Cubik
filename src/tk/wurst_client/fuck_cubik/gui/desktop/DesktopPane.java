package tk.wurst_client.fuck_cubik.gui.desktop;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JDesktopPane;

import tk.wurst_client.fuck_cubik.gui.desktop.frames.Editor;
import tk.wurst_client.fuck_cubik.gui.desktop.frames.Preview;
import tk.wurst_client.fuck_cubik.gui.desktop.frames.TextureViewer;

public class DesktopPane extends JDesktopPane
{
	public Preview preview;
	public Editor editor;
	public TextureViewer textureViewer;
	
	public DesktopPane()
	{
		super();
		preview = new Preview();
		editor = new Editor();
		textureViewer = new TextureViewer();
		setBackground(new Color(240, 240, 240));
		this.add(preview);
		this.add(editor);
		this.add(textureViewer);
		setPreferredSize(new Dimension(1200, 800));
	}
}
