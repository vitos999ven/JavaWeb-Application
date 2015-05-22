package hibernate.logic;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Витос
 */
@Entity
@Table(name="product")
public class ProductInfo {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="id", unique = true, nullable = false)
    private int id;
    @Column(name="title")
    private String title;
    @Column(name="cost")
    private double cost;
    @Column(name="photo1")
    private int photo1;
    @Column(name="photo2")
    private int photo2;
    @Column(name="photo3")
    private int photo3;
    @Column(name="EnglishSI")
    private String EnglishSI;
    @Column(name="RussianSI")
    private String RussianSI;
    @Column(name="DeutschSI")
    private String DeutschSI;
    @Column(name="EnglishBI")
    private String EnglishBI;
    @Column(name="RussianBI")
    private String RussianBI;
    @Column(name="DeutschBI")
    private String DeutschBI;

    public ProductInfo(){
    }
    
    public ProductInfo(int id, String title, double cost, int photo1, int photo2, int photo3, String EnglishSI, String RussianSI, String DeutschSI,  String EnglishBI, String RussianBI, String DeutschBI){
       this.id = id;
       this.title = title;
       this.cost = cost;
       this.photo1 = photo1;
       this.photo2 = photo2;
       this.photo3 = photo3;
       this.DeutschBI = DeutschBI;
       this.DeutschSI = DeutschSI;
       this.RussianBI = RussianBI;
       this.RussianSI = RussianSI;
       this.EnglishBI = EnglishBI;
       this.EnglishSI = EnglishSI;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return this.id;
    }
    
    public void setTitle(String title){
        this.title = title;
    }
    
    public String getTitle(){
        return this.title;
    }
    
    public void setCost(double cost){
        this.cost = cost;
    }
    
    public double getCost(){
        return this.cost;
    }
    
    public void setPhoto1(int photo){
        this.photo1 = photo;
    }
    
    public int getPhoto1(){
        return this.photo1;
    }
    
    public void setPhoto2(int photo){
        this.photo2 = photo;
    }
    
    public int getPhoto2(){
        return this.photo2;
    }
    
    public void setPhoto3(int photo){
        this.photo3 = photo;
    }
    
    public int getPhoto3(){
        return this.photo3;
    }
    
    public void setEnglishSmallInfo(String EnglishSI){
        this.EnglishSI = EnglishSI;
    }
    
    public String getEnglishSmallInfo(){
        return this.EnglishSI;
    }
    
    public void setRussianSmallInfo(String RussianSI){
        this.RussianSI = RussianSI;
    }
    
    public String getRussianSmallInfo(){
        return this.RussianSI;
    }
    
    public void setDeutschSmallInfo(String DeutschSI){
        this.DeutschSI = DeutschSI;
    }
    
    public String getDeutschSmallInfo(){
        return this.DeutschSI;
    }
    
    public void setEnglishBigInfo(String EnglishBI){
        this.EnglishBI = EnglishBI;
    }
    
    public String getEnglishBigInfo(){
        return this.EnglishBI;
    }
    
    public void setRussianBigInfo(String RussianBI){
        this.RussianBI = RussianBI;
    }
    
    public String getRussianBigInfo(){
        return this.RussianBI;
    }
    
    public void setDeutschBigInfo(String DeutschBI){
        this.DeutschBI = DeutschBI;
    }
    
    public String getDeutschBigInfo(){
        return this.DeutschBI;
    }
    
}
