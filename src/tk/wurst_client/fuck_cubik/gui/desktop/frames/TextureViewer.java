package tk.wurst_client.fuck_cubik.gui.desktop.frames;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;

import tk.wurst_client.fuck_cubik.Main;
import tk.wurst_client.fuck_cubik.gui.filechoosers.TextureFileChooser;
import tk.wurst_client.fuck_cubik.gui.textureviewer.TextureViewerPanel;

public class TextureViewer extends AbstractFrame
{
	public TextureViewerPanel viewer;
	private JButton changeButton;
	public JCheckBox gridBox;
	public JCheckBox numbersBox;
	
	public TextureViewer()
	{
		super("Texture Viewer", true, false, false);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		viewer = new TextureViewerPanel();
		this.add(viewer);
		changeButton = new JButton("Change texture");
		changeButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new TextureFileChooser().showOpenDialog(Main.frame);
			}
		});
		this.add(changeButton);
		gridBox = new JCheckBox("Show grid", true);
		gridBox.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				viewer.showGrid = gridBox.isSelected();
				numbersBox.setEnabled(gridBox.isSelected());
				viewer.repaint();
			}
		});
		this.add(gridBox);
		numbersBox = new JCheckBox("Show numbers", true);
		numbersBox.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				viewer.showNumbers = numbersBox.isSelected();
				viewer.repaint();
			}
		});
		this.add(numbersBox);
		this.setSize(600, 200);
		this.setLocation(0, 600);
	}
}
