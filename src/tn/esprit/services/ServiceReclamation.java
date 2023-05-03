/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import entities.Avis;
import entities.Rec;
import entities.Reclamations;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import  tn.esprit.tools.MaConnexion;


/**
 *
 * @author Skander
 */
public class ServiceReclamation{
    
    Connection cnx;

    public ServiceReclamation() {
        cnx = MaConnexion.getInstance().getCnx();
    }

  public void ajouter(Rec t) throws SQLException {
           String req = "INSERT INTO rec(id_utilisateur,sujet,contenu) VALUES("
                 + t.getId_utilisateur()+ ",'" + t.getSujet()+ "','" + t.getContenu()+ "')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    public void modifier(Rec t) throws SQLException {
          String req = "UPDATE rec SET reponse = ?,etat= ?,date= now() where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getReponse());
        ps.setString(2, t.getEtat());
        ps.setInt(3, t.getId());
        System.out.println(t.getId()+" "+t.getEtat()+" "+t.getReponse());
        ps.executeUpdate();
        
    }
      
    

    public boolean supprimer(Rec t) throws SQLException {
        boolean ok = false;
        try {
            String req = "DELETE FROM rec WHERE id = "+t.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            ok = true;
        } catch (SQLException ex) {
            System.out.println("error in delete " + ex);
        }
        return ok;
    }

    public List<Rec> recuperer() throws SQLException {
        List<Rec> rec = new ArrayList<>();
        String s = "select * from Rec ";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Rec i = new Rec();
            i.setId(rs.getInt("id"));
            i.setSujet(rs.getString("sujet"));
            i.setContenu(rs.getString("contenu"));
            i.setUser_r(rs.getString("user_r"));
            i.setDate(rs.getString("date"));
            i.setReponse(rs.getString("reponse"));
       
            rec.add(i);
            
    
        }
        return rec ;
    }
    
    public List<Rec> chercherParId(int id) throws SQLException {
        List<Rec> rec = new ArrayList<>();
        String s = "select * from rec where id = "+id;
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Rec i = new Rec();
            i.setId(rs.getInt("id"));
            i.setSujet(rs.getString("sujet"));
            i.setContenu(rs.getString("contenu"));
            i.setUser_r(rs.getString("user_r"));
            i.setDate(rs.getString("date"));
            i.setReponse(rs.getString("reponse"));
       
            rec.add(i);
            
    
        }
        return rec ;
    }

}