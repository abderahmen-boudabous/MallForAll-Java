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
public class Reclamations {

   
    
    private int id_reclamation, id_utilisateur;
    private String text_reclamation, etat_reclamation,type_reclamation,date_reponse,date_reclamation,text_reponse;

    public Reclamations(int id_reclamation, int id_utilisateur, String text_reclamation, String etat_reclamation, String type_reclamation, String date_reponse, String date_reclamation, String text_reponse) {
        this.id_reclamation = id_reclamation;
        this.id_utilisateur = id_utilisateur;
        this.text_reclamation = text_reclamation;
        this.etat_reclamation = etat_reclamation;
        this.type_reclamation = type_reclamation;
        this.date_reponse = date_reponse;
        this.date_reclamation = date_reclamation;
        this.text_reponse = text_reponse;
    }

    public Reclamations(int id_utilisateur, String text_reclamation, String etat_reclamation, String type_reclamation, String date_reponse, String date_reclamation, String text_reponse) {
        this.id_utilisateur = id_utilisateur;
        this.text_reclamation = text_reclamation;
        this.etat_reclamation = etat_reclamation;
        this.type_reclamation = type_reclamation;
        this.date_reponse = date_reponse;
        this.date_reclamation = date_reclamation;
        this.text_reponse = text_reponse;
    }

    public Reclamations(String text_reclamation, String etat_reclamation, String date_reponse) {
        this.text_reclamation = text_reclamation;
        this.etat_reclamation = etat_reclamation;
        this.date_reponse = date_reponse;
    }

    public Reclamations(int id_utilisateur, String text_reclamation, String type_reclamation) {
        this.id_utilisateur = id_utilisateur;
        this.text_reclamation = text_reclamation;
        this.type_reclamation = type_reclamation;
    }
    

    public Reclamations( String text_reponse, String etat_reclamation,int id_reclamation) {
        this.id_reclamation = id_reclamation;
        this.text_reponse = text_reponse;
        this.etat_reclamation = etat_reclamation;
    }

    
    
    public Reclamations(String text_reclamation, String type_reclamation) {
        this.text_reclamation = text_reclamation;
        this.type_reclamation = type_reclamation;
    }
    

    public Reclamations() {
    }

    public String getText_reponse() {
        return text_reponse;
    }

    public void setText_reponse(String text_reponse) {
        this.text_reponse = text_reponse;
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public String getText_reclamation() {
        return text_reclamation;
    }

    public String getEtat_reclamation() {
        return etat_reclamation;
    }

    public String getType_reclamation() {
        return type_reclamation;
    }

    public String getDate_reponse() {
        return date_reponse;
    }

    public String getDate_reclamation() {
        return date_reclamation;
       
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public void setText_reclamation(String text_reclamation) {
        this.text_reclamation = text_reclamation;
    }

    public void setEtat_reclamation(String etat_reclamation) {
        this.etat_reclamation = etat_reclamation;
    }

    public void setType_reclamation(String type_reclamation) {
        this.type_reclamation = type_reclamation;
    }

    public void setDate_reponse(String date_reponse) {
        this.date_reponse = date_reponse;
    }

    public void setDate_reclamation(String date_reclamation) {
        this.date_reclamation = date_reclamation;
    }

    @Override
    public String toString() {
        return "reclamations{" + "id_reclamation=" + id_reclamation + ", id_utilisateur=" + id_utilisateur + ", text_reclamation=" + text_reclamation + ", etat_reclamation=" + etat_reclamation + ", type_reclamation=" + type_reclamation + ", date_reponse=" + date_reponse + ", date_reclamation=" + date_reclamation + '}';
    }

  
 


   
   
    
    
    
    
    
}

