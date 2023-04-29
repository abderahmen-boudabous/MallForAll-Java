/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Avis;
import entities.Reclamations;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;


/**
 *
 * @author Skander
 */
public class ServiceReclamation{
    
    Connection cnx;

    public ServiceReclamation() {
        cnx = MyDB.getInstance().getCnx();
    }

    public void ajouter(Reclamations t) throws SQLException {
           String req = "INSERT INTO reclamations(id_utilisateur,text_reclamation,type_reclamation) VALUES("
                 + t.getId_utilisateur()+ ",'" + t.getText_reclamation()+ "','" + t.getType_reclamation()+ "')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    public void modifier(Reclamations t) throws SQLException {
          String req = "UPDATE reclamations SET Text_reponse = ?,etat_reclamation= ?,Date_reponse= now() where id_reclamation = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getText_reponse());
        ps.setString(2, t.getEtat_reclamation());
        ps.setInt(3, t.getId_reclamation());
        System.out.println(t.getId_reclamation()+" "+t.getEtat_reclamation()+" "+t.getText_reponse());
        ps.executeUpdate();
        
    }
      
    

    public boolean supprimer(Reclamations t) throws SQLException {
        boolean ok = false;
        try {
            String req = "DELETE FROM Reclamations WHERE id_reclamation = "+t.getId_reclamation();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            ok = true;
        } catch (SQLException ex) {
            System.out.println("error in delete " + ex);
        }
        return ok;
    }

    public List<Reclamations> recuperer() throws SQLException {
        List<Reclamations> reclamations = new ArrayList<>();
        String s = "select * from Reclamations ";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Reclamations i = new Reclamations();
            i.setId_reclamation(rs.getInt("id_reclamation"));
            i.setId_utilisateur(rs.getInt("id_utilisateur"));
            i.setText_reclamation(rs.getString("text_reclamation"));
            i.setEtat_reclamation(rs.getString("etat_reclamation"));
            i.setType_reclamation(rs.getString("type_reclamation"));
            i.setDate_reponse(rs.getString("date_reponse"));
            i.setDate_reclamation(rs.getString("date_reclamation"));
            i.setText_reponse(rs.getString("text_reponse"));
       
            reclamations.add(i);
            
    
        }
        return reclamations ;
    }
    
    public List<Reclamations> chercherParId(int id) throws SQLException {
        List<Reclamations> reclamations = new ArrayList<>();
        String s = "select * from Reclamations where id_utilisateur = "+id;
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Reclamations i = new Reclamations();
            i.setId_reclamation(rs.getInt("id_reclamation"));
            i.setId_utilisateur(rs.getInt("id_utilisateur"));
            i.setText_reclamation(rs.getString("text_reclamation"));
            i.setEtat_reclamation(rs.getString("etat_reclamation"));
            i.setType_reclamation(rs.getString("type_reclamation"));
            i.setDate_reponse(rs.getString("date_reponse"));
            i.setDate_reclamation(rs.getString("date_reclamation"));
            i.setText_reponse(rs.getString("text_reponse"));
       
            reclamations.add(i);
            
    
        }
        return reclamations ;
    }

}