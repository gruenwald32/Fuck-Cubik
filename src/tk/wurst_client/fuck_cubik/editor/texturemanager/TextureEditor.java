package tk.wurst_client.fuck_cubik.editor.texturemanager;

import java.awt.GridLayout;

import javax.swing.JDialog;

import tk.wurst_client.fuck_cubik.Main;
import tk.wurst_client.fuck_cubik.dialogs.ErrorMessage;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class TextureEditor extends JDialog
{
	public JsonObject element;
	public int selection;
	
	public TextureEditor(JsonObject textures, int selection)
	{
		super(Main.frame, "Element #" + (selection + 1));
		this.element = textures;
		this.selection = selection;
		try
		{
			setLayout(new GridLayout(2, 2));
			pack();
			setLocationRelativeTo(Main.frame.desktop.editor);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setAlwaysOnTop(true);
			setVisible(true);
		}catch(Exception e)
		{
			new ErrorMessage("editing texture", e);
		}
	}
	
	public void updateCode()
	{
		try
		{
			JsonObject json = Main.frame.desktop.editor.getCode().getAsJsonObject();
			json.get("textures").getAsJsonArray().set(selection, element);
			Main.frame.desktop.editor.setCode(new GsonBuilder().setPrettyPrinting().create().toJson(json));
			Main.renderer.refresh();
			Main.renderer.markedElement = selection;
		}catch(Exception e)
		{
			new ErrorMessage("changing code through the element editor", e);
		}
	}
}
