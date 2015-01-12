package tk.wurst_client.fuck_cubik.editor.texturemanager;

import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;

import tk.wurst_client.fuck_cubik.Main;
import tk.wurst_client.fuck_cubik.dialogs.ErrorMessage;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class TextureEditor extends JDialog
{
	public JsonObject element;
	public String name;
	
	public TextureEditor(JsonObject textures, String name)
	{
		super(Main.frame, name);
		element = textures;
		this.name = name;
		try
		{
			setLayout(new GridLayout(2, 2));
			add(new JLabel("Name: "));
			add(new JLabel("Test"));
			add(new JLabel("Path: "));
			add(new JLabel("Test"));
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
			json.get("textures").getAsJsonObject().remove(name);
			json.get("textures").getAsJsonObject().addProperty(name, "");
			Main.frame.desktop.editor.setCode(new GsonBuilder().setPrettyPrinting().create().toJson(json));
			Main.renderer.refresh();
		}catch(Exception e)
		{
			new ErrorMessage("changing code through the texture editor", e);
		}
	}
}
