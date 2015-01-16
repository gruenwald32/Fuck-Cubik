package tk.wurst_client.fuck_cubik.editor.elementmanager;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import tk.wurst_client.fuck_cubik.Main;
import tk.wurst_client.fuck_cubik.error.ErrorMessage;

import com.google.gson.JsonObject;

public class ElementManager extends JDialog
{
	public ElementManagerToolBar toolbar;
	public JList<String> elements;
	public JScrollPane scrollbar;
	
	public ElementManager()
	{
		super(Main.frame, "Element manager");
		try
		{
			setLayout(new BorderLayout());
			toolbar = new ElementManagerToolBar(this);
			add(toolbar, BorderLayout.NORTH);
			elements = new JList<String>(new DefaultListModel<String>());
			elements.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			updateList();
			elements.setSelectedIndex(-1);
			Main.renderer.markedElement = -1;
			elements.addListSelectionListener(new ListSelectionListener()
			{
				@Override
				public void valueChanged(ListSelectionEvent e)
				{
					Main.renderer.markedElement = elements.getSelectedIndex();
					toolbar.editButton.setEnabled(elements.getSelectedIndex() != -1);
					toolbar.removeButton.setEnabled(elements.getSelectedIndex() != -1);
				}
			});
			scrollbar = new JScrollPane(elements);
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
	
	public void updateList()
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
