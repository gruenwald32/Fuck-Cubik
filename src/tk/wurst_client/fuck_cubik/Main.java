package tk.wurst_client.fuck_cubik;

import javax.swing.UIManager;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import tk.wurst_client.fuck_cubik.gui.MainFrame;
import tk.wurst_client.fuck_cubik.preview.InputListener;
import tk.wurst_client.fuck_cubik.preview.render.Renderer;

public class Main
{
	public static final String VERSION = "1.0";
	
	public static MainFrame frame;
	public static Renderer renderer;
	public static InputListener inputListener;
	
	public static void main(String[] args)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
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
			e.printStackTrace();
			System.exit(-1);
		}finally
		{
			System.gc();
		}
	}
}
