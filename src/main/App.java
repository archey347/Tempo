package main;

import java.awt.Dimension;

import main.gui.Screen;
import main.gui.studyTimer.StudySessionForm;
import main.gui.studyTimer.StudySessionFormController;
import main.login.*;

public class App {
    public static void main(String[] args) throws Exception {
    
        Screen.setDefaultSize(new Dimension(400, 700));

        Screen.registerForm("login", new LoginForm(new LoginFormController()));
        Screen.registerForm("register", new RegisterForm(new RegisterFormController()));
        Screen.registerForm("study", new StudySessionForm(new StudySessionFormController()));
        
        Screen.showForm("study");

    }
}
