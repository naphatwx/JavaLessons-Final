import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Register extends JFrame {
    private JPanel mainPanel;
    private JTextField firstNameTF;
    private JTextField lastNameTF;
    private JTextField usernameTF;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JButton goToLoginPageButton;
    private JLabel alertLabel;

    public Register() {
        setContentPane(mainPanel);
        setTitle("Register");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRegister();
            }
        });

        goToLoginPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleGoToLoginPage();
            }
        });

        setVisible(true);
    }

    private void handleRegister() {
        String firstName = firstNameTF.getText();
        String lastName = lastNameTF.getText();
        String username = usernameTF.getText();
        String password = String.valueOf(passwordField.getPassword());

        if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || password.isEmpty()) {
            System.out.println("Register failed!");
            alertLabel.setText("Please fill all fields!");
            pack();
            return;
        }

        Person person = new Person(firstName, lastName, username, password);
        Main.personList.add(person);
        System.out.println("Register successful!");
        handleGoToLoginPage();
    }

    private void handleGoToLoginPage() {
        new Login();
        dispose(); // Close this window
    }
}
