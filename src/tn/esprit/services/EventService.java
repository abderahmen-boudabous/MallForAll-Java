package tn.esprit.services;


import java.util.Date;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.entities.Event;
import tn.esprit.entities.Category;
import tn.esprit.tools.MaConnexion;

public class EventService implements NewInterface<Event> {
    Connection cnx;
    String sql="";

    public EventService() {
        cnx=MaConnexion.getInstance().getCnx();
    }

    
    
public void ajouter(Event e) {
    String sql = "INSERT INTO event (category_id, nom, spot, duration, date) VALUES (?, ?, ?, ?, ?)";
    try {
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setInt(1, e.getCategory().getId());
        ps.setString(2, e.getNom());
        ps.setInt(3, e.getSpot());
        ps.setString(4, e.getDuration());
        ps.setDate(5, new java.sql.Date(e.getDate().getTime()));
        ps.executeUpdate();
        System.out.println("Event ajouté avec succès !");
    } catch (SQLException ex) {
        System.out.println("Erreur lors de l'ajout de l'événement : " + ex.getMessage());
    }
}

    

 
@Override
public List<Event> afficher() {
    List<Event> events = new ArrayList<>();
    sql = "SELECT c.titre, e.nom, e.spot, e.duration, e.date FROM event e JOIN category c ON e.category_id = c.id";
    try {
        Statement ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery(sql);
        while (rs.next()) {
            Event e = new Event();
            e.setNom(rs.getString("nom"));
            e.setSpot(rs.getInt("spot"));
            e.setDuration(rs.getString("duration"));
            e.setDate(rs.getDate("date"));
            Category category = new Category();
            category.setTitre(rs.getString("titre"));
            e.setCategory(category);
            events.add(e);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return events;
}


         /*   
     @Override
    public List<Event> afficher() {
        List<Event> events = new ArrayList<>();
        sql="select * from event";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs=ste.executeQuery(sql);
            while(rs.next()){
                Event e = new Event(rs.getInt("id"),
                        
                        rs.getString("nom"),
                        rs.getInt("spot"),
                        
                        rs.getString("duration"),               
                        rs.getDate("date"));
                events.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return events;
    }
     */

    @Override
    public void supprimer(Event e) {
        sql="delete from event where id="+e.getId();
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Event supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //@Override
    public void update(Event e) {
        sql = "update event set nom=?, spot=?, duration=?, date=? where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, e.getNom());
            ste.setInt(2, e.getSpot());
            ste.setString(3, e.getDuration());
            //ste.setDate(4, e.getDate());

            ste.setInt(6, e.getId());
            int rowsUpdated = ste.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Event mis à jour !");
            } else {
                System.out.println("Erreur : La mise à jour a échoué.");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        /*
        public List<Event> trierParDate() {
    List<Event> events = new ArrayList<>();
    String sql = "SELECT * FROM event ORDER BY date ASC";
    try {
        Statement stmt = cnx.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("id");
            int categoryId = rs.getInt("category_id");
            String name = rs.getString("nom");
            int spot = rs.getInt("spot");
            String duration = rs.getString("duration");
            Date date = rs.getDate("date");
            Event event = new Event(id,  name, spot, duration, date);
            events.add(event);
        }
        rs.close();
        stmt.close();
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return events;


        */

    }       

    public void supprimer(int selectedId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



 
}

  

    

