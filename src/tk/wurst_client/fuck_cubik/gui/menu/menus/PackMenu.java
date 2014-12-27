package tk.wurst_client.fuck_cubik.gui.menu.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import tk.wurst_client.fuck_cubik.Main;
import tk.wurst_client.fuck_cubik.gui.filechoosers.PackFileChooser;
import tk.wurst_client.fuck_cubik.pack.PackManager;

public class PackMenu extends JMenu
{
	public PackFileChooser fileChooser;
	public JMenuItem importPack;
	public JMenuItem exportPack;
	public JMenuItem clearPack;
	
	public PackMenu()
	{
		super("Pack");
		fileChooser = new PackFileChooser();
		importPack = new JMenuItem("Import pack");
		importPack.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				fileChooser.showOpenDialog(Main.frame);
			}
		});
		this.add(importPack);
		exportPack = new JMenuItem("Export pack");
		exportPack.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				fileChooser.showSaveDialog(Main.frame);
			}
		});
		updateExportButton();
		this.add(exportPack);
		this.add(new JSeparator());
		clearPack = new JMenuItem("Clear pack");
		clearPack.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				fileChooser.showDeleteDialog(Main.frame);
			}
		});
		updateExportButton();
		this.add(clearPack);
	}
	
	public void updateExportButton()
	{
		exportPack.setEnabled(PackManager.ASSETS_FOLDER.exists() && PackManager.METADATA_FILE.exists());
	}
}
