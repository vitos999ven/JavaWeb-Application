package hibernate.DAO;


import hibernate.logic.ProductInfo;
import java.sql.SQLException;
import java.util.List;
         
         
public interface ProductDAO {
    public void addProductInfo(ProductInfo info) throws SQLException;
    public ProductInfo getProductInfo(int id) throws SQLException;
    public List<ProductInfo> getAllProductInfo() throws SQLException;
    public void deleteProductInfo(int id) throws SQLException;
    
    
}
