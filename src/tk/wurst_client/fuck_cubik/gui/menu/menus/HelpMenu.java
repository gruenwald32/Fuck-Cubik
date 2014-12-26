package tk.wurst_client.fuck_cubik.gui.menu.menus;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import tk.wurst_client.fuck_cubik.Main;

public class HelpMenu extends JMenu
{
	public JMenuItem about;
	public JMenuItem license;
	public JMenuItem website;

	public HelpMenu()
	{
		super("Help");
		about = new JMenuItem("About Fuck Cubik");
		about.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("resources/ABOUT")));
					String message = reader.readLine();
					for(String line = ""; (line = reader.readLine()) != null;)
						message  += line;
					reader.close();
					message = message.replace("<VERSION>", Main.VERSION);
					JOptionPane.showMessageDialog(Main.frame, message, "About Fuck Cubik", JOptionPane.INFORMATION_MESSAGE);
				}catch(Exception e1)
				{
					e1.printStackTrace();
					JOptionPane.showMessageDialog(Main.frame, "Unable to load resource.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		this.add(about);
		license = new JMenuItem("License");
		license.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("resources/LICENSE")));
					String message = reader.readLine();
					for(String line = ""; (line = reader.readLine()) != null;)
						message += "\n" + line;
					reader.close();
					JOptionPane.showMessageDialog(Main.frame, message, "License", JOptionPane.INFORMATION_MESSAGE);
				}catch(Exception e1)
				{
					e1.printStackTrace();
					JOptionPane.showMessageDialog(Main.frame, "Unable to load resource.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		this.add(license);
		website = new JMenuItem("Fuck Cubik website");
		website.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					Desktop.getDesktop().browse(new URI("http://fuck-cubik.wurst-client.tk"));
				}catch(IOException | URISyntaxException e1)
				{
					JOptionPane.showMessageDialog(Main.frame, "Unable to open link.", "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		this.add(website);
	}
}
