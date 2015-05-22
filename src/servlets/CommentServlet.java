package servlets;

import beans.ProductBean;
import hibernate.logic.Booking;
import hibernate.logic.Comment;
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


public class CommentServlet extends HttpServlet{
    
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        response.setCharacterEncoding("utf-8");
        PrintWriter sw = response.getWriter();
        sw.print("");
        sw.close();
        if (!request.isUserInRole("user")) return;
        String name = request.getUserPrincipal().getName();
        String value = request.getParameter("value");
        long date = Long.parseLong(request.getParameter("date"));
        try {
            Factory.getInstance().getCommentDAO().addComment(new Comment(0, name, date, value));
        } catch (SQLException ex) {
            Logger.getLogger(BookingServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    
    
}