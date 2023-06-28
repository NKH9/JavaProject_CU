public class Electronics extends  Product{
    public Electronics(String name, double price) {
        super(name, price);
    }

    @Override
    public ProductType getProductType() {
        return ProductType.ELECTRONICS;
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
