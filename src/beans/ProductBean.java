package beans;

import java.io.Serializable;
import java.util.Map;
import java.util.Properties;
import service.ServiceFactory;


public class ProductBean implements Serializable {
    
    private String id;
    private String language;
    private String photoUrl;
    private String title;
    private String content;
    private double cost;
    
    public String getId(){
        return id;
    }
    
    public void setId(String id){
        this.id = id;
        Properties product = ServiceFactory.getInstance().getProductService().getProductSmallInfo(id, language);
        if (product.size() == 0) return;
        photoUrl = (String)product.get("photoUrl");
        title = (String)product.get("title");
        content = (String)product.get("content");
        if (product.get("cost") != null)
            cost = Double.parseDouble((String)product.get("cost"));
    }
    
    public String getLanguage(){
        return language;
    }
    
    public void setLanguage(String language){
        this.language = language;
    }
    
    public String getPhotoUrl(){
        return photoUrl;
    }
    
    public void setPhotoUrl(String photoUrl){
        this.photoUrl = photoUrl;
    }
    
    public String getTitle(){
        return title;
    }
    
    public void setTitle(String title){
        this.title = title;
    }
    
    public String getContent(){
        return content;
    }
    
    public void setContent(String content){
        this.content = content;
    }
    
    public double getCost(){
        return cost;
    }
    
    public void setCost(double cost){
        this.cost = cost;
    }
}
