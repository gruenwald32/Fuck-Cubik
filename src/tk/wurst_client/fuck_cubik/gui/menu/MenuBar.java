package tk.wurst_client.fuck_cubik.gui.menu;

import javax.swing.JMenuBar;

import tk.wurst_client.fuck_cubik.gui.menu.menus.FileMenu;
import tk.wurst_client.fuck_cubik.gui.menu.menus.HelpMenu;

public class MenuBar extends JMenuBar
{
	public FileMenu fileMenu;
	public HelpMenu helpMenu;

	public MenuBar()
	{
		super();
		fileMenu = new FileMenu();
		this.add(fileMenu);
		helpMenu = new HelpMenu();
		this.add(helpMenu);
	}
}
