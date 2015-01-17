package tk.wurst_client.fuck_cubik.gui.menu;

import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar
{
	public PackMenu packMenu;
	public ModelMenu modelMenu;
	public OptionsMenu optionsMenu;
	public HelpMenu helpMenu;
	
	public MenuBar()
	{
		super();
		packMenu = new PackMenu();
		this.add(packMenu);
		modelMenu = new ModelMenu();
		this.add(modelMenu);
		optionsMenu = new OptionsMenu();
		this.add(optionsMenu);
		helpMenu = new HelpMenu();
		this.add(helpMenu);
	}
}
