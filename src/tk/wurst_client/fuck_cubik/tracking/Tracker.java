package tk.wurst_client.fuck_cubik.tracking;

import tk.wurst_client.fuck_cubik.Main;

import com.dmurph.tracking.AnalyticsConfigData;
import com.dmurph.tracking.AnalyticsRequestData;
import com.dmurph.tracking.JGoogleAnalyticsTracker;

public class Tracker extends JGoogleAnalyticsTracker
{
	public final String ANALYTICS_CODE;
	public final String HOSTNAME;
	
	public Tracker(String analyticsCode, String hostName)
	{
		super(new AnalyticsConfigData(analyticsCode), GoogleAnalyticsVersion.V_4_7_2);
		ANALYTICS_CODE = analyticsCode;
		HOSTNAME = hostName;
		JGoogleAnalyticsTracker.setProxy(System.getenv("http_proxy"));
	}
	
	public void trackPageView(String url, String title)
	{
		if(Main.options.google_analytics.enabled)
			super.trackPageView(url, title, HOSTNAME);
	}
	
	public void trackPageViewFromReferrer(String url, String title, String referrerSite, String referrerPage)
	{
		if(Main.options.google_analytics.enabled)
			super.trackPageViewFromReferrer(url, title, HOSTNAME, referrerSite, referrerPage);
	}
	
	public void trackPageViewFromSearch(String url, String title, String searchSite, String keywords)
	{
		if(Main.options.google_analytics.enabled)
			super.trackPageViewFromSearch(url, title, HOSTNAME, searchSite, keywords);
	}
	
	@Override
	public void trackEvent(String category, String action)
	{
		if(Main.options.google_analytics.enabled)
			super.trackEvent(category, action);
	}
	
	@Override
	public void trackEvent(String category, String action, String label)
	{
		if(Main.options.google_analytics.enabled)
			super.trackEvent(category, action, label);
	}
	
	public void trackEvent(String category, String action, String label, int value)
	{
		if(Main.options.google_analytics.enabled)
			super.trackEvent(category, action, label, new Integer(value));
	}
	
	@Override
	public void makeCustomRequest(AnalyticsRequestData data)
	{
		if(Main.options.google_analytics.enabled)
			super.makeCustomRequest(data);
	}
}
