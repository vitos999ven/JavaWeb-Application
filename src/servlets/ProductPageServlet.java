package servlets;


import hibernate.logic.User;
import hibernate.util.Factory;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import service.ServiceFactory;


public class ProductPageServlet extends HttpServlet{
    
    
    public static String getUnorderedList(String[] elements){
        String html = "<ul class='product-photos'>\n";
        for (String element : elements) {
            html += getListElement(element);
        }
        html += "</ul>\n";
        return html;
    }
 
    public static String getListElement(String source){
        String html = "               <li class='product-photo-li'>\n"
                  + "                   <div class='imgOuterTrimmer'>\n"
                  + "                       <div class='imgInnerTrimmer'>\n"
                  + "                           <img class='product-photo-lowimg' src='resources/images/" + source + "low.jpg'>\n"
                  + "                       </div>\n"
                  + "                   </div>\n"
                  + "               </li>\n";
        return html;
    }
    
    
    public static String getViewContent(Properties props){
        String html = "                               <div class='header'>" + props.getProperty("viewContentHeader") + "</div>\n";
        html += "                               <p>" + props.getProperty("viewContentParagraph") + "</p>\n";
        return html;
    }
    
    public static String getDetailsContent(Properties props){
        String html = "                               <div class='header'>" + props.getProperty("detailsContentHeader") + "</div>\n";
        html += "                               <p>" + props.getProperty("detailsContentLength") + "<br/>"
                  + props.getProperty("detailsContentWidth") + "<br/>" 
                  + props.getProperty("detailsContentThickness") + "<br/>"
                  + props.getProperty("detailsContentWeight") + "</p>\n";
        return html;
    }
    
    public static String getButton(String value, Properties props, String id){
        String html = "                           <div id='" + id + "' class='" + value + "'>\n" + props.getProperty(value) + "</div>\n";
        return html;
    }
    
    public static String getInsetTitle(String value, Properties props, boolean current){
        String html = "                               <div id='" + value + "' class='insetTitle" + ((current)? " current" : "") + "'>\n" + props.getProperty(value) + "</div>\n";
        return html;
    }
    
    public static String getReviewsContainer(Properties props){
        String html = "                               <div class='reviewsContainer'>\n" + getReview("Виктор", "Хороший выбор",
                "Очень хороший телефон. ") 
                  + "/n                               </div>\n";
        return html;
    }
    
    public static String getReview(String username, String title, String content){
        String html = "                                 <div id='" + username + "Review' class='review'>\n" 
                     +"                                     <h6 >" + username + ":" + title + "</h6>\n"
                    + "                                     <p>" + content + "</p>\n"
                    + "                                 </div>\n";
        return html;
    }
  
    
    public static String getPageHeader(String language, HttpServletRequest request){
        String html = "<ul class='menuList'>"
                +     "  <li id='language-li'>"
                +     "      <select id='language-select'>";
        html += "<option value='russian' " + ((language.equals("russian"))? "selected" : "") + " >ru</option>";
        html += "<option value='english' " + ((language.equals("english"))? "selected" : "") + " >en</option>";
        html += "<option value='deutsch' " + ((language.equals("deutsch"))? "selected" : "") + " >de</option>";
        html += "       </select>"
                + "    </li> ";
        if (language.equals("russian")) { 
            if (!request.isUserInRole("user")){
                html += "<li id=\"login-li\"><a href='/LAB3/login'>Вход</a></li>";
            }else{
                html += "<li id=\"cabinet-li\"><a href='/LAB3/cabinet'>Личный кабинет</a></li>\n" +
                        "<li id=\"logout-li\"><a href='/LAB3/logout'>Выход</a></li>";
            }
            html += "        <li id=\"shopping-cart-li\"><a href='/LAB3/cart'>Корзина</a></li>\n" +
"        <li id=\"purchase-history-li\"><a href='/LAB3/cart'>История посещений</a></li>\n" +
"		<li id=\"search-li\"><a href='/LAB3/products'>Поиск</a></li>";        
        } else if (language.equals("english")) { 
            if (!request.isUserInRole("user")){
                html += "<li id=\"login-li\"><a href='/LAB3/login'>Login</a></li>";
            }else{
                html += "<li id=\"cabinet-li\"><a href='/LAB3/cabinet'>Personal Area</a></li>\n" +
                        "<li id=\"logout-li\"><a href='/LAB3/logout'>Logout</a></li>";
            }
            html += 
"        <li id=\"shopping-cart-li\"><a href='/LAB3/cart'>Shopping cart</a></li>\n" +
"        <li id=\"purchase-history-li\"><a href='/LAB3/cart'>Purchase history</a></li>\n" +
"		<li id=\"search-li\"><a href='/LAB3/products'>Search</a></li>";               
        } else { 
            if (!request.isUserInRole("user")){
                html += "<li id=\"login-li\"><a href='/LAB3/login'>Einloggen</a></li>";
            }else{
                html += "<li id=\"cabinet-li\"><a href='/LAB3/cabinet'>Personal Area</a></li>\n" +
                        "<li id=\"logout-li\"><a href='/LAB3/logout'>Ausloggen</a></li>";
            }
            html += "        <li id=\"shopping-cart-li\"><a href='/LAB3/cart'>Einkaufswagen</a></li>\n" +
"        <li id=\"purchase-history-li\"><a href='/LAB3/cart'>Kauf-historie</a></li>\n" +
"		<li id=\"search-li\"><a href='/LAB3/products'>Suche</a></li>";
        }  
        html += "</ul>\n";
        return html;
    }
    
