package tk.wurst_client.fuck_cubik;

import java.awt.Toolkit;

import javax.swing.UIManager;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import tk.wurst_client.fuck_cubik.dialogs.ErrorMessage;
import tk.wurst_client.fuck_cubik.gui.MainFrame;
import tk.wurst_client.fuck_cubik.preview.InputListener;
import tk.wurst_client.fuck_cubik.preview.render.Renderer;
import tk.wurst_client.fuck_cubik.tracking.Tracker;
import tk.wurst_client.fuck_cubik.update.Updater;

public class Main
{
	public static Updater updater;
	public static MainFrame frame;
	public static Renderer renderer;
	public static InputListener inputListener;
	public static Tracker tracker;
	public static boolean isSmallScreen;
	
	public static void main(String[] args)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			tracker = new Tracker("UA-52838431-4", "fuck-cubik.wurst-client.tk");
			tracker.trackPageView("/start/", "Start");
			isSmallScreen = Toolkit.getDefaultToolkit().getScreenSize().height < 860 || Toolkit.getDefaultToolkit().getScreenSize().width < 1216;
			updater = new Updater();
			updater.checkForUpdate();
			frame = new MainFrame();
			renderer = new Renderer();
			inputListener = new InputListener();
			Display.setParent(frame.desktop.preview.canvas);
			Display.setVSyncEnabled(true);
			Display.setDisplayMode(isSmallScreen ? new DisplayMode(400, 400) : new DisplayMode(600, 600));
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
