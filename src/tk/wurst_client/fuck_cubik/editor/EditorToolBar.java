package tk.wurst_client.fuck_cubik.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

import tk.wurst_client.fuck_cubik.Main;
import tk.wurst_client.fuck_cubik.editor.elementmanager.ElementManager;
import tk.wurst_client.fuck_cubik.editor.texturemanager.TextureManager;
import tk.wurst_client.fuck_cubik.error.ErrorMessage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class EditorToolBar extends JToolBar
{
	public Gson gson;
	
	public JButton formatButton;
	public JButton elementManagerButton;
	public JButton textureManagerButton;
	public ElementManager elementManager;
	public TextureManager textureManager;
	
	public EditorToolBar()
	{
		gson = new GsonBuilder().setPrettyPrinting().create();
		formatButton = new JButton("Format code");
		formatButton.setEnabled(false);
		formatButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					if(Main.frame.desktop.editor.getCode() == null)
						return;
					String newCode = gson.toJson(Main.frame.desktop.editor.getCode());
					Main.frame.desktop.editor.setCode(newCode);
				}catch(Exception e1)
				{
					new ErrorMessage(Main.frame.desktop.editor, "formatting code", e1);
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
				elementManager = new ElementManager();
			}
		});
		this.add(elementManagerButton);
		textureManagerButton = new JButton("Texture manager");
		textureManagerButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				textureManager = new TextureManager();
			}
		});
		this.add(textureManagerButton);
	}
}
