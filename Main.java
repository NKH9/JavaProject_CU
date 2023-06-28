import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        try {
            DatabaseOperations database=new DatabaseOperationsManager();
            String url="jdbc:mysql://localhost:3306/javaproject";
            String user="root";
            String password="12345678";
            database.Connect(url,user,password);

            database.InsertProduct(new Books("Python",400.0));
            database.InsertProduct(new Electronics("Smartphone",1400.0));
            database.InsertProduct(new Clothing("Shoes",340.0));

//            get all products from table
            ArrayList<Product> products=database.GetAllProducts();

//            Search product by name
            System.out.println(database.GetProductByName("Laptop").toString());

//            add new products from file
            String filename="C:\\Users\\User\\IdeaProjects\\Project\\src\\Productslist.txt";
            database.InsertProductsFromFile(filename);

//            Threads
            Threads thr=new Threads();
            Thread thr1=new Thread(()-> thr.run1(products));
            Thread thr2=new Thread(()-> thr.run2(products));
            thr1.start();
            thr2.start();

            database.Disconnect();

        } catch (SQLException | ProductNotFound | IOException e) {
            throw new RuntimeException(e);
        }

    }
}