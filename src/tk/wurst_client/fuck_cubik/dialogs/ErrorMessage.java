package tk.wurst_client.fuck_cubik.dialogs;

import javax.swing.JOptionPane;

import tk.wurst_client.fuck_cubik.Main;

public class ErrorMessage
{
	public ErrorMessage(Exception e)
	{
		e.printStackTrace();
		String message = "<html>"
			+ "<body width=\"256px\">"
			+ "<p align=\"center\">"
			+ e.getClass().getPackage().getName()
			+ ".<u>" + e.getClass().getSimpleName()+ "</u>"
			+ "</p>";
		if(e.getLocalizedMessage()!= null && !e.getLocalizedMessage().isEmpty())
			message += "<p>" + e.getLocalizedMessage().replace("\n", "<br>").replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;") + "</p>";
		JOptionPane.showMessageDialog(Main.frame, message , "Error", JOptionPane.ERROR_MESSAGE);
	}

	public ErrorMessage(String message)
	{
		JOptionPane.showMessageDialog(Main.frame, message , "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public ErrorMessage(String comment, Exception e)
	{
		e.printStackTrace();
		String message = "<html>"
			+ "<body width=\"256px\">"
			+ "<p>" + comment + "</p>"
			+ "<p align=\"center\">"
			+ e.getClass().getPackage().getName()
			+ ".<u>" + e.getClass().getSimpleName()+ "</u>"
			+ "</p>";
		if(e.getLocalizedMessage()!= null && !e.getLocalizedMessage().isEmpty())
			message += "<p>" + e.getLocalizedMessage().replace("\n", "<br>").replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;") + "</p>";
		JOptionPane.showMessageDialog(Main.frame, message , "Error", JOptionPane.ERROR_MESSAGE);
	}
}
