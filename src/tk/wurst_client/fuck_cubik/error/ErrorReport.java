package tk.wurst_client.fuck_cubik.error;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.text.BadLocationException;

import tk.wurst_client.fuck_cubik.Main;
import tk.wurst_client.fuck_cubik.update.VersionManager;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ErrorReport extends JDialog
{
	private JTextArea commentField;
	private JScrollPane commentPane;
	private JButton sendButton;
	
	private String action;
	private String stacktrace;
	private String version;
	private String os;
	private String java;
	private String screen;
	private String comment;
	
	public ErrorReport(String action, String stacktrace)
	{
		super(Main.frame, "Error report");
		this.action = action;
		this.stacktrace = stacktrace;
		version = VersionManager.FORMATTED_VERSION;
		os = System.getProperty("os.name");
		java = System.getProperty("java.version");
		screen = Toolkit.getDefaultToolkit().getScreenSize().width + "x" + Toolkit.getDefaultToolkit().getScreenSize().height;
		
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		JLabel label = new JLabel("<html>"
			+ "<center>"
			+ "<h1>Error report</h1>", SwingConstants.CENTER);
		label.setAlignmentX(CENTER_ALIGNMENT);
		add(label);
		label = new JLabel("<html>"
			+ "<p>The following information will be sent:</p>"
			+ "<ul>"
			+ "<li>Last action before the error occured: " + this.action
			+ "<li>Java stacktrace"
			+ "<li>Fuck Cubik version: " + version
			+ "<li>Operating system: " + os
			+ "<li>Java version: " + java
			+ "<li>Screen resolution: " + screen
			+ "</ul>"
			+ "<p>Add a comment (optional):</p>");
		label.setAlignmentX(CENTER_ALIGNMENT);
		add(label);
		commentField = new JTextArea();
		commentField.setAlignmentX(CENTER_ALIGNMENT);
		commentField.setFont(new Font("Arial", Font.PLAIN, 12));
		commentPane = new JScrollPane(commentField);
		commentPane.setAlignmentX(CENTER_ALIGNMENT);
		commentPane.setPreferredSize(new Dimension(commentPane.getPreferredSize().width, 75));
		commentPane.setMaximumSize(new Dimension(commentPane.getMaximumSize().width, 150));
		add(commentPane);
		sendButton = new JButton("Send");
		sendButton.setAlignmentX(CENTER_ALIGNMENT);
		sendButton.setFont(new Font(sendButton.getFont().getFamily(), Font.BOLD, 18));
		sendButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Main.tracker.trackEvent("error report", "send");
				try
				{
					comment = commentField.getDocument().getText(0, commentField.getDocument().getLength());
				}catch(BadLocationException e2)
				{
					comment = "";
				}
				ErrorReport.this.dispose();
				try
				{
					HttpURLConnection get = (HttpURLConnection)new URL("http://fuck-cubik.wurst-client.tk/api/v1/error-report/").openConnection();
					get.setRequestMethod("GET");
					get.connect();
					JsonObject json = new JsonParser().parse(new InputStreamReader(get.getInputStream())).getAsJsonObject();
					String formURL = "https://docs.google.com/forms/d/"
						+ json.get("form").getAsString()
						+ "/formResponse"
						+ "?entry." + json.get("params").getAsJsonObject().get("action").getAsString()
						+ "=" + URLEncoder.encode(ErrorReport.this.action, "UTF-8")
						+ "&entry." + json.get("params").getAsJsonObject().get("stacktrace").getAsString()
						+ "=" + URLEncoder.encode(ErrorReport.this.stacktrace, "UTF-8")
						+ "&entry." + json.get("params").getAsJsonObject().get("version").getAsString()
						+ "=" + URLEncoder.encode(version, "UTF-8")
						+ "&entry." + json.get("params").getAsJsonObject().get("os").getAsString()
						+ "=" + URLEncoder.encode(os, "UTF-8")
						+ "&entry." + json.get("params").getAsJsonObject().get("java").getAsString()
						+ "=" + URLEncoder.encode(java, "UTF-8")
						+ "&entry." + json.get("params").getAsJsonObject().get("screen").getAsString()
						+ "=" + URLEncoder.encode(screen, "UTF-8")
						+ "&entry." + json.get("params").getAsJsonObject().get("comment").getAsString()
						+ "=" + URLEncoder.encode(comment, "UTF-8");
					HttpsURLConnection post = (HttpsURLConnection)new URL(formURL).openConnection();
					post.setInstanceFollowRedirects(true);
					post.connect();
					BufferedReader input = new BufferedReader(new InputStreamReader(post.getInputStream()));
					String response = input.readLine();
					for(String line; (line = input.readLine()) != null;)
						response += "\n" + line;
					boolean success = response.contains(json.get("success_message").getAsString());
					Main.tracker.trackPageView("/error/report/" + (success ? "success" : "failure"), (success ? "Success" : "Failure"));
					int action = JOptionPane.showOptionDialog(
						Main.frame,
						"<html>"
							+ "<body width=\"256px\">"
							+ "<p>Your error report "
							+ (success ? "has been sent" : "was rejected")
							+ ".</p>"
							+ "<p>"
							+ (success ? "Thank you!" : "Maybe the server doesn't accept error reports at the moment.")
							+ "</p>",
						success ? "Success" : "Failure",
						JOptionPane.DEFAULT_OPTION,
						success ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE,
						null,
						success ? new String[]{"OK", "View error reports"} : new String[]{"OK"},
						0);
					if(action == 1)
						Desktop.getDesktop().browse(new URI("http://fuck-cubik.wurst-client.tk/error-reports/"));
					Main.tracker.trackPageView("/", "Main frame");
				}catch(Exception e1)
				{
					e1.printStackTrace();
					JOptionPane.showMessageDialog(
						Main.frame,
						"<html>"
							+ "<body width=\"256px\">"
							+ "<p>Error report could not be sent.</p>"
							+ "<p>Please check your internet connection.</p>",
						"Failure",
						JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		add(sendButton);
		addWindowListener(new WindowListener()
		{
			@Override
			public void windowOpened(WindowEvent e)
			{	
				
			}
			
			@Override
			public void windowIconified(WindowEvent e)
			{	
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e)
			{	
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e)
			{	
				
			}
			
			@Override
			public void windowClosing(WindowEvent e)
			{
				Main.tracker.trackPageView("/", "Main frame");
			}
			
			@Override
			public void windowClosed(WindowEvent e)
			{	
				
			}
			
			@Override
			public void windowActivated(WindowEvent e)
			{	
				
			}
		});
		pack();
		setLocationRelativeTo(Main.frame);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		Main.tracker.trackPageView("/error/report", "Error report");
		setVisible(true);
	}
}
