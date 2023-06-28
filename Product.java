
abstract public class Product {
    private String name;
    private double price;
    private String type;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
//second constructor
    public Product(String name, double price, String type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public abstract ProductType getProductType();

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                '}';
    }
}
