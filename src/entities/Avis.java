/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author arafet
 */
public class Avis {
    
    private int id_avis,id_utilisateur;
    private String text_avis,date_avis;
    private float rate_avis ;

    public Avis(int id_utilisateur, String text_avis, String date_avis, float rate_avis) {
    
        this.text_avis = text_avis;
         this.rate_avis = rate_avis;
        this.id_utilisateur = id_utilisateur;
        this.date_avis = date_avis;
        this.rate_avis = rate_avis;
    }

    public Avis(int id_avis, String text_avis, float rate_avis) {
        this.id_avis = id_avis;
        this.text_avis = text_avis;
        this.rate_avis = rate_avis;
    }
    

    public Avis() {
    }
    
    public Avis(String text_avis, float rate_avis,int id_utilisateur) {
        this.text_avis = text_avis;
        this.rate_avis = rate_avis;
        this.id_utilisateur = id_utilisateur;
       
    }
    

    public Avis(int id_avis, int id_utilisateur, String text_avis, String date_avis, float rate_avis) {
        this.id_avis = id_avis;
        this.id_utilisateur = id_utilisateur;
        this.text_avis = text_avis;
        this.date_avis = date_avis;
        this.rate_avis = rate_avis;
    }

    public int getId_avis() {
        return id_avis;
    }

    public Avis(float rate_avis, String text_avis) {
       
        this.rate_avis = rate_avis;
         this.text_avis = text_avis;
    }
    

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public String getText_avis() {
        return text_avis;
    }

    public String getDate_avis() {
        return date_avis;
    }

    public float getRate_avis() {
        return rate_avis;
    }

    public void setId_avis(int id_avis) {
        this.id_avis = id_avis;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public void setText_avis(String text_avis) {
        this.text_avis = text_avis;
    }

    public void setDate_avis(String date_avis) {
        this.date_avis = date_avis;
    }

    public void setRate_avis(float rate_avis) {
        this.rate_avis = rate_avis;
    }

    @Override
    public String toString() {
        return "avis{" + "id_avis=" + id_avis + ", id_utilisateur=" + id_utilisateur + ", text_avis=" + text_avis + ", date_avis=" + date_avis + ", rate_avis=" + rate_avis + '}';
    }
    
    
    
    
    
    
    
    
    
}
    