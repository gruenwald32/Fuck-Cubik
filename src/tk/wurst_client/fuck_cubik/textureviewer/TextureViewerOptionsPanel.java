package tk.wurst_client.fuck_cubik.textureviewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Map.Entry;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import tk.wurst_client.fuck_cubik.Main;
import tk.wurst_client.fuck_cubik.gui.filechoosers.TextureFileChooser;

public class TextureViewerOptionsPanel extends JPanel
{
	public JComboBox<String> textureCombo;
	public JButton loadButton;
	public JCheckBox gridBox;
	public JCheckBox numbersBox;
	
	public TextureViewerOptionsPanel()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		textureCombo = new JComboBox<String>();
		textureCombo.setAlignmentX(CENTER_ALIGNMENT);
		reloadTextures();
		textureCombo.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(textureCombo.getSelectedIndex() == 0)
					Main.frame.desktop.textureViewer.viewer.setInput(null);
				else
					Main.frame.desktop.textureViewer.viewer.setInput(Main.renderer.textureLinkMap.get(textureCombo.getSelectedItem()));
			}
		});
		this.add(textureCombo);
		gridBox = new JCheckBox("Show grid", true);
		gridBox.setAlignmentX(CENTER_ALIGNMENT);
		gridBox.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Main.frame.desktop.textureViewer.viewer.showGrid = gridBox.isSelected();
				numbersBox.setEnabled(gridBox.isSelected());
				Main.frame.desktop.textureViewer.viewer.repaint();
			}
		});
		this.add(gridBox);
		numbersBox = new JCheckBox("Show numbers", true);
		numbersBox.setAlignmentX(CENTER_ALIGNMENT);
		numbersBox.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Main.frame.desktop.textureViewer.viewer.showNumbers = numbersBox.isSelected();
				Main.frame.desktop.textureViewer.viewer.repaint();
			}
		});
		this.add(numbersBox);
		loadButton = new JButton("Load external texture");
		loadButton.setAlignmentX(CENTER_ALIGNMENT);
		loadButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new TextureFileChooser().showOpenDialog(Main.frame);
			}
		});
		this.add(loadButton);
	}
	
	public void reloadTextures()
	{
		int lastCount = textureCombo.getItemCount();
		int lastIndex = textureCombo.getSelectedIndex();
		textureCombo.removeAllItems();
		textureCombo.addItem("__missing");
		if(Main.renderer != null)
			for(Entry<String, File> entry : Main.renderer.textureLinkMap.entrySet())
				textureCombo.addItem(entry.getKey());
		if(textureCombo.getItemCount() == lastCount)
			textureCombo.setSelectedIndex(lastIndex);
		else
			textureCombo.setSelectedIndex(-1);
	}
}
