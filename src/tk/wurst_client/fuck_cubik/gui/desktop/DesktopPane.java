package tk.wurst_client.fuck_cubik.gui.desktop;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JDesktopPane;

import tk.wurst_client.fuck_cubik.gui.desktop.frames.Editor;
import tk.wurst_client.fuck_cubik.gui.desktop.frames.TextureViewer;
import tk.wurst_client.fuck_cubik.gui.desktop.frames.Preview;

public class DesktopPane extends JDesktopPane
{
	public Preview preview;
	public Editor editor;
	public TextureViewer textureViewer;

	public DesktopPane()
	{
		super();
		this.preview = new Preview();
		this.editor = new Editor();
		this.textureViewer = new TextureViewer();
		this.setBackground(new Color(240, 240, 240));
		this.add(preview);
		this.add(editor);
		this.add(textureViewer);
		this.setPreferredSize(new Dimension(1200, 800));
	}
}
