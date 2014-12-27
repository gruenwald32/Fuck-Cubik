package tk.wurst_client.fuck_cubik.gui.desktop.frames;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

import tk.wurst_client.fuck_cubik.Main;

public class Editor extends AbstractFrame
{
	public JTextArea textarea;
	public JScrollPane scrollpane;
	public UndoManager undoManager;
	private File file;
	public boolean fileChanged;
	
	public Editor()
	{
		super("Editor", true, false, true);
		textarea = new JTextArea();
		undoManager = new UndoManager();
		textarea.getDocument().addUndoableEditListener(undoManager);
		textarea.getDocument().addUndoableEditListener(new UndoableEditListener()
		{
			@Override
			public void undoableEditHappened(UndoableEditEvent e)
			{
				updateTitle(true);
			}
		});
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
						{
							undoManager.undo();
						}else if(e.getKeyCode() == KeyEvent.VK_Y)
						{
							undoManager.redo();
						}else if(e.getKeyCode() == KeyEvent.VK_S)
						{
							if(Main.frame.menuBar.modelMenu.save.isEnabled())
								Main.frame.menuBar.modelMenu.save.doClick();
							else
								Main.frame.menuBar.modelMenu.saveAs.doClick();
						}else if(e.getKeyCode() == KeyEvent.VK_O)
						{
							Main.frame.menuBar.modelMenu.open.doClick();
						}
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

	public void updateTitle(boolean fileChanged)
	{
		if(this.fileChanged != fileChanged)
		{
			this.setTitle((file == null ? "Editor" : (fileChanged ? "* " : "") + "Editor - " + file.getName()));
			this.fileChanged = fileChanged;
		}
	}

	public File getFile()
	{
		return file;
	}

	public void setFile(File file)
	{
		this.file = file;
		Main.frame.menuBar.modelMenu.save.setEnabled(file != null);
		updateTitle(false);
	}
}
