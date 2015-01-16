package tk.wurst_client.fuck_cubik.gui;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;

import javax.swing.JFrame;

import tk.wurst_client.fuck_cubik.Main;
import tk.wurst_client.fuck_cubik.files.ResourceManager;
import tk.wurst_client.fuck_cubik.gui.desktop.DesktopPane;
import tk.wurst_client.fuck_cubik.gui.menu.MenuBar;
import tk.wurst_client.fuck_cubik.update.VersionManager;

public class MainFrame extends JFrame
{
	public MenuBar menuBar;
	public DesktopPane desktop;
	
	public MainFrame() throws HeadlessException
	{
		super("Fuck Cubik v" + VersionManager.FORMATTED_VERSION);
		menuBar = new MenuBar();
		desktop = new DesktopPane();
		setLayout(new BorderLayout());
		
		this.add(menuBar, BorderLayout.NORTH);
		this.add(desktop, BorderLayout.CENTER);
		
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/" + ResourceManager.ICON_IMAGE)));
		setVisible(true);
		Main.tracker.trackPageView("/", "Main frame");
	}
}
