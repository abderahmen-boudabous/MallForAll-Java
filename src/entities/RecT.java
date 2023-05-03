package entities;

import java.sql.Date;


public class RecT {
   
    private int id;
    private String nom_t;

   

    public RecT() {}

    public RecT(int id, String nom_t) {
        this.id = id;
        this.nom_t = nom_t;

    }

    public RecT(String nom_t) {      
        this.nom_t = nom_t;

    }

    // getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_t() {
        return nom_t;
    }

    public void setNom_t(String name) {
        this.nom_t = nom_t;
    }

   

    @Override
    public String toString() {
        return "Rec_T{" +
                "id=" + id +
                ", nom_t='" + nom_t + '\'' +
                
                '}';
    }
}
