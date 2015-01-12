package tk.wurst_client.fuck_cubik.options;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

import tk.wurst_client.fuck_cubik.dialogs.ErrorMessage;
import tk.wurst_client.fuck_cubik.files.FileManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class OptionsManager
{
	public static Options getOptions()
	{
		if(FileManager.OPTIONS_FILE.exists() || FileManager.OPTIONS_FILE_FALLBACK.exists())
			return load();
		else
			return save(new Options());
	}
	
	private static Options load()
	{
		try
		{
			BufferedReader load;
			try
			{
				load = new BufferedReader(new FileReader(FileManager.OPTIONS_FILE));
			}catch(Exception e)
			{
				load = new BufferedReader(new FileReader(FileManager.OPTIONS_FILE_FALLBACK));
			}
			String content = load.readLine();
			for(String line; (line = load.readLine()) != null;)
				content += "\n" + line;
			load.close();
			return new Gson().fromJson(content, Options.class);
		}catch(Exception e)
		{
			new ErrorMessage("loading options", e);
			return null;
		}
	}
	
	public static Options save(Options options)
	{
		try
		{
			PrintWriter save;
			try
			{
				save = new PrintWriter(FileManager.OPTIONS_FILE);
			}catch(Exception e)
			{
				save = new PrintWriter(FileManager.OPTIONS_FILE_FALLBACK);
			}
			save.print(new GsonBuilder().setPrettyPrinting().create().toJson(options));
			save.close();
		}catch(Exception e)
		{
			new ErrorMessage("saving options", e);
		}
		return options;
	}
}
