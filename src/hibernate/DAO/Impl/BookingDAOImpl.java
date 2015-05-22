package hibernate.DAO.Impl;

import hibernate.DAO.BookingDAO;
import hibernate.logic.Booking;
import hibernate.util.HibernateUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;



public class BookingDAOImpl implements BookingDAO{

    @Override
    public void addBooking(Booking booking) throws SQLException {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(booking);
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
    public Booking getBooking(int id) throws SQLException {
        Session session = null;
        Booking booking = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createSQLQuery(
                "SELECT * "
              + "FROM booking b "
              + "WHERE b.id = :id "
                ).addEntity(Booking.class).setInteger("id", id);
            if(!query.list().isEmpty())
                booking = (Booking)query.list().get(0);
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
        return booking;
    }

    @Override
    public List<Booking> getAllBookings() throws SQLException {
        Session session = null;
        List<Booking> bookings = new ArrayList<>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            bookings = session.createCriteria(Booking.class).list();
        }catch(HibernateException e){
            System.out.println(e.toString());
            StackTraceElement[] el = e.getStackTrace();
                for (StackTraceElement el1 : el) {
                    System.out.println(el1);
                }  
        }finally{
            if((session != null)&&(session.isOpen())) session.close();
        }
        return bookings;
    }

    @Override
    public List<Booking> getAllBookings(String user) throws SQLException {
        Session session = null;
        List<Booking> bookings = new ArrayList<>();  
        if (user.equals("")) return bookings;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createSQLQuery(
                "SELECT * " 
              + "FROM booking b " 
              + "WHERE b.name = :login " 
              + "ORDER by b.date desc"
                ).addEntity(Booking.class).setString("login", user);
            if(!query.list().isEmpty()) {
                bookings = (List<Booking>)query.list();
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
        return bookings;
    }

    @Override
    public void deleteBooking(int id) throws SQLException {
        Session session = null;
        Booking booking = getBooking(id);
        if (booking == null) return;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(booking);
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
