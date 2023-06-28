import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DatabaseOperationsManager implements DatabaseOperations {
    private Connection connection;
    @Override
    public void Connect(String url, String username, String password) throws SQLException {
        connection= DriverManager.getConnection(url, username, password);

    }

    @Override
    public void Disconnect() throws SQLException {
        connection.close();

    }

    @Override
    public void InsertProduct(Product product) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("" +
                "INSERT INTO products (name, price, type) VALUES (?, ?, ?)");
        statement.setString(1, product.getName());
        statement.setDouble(2, product.getPrice());
        statement.setString(3, product.getProductType().name());
        statement.executeUpdate();

    }

    @Override
    public void InsertProductsFromFile(String Filename) throws SQLException, FileNotFoundException, IOException {

          try(Scanner sc=new Scanner(new BufferedReader(new FileReader(Filename)))){
              ArrayList<String> prods=new ArrayList();
              while(sc.hasNext()) {
                  prods.add(sc.nextLine());
              }
              for(String str : prods) {
                  String[] wordsofstr=str.split(",");
                  String name=wordsofstr[0];
                  Double price=Double.parseDouble(wordsofstr[1]);
                  String type=wordsofstr[2];
                  Product pr=createProduct(name, price,type);
                  InsertProduct(pr);
              }

              System.out.println("Products from File added !");

          }catch (FileNotFoundException e) {
              throw new RuntimeException(e);
          }
    }

    private Product createProduct(String name, double price, String type) {
        switch (ProductType.valueOf(type)) {
            case ELECTRONICS:
                return new Electronics(name, price);
            case CLOTHING:
                return new Clothing(name, price);
            case BOOKS:
                return new Books(name, price);
            default:
                return null;
        }
    }

    @Override
    public ArrayList<Product> GetAllProducts() throws SQLException {
        Statement statement=connection.createStatement();
        ResultSet rs= statement.executeQuery("SELECT * FROM products ");

        ArrayList<Product> products=new ArrayList<>();
        while(rs.next()){
            String name=rs.getString("name");
            double price=rs.getDouble("price");
            String type=rs.getString("type");
            Product product=createProduct(name,price,type);
            if (product != null) {
                products.add(product);
            }
        }
        return  products;
    }

    @Override
    public Product GetProductByName(String name) throws SQLException, ProductNotFound {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM products WHERE name = ?");
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            double price = resultSet.getDouble("price");
            String type = resultSet.getString("type");

            return createProduct(name, price, type);
        } else {
            throw new ProductNotFound("Product " +name+" Not Found!");
        }
    }

}
