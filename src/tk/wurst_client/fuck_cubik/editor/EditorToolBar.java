package tk.wurst_client.fuck_cubik.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

import tk.wurst_client.fuck_cubik.Main;
import tk.wurst_client.fuck_cubik.dialogs.ElementManager;
import tk.wurst_client.fuck_cubik.dialogs.ErrorMessage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

public class EditorToolBar extends JToolBar
{
	public Gson gson;
	
	public JButton formatButton;
	public JButton elementManagerButton;
	
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
		elementManagerButton = new JButton("Element manager");
		elementManagerButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new ElementManager();
			}
		});
		this.add(elementManagerButton);
	}
}
