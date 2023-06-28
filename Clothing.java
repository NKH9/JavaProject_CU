public class Clothing extends Product{
    public Clothing(String name, double price) {
        super(name, price);
    }

    @Override
    public ProductType getProductType() {
        return ProductType.CLOTHING;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + this.getName() + '\'' +
                ", price=" + this.getPrice() +
                ", type='" + getProductType() + '\'' +
                '}';
    }
}
