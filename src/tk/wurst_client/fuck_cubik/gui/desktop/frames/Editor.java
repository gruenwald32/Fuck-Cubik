package tk.wurst_client.fuck_cubik.gui.desktop.frames;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Editor extends AbstractFrame
{
	public JTextArea textarea;
	public JScrollPane scrollpane;
	
	public Editor()
	{
		super("Editor", true, false, true);
		textarea = new JTextArea();
		scrollpane = new JScrollPane(textarea);
		this.add(scrollpane, BorderLayout.CENTER);
		this.setSize(600, 800);
		this.setLocation(600, 0);
	}
}
