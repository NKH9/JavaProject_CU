import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Threads{
    public synchronized void run1(ArrayList<Product> products) {
        double totalPrice = products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
        System.out.println("Total Price: " + totalPrice);
    }
    public synchronized void run2(ArrayList<Product> products) {
        List<Product> filteredProducts = products.stream()
                .filter(p -> p.getPrice() > 50.0)
                .collect(Collectors.toList());

        System.out.println(filteredProducts);    }
}
