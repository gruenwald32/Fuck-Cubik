package tk.wurst_client.fuck_cubik.editor.texturemanager;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Map.Entry;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import tk.wurst_client.fuck_cubik.Main;
import tk.wurst_client.fuck_cubik.error.ErrorMessage;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class TextureManager extends JDialog
{
	public TextureManagerToolBar toolbar;
	public JList<String> textures;
	public JScrollPane scrollbar;
	
	public TextureManager()
	{
		super(Main.frame, "Texture manager");
		try
		{
			setLayout(new BorderLayout());
			toolbar = new TextureManagerToolBar(this);
			add(toolbar, BorderLayout.NORTH);
			textures = new JList<String>(new DefaultListModel<String>());
			textures.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			updateList();
			textures.setSelectedIndex(-1);
			textures.addListSelectionListener(new ListSelectionListener()
			{
				@Override
				public void valueChanged(ListSelectionEvent e)
				{
					toolbar.editButton.setEnabled(textures.getSelectedIndex() != -1);
					toolbar.removeButton.setEnabled(textures.getSelectedIndex() != -1);
				}
			});
			scrollbar = new JScrollPane(textures);
			add(scrollbar, BorderLayout.CENTER);
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
					Main.tracker.trackPageView("/", "Main frame");
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
			setSize(256, 320);
			setLocationRelativeTo(Main.frame.desktop.editor);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setAlwaysOnTop(true);
			Main.tracker.trackPageView("/editor/texture-manager", "Texture manager");
			setVisible(true);
		}catch(Exception e)
		{
			new ErrorMessage("loading textures", e);
		}
	}
	
	public void updateList()
	{
		((DefaultListModel<String>)textures.getModel()).clear();
		try
		{
			JsonObject json = Main.frame.desktop.editor.getCode().getAsJsonObject();
			for(Entry<String, JsonElement> entry : json.get("textures").getAsJsonObject().entrySet())
				((DefaultListModel<String>)textures.getModel()).addElement(entry.getKey());
		}catch(Exception e)
		{	
			
		}
	}
}
