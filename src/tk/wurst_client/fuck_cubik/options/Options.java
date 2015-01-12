package tk.wurst_client.fuck_cubik.options;

import java.util.Random;


public class Options
{
	public Options.GoogleAnalytics google_analytics = new Options.GoogleAnalytics();
	
	public class GoogleAnalytics
	{
		public boolean enabled = true;
		public int cookie1 = new Random().nextInt();
		public int cookie2 = new Random().nextInt();
	}
	
	public void save()
	{
		OptionsManager.save(this);
	}
}
