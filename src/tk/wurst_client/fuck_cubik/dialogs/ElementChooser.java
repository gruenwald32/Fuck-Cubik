package tk.wurst_client.fuck_cubik.dialogs;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import tk.wurst_client.fuck_cubik.Main;

public class ElementChooser extends JDialog
{
	public JComboBox<String> elementsCombo;
	public JButton okButton;

	public ElementChooser()
	{
		super(Main.frame, "Choose an element");
		try
		{
			if(Main.frame.desktop.editor.getCode().isJsonNull())
				throw new JsonSyntaxException("No code found.");
			this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
			this.add(Box.createVerticalGlue());
			elementsCombo = new JComboBox<String>();
			elementsCombo.setAlignmentX(CENTER_ALIGNMENT);
			JsonObject json = Main.frame.desktop.editor.getCode().getAsJsonObject();
			if(!json.has("elements"))
				throw new JsonSyntaxException("No elements found.");
			for(int i = 0; i < json.get("elements").getAsJsonArray().size(); i++)
			{
				elementsCombo.addItem("Element #" + (i + 1));
			}
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
					ElementChooser.this.dispose();
					Main.renderer.markedElement = selection;
					new ElementEditor(Main.frame.desktop.editor.getCode().getAsJsonObject().get("elements").getAsJsonArray().get(selection).getAsJsonObject(), selection);
				}
			});
			okButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 24));
			this.add(okButton);
			this.add(Box.createVerticalGlue());
			this.addWindowListener(new WindowListener()
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
			this.setSize(256, 128);
			this.setLocationRelativeTo(Main.frame);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			this.setAlwaysOnTop(true);
			this.setVisible(true);
		}catch(Exception e)
		{
			new ErrorMessage("loading elements", e);
		}
	}
}
