package tk.wurst_client.fuck_cubik.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import tk.wurst_client.fuck_cubik.Main;
import tk.wurst_client.fuck_cubik.files.FileManager;
import tk.wurst_client.fuck_cubik.gui.filechoosers.PackFileChooser;

public class PackMenu extends JMenu
{
	public PackFileChooser fileChooser;
	public JMenuItem importPack;
	public JMenuItem exportPack;
	public JMenuItem editDescription;
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
				Main.tracker.trackPageView("/menu/pack/import", "Import pack");
				fileChooser.showOpenDialog(Main.frame);
				Main.tracker.trackPageView("/", "Main frame");
			}
		});
		this.add(importPack);
		exportPack = new JMenuItem("Export pack");
		exportPack.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Main.tracker.trackPageView("/menu/pack/export", "Export pack");
				fileChooser.showSaveDialog(Main.frame);
				Main.tracker.trackPageView("/", "Main frame");
			}
		});
		updateExportButton();
		this.add(exportPack);
		editDescription = new JMenuItem("Edit pack description");
		editDescription.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Main.tracker.trackPageView("/menu/pack/edit-description", "Edit pack description");
				fileChooser.showDescriptionDialog(Main.frame);
				Main.tracker.trackPageView("/", "Main frame");
			}
		});
		this.add(editDescription);
		this.add(new JSeparator());
		clearPack = new JMenuItem("Clear pack");
		clearPack.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Main.tracker.trackPageView("/menu/pack/clear", "Clear pack");
				fileChooser.showDeleteDialog(Main.frame);
				Main.tracker.trackPageView("/", "Main frame");
			}
		});
		this.add(clearPack);
	}
	
	public void updateExportButton()
	{
		exportPack.setEnabled(FileManager.ASSETS_DIRECTORY.exists() && FileManager.METADATA_FILE.exists());
	}
}
