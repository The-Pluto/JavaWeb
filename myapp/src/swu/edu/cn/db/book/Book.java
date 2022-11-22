package swu.edu.cn.db.book;

public class Book {
    private long id;
    private String name;
    private String author;
    private float price;
    private String describe;

    public Book(long id,String name,String author,float price,String describe){
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.describe = describe;
    }
    public Book(String name,String author,float price,String describe){
        this.name = name;
        this.author = author;
        this.price = price;
        this.describe = describe;
    }
    public Book(){

    }
    public long getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getAuthor(){
        return this.author;
    }
    public float getPrice(){
        return this.price;
    }
    public String getDescribe(){
        return this.describe;
    }
    public void setId(long id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public  void setAuthor(String author){
        this.author = author;
    }
    public void setPrice(float price){
        this.price = price;
    }
    public void setDescribe(String describe){
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", describe='" + describe + '\'' +
                '}';
    }
}
