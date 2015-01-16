package tk.wurst_client.fuck_cubik.update;

@SuppressWarnings("unused")
public class VersionManager
{
	public static final byte MAJOR_VERSION = 1;
	public static final byte MINOR_VERSION = 4;
	public static final byte PATCH = 0;
	public static final byte PRE_RELEASE = 0;
	
	public static final String FORMATTED_VERSION = MAJOR_VERSION
		+ "." + MINOR_VERSION
		+ (PATCH > 0 ? "." + PATCH : "")
		+ (PRE_RELEASE > 0 ? "pre" + PRE_RELEASE : "");
}
