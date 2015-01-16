package tk.wurst_client.fuck_cubik.gui.filechoosers;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import tk.wurst_client.fuck_cubik.files.FileManager;

public class TextureFileChooser extends JFileChooser
{
	public TextureFileChooser()
	{
		super(FileManager.TEXTURES_DIRECTORY);
		setAcceptAllFileFilterUsed(false);
		addChoosableFileFilter(new FileNameExtensionFilter("PNG textures", "png"));
		setFileSelectionMode(FILES_ONLY);
	}
}
