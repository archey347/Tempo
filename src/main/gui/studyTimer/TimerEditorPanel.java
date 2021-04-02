package main.gui.studyTimer;

import main.gui.Stylesheet;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

/**
 * A panel for editing a timer.
 */
public class TimerEditorPanel extends JPanel
{
	private final int defaultWorkTime;
	private final int defaultRestTime;
	private final Consumer<JTextField> bindWorkTextField;
	private final Consumer<JTextField> bindRestTextField;
	private final Color backgroundColour;
	
	public TimerEditorPanel(int defaultWorkTime, int defaultRestTime, Color backgroundColour)
	{
		this(defaultWorkTime, defaultRestTime, null, null, backgroundColour);
	}
	
	public TimerEditorPanel(int defaultWorkTime, int defaultRestTime, Consumer<JTextField> bindWorkTextField, Consumer<JTextField> bindRestTextField, Color backgroundColour)
	{
		this.defaultWorkTime = defaultWorkTime;
		this.defaultRestTime = defaultRestTime;
		this.bindWorkTextField = bindWorkTextField;
		this.bindRestTextField = bindRestTextField;
		this.backgroundColour = backgroundColour;
		
		setupPanel();
	}
	
	private void setupPanel()
	{
		LayoutManager bodyLayout = new BoxLayout(this, BoxLayout.X_AXIS);
		this.setLayout(bodyLayout);
		this.setBackground(backgroundColour);

		JPanel workPanel = genTimerSetupField("Work", Integer.toString(defaultWorkTime), bindWorkTextField);
		this.add(workPanel);

		JPanel restPanel = genTimerSetupField("Rest", Integer.toString(defaultRestTime), bindRestTextField);
		this.add(restPanel);
	}

	private JPanel genTimerSetupField(String labelText, String defaultValueString, Consumer<JTextField> controllerBind)
	{
		JPanel panel = new JPanel();
		LayoutManager panelLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(panelLayout);
		panel.setBackground(backgroundColour);

		JLabel topLabel = new JLabel(labelText);
		topLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		topLabel.setFont(new Font("Arial", Font.BOLD, 15));
		panel.add(topLabel);

		JPanel inputPanel = new JPanel();
		LayoutManager inputPanelLayout = new BoxLayout(inputPanel, BoxLayout.X_AXIS);
		inputPanel.setLayout(inputPanelLayout);
		inputPanel.setBackground(backgroundColour);

		JTextField textField = new JTextField();
		textField.setAlignmentX(Component.LEFT_ALIGNMENT);
		Stylesheet.formatInput(textField);
		textField.setHorizontalAlignment(JTextField.CENTER);
		textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, textField.getPreferredSize().height));
		textField.setText(defaultValueString);
		inputPanel.add(textField);

		if (controllerBind != null)
		{
			controllerBind.accept(textField);
		}

		JLabel minsLabel = new JLabel("mins");
		minsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		minsLabel.setFont(new Font("Arial", Font.BOLD, 15));
		inputPanel.add(minsLabel);

		panel.add(inputPanel);

		return panel;
	}
}
