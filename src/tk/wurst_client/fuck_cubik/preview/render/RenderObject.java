package tk.wurst_client.fuck_cubik.preview.render;

public class RenderObject
{
	public final int[] from;
	public final int[] to;
	public final RenderObjectFace[] faces;
	
	public RenderObject(int[] from, int[] to, RenderObjectFace[] faces)
	{
		this.from = from;
		this.to = to;
		this.faces = faces;
	}
}
