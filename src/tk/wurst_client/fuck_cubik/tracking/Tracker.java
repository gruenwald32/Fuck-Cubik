package tk.wurst_client.fuck_cubik.tracking;

import com.dmurph.tracking.AnalyticsConfigData;
import com.dmurph.tracking.JGoogleAnalyticsTracker;
import com.dmurph.tracking.JGoogleAnalyticsTracker.GoogleAnalyticsVersion;

public class Tracker
{
	public final String ANALYTICS_CODE;
	public final String HOSTNAME;
	private JGoogleAnalyticsTracker tracker;

	public Tracker(String analyticsCode, String hostName)
	{
		ANALYTICS_CODE = analyticsCode;
		HOSTNAME = hostName;
		JGoogleAnalyticsTracker.setProxy(System.getenv("http_proxy"));
		tracker = new JGoogleAnalyticsTracker(new AnalyticsConfigData(ANALYTICS_CODE), GoogleAnalyticsVersion.V_4_7_2);
	}
	
	public void trackPageView(String url, String title)
	{
		tracker.trackPageView(url, title, HOSTNAME);
	}
	
	public void trackPageViewFromReferrer(String url, String title, String referrerSite, String referrerPage)
	{
		tracker.trackPageViewFromReferrer(url, title, HOSTNAME, referrerSite, referrerPage);
	}
	
	public void trackPageViewFromSearch(String url, String title, String searchSite, String keywords)
	{
		tracker.trackPageViewFromSearch(url, title, HOSTNAME, searchSite, keywords);
	}
	
	public void trackEvent(String category, String action)
	{
		tracker.trackEvent(category, action);
	}
	
	public void trackEvent(String category, String action, String label)
	{
		tracker.trackEvent(category, action, label);
	}
	
	public void trackEvent(String category, String action, String label, int value)
	{
		tracker.trackEvent(category, action, label, new Integer(value));
	}
}
