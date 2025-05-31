import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Login extends JFrame {
    private JPanel mainPanel;
    private JTextField firstNameTF;
    private JButton loginButton;
    private JButton goToRegisterPageButton;
    private JPasswordField passwordField;
    private JLabel alertLabel;

    public Login() {
        setContentPane(mainPanel);
        setTitle("Login");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        goToRegisterPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleGoToRegisterPage();
            }
        });

        setVisible(true);
    }

    private void handleLogin() {
        List<Person> personList = Main.personList;
        String username = firstNameTF.getText();
        String password = String.valueOf(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Login failed!");
            alertLabel.setText("Please fill all fields!");
            firstNameTF.setText("");
            passwordField.setText("");
            pack();
            return;
        }

        Person personToLogin = personList.stream()
                .filter(person -> person.getUsername().equals(username)
                && person.getPassword().equals(password))
                .findFirst()
                .orElse(null);

        if (personToLogin != null) {
            System.out.println("Login successful!");
            goToHome();
        } else {
            System.out.println("Login failed!");
            alertLabel.setText("Invalid username or password!");
            firstNameTF.setText("");
            passwordField.setText("");
            pack();
        }
    }

    private void handleGoToRegisterPage() {
        new Register();
        dispose();
    }

    private void goToHome() {
        new Home();
        dispose();
    }
}
