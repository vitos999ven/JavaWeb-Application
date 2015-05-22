package servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;


public class ShoppingServlet extends HttpServlet{
    
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{  
        response.setCharacterEncoding("utf-8");
        PrintWriter sw = response.getWriter();
        if (request.isUserInRole("user")){
            HttpSession session = request.getSession(true);
            List<String> products = (List<String>) session.getAttribute("products");
            if (products == null){
                products = new ArrayList<>();
            }
            String newProduct = request.getParameter("id");
            if (!newProduct.equals(""))
                products.add(newProduct);
            session.setAttribute("products", products);
            sw.print(products.size());
        }else{
            sw.print("0");
        }
        sw.close();
    }
    
    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        response.setCharacterEncoding("utf-8");
        PrintWriter sw = response.getWriter();
        HttpSession session = request.getSession(true);
        List<String> products = (List<String>) session.getAttribute("products");
        if (products == null){
            products = new ArrayList<>();
        }
        String deletedProduct = request.getParameter("id");
        if (products.contains(deletedProduct))
            products.remove(products.lastIndexOf(deletedProduct));
        session.setAttribute("products", products);
        sw.print(products.size());
        sw.close();
    }
   
    
}