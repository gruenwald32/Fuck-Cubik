package tk.wurst_client.fuck_cubik.options;

import java.security.SecureRandom;

public class Options
{
	public Options.GoogleAnalytics google_analytics = new Options.GoogleAnalytics();
	
	public class GoogleAnalytics
	{
		public boolean enabled = true;
		public boolean report_errors = true;
		public int tracking_id = new SecureRandom().nextInt() & 0x7FFFFFFF;
		public long first_launch = System.currentTimeMillis() / 1000L;
		public long last_launch = System.currentTimeMillis() / 1000L;
		public int launches = 0;
	}
	
	public void save()
	{
		OptionsManager.save(this);
	}
}
