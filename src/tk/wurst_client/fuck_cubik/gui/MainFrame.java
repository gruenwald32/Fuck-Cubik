package tk.wurst_client.fuck_cubik.gui;

import java.awt.BorderLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;

import tk.wurst_client.fuck_cubik.Main;
import tk.wurst_client.fuck_cubik.gui.desktop.DesktopPane;
import tk.wurst_client.fuck_cubik.gui.menu.MenuBar;

public class MainFrame extends JFrame
{
	public MenuBar menuBar;
	public DesktopPane desktop;

	public MainFrame() throws HeadlessException
	{
		super("Fuck Cubik v" + Main.VERSION);
		this.menuBar = new MenuBar();
		this.desktop = new DesktopPane();
		this.setLayout(new BorderLayout());
		
		this.add(menuBar, BorderLayout.NORTH);
		this.add(desktop, BorderLayout.CENTER);
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
