package tk.wurst_client.fuck_cubik.gui.menu;

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
import javax.swing.JSeparator;

import tk.wurst_client.fuck_cubik.Main;
import tk.wurst_client.fuck_cubik.error.ErrorMessage;
import tk.wurst_client.fuck_cubik.files.ResourceManager;
import tk.wurst_client.fuck_cubik.update.VersionManager;

public class HelpMenu extends JMenu
{
	public JMenuItem gettingstarted;
	private JMenuItem wiki;
	public JMenuItem website;
	public JMenuItem about;
	public JMenuItem license;
	
	public HelpMenu()
	{
		super("Help");
		gettingstarted = new JMenuItem("Getting started guide");
		gettingstarted.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Main.tracker.trackEvent("help menu", "open link", "Getting started guide");
				try
				{
					Desktop.getDesktop().browse(new URI("https://github.com/Fuck-Cubik/Fuck-Cubik/wiki/Getting-started"));
				}catch(Exception e1)
				{
					new ErrorMessage("opening link (getting started guide)", e1);
				}
			}
		});
		add(gettingstarted);
		wiki = new JMenuItem("Fuck Cubik wiki");
		wiki.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Main.tracker.trackEvent("help menu", "open link", "Fuck Cubik wiki");
				try
				{
					Desktop.getDesktop().browse(new URI("https://github.com/Fuck-Cubik/Fuck-Cubik/wiki"));
				}catch(Exception e1)
				{
					new ErrorMessage("opening link (wiki)", e1);
				}
			}
		});
		add(wiki);
		website = new JMenuItem("Fuck Cubik website");
		website.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Main.tracker.trackEvent("help menu", "open link", "Fuck Cubik website");
				try
				{
					Desktop.getDesktop().browse(new URI("http://fuck-cubik.wurst-client.tk"));
				}catch(IOException | URISyntaxException e1)
				{
					new ErrorMessage("opening link (Fuck Cubik website)", e1);
				}
			}
		});
		add(website);
		add(new JSeparator());
		about = new JMenuItem("About Fuck Cubik");
		about.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Main.tracker.trackPageView("/menu/help/about", "About Fuck Cubik");
				try
				{
					BufferedReader reader = new BufferedReader(new InputStreamReader(ResourceManager.getStream(ResourceManager.ABOUT_FILE)));
					String message = reader.readLine();
					for(String line = ""; (line = reader.readLine()) != null;)
						message += line;
					reader.close();
					message = message
						.replace("<version>", VersionManager.FORMATTED_VERSION);
					JOptionPane.showMessageDialog(Main.frame, message, "About Fuck Cubik", JOptionPane.INFORMATION_MESSAGE);
				}catch(Exception e1)
				{
					new ErrorMessage("loading \"ABOUT\" resource", e1);
				}
				Main.tracker.trackPageView("/", "Main frame");
			}
		});
		add(about);
		license = new JMenuItem("License");
		license.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Main.tracker.trackPageView("/menu/help/license", "License");
				try
				{
					BufferedReader reader = new BufferedReader(new InputStreamReader(ResourceManager.getStream(ResourceManager.LICENSE_FILE)));
					String message = reader.readLine();
					for(String line = ""; (line = reader.readLine()) != null;)
						message += "\n" + line;
					reader.close();
					JOptionPane.showMessageDialog(Main.frame, message, "License", JOptionPane.INFORMATION_MESSAGE);
				}catch(Exception e1)
				{
					new ErrorMessage("loading \"LICENSE\" resource", e1);
				}
				Main.tracker.trackPageView("/", "Main frame");
			}
		});
		add(license);
	}
}
