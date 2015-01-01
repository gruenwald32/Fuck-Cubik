package tk.wurst_client.fuck_cubik.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

import tk.wurst_client.fuck_cubik.Main;
import tk.wurst_client.fuck_cubik.dialogs.ElementChooser;
import tk.wurst_client.fuck_cubik.dialogs.ErrorMessage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

public class EditorToolBar extends JToolBar
{
	public Gson gson;
	
	public JButton formatButton;
	public JButton newElementButton;
	public JButton elementEditorButton;
	
	public EditorToolBar()
	{
		gson = new GsonBuilder().setPrettyPrinting().create();
		formatButton = new JButton("Format code");
		formatButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					if(Main.frame.desktop.editor.getCode().isJsonNull())
						throw new JsonSyntaxException("No code found.");
					String newCode = gson.toJson(Main.frame.desktop.editor.getCode());
					Main.frame.desktop.editor.setCode(newCode);
				}catch(Exception e1)
				{
					new ErrorMessage("formatting code", e1);
				}
			}
		});
		this.add(formatButton);
		newElementButton = new JButton("New element");
		newElementButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					if(Main.frame.desktop.editor.getCode().isJsonNull())
						Main.frame.desktop.editor.setCode(gson.toJson(new JsonObject()));
					JsonObject json = Main.frame.desktop.editor.getCode().getAsJsonObject();
					JsonObject element = new JsonObject();
					element.add("from", gson.toJsonTree(new int[]{0, 0, 0}));
					element.add("to", gson.toJsonTree(new int[]{16, 16, 16}));
					element.add("faces", new JsonObject());
					String[] faceNames = new String[]{"up", "down", "north", "south", "west", "east"};
					for(int i = 0; i < 6; i++)
					{
						JsonObject face = new JsonObject();
						face.add("uv", gson.toJsonTree(new int[]{0, 0, 16, 16}));
						face.add("texture", gson.toJsonTree("#"));
						element.get("faces").getAsJsonObject().add(faceNames[i], face);
					}
					if(!json.has("elements"))
						json.add("elements", new JsonArray());
					json.get("elements").getAsJsonArray().add(element);
					Main.frame.desktop.editor.setCode(gson.toJson(json));
				}catch(Exception e1)
				{
					new ErrorMessage("adding new element", e1);
				}
			}
		});
		this.add(newElementButton);
		elementEditorButton = new JButton("Element editor");
		elementEditorButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new ElementChooser();
			}
		});
		this.add(elementEditorButton);
	}
}
