package tk.wurst_client.fuck_cubik.preview.render;

import org.lwjgl.opengl.GL11;

public class GUIRenderer
{
	public void renderGrid()
	{
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		for(float i = 0F; i <= 1F; i += 1F / 16F)
		{
			GL11.glBegin(GL11.GL_LINE_LOOP);
			{
				GL11.glColor3f(1F, 1F, 1F);
				GL11.glVertex3f(0.5F, i, -0.5F);
				GL11.glVertex3f(-0.5F, i, -0.5F);
				GL11.glVertex3f(-0.5F, i, 0.5F);
				GL11.glVertex3f(0.5F, i, 0.5F);
			}
			GL11.glEnd();
		}
		for(float i = -0.5F; i <= 0.5F; i += 1F / 16F)
		{
			GL11.glBegin(GL11.GL_LINE_LOOP);
			{
				GL11.glColor3f(1F, 1F, 1F);
				GL11.glVertex3f(i, 0F, -0.5F);
				GL11.glVertex3f(i, 1F, -0.5F);
				GL11.glVertex3f(i, 1F, 0.5F);
				GL11.glVertex3f(i, 0F, 0.5F);
			}
			GL11.glEnd();
			GL11.glBegin(GL11.GL_LINE_LOOP);
			{
				GL11.glColor3f(1F, 1F, 1F);
				GL11.glVertex3f(0.5F, 0F, i);
				GL11.glVertex3f(-0.5F, 0F, i);
				GL11.glVertex3f(-0.5F, 1F, i);
				GL11.glVertex3f(0.5F, 1F, i);
			}
			GL11.glEnd();
		}
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}
	
	public void renderFocus()
	{
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glBegin(GL11.GL_LINES);
		{
			float size = 1 / 16F;
			GL11.glColor3f(1F, 1F, 0F);
			GL11.glVertex3f(size, -size, -size);
			GL11.glVertex3f(-size, -size, -size);
			GL11.glVertex3f(-size, -size, -size);
			GL11.glVertex3f(-size, -size, size);
			GL11.glVertex3f(-size, -size, size);
			GL11.glVertex3f(size, -size, size);
			GL11.glVertex3f(size, -size, size);
			GL11.glVertex3f(size, -size, -size);
			GL11.glVertex3f(size, -size, -size);
			GL11.glVertex3f(size, size, -size);
			GL11.glVertex3f(-size, -size, -size);
			GL11.glVertex3f(-size, size, -size);
			GL11.glVertex3f(-size, -size, size);
			GL11.glVertex3f(-size, size, size);
			GL11.glVertex3f(size, -size, size);
			GL11.glVertex3f(size, size, size);
			GL11.glVertex3f(size, size, -size);
			GL11.glVertex3f(-size, size, -size);
			GL11.glVertex3f(-size, size, -size);
			GL11.glVertex3f(-size, size, size);
			GL11.glVertex3f(-size, size, size);
			GL11.glVertex3f(size, size, size);
			GL11.glVertex3f(size, size, size);
			GL11.glVertex3f(size, size, -size);
		}
		GL11.glEnd();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}
}
