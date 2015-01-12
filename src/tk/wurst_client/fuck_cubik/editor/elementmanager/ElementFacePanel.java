package tk.wurst_client.fuck_cubik.editor.elementmanager;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ElementFacePanel extends JPanel
{
	public JsonObject element;
	public String faceName;
	public ElementEditor elementEditor;
	
	public JsonObject face;
	public JCheckBox enabled;
	public JsonArray uvArray;
	public ElementUVSpinner x1;
	public ElementUVSpinner x2;
	public ElementUVSpinner y1;
	public ElementUVSpinner y2;
	public JTextField texture;
	public JButton autoUV;
	
	public ElementFacePanel(JsonObject element, String faceName, ElementEditor elementEditor)
	{
		super(new GridLayout(3, 4));
		this.element = element;
		this.faceName = faceName;
		this.elementEditor = elementEditor;
		face = null;
		try
		{
			face = element.get("faces").getAsJsonObject().get(faceName).getAsJsonObject();
		}catch(Exception e)
		{	
			
		}
		setBorder(BorderFactory.createTitledBorder(faceName.substring(0, 1).toUpperCase() + faceName.substring(1)));
		enabled = new JCheckBox("enabled", face != null);
		enabled.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(enabled.isSelected())
				{
					Gson gson = new GsonBuilder().setPrettyPrinting().create();
					face = new JsonObject();
					face.add("uv", gson.toJsonTree(new int[]{0, 0, 16, 16}));
					face.add("texture", gson.toJsonTree("#"));
					texture.setText("#");
					ElementFacePanel.this.element.get("faces").getAsJsonObject().add(ElementFacePanel.this.faceName, face);
				}else
				{
					face = null;
					ElementFacePanel.this.element.get("faces").getAsJsonObject().remove(ElementFacePanel.this.faceName);
				}
				ElementFacePanel.this.elementEditor.updateCode();
				updateSpinners();
			}
		});
		this.add(enabled);
		this.add(new JLabel("UV ", SwingConstants.RIGHT));
		this.add(new JLabel("X", SwingConstants.CENTER));
		this.add(new JLabel("Y", SwingConstants.CENTER));
		texture = new JTextField();
		try
		{
			texture.setText(face.get("texture").getAsString());
		}catch(Exception e)
		{	
			
		}
		texture.getDocument().addUndoableEditListener(new UndoableEditListener()
		{
			@Override
			public void undoableEditHappened(UndoableEditEvent e)
			{
				if(texture.isEnabled())
				{
					Gson gson = new GsonBuilder().setPrettyPrinting().create();
					face.remove("texture");
					face.add("texture", gson.toJsonTree(texture.getText()));
					ElementFacePanel.this.elementEditor.updateCode();
				}
			}
		});
		this.add(texture);
		this.add(new JLabel("From: ", SwingConstants.RIGHT));
		x1 = new ElementUVSpinner(0, this);
		this.add(x1);
		x2 = new ElementUVSpinner(1, this);
		this.add(x2);
		autoUV = new JButton("Automatic UV mapping");
		autoUV.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				JsonArray from = ElementFacePanel.this.element.get("from").getAsJsonArray();
				JsonArray to = ElementFacePanel.this.element.get("to").getAsJsonArray();
				if(ElementFacePanel.this.faceName.equals("up") || ElementFacePanel.this.faceName.equals("down"))
				{
					uvArray.set(0, gson.toJsonTree(from.get(0).getAsInt()));
					uvArray.set(1, gson.toJsonTree(from.get(2).getAsInt()));
					uvArray.set(2, gson.toJsonTree(to.get(0).getAsInt()));
					uvArray.set(3, gson.toJsonTree(to.get(2).getAsInt()));
				}else if(ElementFacePanel.this.faceName.equals("north"))
				{
					uvArray.set(0, gson.toJsonTree(16 - to.get(0).getAsInt()));
					uvArray.set(1, gson.toJsonTree(16 - to.get(1).getAsInt()));
					uvArray.set(2, gson.toJsonTree(16 - from.get(0).getAsInt()));
					uvArray.set(3, gson.toJsonTree(16 - from.get(1).getAsInt()));
				}else if(ElementFacePanel.this.faceName.equals("south"))
				{
					uvArray.set(0, gson.toJsonTree(from.get(0).getAsInt()));
					uvArray.set(1, gson.toJsonTree(16 - to.get(1).getAsInt()));
					uvArray.set(2, gson.toJsonTree(to.get(0).getAsInt()));
					uvArray.set(3, gson.toJsonTree(16 - from.get(1).getAsInt()));
				}else if(ElementFacePanel.this.faceName.equals("west"))
				{
					uvArray.set(0, gson.toJsonTree(from.get(2).getAsInt()));
					uvArray.set(1, gson.toJsonTree(16 - to.get(1).getAsInt()));
					uvArray.set(2, gson.toJsonTree(to.get(2).getAsInt()));
					uvArray.set(3, gson.toJsonTree(16 - from.get(1).getAsInt()));
				}else if(ElementFacePanel.this.faceName.equals("east"))
				{
					uvArray.set(0, gson.toJsonTree(16 - to.get(2).getAsInt()));
					uvArray.set(1, gson.toJsonTree(16 - to.get(1).getAsInt()));
					uvArray.set(2, gson.toJsonTree(16 - from.get(2).getAsInt()));
					uvArray.set(3, gson.toJsonTree(16 - from.get(1).getAsInt()));
				}
				ElementFacePanel.this.elementEditor.updateCode();
				updateSpinners();
			}
		});
		this.add(autoUV);
		this.add(new JLabel("To: ", SwingConstants.RIGHT));
		y1 = new ElementUVSpinner(2, this);
		this.add(y1);
		y2 = new ElementUVSpinner(3, this);
		this.add(y2);
		updateSpinners();
	}
	
	public void updateSpinners()
	{
		uvArray = null;
		try
		{
			uvArray = face.get("uv").getAsJsonArray();
		}catch(Exception e)
		{	
			
		}
		
		x1.array = uvArray;
		x1.setEnabled(x1.array != null);
		if(x1.array != null)
			x1.setValue(x1.array.get(x1.index).getAsInt());
		else
			x1.setValue(0);
		
		x2.array = uvArray;
		x2.setEnabled(x2.array != null);
		if(x2.array != null)
			x2.setValue(x2.array.get(x2.index).getAsInt());
		else
			x2.setValue(0);
		
		y1.array = uvArray;
		y1.setEnabled(y1.array != null);
		if(y1.array != null)
			y1.setValue(y1.array.get(y1.index).getAsInt());
		else
			y1.setValue(0);
		
		y2.array = uvArray;
		y2.setEnabled(y2.array != null);
		if(y2.array != null)
			y2.setValue(y2.array.get(y2.index).getAsInt());
		else
			y2.setValue(0);
		
		texture.setEnabled(uvArray != null);
		autoUV.setEnabled(uvArray != null);
	}
}
