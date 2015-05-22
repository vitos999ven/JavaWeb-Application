package hibernate.DAO.Impl;

import hibernate.DAO.CommentDAO;
import hibernate.logic.Comment;
import hibernate.util.HibernateUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;



public class CommentDAOImpl implements CommentDAO{
    
    @Override
    public void addComment(Comment comment) throws SQLException{
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(comment);
            session.getTransaction().commit();   
        }catch(ConstraintViolationException e){
            System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!\nERROR! FOREIGN KEY IS WRONG!\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
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
    public List<Comment> getAllComments(String user) throws SQLException{
        Session session = null;
        List<Comment> comments = new ArrayList<>(); 
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createSQLQuery(
                "select * " 
              + "from comments c "
              + "WHERE c.user = :login "
              + "order by c.date")
                .addEntity(Comment.class).setString("login", user);
             
            if(!query.list().isEmpty()) 
                comments = (List<Comment>)query.list();
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
        return comments;
    }
    
    @Override
    public void deleteAllComments(String user) throws SQLException{
        Session session = null;
        List<Comment> photoComments = getAllComments(user);
        if (photoComments.isEmpty()) return;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
                for (Comment photoComment : photoComments) {
                    session.delete(photoComment);
                }
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
