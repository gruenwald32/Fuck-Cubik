package tk.wurst_client.fuck_cubik.gui.filechoosers;

import java.awt.Component;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import tk.wurst_client.fuck_cubik.Main;

public class ModelFileChooser extends JFileChooser
{
	public ModelFileChooser()
	{
		super(".");
		this.setAcceptAllFileFilterUsed(false);
		this.addChoosableFileFilter(new FileNameExtensionFilter("JSON 3D models", "json"));
		this.setFileSelectionMode(FILES_ONLY);
	}
	
	@Override
	public int showOpenDialog(Component parent) throws HeadlessException
	{
		int action = super.showOpenDialog(parent);
		if(action == APPROVE_OPTION)
		{
			try
			{
				BufferedReader load = new BufferedReader(new InputStreamReader(new FileInputStream(getSelectedFile())));
				String content = load.readLine();
				for(String line = ""; (line = load.readLine()) != null;)
					content += "\n" + line;
				load.close();
				Main.frame.desktop.editor.textarea.setText(content);
			}catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		return action;
	}
}
