package tk.wurst_client.fuck_cubik.update;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.JOptionPane;

import tk.wurst_client.fuck_cubik.dialogs.ErrorMessage;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Updater
{
	private boolean outdated;
	private String latestVersion;
	private byte latestMajor;
	private byte latestMinor;
	private byte latestPatch;
	private byte latestPreRelease;
	private JsonArray json;
	private JsonObject latestRelease;
	
	public void checkForUpdate()
	{
		try
		{
			HttpsURLConnection connection = (HttpsURLConnection)new URL("https://api.github.com/repos/Fuck-Cubik/Fuck-Cubik/releases").openConnection();
			BufferedReader load = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String content = load.readLine();
			for(String line = ""; (line = load.readLine()) != null;)
				content += "\n" + line;
			load.close();
			System.out.println(content);
			json = new JsonParser().parse(content).getAsJsonArray();
			latestRelease = new JsonObject();
			for(JsonElement release : json)
				if(!release.getAsJsonObject().get("prerelease").getAsBoolean() || VersionManager.PRE_RELEASE > 0)
				{
					latestRelease = release.getAsJsonObject();
					break;
				}
			latestVersion = latestRelease.get("tag_name").getAsString();
			outdated = false;
			try
			{
				latestMajor = Byte.parseByte(latestVersion.split("\\.")[0]);
				latestMinor = Byte.parseByte(latestVersion.split("\\.")[1]);
				if(latestRelease.get("prerelease").getAsBoolean())
					latestPreRelease = Byte.parseByte(latestVersion.substring(latestVersion.indexOf("pre")));
				else
					latestPreRelease = 0;
				try
				{
					latestPatch = Byte.parseByte(latestVersion.split("\\.")[2]);
				}catch(Exception e)
				{
					latestPatch = 0;
				}
				outdated = isLatestVersionHigher();
			}catch(Exception e)
			{
				System.out.println("Latest version (\"" + latestVersion + "\") doesn't follow the semver.org syntax!");
				outdated = !latestVersion.equals(VersionManager.SIMPLE_VERSION);
			}
			if(outdated)
				System.out.println("Update found: " + latestVersion);
			else
				System.out.println("No update found.");
			if(outdated)
				update();
		}catch(Exception e)
		{
			new ErrorMessage("checking for updates", e);
		}
	}
	
	private boolean isLatestVersionHigher()
	{
		if(latestMajor > VersionManager.MAJOR_VERSION)
			return true;
		else if(latestMajor < VersionManager.MAJOR_VERSION)
			return false;
		else if(latestMinor > VersionManager.MINOR_VERSION)
			return true;
		else if(latestMajor < VersionManager.MINOR_VERSION)
			return false;
		else if(latestPatch > VersionManager.PATCH)
			return true;
		else if(latestMajor < VersionManager.PATCH)
			return false;
		else if(latestPreRelease > VersionManager.PRE_RELEASE)
			return true;
		else
			return false;
	}
	
	@SuppressWarnings("unused")
	private void update()
	{
		Object message = "<html>"
			+ "<p>Version " + latestVersion + " of Fuck Cubik is available.</p>";
		int action = JOptionPane.showOptionDialog(null, message, "Update available", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Update now", "View changelog", "Update later"}, 0);
		if(action == 0 || action == 1)
		{
			try
			{
				String url;
				if(VersionManager.PRE_RELEASE > 0)
					url = json.get(0).getAsJsonObject().get("html_url").getAsString();
				else
					url = "https://github.com/Fuck-Cubik/Fuck-Cubik/releases/latest";
				Desktop.getDesktop().browse(new URI(url));
			}catch(Exception e)
			{
				new ErrorMessage("opening link", e);
			}
			System.exit(0);
		}
	}
	
	public boolean isOutdated()
	{
		return outdated;
	}
	
	public String getLatestVersion()
	{
		return latestVersion;
	}
	
	public byte getLatestMajor()
	{
		return latestMajor;
	}
	
	public byte getLatestMinor()
	{
		return latestMinor;
	}
	
	public byte getLatestPatch()
	{
		return latestPatch;
	}
	
	public byte getLatestPreRelease()
	{
		return latestPreRelease;
	}
}
