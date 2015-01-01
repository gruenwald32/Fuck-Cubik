package tk.wurst_client.fuck_cubik.preview.render;

public class RenderObject
{
	public final double[] from;
	public final double[] to;
	public final RenderObjectFace[] faces;
	
	public RenderObject(double[] from, double[] to, RenderObjectFace[] faces)
	{
		this.from = from;
		this.to = to;
		this.faces = faces;
	}
}
