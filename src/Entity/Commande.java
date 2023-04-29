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
public class Commande {
    int id ;
    int product_id ;
    String nom ;
    String adresse ;
    int quantite ;
    int telephone ;
    int note;

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public Commande(int id, int product_id, String nom, String adresse, int quantite, int telephone, int note) {
        this.id = id;
        this.product_id = product_id;
        this.nom = nom;
        this.adresse = adresse;
        this.quantite = quantite;
        this.telephone = telephone;
        this.note = note;
    }

    public Commande(int id, int product_id, String nom, String adresse, int quantite, int telephone) {
        this.id = id;
        this.product_id = product_id;
        this.nom = nom;
        this.adresse = adresse;
        this.quantite = quantite;
        this.telephone = telephone;
    }

    public Commande() {
    }

    public Commande(int product_id, String nom, String adresse, int quantite, int telephone) {
        this.product_id = product_id;
        this.nom = nom;
        this.adresse = adresse;
        this.quantite = quantite;
        this.telephone = telephone;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }



    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", product_id=" + product_id + ", nom=" + nom + ", adresse=" + adresse + ", quantite=" + quantite + ", telephone=" + telephone + ", note=" + note + '}';
    }

   
            
    
}
