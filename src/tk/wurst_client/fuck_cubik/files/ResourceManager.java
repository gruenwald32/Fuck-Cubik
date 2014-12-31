package tk.wurst_client.fuck_cubik.files;

import java.io.InputStream;

public class ResourceManager
{
	public static final String RESOURCE_FOLDER = "resources";
	public static final String MISSING_TEXTURE = RESOURCE_FOLDER + "/missing.png";
	public static final String ABOUT_FILE = RESOURCE_FOLDER + "/ABOUT";
	public static final String LICENSE_FILE = RESOURCE_FOLDER + "/LICENSE";
	
	public static InputStream getStream(String resource)
	{
		return ResourceManager.class.getClassLoader().getResourceAsStream(resource);
	}
}
