package tk.wurst_client.fuck_cubik.editor.texturemanager;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;

import tk.wurst_client.fuck_cubik.Main;
import tk.wurst_client.fuck_cubik.dialogs.ErrorMessage;
import tk.wurst_client.fuck_cubik.gui.filechoosers.TextureFileChooser;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class TextureEditor extends JDialog
{
	public JsonObject textures;
	public String name;
	
	public JPanel panel;
	public JTextField nameField;
	public JButton pathButton;
	
	public TextureEditor(JsonObject textures, String name)
	{
		super(Main.frame, name);
		this.textures = textures;
		this.name = name;
		try
		{
			panel = new JPanel(new GridLayout(2, 2));
			panel.setBorder(BorderFactory.createTitledBorder("Properties"));
			panel.add(new JLabel("Name: "));
			nameField = new JTextField(name);
			nameField.getDocument().addUndoableEditListener(new UndoableEditListener()
			{
				@Override
				public void undoableEditHappened(UndoableEditEvent e)
				{
					String path = TextureEditor.this.textures.get(TextureEditor.this.name).getAsString();
					TextureEditor.this.textures.remove(TextureEditor.this.name);
					TextureEditor.this.name = TextureEditor.this.nameField.getText();
					TextureEditor.this.textures.addProperty(TextureEditor.this.name, path);
					TextureEditor.this.updateCode();
				}
			});
			panel.add(nameField);
			panel.add(new JLabel("Path: "));
			pathButton = new JButton(textures.get(name).getAsString());
			pathButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					TextureFileChooser fileChooser = new TextureFileChooser();
					int action = fileChooser.showOpenDialog(TextureEditor.this);
					if(action != JFileChooser.APPROVE_OPTION)
						return;
					String path = fileChooser.getSelectedFile().getPath().replace("\\", "/");
					path = path.substring(path.indexOf("assets/minecraft/textures/") + 26, path.length() - 4);
					TextureEditor.this.pathButton.setText(path);
					TextureEditor.this.textures.remove(TextureEditor.this.name);
					TextureEditor.this.textures.addProperty(TextureEditor.this.name, path);
					TextureEditor.this.updateCode();
				}
			});
			panel.add(pathButton);
			add(panel);
			addWindowListener(new WindowListener()
			{
				@Override
				public void windowOpened(WindowEvent e)
				{	
					
				}
				
				@Override
				public void windowIconified(WindowEvent e)
				{	
					
				}
				
				@Override
				public void windowDeiconified(WindowEvent e)
				{	
					
				}
				
				@Override
				public void windowDeactivated(WindowEvent e)
				{	
					
				}
				
				@Override
				public void windowClosing(WindowEvent e)
				{
					Main.frame.desktop.editor.toolbar.textureManager.updateList();
					Main.frame.desktop.editor.toolbar.textureManager.setVisible(true);
				}
				
				@Override
				public void windowClosed(WindowEvent e)
				{	
					
				}
				
				@Override
				public void windowActivated(WindowEvent e)
				{	
					
				}
			});
			pack();
			setLocationRelativeTo(Main.frame.desktop.editor);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setAlwaysOnTop(true);
			Main.frame.desktop.editor.toolbar.textureManager.setVisible(false);
			setVisible(true);
		}catch(Exception e)
		{
			new ErrorMessage("editing texture", e);
		}
	}
	
	public void updateCode()
	{
		try
		{
			JsonObject json = Main.frame.desktop.editor.getCode().getAsJsonObject();
			json.remove("textures");
			json.add("textures", textures);
			Main.frame.desktop.editor.setCode(new GsonBuilder().setPrettyPrinting().create().toJson(json));
			Main.renderer.refreshLater();
		}catch(Exception e)
		{
			new ErrorMessage("changing code through the texture editor", e);
		}
	}
}
