package tk.wurst_client.fuck_cubik.dialogs;

import javax.swing.JDialog;
import javax.swing.JProgressBar;

import tk.wurst_client.fuck_cubik.Main;

public class UnknownProgressDialog extends JDialog
{
	public UnknownProgressDialog(String title)
	{
		super(Main.frame, title);
		JProgressBar progress = new JProgressBar();
		progress.setIndeterminate(true);
		this.add(progress);
		this.setSize(256, 64);
		this.setLocationRelativeTo(Main.frame);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setAlwaysOnTop(true);
		this.setVisible(true);
	}
}
