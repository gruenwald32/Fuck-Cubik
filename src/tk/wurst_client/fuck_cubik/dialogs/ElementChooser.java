package tk.wurst_client.fuck_cubik.dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import com.google.gson.JsonObject;

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
			elementsCombo = new JComboBox<String>();
			JsonObject json = Main.frame.desktop.editor.getCode().getAsJsonObject();
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
			this.add(elementsCombo);
			okButton = new JButton("OK");
			this.add(okButton);
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