    public Properties parsePropertiesString(String s) {
        final Properties p = new Properties();
        try {
            p.load(new StringReader(s));
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    return p;
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{  
        /*OutputStream os = response.getOutputStream();
    PrintWriter sw = new PrintWriter(new OutputStreamWriter(os,"Cp1251"));*/
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter sw = response.getWriter();
        String inset = getInitParameter("startInset");
        Cookie[] cookies = request.getCookies();
        String id = request.getParameter("id");
        if ((id == null) || (id.equals(""))){
            if (request.isUserInRole("user")){
                try {
                    User user = Factory.getInstance().getUserDAO().getUser(request.getUserPrincipal().getName());
                    id = "" + user.getBookmark();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductPageServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }else{
                id = "1";
            }
        }
        String language = CookieMethods.getCookieValue(cookies, "language");
        if (language.equals("") || 
                (!language.equals("english") && !language.equals("russian") && !language.equals("deutsch"))){
            language = getInitParameter("startLanguage");
            response.addCookie(new Cookie("language", language));
        }
        Properties props = ServiceFactory.getInstance().getProductService().getProductBigInfo(id, language);
        Properties titles;
        if (language.equals("russian")){
            titles = parsePropertiesString(
                "viewTitle=Обзор\n"    
              + "detailsTitle=Детали\n"
              + "reviewsTitle=Отзывы\n"
              + "detailsContentHeader=Основное\n"            
              + "buyButton=Купить\n"   
                );
        }else if (language.equals("english")){
            titles = parsePropertiesString(
                "viewTitle=View\n"  
              + "detailsTitle=Details\n"
              + "reviewsTitle=Reviews\n"
              + "detailsContentHeader=Essentials\n"            
              + "buyButton=Buy\n"   
                );
        }else{
            titles = parsePropertiesString(
                "viewTitle=Rezension\n"
              + "detailsTitle=Einzelheiten\n"
              + "reviewsTitle=Bewertungen\n"
              + "detailsContentHeader=Wesentliche\n"          
              + "buyButton=Кaufen\n"   
                );
        }
        props.put("viewTitle", titles.get("viewTitle"));
        props.put("detailsTitle", titles.get("detailsTitle"));
        props.put("reviewsTitle", titles.get("reviewsTitle"));
        props.put("detailsContentHeader", titles.get("detailsContentHeader"));
        props.put("buyButton", titles.get("buyButton"));
        String[] elements = new String[3];
        elements[0] = props.getProperty("photo1");
        elements[1] = props.getProperty("photo2");
        elements[2] = props.getProperty("photo3");
        
        sw.print(
          "<html>\n"
                  + "   <head>\n"
                  + "       <meta charset=\"utf-8\">\n"
                  + "       <title>Lets</title>\n"
                  + "       <link rel='stylesheet' type='text/css' href='myStyles.css' media='all' />\n"
                  + "   </head>\n"
                  + "   <body>\n"
                  + "       <script src='jquery-2.1.1.min.js'></script>\n"
                  + "       <script src='jQueryUI/jquery-ui.min.js'></script>\n"
                  + "       <script src='myMethods.js'></script>\n" 
                  + "       <script src='buyButtonEvents.js'></script>\n"
                  + "       <script src='navBarEvents.js'></script>\n"
                  + "       <script src='productPageEvents.js'></script>\n"
                  + "       <div class='pageHeader'>"
        );
        sw.print(getPageHeader(language, request));
        sw.print(   "       </div>\n"
                  + "       <div class='productContainer'>"
                  + "       <div class='product'>\n"
                  + "           <div class='header'>\n"
                  + "               " + props.getProperty("title") + ":" + props.getProperty("cost") + "\n"
                  + "           </div><br/>\n"
        );
        sw.print(getButton("buyButton", props, id));
        sw.print(   "           <table id='PhotoTable'><tr><td>\n");
        sw.print(getUnorderedList(elements));
        sw.print(   "           </td><td>\n");
        sw.print(   "               <img class='product-photo-bigimg' src='resources/images/" + elements[0] + ".jpg'>\n");
        sw.print(   "           </td></tr></table>\n"
                  + "           <table class='insetTable'>\n"
                  + "               <tr>\n"
                  + "                   <td style='width:33%'>\n"
        );
        
        sw.print(getInsetTitle("viewTitle", props, inset.equals("view")));
        sw.print(   "                   </td>\n"
                  + "                   <td style='width:33%'>\n");
        sw.print(getInsetTitle("detailsTitle", props, inset.equals("details")));
        sw.print(   "                   </td>\n"
                  + "                   <td style='width:33%'>\n");
        sw.print(getInsetTitle("reviewsTitle", props, inset.equals("reviews")));
        sw.print(   "                   </td>\n"
                  + "               </tr><tr>\n"
                  + "                   <td colspan='3'>\n"
                  + "                       <div id='viewContent' class='insetContent " 
                  + ((inset.equals("view"))? "" : "hidden") + "'>\n"
        );   
        sw.print(getViewContent(props));
        sw.print(   "                       </div>\n"
                  + "                       <div id='detailsContent' class='insetContent " 
                  + ((inset.equals("details"))? "" : "hidden") + "'>\n"
        );        
        sw.print(getDetailsContent(props));        
        sw.print(   "                           </div>\n"
                  + "                       </div>\n"
                  + "                       <div id='reviewsContent' class='insetContent " 
                  + ((inset.equals("reviews"))? "" : "hidden") + "'>\n");
        sw.print(getReviewsContainer(props));          
        sw.print(   "                   </td>\n"
                  + "               </tr>\n"
                  + "           </table>\n"
                  + "       </div></div>\n"
        );
        sw.print(   "   </body>\n"
                  + "</html>"
        );
        sw.close();
    }
   
    
}
