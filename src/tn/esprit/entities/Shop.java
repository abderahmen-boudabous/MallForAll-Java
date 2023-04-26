package tn.esprit.entities;

import java.sql.Date;
import java.io.File;


public class Shop {
   
    private int id;
    private String name;
    private String description;
    private String email;
    private Date date;
    private String user_s;
    private String img;
    private int like;
    private int dislike;
    

    public Shop() {}
    
    
    public Shop(int id, String name, String description, String email, String user_s, Date date, String img) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.email = email;
        this.user_s = user_s;
        this.date = date;
        this.img = img;
    }

    public Shop(int id, String name, String description, String email, String user_s, Date date/*, byte[] photo*/) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.email = email;
        this.user_s = user_s;
        this.date = date;
        //this.photo = photo;
    }
    
    

    public Shop(String name, String description, String email, String user_s, Date date,String img) {      
        this.name = name;
        this.description = description;
        this.email = email;
        this.user_s = user_s;
        this.date = date;
        this.img = img;
    }
    
    public Shop(String name, String description, String email, String user_s, Date date,String img,int like, int dislike) {      
        this.name = name;
        this.description = description;
        this.email = email;
        this.user_s = user_s;
        this.date = date;
        this.img = img;
        this.like = like;
        this.dislike = dislike;
    }

    
    public Shop(String name, String description, String email, String user_s, Date date) {      
        this.name = name;
        this.description = description;
        this.email = email;
        this.user_s = user_s;
        this.date = date;
        
    }


    // getters and setters

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public String getUser_s() {
        return user_s;
    }

    public void setUser_s(String user_s) {
        this.user_s = user_s;
    }
    
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    @Override
    public String toString() {
        return "Shop{" + "id=" + id + ", name=" + name + ", description=" + description + ", email=" + email + ", date=" + date + ", user_s=" + user_s + ", img=" + img + ", like=" + like + ", dislike=" + dislike + '}';
    }

    

    
   

}
