package hibernate.logic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="id", unique = true, nullable = false)
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="product")
    private int product;
    @Column(name="date")
    private long date;
    @Column(name="shop")
    private String shop;
    
    public Booking(){
        
    }
    
    public Booking(int id, String name, int product, long date, String shop){
        this.id = id;
        this.name = name;
        this.product = product;
        this.date = date;
        this.shop = shop;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return id;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
    public void setProduct(int product){
        this.product = product;
    }
    
    public int getProduct(){
        return product;
    }
    
    public void setDate(long date){
        this.date = date;
    }
    
    public long getDate(){
        return date;
    }
    
    public void setShop(String shop){
        this.shop = shop;
    }
    
    public String getShop(){
        return shop;
    }
}
