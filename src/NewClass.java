
import hibernate.logic.ProductInfo;
import hibernate.logic.User;
import hibernate.util.Factory;
import java.io.IOException;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.ProductServiceImpl;
import service.ServiceFactory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Витос
 */
public class NewClass {
    public static void main(String[] args){
        try {
            //Factory.getInstance().getUserDAO().addUser(new User("other", 0, 1));
            User user = Factory.getInstance().getUserDAO().getUser("other");
            System.out.println(user.getLogin() + " " + user.getAvatar() + " " + user.getBookmark());
        } catch (SQLException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("");
    }
}
