/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Bettaibi Walid
 */
public class Favori {
    int id ;
    int product_id ;
    int note;
    
    

    public Favori() {
    }
    public Favori(int id, int product_id, int note) {
        this.id = id;
        this.product_id = product_id;
    }

    public Favori(int id, int product_id) {
        this.id = id;
        this.product_id = product_id;
    }

    public Favori( int product_id) {
        this.product_id = product_id;
    }
    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    @Override
    public String toString() {
        return "Favori{" + "id=" + id + ", product_id=" + product_id +",note ="+note+ '}';
    }

    
        
    
    
}
