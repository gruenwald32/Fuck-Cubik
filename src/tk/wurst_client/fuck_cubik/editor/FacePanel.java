package tk.wurst_client.fuck_cubik.editor;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import tk.wurst_client.fuck_cubik.dialogs.ElementEditor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class FacePanel extends JPanel
{
	public JsonObject element;
	public String faceName;
	public ElementEditor elementEditor;
	
	public JsonObject face;
	public JCheckBox enabled;
	public JsonArray uvArray;
	public UVSpinner x1;
	public UVSpinner x2;
	public UVSpinner y1;
	public UVSpinner y2;
	public JButton autoUV;
	
	public FacePanel(JsonObject element, String faceName, ElementEditor elementEditor)
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
		setBorder(BorderFactory.createTitledBorder(faceName));
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
					FacePanel.this.element.get("faces").getAsJsonObject().add(FacePanel.this.faceName, face);
				}else
				{
					face = null;
					FacePanel.this.element.get("faces").getAsJsonObject().remove(FacePanel.this.faceName);
				}
				FacePanel.this.elementEditor.updateCode();
				updateSpinners();
			}
		});
		this.add(enabled);
		this.add(new JLabel("UV"));
		this.add(new JLabel("X", SwingConstants.CENTER));
		this.add(new JLabel("Y", SwingConstants.CENTER));
		this.add(new JLabel("Texture"));
		this.add(new JLabel("1"));
		x1 = new UVSpinner(0, this);
		this.add(x1);
		x2 = new UVSpinner(1, this);
		this.add(x2);
		autoUV = new JButton("AutoUV");
		this.add(autoUV);
		this.add(new JLabel("2"));
		y1 = new UVSpinner(2, this);
		this.add(y1);
		y2 = new UVSpinner(3, this);
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
		
		autoUV.setEnabled(uvArray != null);
	}
}
