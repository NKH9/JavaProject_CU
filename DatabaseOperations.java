import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface DatabaseOperations {
    void Connect(String url,String username, String password) throws SQLException;
    void Disconnect() throws SQLException;
    void InsertProduct(Product product) throws SQLException;
    void InsertProductsFromFile(String Filename) throws SQLException, IOException;

     ArrayList<Product> GetAllProducts() throws SQLException;
     Product GetProductByName(String name) throws SQLException,ProductNotFound;
}
