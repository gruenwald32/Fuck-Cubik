package tk.wurst_client.fuck_cubik.preview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JToolBar;
import javax.swing.SwingWorker;

import tk.wurst_client.fuck_cubik.Main;

public class PreviewToolBar extends JToolBar
{
	public JButton resetButton;
	public JButton refreshButton;
	public JCheckBox gridBox;
	public JCheckBox focusBox;
	public JCheckBox compassBox;
	
	public PreviewToolBar()
	{
		resetButton = new JButton("Reset camera");
		resetButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Main.tracker.trackEvent("preview toolbar", "reset camera");
				Main.renderer.reset();
			}
		});
		this.add(resetButton);
		refreshButton = new JButton("Refresh (F5)");
		refreshButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Main.tracker.trackEvent("preview toolbar", "refresh");
				new SwingWorker<Object, Object>()
				{
					@Override
					protected Object doInBackground() throws Exception
					{
						try
						{
							Main.renderer.refresh();
						}catch(Exception e)
						{	
							
						}
						return null;
					}
				}.execute();
			}
		});
		this.add(refreshButton);
		gridBox = new JCheckBox("Show grid", true);
		gridBox.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Main.tracker.trackEvent("preview toolbar", "toggle grid", gridBox.isSelected() ? "enable" : "disable");
				Main.frame.desktop.preview.showGrid = gridBox.isSelected();
			}
		});
		this.add(gridBox);
		focusBox = new JCheckBox("Show focus", true);
		focusBox.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Main.tracker.trackEvent("preview toolbar", "toggle focus", focusBox.isSelected() ? "enable" : "disable");
				Main.frame.desktop.preview.showFocus = focusBox.isSelected();
			}
		});
		this.add(focusBox);
		compassBox = new JCheckBox("Show compass", true);
		compassBox.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Main.tracker.trackEvent("preview toolbar", "toggle compass", compassBox.isSelected() ? "enable" : "disable");
				Main.frame.desktop.preview.showCompass = compassBox.isSelected();
			}
		});
		this.add(compassBox);
	}
}
