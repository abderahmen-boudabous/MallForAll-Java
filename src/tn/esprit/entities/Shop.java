package tn.esprit.entities;

import java.sql.Date;

public class Shop {
    private int id;
    private String name;
    private String description;
    private String email;
    private Date date;
    private String user_s;

    public Shop() {
    }

    public Shop(int id, String name, String description, String email, String user_s, Date date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.email = email;
        this.user_s = user_s;
        this.date = date;      
    }

    public Shop(String name, String description, String email, String user_s, Date date) {      
        this.name = name;
        this.description = description;
        this.email = email;
        this.user_s = user_s;
        this.date = date;      
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
    
    // Other getters and setters as needed

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", email='" + email + '\'' +
                ", user_s='" + user_s + '\'' +
                ", date=" + date +
                '}';
    }

    
}
