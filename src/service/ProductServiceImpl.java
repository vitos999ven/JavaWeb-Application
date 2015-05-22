package service;

import hibernate.logic.ProductInfo;
import hibernate.util.Factory;
import java.io.IOException;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;



public class ProductServiceImpl implements ProductService {
    
    Map<String, String> ids = null;
    
    public ProductServiceImpl(){
        init();
    }
    
    private void init(){
        ids = new HashMap<>();
        List<ProductInfo> info = null;
        try {
            info = Factory.getInstance().getProductDAO().getAllProductInfo();
        } catch (SQLException ex) {
            Logger.getLogger(ProductServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (ProductInfo obj : info){
            ids.put(obj.getTitle().toLowerCase(), Integer.toString(obj.getId()));
            System.out.println("title: " + obj.getTitle().toLowerCase());
        }
    }
    
    @Override
    public List<String> getAllIdsBySearch(String search) {
        ArrayList<String> list = new ArrayList<>();
        System.out.println("search:" + search);
        ids.entrySet().stream().forEach((entry) -> {
            System.out.println("entry: " + entry.getKey());
            if ((search.equals("")) || (entry.getKey().contains(search.toLowerCase()))){
                list.add(entry.getValue());
                System.out.println("TRUE");
            }
        });
        return list;
    }
    
    @Override
    public Properties getProductSmallInfo(String id, String language) {
        ProductInfo info = null;
        if (id.equals("")) return new Properties();
        try {
            info = Factory.getInstance().getProductDAO().getProductInfo(Integer.parseInt(id));
        } catch (SQLException ex) {
            Logger.getLogger(ProductServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        Properties prop = null;
        if (language.equals("english")){
            prop = parsePropertiesString(info.getEnglishSmallInfo());
        }else if (language.equals("russian")){
            prop = parsePropertiesString(info.getRussianSmallInfo());
        }else{
            prop = parsePropertiesString(info.getDeutschSmallInfo());
        }
        prop.put("photoUrl", "resources/images/" + info.getPhoto1() + "low.jpg");
        return prop;
    }

    @Override
    public Properties getProductBigInfo(String id, String language) {
        ProductInfo info = null;
        try {
            info = Factory.getInstance().getProductDAO().getProductInfo(Integer.parseInt(id));
        } catch (SQLException ex) {
            Logger.getLogger(ProductServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        Properties prop = null;
        if (language.equals("english")){
            prop = parsePropertiesString(info.getEnglishBigInfo());
        }else if (language.equals("russian")){
            prop = parsePropertiesString(info.getRussianBigInfo());
        }else{
            prop = parsePropertiesString(info.getDeutschBigInfo());
        }
        prop.put("photo1", Integer.toString(info.getPhoto1()));
        prop.put("photo2", Integer.toString(info.getPhoto2()));
        prop.put("photo3", Integer.toString(info.getPhoto3()));
        
        return prop;
    }

    public Properties parsePropertiesString(String s) {
        Properties p = new Properties();
        try {
            p.load(new StringReader(s));
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        return p;
    }
}
