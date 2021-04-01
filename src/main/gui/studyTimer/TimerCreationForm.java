package main.gui.studyTimer;

import main.gui.Stylesheet;
import main.login.AbstractStartForm;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.function.Consumer;

public class TimerCreationForm extends AbstractStartForm
{
	private final TimerCreationFormController controller;
	private final int defaultWorkTime;
	private final int defaultRestTime;
	
	private final JList<PITimer> timers;
	
	public TimerCreationForm(TimerCreationFormController controller, int defaultWorkTime, int defaultRestTime)
	{
		this.controller = controller;
		this.defaultWorkTime = defaultWorkTime;
		this.defaultRestTime = defaultRestTime;
		
		DefaultListModel<PITimer> listModel = new DefaultListModel<>();
		ListCellRenderer<PITimer> listCellRenderer = new TimerRenderer();
		
		this.controller.bindTimersListModel(listModel);
		timers = new JList<>(listModel);
		timers.setCellRenderer(listCellRenderer);
		
		getContentPane().add(this.genMain());
	}
	
	@Override
	public void setVisible(boolean b)
	{
		super.setVisible(b);
		
		controller.focusPlayButton();
	}
	
	@Override
	public JComponent genBody()
	{
		JPanel bodyPanel = new JPanel();
		bodyPanel.setAutoscrolls(true);
		
		LayoutManager rowsLayout = new BoxLayout(bodyPanel, BoxLayout.Y_AXIS);
		bodyPanel.setLayout(rowsLayout);
		bodyPanel.setBackground(Color.white);
		
		JScrollPane listScroll = new JScrollPane(this.timers);
		
		bodyPanel.add(Box.createVerticalStrut(20));
		bodyPanel.add(listScroll);
		bodyPanel.add(Box.createVerticalStrut(40));
		bodyPanel.add(this.genButtons());
		
		return bodyPanel;
	}
	
	public JPanel genTimerSetup()
	{
		JPanel setupPanel = new JPanel();
		
		LayoutManager bodyLayout = new BoxLayout(setupPanel, BoxLayout.X_AXIS);
		setupPanel.setLayout(bodyLayout);
		setupPanel.setBackground(Color.white);
		
		JPanel workPanel = genTimerSetupField("Work", Integer.toString(defaultWorkTime), controller::bindWorkTextField);
		setupPanel.add(workPanel);
		
		JPanel restPanel = genTimerSetupField("Rest", Integer.toString(defaultRestTime), controller::bindRestTextField);
		setupPanel.add(restPanel);
		
		return setupPanel;
	}
	
	private JPanel genTimerSetupField(String labelText, String defaultValueString, Consumer<JTextField> controllerBind)
	{
		JPanel panel = new JPanel();
		LayoutManager panelLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(panelLayout);
		panel.setBackground(Color.white);
		
		JLabel topLabel = new JLabel(labelText);
		topLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		topLabel.setFont(new Font("Arial", Font.BOLD, 15));
		panel.add(topLabel);
		
		JPanel inputPanel = new JPanel();
		LayoutManager inputPanelLayout = new BoxLayout(inputPanel, BoxLayout.X_AXIS);
		inputPanel.setLayout(inputPanelLayout);
		inputPanel.setBackground(Color.white);
		
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
	
	public JPanel genButtons()
	{
		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		
		LayoutManager layout = new GridLayout();
		panel.setLayout(layout);

		JButton addTimerButton = new JButton("Create Timer");
		addTimerButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		Stylesheet.formatButton(addTimerButton, "primary");
		panel.add(addTimerButton);
		this.controller.bindAddTimerButton(addTimerButton);
		
		JButton playButton = new JButton("Start");
		playButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		Stylesheet.formatButton(playButton, "primary");
		panel.add(playButton);
		this.controller.bindPlayButton(playButton);
		
		return panel;
	}
}
