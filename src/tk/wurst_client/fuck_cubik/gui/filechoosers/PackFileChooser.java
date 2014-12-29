package tk.wurst_client.fuck_cubik.gui.filechoosers;

import java.awt.Component;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

import tk.wurst_client.fuck_cubik.Main;
import tk.wurst_client.fuck_cubik.dialogs.ErrorMessage;
import tk.wurst_client.fuck_cubik.dialogs.UnknownProgressDialog;
import tk.wurst_client.fuck_cubik.files.FileManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class PackFileChooser extends JFileChooser
{
	private UnknownProgressDialog progress;
	
	public PackFileChooser()
	{
		super(FileManager.CURRENT_DIRECTORY);
		this.setAcceptAllFileFilterUsed(false);
		this.addChoosableFileFilter(new FileNameExtensionFilter("ResourcePacks", "zip"));
		this.setFileSelectionMode(FILES_ONLY);
	}
	
	@Override
	public int showOpenDialog(Component parent) throws HeadlessException
	{
		int action = super.showOpenDialog(parent);
		if(action == APPROVE_OPTION)
		{
			progress = new UnknownProgressDialog("Importing pack...");
			new SwingWorker()
			{
				@Override
				protected Object doInBackground() throws Exception
				{
					try
					{
						File pack = getSelectedFile();
						ZipInputStream input = new ZipInputStream(new FileInputStream(pack));
						for(ZipEntry entry; (entry = input.getNextEntry()) != null;)
						{
							File file = new File(FileManager.CURRENT_DIRECTORY, entry.getName());
							if(!file.getParentFile().exists())
								file.getParentFile().mkdirs();
							if(!entry.isDirectory())
							{
								FileOutputStream output = new FileOutputStream(file);
								byte[] buffer = new byte[1024];
								for(int length; (length = input.read(buffer)) > 0;)
									output.write(buffer, 0, length);
								output.close();
							}
							input.closeEntry();
						}
						input.close();
					}catch(IOException e)
					{
						new ErrorMessage("importing pack", e);
					}finally
					{
						progress.dispose();
						Main.frame.menuBar.packMenu.updateExportButton();
					}
					return null;
				}
			}.execute();
		}
		return action;
	}
	
	@Override
	public int showSaveDialog(Component parent) throws HeadlessException
	{
		int action = super.showSaveDialog(parent);
		if(action == APPROVE_OPTION)
		{
			progress = new UnknownProgressDialog("Exporting pack...");
			new SwingWorker()
			{
				@Override
				protected Object doInBackground() throws Exception
				{
					try
					{
						File pack = getSelectedFile();
						if(!pack.getName().endsWith(".zip"))
							pack = new File(pack.getPath() + ".zip");
						ZipOutputStream output = new ZipOutputStream(new FileOutputStream(pack));
						addToZIP(FileManager.METADATA_FILE, output, FileManager.CURRENT_DIRECTORY);
						addToZIP(FileManager.ASSETS_DIRECTORY, output, FileManager.CURRENT_DIRECTORY);
						output.close();
					}catch(IOException e)
					{
						new ErrorMessage("exporting pack", e);
					}finally
					{
						progress.dispose();
					}
					return null;
				}
			}.execute();
		}
		return action;
	}
	
	public void showDescriptionDialog(Component parent)
	{
		JsonObject json;
		String oldDescription;
		String newDescription;
		try
		{
			BufferedReader load = new BufferedReader(new InputStreamReader(new FileInputStream(FileManager.METADATA_FILE)));
			String content = load.readLine();
			for(String line = ""; (line = load.readLine()) != null;)
				content += "\n" + line;
			load.close();
			json = new JsonParser().parse(content).getAsJsonObject();
			oldDescription = json.get("pack").getAsJsonObject().get("description").getAsString();
		}catch(Exception e)
		{
			new ErrorMessage("reading metadata", e);
			return;
		}
		newDescription = (String)JOptionPane.showInputDialog(parent, "New description:", "Edit pack description", JOptionPane.QUESTION_MESSAGE, null, null, oldDescription);
		if(newDescription != null)
		{
			try
			{
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				
				json.get("pack").getAsJsonObject().remove("description");
				json.get("pack").getAsJsonObject().addProperty("description", newDescription);
				
				PrintWriter save = new PrintWriter(new OutputStreamWriter(new FileOutputStream(FileManager.METADATA_FILE)));
				for(String line : gson.toJson(json).split("\n"))
					save.println(line);
				save.close();
			}catch(Exception e)
			{
				new ErrorMessage("saving metadata", e);
			}
		}
	}
	
	public int showDeleteDialog(Component parent)
	{
		int action = JOptionPane.showOptionDialog
			(
				parent,
				"Are you sure you want to clear the current pack?"
					+ "\nAll non-exported data will be lost!",
				"Are you sure?",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				null,
				null
			);
		if(action == APPROVE_OPTION)
		{
			progress = new UnknownProgressDialog("Clearing pack...");
			new SwingWorker()
			{
				@Override
				protected Object doInBackground() throws Exception
				{
					try
					{
						delete(FileManager.METADATA_FILE);
						delete(FileManager.ASSETS_DIRECTORY);
					}catch(Exception e)
					{
						new ErrorMessage("clearing pack", e);
					}finally
					{
						progress.dispose();
					}
					return null;
				}
			}.execute();
		}
		return action;
	}
	
	private void addToZIP(File file, ZipOutputStream output, final File root) throws IOException
	{
		String entryPath = file.getAbsolutePath().substring(root.getAbsolutePath().length() + 1).replace("\\", "/");
		if(file.isDirectory())
			entryPath += "/";
		output.putNextEntry(new ZipEntry(entryPath));
		if(!file.isDirectory())
		{
			FileInputStream input = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			for(int length; (length = input.read(buffer)) > 0;)
				output.write(buffer, 0, length);
			input.close();
		}
		output.closeEntry();
		if(file.isDirectory())
		{
			for(File subfile : file.listFiles())
				addToZIP(subfile, output, root);
		}
	}
	
	private void delete(File file)
	{
		if(file.isDirectory())
		{
			for(File subfile : file.listFiles())
				delete(subfile);
		}
		file.delete();
	}
}
