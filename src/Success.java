import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Success extends JFrame {
    private JPanel mainPanel;
    private JButton backToHomeButton;

    public Success() {
        setContentPane(mainPanel);
        setTitle("Success");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        backToHomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleBackToHome();
            }
        });

        setVisible(true);
    }

    private void handleBackToHome() {
        Main.cart.clearCart();
        new Home();
        dispose();
    }
}
