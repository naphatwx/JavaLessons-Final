import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame {
    private JPanel mainPanel;
    private JButton checkOutButton;
    private JButton logoutButton;
    private JScrollPane productListScroll;

    private DefaultListModel<Product> productListModel = new DefaultListModel<>();

    public Home() {
        setContentPane(mainPanel);
        setTitle("Home");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Dimension scrollSize = new Dimension(400, 300);
        productListScroll.setPreferredSize(scrollSize);
        productListScroll.setMinimumSize(scrollSize);
        productListScroll.setMaximumSize(scrollSize);
        productListScroll.revalidate();
        productListScroll.repaint();

        pack();
        setLocationRelativeTo(null);

        setDefaultProductListModel();
        refreshProductListUI();

        checkOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCheckOut();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogout();
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
            JButton addToCartButton = new JButton("Add to cart");

            productPanel.add(productLabel, BorderLayout.WEST);
            productPanel.add(addToCartButton, BorderLayout.EAST);

            addToCartButton.addActionListener(e -> handleProductButtonClick(product));

            productsPanel.add(productPanel);
        }

        // Add the products panel directly to productListScroll
        productListScroll.setViewportView(productsPanel);
    }

    private void setDefaultProductListModel() {
        productListModel.addElement(new Product("Apple", 1000));
        productListModel.addElement(new Product("Banana", 500));
        productListModel.addElement(new Product("Orange", 300));
        productListModel.addElement(new Product("Grape", 200));
        productListModel.addElement(new Product("Pear", 400));
    }

    private void handleCheckOut() {
        new Summary();
        dispose();
    }

    private void handleLogout() {
        new Login();
        dispose();
    }

    private void handleProductButtonClick(Product product) {
        Main.cart.addProduct(product);
        System.out.println(Main.cart.getTotalPrice());
        System.out.println("Product added to cart!");
    }
}
