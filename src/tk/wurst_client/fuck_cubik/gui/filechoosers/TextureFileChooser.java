package tk.wurst_client.fuck_cubik.gui.filechoosers;

import java.awt.Component;
import java.awt.HeadlessException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import tk.wurst_client.fuck_cubik.Main;
import tk.wurst_client.fuck_cubik.files.FileManager;

public class TextureFileChooser extends JFileChooser
{
	public TextureFileChooser()
	{
		super(FileManager.TEXTURES_DIRECTORY);
		this.setAcceptAllFileFilterUsed(false);
		this.addChoosableFileFilter(new FileNameExtensionFilter("PNG textures", "png"));
		this.setFileSelectionMode(FILES_ONLY);
	}
	
	@Override
	public int showOpenDialog(Component parent) throws HeadlessException
	{
		int action = super.showOpenDialog(parent);
		if(action == APPROVE_OPTION)
		{
			Main.frame.desktop.textureViewer.options.textureCombo.setSelectedIndex(-1);
			Main.frame.desktop.textureViewer.viewer.setInput(this.getSelectedFile());
		}
		return action;
	}
}
