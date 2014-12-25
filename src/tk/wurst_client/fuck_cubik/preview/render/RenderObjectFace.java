package tk.wurst_client.fuck_cubik.preview.render;


public class RenderObjectFace
{
	public final Side side;
	public final int[] uv;
	public final String textureLink;
	
	public RenderObjectFace(Side side, int[] uv, String textureLink)
	{
		this.side = side;
		this.uv = uv;
		this.textureLink = textureLink;
	}
}
