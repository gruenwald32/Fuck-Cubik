package tk.wurst_client.fuck_cubik.dialogs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import tk.wurst_client.fuck_cubik.Main;

public class ErrorReport extends JDialog
{
	private JTextField actionField;
	private JTextArea stacktraceField;
	private JScrollPane stackTracePane;
	private JTextArea commentField;
	private JScrollPane commentPane;
	private JButton sendButton;

	public ErrorReport(String action, String stacktrace)
	{
		super(Main.frame, "Error report");
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		add(Box.createVerticalGlue());
		JLabel label = new JLabel("<html>"
			+ "<center>"
			+ "<h1>Error report</h1>"
			+ "<p>The following information will be sent:</p>", JLabel.CENTER);
		label.setAlignmentX(CENTER_ALIGNMENT);
		add(label);
		add(Box.createVerticalGlue());
		label = new JLabel("<html>"
			+ "<h3>Last action</h3>"
			+ "<p>The last thing you did before the error occured.</p>"
			+ "<p>Fuck Cubik detects this automatically.</p>");
		label.setAlignmentX(CENTER_ALIGNMENT);
		add(label);
		actionField = new JTextField(action);
		actionField.setEditable(false);
		actionField.setBackground(new Color(240, 240, 240));
		actionField.setMaximumSize(new Dimension(actionField.getMaximumSize().width, actionField.getPreferredSize().height));
		actionField.setAlignmentX(CENTER_ALIGNMENT);
		add(actionField);
		add(Box.createVerticalGlue());
		label = new JLabel("<html>"
			+ "<h3>Java stacktrace</h3>"
			+ "<p>Developers can use this code to find and fix the error.</p>"
			+ "<p>Fuck Cubik detects this automatically.</p>");
		label.setAlignmentX(CENTER_ALIGNMENT);
		add(label);//TODO: Add missing info
		stacktraceField = new JTextArea(stacktrace);
		stacktraceField.setEditable(false);
		stacktraceField.setBackground(new Color(240, 240, 240));
		stacktraceField.setAlignmentX(CENTER_ALIGNMENT);
		stackTracePane = new JScrollPane(stacktraceField);
		stackTracePane.setPreferredSize(new Dimension(stackTracePane.getPreferredSize().width, 100));
		stackTracePane.setMaximumSize(new Dimension(stackTracePane.getMaximumSize().width, 200));
		stackTracePane.setAlignmentX(CENTER_ALIGNMENT);
		add(stackTracePane);
		add(Box.createVerticalGlue());
		label = new JLabel("<html>"
			+ "<h3>Comment (optional)</h3>"
			+ "<p>Keep in mind that this information will be public.</p>");
		label.setAlignmentX(CENTER_ALIGNMENT);
		add(label);
		commentField = new JTextArea();
		commentField.setAlignmentX(CENTER_ALIGNMENT);
		commentPane = new JScrollPane(commentField);
		commentPane.setPreferredSize(new Dimension(commentPane.getPreferredSize().width, 100));
		commentPane.setMaximumSize(new Dimension(commentPane.getMaximumSize().width, 200));
		commentPane.setAlignmentX(CENTER_ALIGNMENT);
		add(commentPane);
		add(Box.createVerticalGlue());
		sendButton = new JButton("Send");
		sendButton.setFont(new Font(sendButton.getFont().getFamily(), Font.BOLD, 24));
		add(sendButton);
		add(Box.createVerticalGlue());
		pack();
		setLocationRelativeTo(Main.frame);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setVisible(true);
		System.out.println(getSize());
	}
}
