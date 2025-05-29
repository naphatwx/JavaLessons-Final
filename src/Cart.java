import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> products = new ArrayList<Product>();
    private double totalPrice = 0;

    public Cart() {}

    public Cart(List<Product> products, double totalPrice) {
        this.products = products;
        this.totalPrice = totalPrice;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void addProduct(Product product) {
        products.add(product);
        calculateTotalPrice();
    }

    public void removeProduct(Product product) {
        products.remove(product);
        calculateTotalPrice();
    }

    public void calculateTotalPrice() {
        totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getPrice();
        }
    }

    public void clearCart() {
        products.clear();
        totalPrice = 0;
    }
}
