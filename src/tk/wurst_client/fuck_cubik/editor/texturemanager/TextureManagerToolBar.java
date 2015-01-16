package tk.wurst_client.fuck_cubik.editor.texturemanager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

import tk.wurst_client.fuck_cubik.Main;
import tk.wurst_client.fuck_cubik.error.ErrorMessage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class TextureManagerToolBar extends JToolBar
{
	private Gson gson;
	
	public JButton newButton;
	public JButton editButton;
	public JButton removeButton;
	public TextureEditor textureEditor;
	
	public TextureManagerToolBar(final TextureManager textureManager)
	{
		gson = new GsonBuilder().setPrettyPrinting().create();
		newButton = new JButton("New");
		newButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					if(Main.frame.desktop.editor.getCode().isJsonNull())
						Main.frame.desktop.editor.setCode(gson.toJson(new JsonObject()));
					JsonObject json = Main.frame.desktop.editor.getCode().getAsJsonObject();
					if(!json.has("textures"))
						json.add("textures", new JsonObject());
					String name = "unnamed" + (int)(Math.random() * 1000);
					json.get("textures").getAsJsonObject().addProperty(name, "");
					Main.frame.desktop.editor.setCode(gson.toJson(json));
					Main.renderer.refreshLater();
					textureManager.updateList();
					textureEditor = new TextureEditor(Main.frame.desktop.editor.getCode().getAsJsonObject().get("textures").getAsJsonObject(), name);
				}catch(Exception e1)
				{
					new ErrorMessage("adding new texture", e1);
				}
			}
		});
		add(newButton);
		editButton = new JButton("Edit");
		editButton.setEnabled(false);
		editButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String selection = textureManager.textures.getSelectedValue();
				textureEditor = new TextureEditor(Main.frame.desktop.editor.getCode().getAsJsonObject().get("textures").getAsJsonObject(), selection);
			}
		});
		add(editButton);
		removeButton = new JButton("Remove");
		removeButton.setEnabled(false);
		removeButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String selection = textureManager.textures.getSelectedValue();
				int action = JOptionPane.showConfirmDialog(TextureManagerToolBar.this, "Are you sure you want to delete the texture \"" + selection + "\"?", "Are you sure?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if(action == JOptionPane.YES_OPTION)
				{
					JsonObject json = Main.frame.desktop.editor.getCode().getAsJsonObject();
					json.get("textures").getAsJsonObject().remove(selection.substring(1));
					Main.frame.desktop.editor.setCode(gson.toJson(json));
					Main.renderer.refreshLater();
					textureManager.updateList();
				}
			}
		});
		add(removeButton);
	}
}
