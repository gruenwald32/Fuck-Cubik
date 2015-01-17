package tk.wurst_client.fuck_cubik.gui.menu;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import tk.wurst_client.fuck_cubik.Main;
import tk.wurst_client.fuck_cubik.error.ErrorMessage;

public class OptionsMenu extends JMenu
{
	public Analytics analytics;

	public OptionsMenu()
	{
		super("Options");
		analytics = new Analytics();
		add(analytics);
	}
	
	public class Analytics extends JMenu
	{
		public JCheckBoxMenuItem enabled;
		public JMenuItem about;
		public JMenuItem policy;
		
		public Analytics()
		{
			super("Google Analytics");
			enabled = new JCheckBoxMenuItem("Enabled", Main.options.google_analytics.enabled);
			enabled.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					if(enabled.isSelected())
					{
						Main.options.google_analytics.enabled = true;
						Main.tracker.trackEvent("analytics", "toggle tracking", "enable");
					}else
					{
						Main.tracker.trackEvent("analytics", "toggle tracking", "disable");
						Main.options.google_analytics.enabled = false;
					}
					Main.options.save();
				}
			});
			add(enabled);
			about = new JMenuItem("About");
			about.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					Main.tracker.trackEvent("analytics", "open link", "About Google Analytics");
					try
					{
						Desktop.getDesktop().browse(new URI("http://www.google.com/analytics/"));
					}catch(Exception e1)
					{
						new ErrorMessage("opening link (about analytics)", e1);
					}
				}
			});
			add(about);
			policy = new JMenuItem("Privacy policy");
			policy.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					Main.tracker.trackEvent("analytics", "open link", "Privacy policy");
					try
					{
						Desktop.getDesktop().browse(new URI("https://www.google.com/policies/privacy/"));
					}catch(Exception e1)
					{
						new ErrorMessage("opening link (analytics policy)", e1);
					}
				}
			});
			add(policy);
		}
	}
}
