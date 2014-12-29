package tk.wurst_client.fuck_cubik;

import javax.swing.UIManager;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import tk.wurst_client.fuck_cubik.dialogs.ErrorMessage;
import tk.wurst_client.fuck_cubik.gui.MainFrame;
import tk.wurst_client.fuck_cubik.preview.InputListener;
import tk.wurst_client.fuck_cubik.preview.render.Renderer;
import tk.wurst_client.fuck_cubik.updater.Updater;

public class Main
{
	public static Updater updater;
	public static MainFrame frame;
	public static Renderer renderer;
	public static InputListener inputListener;
	
	public static void main(String[] args)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			updater = new Updater();
			updater.checkForUpdate();
			frame = new MainFrame();
			renderer = new Renderer();
			inputListener = new InputListener();
			Display.setParent(frame.desktop.preview.canvas);
			Display.setVSyncEnabled(true);
			Display.setDisplayMode(new DisplayMode(600, 600));
			Display.setInitialBackground(240, 240, 240);
			Display.create();
			renderer.init();
			while(!Display.isCloseRequested())
			{
				renderer.render();
				Display.update();
				inputListener.listen();
				Display.sync(60);
			}
			Display.destroy();
			System.exit(0);
		}catch(Exception e)
		{
			new ErrorMessage("running Fuck Cubik", e);
			System.exit(-1);
		}finally
		{
			System.gc();
		}
	}
}
