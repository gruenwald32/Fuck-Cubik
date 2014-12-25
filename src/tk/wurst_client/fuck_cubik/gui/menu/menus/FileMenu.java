package tk.wurst_client.fuck_cubik.gui.menu.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import tk.wurst_client.fuck_cubik.Main;
import tk.wurst_client.fuck_cubik.gui.filechoosers.ModelFileChooser;

public class FileMenu extends JMenu
{
	public JMenuItem open;

	public FileMenu()
	{
		super("File");
		this.open = new JMenuItem("Open");
		open.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new ModelFileChooser().showOpenDialog(Main.frame);
			}
		});
		this.add(open);
	}
}
