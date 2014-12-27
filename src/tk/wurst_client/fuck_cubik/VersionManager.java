package tk.wurst_client.fuck_cubik;

@SuppressWarnings("unused")
public class VersionManager
{
	public static final byte MAJOR_VERSION = 1;
	public static final byte MINOR_VERSION = 1;
	public static final byte PATCH = 0;
	public static final long BUILD = 33;
	public static final byte PRE_RELEASE = 0;
	public static final String SIMPLE_VERSION = MAJOR_VERSION + "." + MINOR_VERSION + (PATCH > 0 ? "." + PATCH : "") + (PRE_RELEASE > 0 ? "pre" + PRE_RELEASE : "" );
	public static final String FULL_VERSION = MAJOR_VERSION + "." + MINOR_VERSION + "." + PATCH + (PRE_RELEASE > 0 ? "pre" + PRE_RELEASE : "" ) + "_" + BUILD;
}
