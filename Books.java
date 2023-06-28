public class Books extends Product{
    public Books(String name, double price) {
        super(name, price);
    }

    @Override
    public ProductType getProductType() {
        return ProductType.BOOKS;
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
