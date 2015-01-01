package tk.wurst_client.fuck_cubik.editor;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

public class UVSpinner extends JSpinner
{
	public JsonArray array;
	public int index;
	public FacePanel facePanel;
	
	public UVSpinner(int index, FacePanel facePanel)
	{
		super(new SpinnerNumberModel(0, 0, 16, 1));
		this.index = index;
		this.facePanel = facePanel;
		addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				if(UVSpinner.this.array != null)
				{
					UVSpinner.this.array.set(UVSpinner.this.index, new Gson().toJsonTree((int)UVSpinner.this.getValue()));
					UVSpinner.this.facePanel.elementEditor.updateCode();
				}
			}
		});
	}
}
