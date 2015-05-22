package hibernate.util;


import hibernate.DAO.BookingDAO;
import hibernate.DAO.CommentDAO;
import hibernate.DAO.Impl.BookingDAOImpl;
import hibernate.DAO.Impl.CommentDAOImpl;
import hibernate.DAO.ProductDAO;
import hibernate.DAO.Impl.ProductDAOImpl;
import hibernate.DAO.Impl.UserDAOImpl;
import hibernate.DAO.UserDAO;


public class Factory {
    
    private static class FactoryHolder{
        private final static Factory instance = new Factory();
    }
    
    private static class ProductDAOHolder{
        private final static ProductDAO productDAO = new ProductDAOImpl();
    }
    
    private static class BookingDAOHolder{
        private final static BookingDAO bookingDAO = new BookingDAOImpl();
    }
    
    private static class UserDAOHolder{
        private final static UserDAO userDAO = new UserDAOImpl();
    }
    
    private static class CommentDAOHolder{
        private final static CommentDAO commentDAO = new CommentDAOImpl();
    }
    
    public static Factory getInstance(){
        return FactoryHolder.instance;
    }
    
    public ProductDAO getProductDAO(){
        return ProductDAOHolder.productDAO;
    }
    
    public BookingDAO getBookingDAO(){
        return BookingDAOHolder.bookingDAO;
    }
    
    public UserDAO getUserDAO(){
        return UserDAOHolder.userDAO;
    }
    
    public CommentDAO getCommentDAO(){
        return CommentDAOHolder.commentDAO;
    }
    
}
