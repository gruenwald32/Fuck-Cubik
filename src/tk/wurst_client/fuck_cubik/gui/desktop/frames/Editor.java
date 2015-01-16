package tk.wurst_client.fuck_cubik.gui.desktop.frames;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

import tk.wurst_client.fuck_cubik.Main;
import tk.wurst_client.fuck_cubik.editor.EditorToolBar;
import tk.wurst_client.fuck_cubik.error.ErrorMessage;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class Editor extends AbstractFrame
{
	public EditorToolBar toolbar;
	public JTextArea textarea;
	public JScrollPane scrollpane;
	public UndoManager undoManager;
	private File file;
	public boolean fileChanged;
	
	public Editor()
	{
		super("Editor", true, false, true);
		toolbar = new EditorToolBar();
		this.add(toolbar, BorderLayout.NORTH);
		textarea = new JTextArea();
		undoManager = new UndoManager();
		textarea.getDocument().addUndoableEditListener(undoManager);
		textarea.getDocument().addUndoableEditListener(new UndoableEditListener()
		{
			@Override
			public void undoableEditHappened(UndoableEditEvent e)
			{
				onCodeChange(true);
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
					try
					{
						if(e.getKeyCode() == KeyEvent.VK_Z)
							undoManager.undo();
						else if(e.getKeyCode() == KeyEvent.VK_Y)
							undoManager.redo();
						else if(e.getKeyCode() == KeyEvent.VK_S)
						{
							if(Main.frame.menuBar.modelMenu.save.isEnabled())
								Main.frame.menuBar.modelMenu.save.doClick();
							else
								Main.frame.menuBar.modelMenu.saveAs.doClick();
						}else if(e.getKeyCode() == KeyEvent.VK_O)
							Main.frame.menuBar.modelMenu.open.doClick();
					}catch(CannotUndoException | CannotRedoException e1)
					{	
						
					}
				else if(e.getKeyCode() == KeyEvent.VK_F5)
					Main.frame.desktop.preview.toolbar.refreshButton.doClick();
			}
		});
		scrollpane = new JScrollPane(textarea);
		this.add(scrollpane, BorderLayout.CENTER);
		if(Main.isSmallScreen)
		{
			this.setSize(400, 600);
			this.setLocation(400, 0);
		}else
		{
			this.setSize(600, 800);
			this.setLocation(600, 0);
		}
	}
	
	public void onCodeChange(boolean fileChanged)
	{
		if(this.fileChanged != fileChanged)
		{
			setTitle(file == null ? "Editor" : (fileChanged ? "* " : "") + "Editor - " + file.getName());
			this.fileChanged = fileChanged;
		}
		toolbar.formatButton.setEnabled(!textarea.getText().isEmpty());
	}
	
	public File getFile()
	{
		return file;
	}
	
	public void setFile(File file)
	{
		this.file = file;
		Main.frame.menuBar.modelMenu.save.setEnabled(file != null);
		onCodeChange(false);
	}
	
	public JsonElement getCode()
	{
		try
		{
			return new JsonParser().parse(textarea.getDocument().getText(0, Main.frame.desktop.editor.textarea.getDocument().getLength()));
		}catch(JsonSyntaxException e)
		{
			JOptionPane.showMessageDialog(Main.frame.desktop.editor, e.getLocalizedMessage(), "Syntax error", JOptionPane.INFORMATION_MESSAGE);
		}catch(Exception e)
		{
			new ErrorMessage("reading code", e);
		}
		return null;
	}
	
	public void setCode(String code)
	{
		textarea.setText(code);
	}
}
