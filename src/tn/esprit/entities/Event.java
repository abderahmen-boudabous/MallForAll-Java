package tn.esprit.entities;



import java.time.LocalDate;
import java.util.Date;

public class Event {
    private int id;
    private String nom;
    private int spot;
    private String duration;
    private Date date;
    private Category category;

    public Event() {
    }

public Event(String nom, int id) {
    
    this.nom = nom;
    this.id = id;
    
}
    
    public Event(int categoryId, String nom, int spot, String duration, Date date) {
    this.category = new Category(categoryId, null, null);
    this.nom = nom;
    this.spot = spot;
    this.duration = duration;
    this.date = date;
}
    
    public Event(String nom, int spot, String duration, Date date , Category category ) {
    this.category = category;
    this.nom = nom;
    this.spot = spot;
    this.duration = duration;
    this.date = date;
}

    public Event(String nom, int spot, String duration, LocalDate value, Category category) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }




    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSpot() {
        return spot;
    }

    public void setSpot(int spot) {
        this.spot = spot;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Event{" +
               // "id=" + id +
                ", nom='" + nom + '\'' +
                ", spot=" + spot +
                ", duration='" + duration + '\'' +
                ", date=" + date +
                ", category=" + category +
                '}';
    }
}