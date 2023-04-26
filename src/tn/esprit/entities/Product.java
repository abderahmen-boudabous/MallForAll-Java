package tn.esprit.entities;

public class Product {
    private int id;
    private String name;
    private float price;
    private String type;
    private int stock;
    private Shop shop;
    private String photo;

    public Product() {
    }

    public Product(int id, String name, float price, String type, int stock,Shop shop) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.stock = stock;
        this.shop = shop;
    }
    
    public Product(int id, String name, float price, String type, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.stock = stock;
        
    }
    
    public Product(String name, float price, String type, int stock,Shop shop) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.stock = stock;
        this.shop = shop;
    }
    
    
    public Product(String name, float price, String type, int stock,Shop shop,String photo) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.stock = stock;
        this.shop = shop;
        this.photo = photo;
    }
    
    
    public Product(String name, float price, String type, int stock) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.stock = stock;
        
    }

    

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
        public Shop getShop() {
        return shop;
    }

    public void setShop(Shop Shop) {
        this.shop = Shop;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", price=" + price + ", type=" + type + ", stock=" + stock + ", shop=" + shop + ", photo=" + photo + '}';
    }

            
}
