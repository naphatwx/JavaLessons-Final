import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Summary extends JFrame {
    private JPanel mainPanel;
    private JList<Product> productList;
    private JButton checkOutButton;
    private JButton backToHomeButton;
    private JLabel totalPanel;
    private JScrollPane productListScroll;
    private JLabel alertLabel;

    private final DefaultListModel<Product> productListModel = new DefaultListModel<>();

    public Summary() {
        setContentPane(mainPanel);
        setTitle("Cart Summary");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Dimension scrollSize = new Dimension(400, 300);
        productListScroll.setPreferredSize(scrollSize);
        productListScroll.setMinimumSize(scrollSize);
        productListScroll.setMaximumSize(scrollSize);
        productListScroll.revalidate();
        productListScroll.repaint();

        pack();
        setLocationRelativeTo(null);

        refreshProductList();
        refreshProductListUI();

        checkOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCheckOut();
            }
        });

        backToHomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleBackToHome();
            }
        });

        setVisible(true);
    }

    private void refreshProductListUI() {
        // Create the products panel
        JPanel productsPanel = new JPanel();
        productsPanel.setLayout(new BoxLayout(productsPanel, BoxLayout.Y_AXIS));

        for (int i = 0; i < productListModel.size(); i++) {
            Product product = productListModel.getElementAt(i);

            JPanel productPanel = new JPanel(new BorderLayout());
            productPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            JLabel productLabel = new JLabel(product.getName() + " - $" + product.getPrice());
            JButton removeButton = new JButton("Remove");

            productPanel.add(productLabel, BorderLayout.WEST);
            productPanel.add(removeButton, BorderLayout.EAST);

            removeButton.addActionListener(e -> handleProductButtonClick(product));

            productsPanel.add(productPanel);
        }
        productListScroll.setViewportView(productsPanel);
    }

    private void refreshProductList() {
        productListModel.clear();
        for (Product product : Main.cart.getProducts()) {
            productListModel.addElement(product);
        }
        totalPanel.setText("Total: $" + Main.cart.getTotalPrice());
    }

    private void handleProductButtonClick(Product product) {
        Main.cart.removeProduct(product);
        refreshProductList();
        refreshProductListUI();
    }

    private void handleCheckOut() {
        if (Main.cart.getProducts().isEmpty()) {
            System.out.println("Cart is empty! Cannot checkout!");
            alertLabel.setText("Please add products to cart!");
            pack();
            return;
        }

        new Success();
        dispose();
    }

    private void handleBackToHome() {
        new Home();
        dispose();
    }
}
