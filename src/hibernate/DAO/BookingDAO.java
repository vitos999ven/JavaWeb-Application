package hibernate.DAO;

import hibernate.logic.Booking;
import java.sql.SQLException;
import java.util.List;



public interface BookingDAO {
    public void addBooking(Booking booking) throws SQLException;
    public Booking getBooking(int id) throws SQLException;
    public List<Booking> getAllBookings() throws SQLException;
    public List<Booking> getAllBookings(String user) throws SQLException;
    public void deleteBooking(int id) throws SQLException;
}
