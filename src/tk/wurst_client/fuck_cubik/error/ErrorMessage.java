package tk.wurst_client.fuck_cubik.error;

import java.awt.Component;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JOptionPane;

import tk.wurst_client.fuck_cubik.Main;

public class ErrorMessage
{
	public ErrorMessage(String action, Exception e)
	{
		this(Main.frame, action, e);
	}
	
	public ErrorMessage(Component parent, String action, Exception e)
	{
		e.printStackTrace();
		if(!Main.updater.isOutdated())
			if(e.getMessage() != null && !e.getMessage().isEmpty())
				Main.tracker.trackEvent("error", action, e.getMessage());
			else
				Main.tracker.trackEvent("error", action);
		String message = "<html>"
			+ "<body width=\"256px\">"
			+ "<p>Exception while " + action + ":</p>"
			+ "<p>"
			+ e.getClass().getPackage().getName()
			+ ".<u>" + e.getClass().getSimpleName() + "</u>"
			+ ":</p>";
		if(e.getLocalizedMessage() != null && !e.getLocalizedMessage().isEmpty())
			message += "<p>" + e.getLocalizedMessage().replace("\n", "<br>").replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;") + "</p>";
		int clickedButton = JOptionPane.showOptionDialog(parent, message, "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Send error report", "Don't send error report"}, 0);
		if(clickedButton == 0)
		{
			StringWriter stacktraceWriter = new StringWriter();
			e.printStackTrace(new PrintWriter(stacktraceWriter));
			String stacktrace = stacktraceWriter.toString();
			if(Main.updater.isOutdated())
				JOptionPane.showMessageDialog(
					Main.frame,
					"<html><body width=\"256px\">"
						+ "<p>You are using an outdated version of Fuck Cubik.</p>"
						+ "<p>Error reports can only be sent from the newest verion.</p>",
					"Outdated",
					JOptionPane.ERROR_MESSAGE);
			else
				new ErrorReport(action, stacktrace);
		}
	}
}
