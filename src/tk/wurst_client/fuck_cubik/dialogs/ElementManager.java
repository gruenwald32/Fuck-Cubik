package tk.wurst_client.fuck_cubik.dialogs;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import tk.wurst_client.fuck_cubik.Main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ElementManager extends JDialog
{
	private Gson gson;
	
	public JComboBox<String> elementsCombo;
	public JButton okButton;
	public JMenuBar menu;
	public JButton newButton;
	public JList<String> elements;
	public JScrollPane scrollbar;
	public JButton editButton;
	public JButton removeButton;
	
	public ElementManager()
	{
		super(Main.frame, "Element manager");
		try
		{
			setLayout(new BorderLayout());
			gson = new GsonBuilder().setPrettyPrinting().create();
			
			menu = new JMenuBar();
			menu.add(Box.createHorizontalGlue());
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
						Main.frame.desktop.preview.toolbar.refreshButton.doClick();
						updateList();
					}catch(Exception e1)
					{
						new ErrorMessage("adding new element", e1);
					}
				}
			});
			menu.add(newButton);
			editButton = new JButton("Edit");
			editButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					
				}
			});
			menu.add(editButton);
			removeButton = new JButton("Remove");
			removeButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					
				}
			});
			menu.add(removeButton);
			menu.add(Box.createHorizontalGlue());
			add(menu, BorderLayout.NORTH);
			elements = new JList<String>(new DefaultListModel<String>());
			updateList();
			elements.setSelectedIndex(-1);
			Main.renderer.markedElement = -1;
			elements.addListSelectionListener(new ListSelectionListener()
			{
				@Override
				public void valueChanged(ListSelectionEvent e)
				{
					Main.renderer.markedElement = e.getFirstIndex();
				}
			});
			scrollbar = new JScrollPane(elements);
			add(scrollbar, BorderLayout.CENTER);
			
			/*if(Main.frame.desktop.editor.getCode().isJsonNull())
				throw new JsonSyntaxException("No code found.");
			setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
			this.add(Box.createVerticalGlue());
			elementsCombo = new JComboBox<String>();
			elementsCombo.setAlignmentX(CENTER_ALIGNMENT);
			JsonObject json = Main.frame.desktop.editor.getCode().getAsJsonObject();
			if(!json.has("elements"))
				throw new JsonSyntaxException("No elements found.");
			for(int i = 0; i < json.get("elements").getAsJsonArray().size(); i++)
				elementsCombo.addItem("Element #" + (i + 1));
			elementsCombo.setSelectedIndex(-1);
			Main.renderer.markedElement = -1;
			elementsCombo.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					Main.renderer.markedElement = elementsCombo.getSelectedIndex();
				}
			});
			elementsCombo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 24));
			this.add(elementsCombo);
			this.add(Box.createVerticalGlue());
			okButton = new JButton("OK");
			okButton.setAlignmentX(CENTER_ALIGNMENT);
			okButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					int selection = elementsCombo.getSelectedIndex();
					ElementManager.this.dispose();
					Main.renderer.markedElement = selection;
					new ElementEditor(Main.frame.desktop.editor.getCode().getAsJsonObject().get("elements").getAsJsonArray().get(selection).getAsJsonObject(), selection);
				}
			});
			okButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 24));
			this.add(okButton);
			this.add(Box.createVerticalGlue());*/
			addWindowListener(new WindowListener()
			{
				@Override
				public void windowOpened(WindowEvent e)
				{	
					
				}
				
				@Override
				public void windowIconified(WindowEvent e)
				{	
					
				}
				
				@Override
				public void windowDeiconified(WindowEvent e)
				{	
					
				}
				
				@Override
				public void windowDeactivated(WindowEvent e)
				{	
					
				}
				
				@Override
				public void windowClosing(WindowEvent e)
				{
					Main.renderer.markedElement = -1;
				}
				
				@Override
				public void windowClosed(WindowEvent e)
				{	
					
				}
				
				@Override
				public void windowActivated(WindowEvent e)
				{	
					
				}
			});
			this.setSize(256, 320);
			setLocationRelativeTo(Main.frame.desktop.editor);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setAlwaysOnTop(true);
			setVisible(true);
		}catch(Exception e)
		{
			new ErrorMessage("loading elements", e);
		}
	}
	
	private void updateList()
	{
		((DefaultListModel<String>)elements.getModel()).clear();
		try
		{
			JsonObject json = Main.frame.desktop.editor.getCode().getAsJsonObject();
			for(int i = 0; i < json.get("elements").getAsJsonArray().size(); i++)
				((DefaultListModel<String>)elements.getModel()).addElement("Element #" + (i + 1));
		}catch(Exception e)
		{
			
		}
	}
}
