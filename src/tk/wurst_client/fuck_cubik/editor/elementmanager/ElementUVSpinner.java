package tk.wurst_client.fuck_cubik.editor.elementmanager;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

public class ElementUVSpinner extends JSpinner
{
	public JsonArray array;
	public int index;
	public ElementFacePanel facePanel;
	
	public ElementUVSpinner(int index, ElementFacePanel facePanel)
	{
		super(new SpinnerNumberModel(0, 0, 16, 1));
		this.index = index;
		this.facePanel = facePanel;
		addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				if(array != null)
				{
					array.set(ElementUVSpinner.this.index, new Gson().toJsonTree((int)ElementUVSpinner.this.getValue()));
					ElementUVSpinner.this.facePanel.elementEditor.updateCode();
				}
			}
		});
	}
}
