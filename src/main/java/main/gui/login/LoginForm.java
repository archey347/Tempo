package main.gui.login;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.LayoutManager;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import main.gui.*;

public class LoginForm extends AbstractStartForm {

    private LoginFormController controller;

    private JPanel alertPane;

    public LoginForm(LoginFormController controller) {
        controller.bindAlertable(this);

        this.controller = controller;

        getContentPane().add(this.genMain());
    }

    public void showAlert(JAlert alert) {
        alertPane.removeAll();
        if(alert != null) {
            alertPane.add(alert);
            alertPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, alert.getPreferredSize().height));
        }
        alertPane.revalidate();
    }

    public void setVisible(boolean visible) {
        this.controller.onFormVisibleChange(visible);

        super.setVisible(visible);
    }

    public JPanel genBody() {
        JPanel panel = new JPanel();

        panel.setAutoscrolls(true);

        LayoutManager layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);
        Stylesheet.formatMainBackground(panel);


        panel.add(Box.createVerticalStrut(50));


        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(usernameLabel);

        panel.add(Box.createVerticalStrut(5));

        JTextField usernameField = new JTextField();
        usernameField.setAlignmentX(Component.LEFT_ALIGNMENT);  
        Stylesheet.formatInput(usernameField);
        //usernameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, usernameField.getPreferredSize().height));
        panel.add(usernameField);
        controller.bindUsernameField(usernameField);


        panel.add(Box.createVerticalStrut(20));


        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(passwordLabel);

        panel.add(Box.createVerticalStrut(5));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setAlignmentX(Component.LEFT_ALIGNMENT);
        Stylesheet.formatInput(passwordField);
        //passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, passwordField.getPreferredSize().height));
        panel.add(passwordField);
        controller.bindPasswordField(passwordField);

        
        panel.add(Box.createVerticalStrut(20));

        alertPane = new JPanel();
        alertPane.setLayout(new GridLayout());
        alertPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        alertPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, 0));
        panel.add(alertPane);

        panel.add(Box.createVerticalStrut(20));

        JPanel buttons = new JPanel();
        Stylesheet.formatMainBackground(buttons);
        buttons.setMaximumSize(new Dimension(Integer.MAX_VALUE, 170));
        buttons.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttons.setLayout(new GridLayout(4, 1, 10, 10));

        JButton loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        Stylesheet.formatButton(loginButton, "primary");
        buttons.add(loginButton);
        this.controller.bindLoginButton(loginButton);

        JButton forgotButton = new JButton("Forgotten Password");
        loginButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        Stylesheet.formatButton(forgotButton, "secondary");
        buttons.add(forgotButton);
        //this.controller.bindLoginButton(loginButton);

        JLabel label = new JLabel("Don't have an account?");
        buttons.add(label);

        JButton registerButton = new JButton("Register");
        loginButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        Stylesheet.formatButton(registerButton, "secondary");
        buttons.add(registerButton);
        this.controller.bindRegisterButton(registerButton);
        

        panel.add(buttons);
        
        return panel;
    }
}
