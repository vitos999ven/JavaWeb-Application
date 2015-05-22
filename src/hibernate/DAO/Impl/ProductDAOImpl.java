

package hibernate.DAO.Impl;

import hibernate.DAO.ProductDAO;
import hibernate.logic.ProductInfo;
import hibernate.util.HibernateUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;


public class ProductDAOImpl implements ProductDAO{
    
    
    @Override
    public void addProductInfo(ProductInfo info) throws SQLException{
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(info);
            session.getTransaction().commit();
        }catch(HibernateException e){
            System.out.println(e.toString());
            StackTraceElement[] el = e.getStackTrace();
                for (StackTraceElement el1 : el) {
                    System.out.println(el1);
                }  
        }finally{
            if((session != null)&&(session.isOpen())) session.close();
        }
    }
    
    @Override
    public ProductInfo getProductInfo(int id) throws SQLException{
        Session session = null;
        ProductInfo info = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createSQLQuery(
                "SELECT * "
              + "FROM product p "
              + "WHERE p.id = :id "
                ).addEntity(ProductInfo.class).setInteger("id", id);
            if(!query.list().isEmpty())
                info = (ProductInfo)query.list().get(0);
             session.getTransaction().commit();
        }catch(HibernateException e){
            System.out.println(e.toString());
            StackTraceElement[] el = e.getStackTrace();
                for (StackTraceElement el1 : el) {
                    System.out.println(el1);
                }
        }finally{
            if((session != null)&&(session.isOpen())) session.close();
        }
        return info;
    }
     
    @Override
    public List<ProductInfo> getAllProductInfo() throws SQLException{
        Session session = null;
        List<ProductInfo> info = new ArrayList<>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            info = session.createCriteria(ProductInfo.class).list();
        }catch(HibernateException e){
            System.out.println(e.toString());
            StackTraceElement[] el = e.getStackTrace();
                for (StackTraceElement el1 : el) {
                    System.out.println(el1);
                }  
        }finally{
            if((session != null)&&(session.isOpen())) session.close();
        }
        return info;
    }
    
    
   
        
    @Override
    public void deleteProductInfo(int id) throws SQLException{
        Session session = null;
        ProductInfo info = getProductInfo(id);
        if (info == null) return;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(info);
            session.getTransaction().commit();
        }catch(HibernateException e){
            System.out.println(e.toString());
            StackTraceElement[] el = e.getStackTrace();
                for (StackTraceElement el1 : el) {
                    System.out.println(el1);
                }   
        }finally{
            if((session != null)&&(session.isOpen())) session.close();
        }
    } 
    
}
