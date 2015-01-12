package tk.wurst_client.fuck_cubik.files;

import java.io.File;

public abstract class FileManager
{
	public static final File CURRENT_DIRECTORY = new File(FileManager.class.getProtectionDomain().getCodeSource().getLocation().getPath());
	public static final File OPTIONS_FILE = new File(CURRENT_DIRECTORY, "options.json");
	public static final File OPTIONS_FILE_FALLBACK = new File(".", "options.json");
	public static final File METADATA_FILE = new File(CURRENT_DIRECTORY, "pack.mcmeta");
	public static final File ASSETS_DIRECTORY = new File(CURRENT_DIRECTORY, "assets");
	public static final File MODELS_DIRECTORY = new File(ASSETS_DIRECTORY, "minecraft\\models");
	public static final File TEXTURES_DIRECTORY = new File(ASSETS_DIRECTORY, "minecraft\\textures");
}
