/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.entities.Personne;
import tn.esprit.tools.MaConnexion;

/**
 *
 * @author Fayechi
 */
public class PersonneService implements NewInterface<Personne>{
    Connection cnx;
    String sql="";

    public PersonneService() {
        cnx=MaConnexion.getInstance().getCnx();
    }
    
    @Override
    public void ajouter(Personne p) {
//        sql="insert into personne(nom,prenom,age) values('"+p.getNom()+
//                "','"+p.getPrenom()+"','"+p.getAge()+"')";
//        try {
//            Statement ste = cnx.createStatement();
//            ste.executeUpdate(sql);
//            System.out.println("Personne ajoutée");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
    sql = "insert into personne(nom,prenom,age) values (?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, p.getNom());
            ste.setString(2, p.getPrenom());
            ste.setInt(3, p.getAge());
            ste.executeUpdate();
            System.out.println("Personne Ajoutée !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Personne> afficher() {
        List<Personne> personnes = new ArrayList<>();
        sql="select * from personne";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs=ste.executeQuery(sql);
            while(rs.next()){
                Personne p = new Personne(rs.getInt(1),
                        rs.getInt("age"),
                        rs.getString(2), rs.getString("prenom"));
                personnes.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return personnes;
    }

    @Override
    public void supprimer(Personne p) {
        sql="delete from personne where id="+p.getId();
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Personne supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
}
