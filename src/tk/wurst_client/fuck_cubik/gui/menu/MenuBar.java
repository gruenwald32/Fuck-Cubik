package tk.wurst_client.fuck_cubik.gui.menu;

import javax.swing.JMenuBar;

import tk.wurst_client.fuck_cubik.gui.menu.menus.HelpMenu;
import tk.wurst_client.fuck_cubik.gui.menu.menus.ModelMenu;
import tk.wurst_client.fuck_cubik.gui.menu.menus.PackMenu;

public class MenuBar extends JMenuBar
{
	public PackMenu packMenu;
	public ModelMenu modelMenu;
	public HelpMenu helpMenu;
	
	public MenuBar()
	{
		super();
		packMenu = new PackMenu();
		this.add(packMenu);
		modelMenu = new ModelMenu();
		this.add(modelMenu);
		helpMenu = new HelpMenu();
		this.add(helpMenu);
	}
}
