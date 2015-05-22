package servlets;

import beans.ProductBean;
import hibernate.logic.Booking;
import hibernate.util.Factory;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class BookingServlet extends HttpServlet{
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        response.setCharacterEncoding("utf-8");
        if (!request.isUserInRole("user")) return;
        PrintWriter sw = response.getWriter();
        
        Cookie[] cookies = request.getCookies();
        String language = CookieMethods.getCookieValue(cookies, "language");
        if (language.equals("") || 
                (!language.equals("english") && !language.equals("russian") && !language.equals("deutsch"))){
            language = "english";
            response.addCookie(new Cookie("language", language));
        }
        String name = request.getUserPrincipal().getName();
        String resp = "";
        try {
            List<Booking> bookings = Factory.getInstance().getBookingDAO().getAllBookings(name);
            for (Booking booking: bookings){
                ProductBean bean = new ProductBean();
                bean.setLanguage(language);
                bean.setId(Integer.toString(booking.getProduct()));
                resp += "<a href='/LAB3/product?id=" + bean.getId() +"'>" + bean.getTitle() + " - " + bean.getCost() + "</a>:" + booking.getShop() + ", " + booking.getDate() + "<br/>";               
            }
            sw.print(resp);
        } catch (SQLException ex) {
            Logger.getLogger(BookingServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sw.close();
    } 
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        response.setCharacterEncoding("utf-8");
        PrintWriter sw = response.getWriter();
        sw.print("");
        sw.close();
        if (!request.isUserInRole("user")) return;
        String name = request.getUserPrincipal().getName();
        int productId = Integer.parseInt(request.getParameter("product"));
        String shop = request.getParameter("shop");
        try {
            Factory.getInstance().getBookingDAO().addBooking(new Booking(0, name, productId, new GregorianCalendar().getTimeInMillis(), shop));
        } catch (SQLException ex) {
            Logger.getLogger(BookingServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Factory.getInstance().getBookingDAO().deleteBooking(id);
        } catch (SQLException ex) {
            Logger.getLogger(BookingServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
}
