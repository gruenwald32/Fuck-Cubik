package tk.wurst_client.fuck_cubik.preview;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import tk.wurst_client.fuck_cubik.Main;

public class InputListener
{
	public void listen()
	{
		int deltaX = Mouse.getDX();
		int deltaY = Mouse.getDY();
		int deltaWheel = Mouse.getDWheel();
		
		if(deltaWheel > 0 && Main.renderer.zoom < -1F / 8F)
		{
			Main.renderer.zoom += 1F / 8F;
		}
		else if(deltaWheel < 0 && Main.renderer.zoom > -16)
		{
			Main.renderer.zoom -= 1F / 8F;
		}
		if(Mouse.isButtonDown(0))
		{
			if(deltaX != 0)
				Main.renderer.rotY += (float)deltaX / 5F;
			if(deltaY != 0 && ((Main.renderer.rotX < 90F && deltaY < 0F) || (Main.renderer.rotX > -90F && deltaY > 0F)))
				Main.renderer.rotX -= (float)deltaY / 5F;
		}else if(Mouse.isButtonDown(1))
		{
			if(deltaX != 0)
			{
				Main.renderer.posX -= (float)deltaX / 256F * (float)Math.sin(Math.toRadians(Main.renderer.rotY - 90));
				Main.renderer.posZ += (float)deltaX / 256F * (float)Math.cos(Math.toRadians(Main.renderer.rotY - 90));
			}
			if(deltaY != 0)
			{
				Main.renderer.posX -= (float)deltaY / 256F * (float)Math.sin(Math.toRadians(Main.renderer.rotY)) * (float)Math.cos(Math.toRadians(Main.renderer.rotX + 90));
				Main.renderer.posY += (float)deltaY / 256F * (float)Math.sin(Math.toRadians(Main.renderer.rotX + 90));
				Main.renderer.posZ += (float)deltaY / 256F * (float)Math.cos(Math.toRadians(Main.renderer.rotY)) * (float)Math.cos(Math.toRadians(Main.renderer.rotX + 90));
			}
		}
		while(Keyboard.next())
		{
			int eventKey = Keyboard.getEventKey();
			boolean eventKeyState = Keyboard.getEventKeyState();
			if(eventKeyState)
			{
				if(eventKey == Keyboard.KEY_F5)
				{
					Main.frame.desktop.preview.toolbar.refreshButton.doClick();
				}
			}
		}
	}
}
