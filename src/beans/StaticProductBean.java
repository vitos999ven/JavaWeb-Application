package beans;



public class StaticProductBean {
        
    private static ProductBean bean = null;
    
    public static void setBean(String id, String language){
        if (bean == null){
            bean = new ProductBean();
        }
        bean.setLanguage(language);
        bean.setId(id);
    }
    
    public static String getTitle(){
        return bean.getTitle();
    }
    
    public static String getPhotoUrl(){
        return bean.getPhotoUrl();
    }   
    
    public static String getContent(){
        return bean.getContent();
    }
    
    public static Double getCost(){
        return bean.getCost();
    }
}
