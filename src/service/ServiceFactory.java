package service;

public class ServiceFactory {
    
    
    private static class ServiceFactoryHolder{
        private final static ServiceFactory instance = new ServiceFactory();
    }
    
    private static class ProductServiceHolder{
        private final static ProductService productService = new ProductServiceImpl();
    }
    
    public static ServiceFactory getInstance(){
        return ServiceFactoryHolder.instance;
    }
    
    public ProductService getProductService(){
        return ProductServiceHolder.productService;
    }

    
}
