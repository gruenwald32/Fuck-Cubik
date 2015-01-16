package tk.wurst_client.fuck_cubik.editor.elementmanager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

import tk.wurst_client.fuck_cubik.Main;
import tk.wurst_client.fuck_cubik.dialogs.ErrorMessage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ElementManagerToolBar extends JToolBar
{
	private Gson gson;
	
	public JButton newButton;
	public JButton editButton;
	public JButton removeButton;
	public ElementEditor elementEditor;
	
	public ElementManagerToolBar(final ElementManager elementManager)
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
					Main.renderer.refreshLater();
					elementManager.updateList();
					int newElement = elementManager.elements.getModel().getSize() - 1;
					elementEditor = new ElementEditor(Main.frame.desktop.editor.getCode().getAsJsonObject().get("elements").getAsJsonArray().get(newElement).getAsJsonObject(), newElement);
				}catch(Exception e1)
				{
					new ErrorMessage("adding new element", e1);
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
				int selection = elementManager.elements.getSelectedIndex();
				elementEditor = new ElementEditor(Main.frame.desktop.editor.getCode().getAsJsonObject().get("elements").getAsJsonArray().get(selection).getAsJsonObject(), selection);
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
				int selection = elementManager.elements.getSelectedIndex();
				int action = JOptionPane.showConfirmDialog(ElementManagerToolBar.this, "Are you sure you want to delete element #" + (selection + 1) + "?", "Are you sure?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if(action == JOptionPane.YES_OPTION)
				{
					JsonObject json = Main.frame.desktop.editor.getCode().getAsJsonObject();
					json.get("elements").getAsJsonArray().remove(selection);
					Main.frame.desktop.editor.setCode(gson.toJson(json));
					Main.renderer.refreshLater();
					elementManager.updateList();
				}
			}
		});
		add(removeButton);
	}
}
