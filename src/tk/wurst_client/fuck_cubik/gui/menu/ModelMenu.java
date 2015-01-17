package tk.wurst_client.fuck_cubik.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.text.BadLocationException;

import tk.wurst_client.fuck_cubik.Main;
import tk.wurst_client.fuck_cubik.error.ErrorMessage;
import tk.wurst_client.fuck_cubik.gui.filechoosers.ModelFileChooser;

public class ModelMenu extends JMenu
{
	public ModelFileChooser fileChooser;
	public JMenuItem open;
	public JMenuItem save;
	public JMenuItem saveAs;
	
	public ModelMenu()
	{
		super("Model");
		fileChooser = new ModelFileChooser();
		open = new JMenuItem("Open");
		open.setToolTipText("Open (CTRL + O)");
		open.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Main.tracker.trackPageView("/menu/model/open", "Open");
				fileChooser.showOpenDialog(Main.frame);
				Main.tracker.trackPageView("/", "Main frame");
			}
		});
		this.add(open);
		save = new JMenuItem("Save");
		save.setToolTipText("Save (CTRL + S)");
		save.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					fileChooser.save(Main.frame.desktop.editor.getFile());
				}catch(FileNotFoundException | BadLocationException e1)
				{
					new ErrorMessage("saving model", e1);
				}
			}
		});
		save.setEnabled(false);
		this.add(save);
		saveAs = new JMenuItem("Save as...");
		saveAs.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Main.tracker.trackPageView("/menu/model/save-as", "Save as...");
				fileChooser.showSaveDialog(Main.frame);
				Main.tracker.trackPageView("/", "Main frame");
			}
		});
		this.add(saveAs);
	}
}
