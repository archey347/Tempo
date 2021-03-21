package main.login;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComponent;

import java.awt.LayoutManager;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.font.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import main.gui.*;

public abstract class AbstractStartForm extends Form {

    public JComponent genMain() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        Stylesheet.formatMainBackground(panel);

        panel.add(this.genTitle(), BorderLayout.NORTH);

        // Login form
        panel.add(this.genBody(), BorderLayout.CENTER);

        // Left/right margins
        panel.add(Box.createHorizontalStrut(10), BorderLayout.EAST);
        panel.add(Box.createHorizontalStrut(10), BorderLayout.WEST);

        // Footer
        panel.add(genFooter(), BorderLayout.SOUTH);

        JScrollPane scroll = new JScrollPane(panel);



        return scroll;
    }

    public JComponent genTitle() {
        JPanel panel = new JPanel();
        LayoutManager layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);

        panel.setBackground(Color.LIGHT_GRAY);

        panel.add(Box.createVerticalStrut(80));

        JLabel title = new JLabel("App Name");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        panel.add(title);

        panel.add(Box.createVerticalStrut(20));
        return panel;
    }

    public JComponent genFooter() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout());
        Stylesheet.formatFooterBackgorund(panel);

        panel.add(Box.createVerticalStrut(50));

        return panel;
    }

    public abstract JComponent genBody();
}
