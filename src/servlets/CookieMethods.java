package servlets;

import javax.servlet.http.Cookie;


public class CookieMethods {
    
    public static String getCookieValue(Cookie[] cookies, String name){
        String value = "";
        if (cookies != null){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals(name)) {
                    value = cookie.getValue(); 
                    break;
                }
            }
        }
        return value;
    }
    
}
