package tk.wurst_client.fuck_cubik.preview.render;

public class RenderObject
{
	public final int[] from;
	public final int[] to;
	public final RenderObjectFace[] faces;
	public final String[] textureLinks;
	
	public RenderObject(int[] from, int[] to, RenderObjectFace[] faces, String[] textureLinks)
	{
		this.from = from;
		this.to = to;
		this.faces = faces;
		this.textureLinks = textureLinks;
	}
}
