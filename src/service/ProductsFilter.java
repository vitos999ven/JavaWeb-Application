package service;

import java.util.List;


public class ProductsFilter {
    
    String search;
    byte pageProductsCount;
    int pageNum;
    
    public ProductsFilter(String search, byte pageProductsCount, int pageNum){
        this.search = search;
        this.pageProductsCount = pageProductsCount;
        this.pageNum = pageNum;
    }
    
    public String[] getProductsIds(){
        List<String> allIds = ServiceFactory.getInstance().getProductService().getAllIdsBySearch(search);
        //int arraySize = ((allIds.size() > pageProductsCount * pageNum)? pageProductsCount * pageNum : allIds.size()) - pageProductsCount * (pageNum - 1);
        //if (arraySize <= 0) return new String[0];
        String[] ids = new String[allIds.size()];
        for (int i = 0; i < allIds.size(); i++){
           /// int j = pageProductsCount * (pageNum - 1) + i;
            ids[i] = allIds.get(i);
        }
        return  ids; 
    }
    
    public int getCountOfPages(){
        List<String> ids = ServiceFactory.getInstance().getProductService().getAllIdsBySearch(search);
        return  (ids.size()/pageProductsCount + (((ids.size() % pageProductsCount) == 0)? 0 : 1)); 
    }
}
