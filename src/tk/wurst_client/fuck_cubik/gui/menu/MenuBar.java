package tk.wurst_client.fuck_cubik.gui.menu;

import javax.swing.JMenuBar;

import tk.wurst_client.fuck_cubik.gui.menu.menus.FileMenu;

public class MenuBar extends JMenuBar
{
	public FileMenu fileMenu;

	public MenuBar()
	{
		super();
		this.fileMenu = new FileMenu();
		this.add(fileMenu);
	}
}
