package tk.wurst_client.fuck_cubik.editor.elementmanager;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import tk.wurst_client.fuck_cubik.Main;
import tk.wurst_client.fuck_cubik.dialogs.ErrorMessage;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class ElementEditor extends JDialog
{
	public JPanel fromto;
	public JsonObject element;
	public int selection;
	public JPanel faces;
	
	public ElementEditor(JsonObject element, int selection)
	{
		super(Main.frame, "Element #" + (selection + 1));
		this.element = element;
		this.selection = selection;
		try
		{
			setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
			fromto = new JPanel(new GridLayout(3, 4));
			fromto.setBorder(BorderFactory.createTitledBorder("Coordinates"));
			fromto.add(Box.createGlue());
			fromto.add(new JLabel("X", SwingConstants.CENTER));
			fromto.add(new JLabel("Y", SwingConstants.CENTER));
			fromto.add(new JLabel("Z", SwingConstants.CENTER));
			fromto.add(new JLabel("From: ", SwingConstants.RIGHT));
			fromto.add(new ElementFromToSpinner(element.get("from").getAsJsonArray(), 0, this));
			fromto.add(new ElementFromToSpinner(element.get("from").getAsJsonArray(), 1, this));
			fromto.add(new ElementFromToSpinner(element.get("from").getAsJsonArray(), 2, this));
			fromto.add(new JLabel("To: ", SwingConstants.RIGHT));
			fromto.add(new ElementFromToSpinner(element.get("to").getAsJsonArray(), 0, this));
			fromto.add(new ElementFromToSpinner(element.get("to").getAsJsonArray(), 1, this));
			fromto.add(new ElementFromToSpinner(element.get("to").getAsJsonArray(), 2, this));
			this.add(fromto);
			faces = new JPanel();
			faces.setLayout(new BoxLayout(faces, BoxLayout.Y_AXIS));
			faces.setBorder(BorderFactory.createTitledBorder("Faces"));
			String[] faceNames = new String[]{"up", "down", "north", "south", "west", "east"};
			for(String faceName : faceNames)
				faces.add(new ElementFacePanel(element, faceName, this));
			this.add(faces);
			pack();
			if(Main.isSmallScreen)
				setSize(getSize().width, 660);
			setLocationRelativeTo(Main.frame.desktop.editor);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setAlwaysOnTop(true);
			setVisible(true);
		}catch(Exception e)
		{
			new ErrorMessage("editing element", e);
		}
	}
	
	public void updateCode()
	{
		try
		{
			JsonObject json = Main.frame.desktop.editor.getCode().getAsJsonObject();
			json.get("elements").getAsJsonArray().set(selection, element);
			Main.frame.desktop.editor.setCode(new GsonBuilder().setPrettyPrinting().create().toJson(json));
			Main.renderer.refresh();
			Main.renderer.markedElement = selection;
		}catch(Exception e)
		{
			new ErrorMessage("changing code through the element editor", e);
		}
	}
}
