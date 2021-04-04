package main.gui.studyTimer;

import main.gui.AbstractMainFormController;
import main.gui.Screen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerCreationFormController extends AbstractMainFormController implements ActionListener
{
	private JTextField workTextField;
	private JTextField restTextField;
	private JButton playButton;

	public void bindWorkTextField(JTextField textField)
	{
		if (textField != null)
		{
			this.workTextField = textField;
		}
	}
	
	public void bindRestTextField(JTextField textField)
	{
		if (textField != null)
		{
			this.restTextField = textField;
		}
	}
	
	public void bindPlayButton(JButton button)
	{
		if (button != null)
		{
			button.addActionListener(this);
			this.playButton = button;
		}
	}

	public void focusPlayButton()
	{
		playButton.requestFocusInWindow();
	}
	
	private void startTimer(PITimer timer)
	{
		TimerRunningForm timerForm = (TimerRunningForm)Screen.getForm("runTimer");
		timerForm.setTimer(timer);
		Screen.showForm(timerForm);
	}
	
	/**
	 * Invoked when an action occurs.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		
		// Starting timer
		if (source == this.playButton)
		{
			String workText = workTextField.getText();
			String restText = restTextField.getText();
			
			// Checking if both fields are positive integers
			if (workText.matches("^\\d+$") && restText.matches("^\\d+$"))
			{
				int workLength = Integer.parseInt(workText);
				int restLength = Integer.parseInt(restText);
				
				PITimer timer = new PITimer(workLength, restLength);
				startTimer(timer);
			}
		}
	}
}
