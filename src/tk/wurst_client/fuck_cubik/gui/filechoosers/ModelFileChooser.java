package tk.wurst_client.fuck_cubik.gui.filechoosers;

import java.awt.Component;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import tk.wurst_client.fuck_cubik.Main;
import tk.wurst_client.fuck_cubik.dialogs.ErrorMessage;
import tk.wurst_client.fuck_cubik.files.FileManager;

public class ModelFileChooser extends JFileChooser
{
	public ModelFileChooser()
	{
		super(FileManager.MODELS_DIRECTORY);
		setAcceptAllFileFilterUsed(false);
		addChoosableFileFilter(new FileNameExtensionFilter("JSON 3D models", "json"));
		setFileSelectionMode(FILES_ONLY);
	}
	
	@Override
	public int showOpenDialog(Component parent) throws HeadlessException
	{
		int action = super.showOpenDialog(parent);
		if(action == APPROVE_OPTION)
			try
			{
				File file = getSelectedFile();
				BufferedReader load = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				String content = load.readLine();
				for(String line = ""; (line = load.readLine()) != null;)
					content += "\n" + line;
				load.close();
				Main.frame.desktop.editor.textarea.setText(content);
				Main.frame.desktop.editor.setFile(file);
				Main.renderer.refreshLater();
			}catch(IOException e)
			{
				new ErrorMessage("loading model", e);
			}
		return action;
	}
	
	@Override
	public int showSaveDialog(Component parent) throws HeadlessException
	{
		int action = super.showSaveDialog(parent);
		if(action == APPROVE_OPTION)
			try
			{
				File file = getSelectedFile();
				save(file);
			}catch(IOException | BadLocationException e)
			{
				new ErrorMessage("saving model", e);
			}
		return action;
	}
	
	public void save(File file) throws BadLocationException, FileNotFoundException
	{
		if(!file.getName().endsWith(".json"))
			file = new File(file.getPath() + ".json");
		PrintWriter save = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file)));
		Document document = Main.frame.desktop.editor.textarea.getDocument();
		String content = document.getText(0, document.getLength());
		for(String line : content.split("\n"))
			save.println(line);
		save.close();
		Main.frame.desktop.editor.setFile(file);
	}
}
