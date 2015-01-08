package tk.wurst_client.fuck_cubik.editor.elementmanager;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

public class FromToSpinner extends JSpinner
{
	private JsonArray array;
	private int index;
	private ElementEditor elementEditor;
	
	public FromToSpinner(JsonArray array, int index, ElementEditor elementEditor)
	{
		super(new SpinnerNumberModel(array.get(index).getAsDouble(), -16D, 32D, 0.1D));
		this.array = array;
		this.index = index;
		this.elementEditor = elementEditor;
		addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				FromToSpinner.this.array.set(FromToSpinner.this.index, new Gson().toJsonTree((double)FromToSpinner.this.getValue()));
				FromToSpinner.this.elementEditor.updateCode();
			}
		});
	}
}
