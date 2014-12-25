package tk.wurst_client.fuck_cubik.gui.desktop.frames;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

public class Editor extends AbstractFrame
{
	public JTextArea textarea;
	public JScrollPane scrollpane;
	public UndoManager undoManager;
	
	public Editor()
	{
		super("Editor", true, false, true);
		textarea = new JTextArea();
		undoManager = new UndoManager();
		textarea.getDocument().addUndoableEditListener(undoManager);
		textarea.addKeyListener(new KeyListener()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
				
			}
			
			@Override
			public void keyReleased(KeyEvent e)
			{
				
			}
			
			@Override
			public void keyPressed(KeyEvent e)
			{
				if(e.isControlDown())
				{
					try
					{
						if(e.getKeyCode() == KeyEvent.VK_Z)
							undoManager.undo();
						else if(e.getKeyCode() == KeyEvent.VK_Y)
							undoManager.redo();
					}catch(CannotUndoException | CannotRedoException e1)
					{
						
					}
				}
			}
		});
		scrollpane = new JScrollPane(textarea);
		this.add(scrollpane, BorderLayout.CENTER);
		this.setSize(600, 800);
		this.setLocation(600, 0);
	}
}
