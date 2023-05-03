package tn.esprit.services;


import java.util.Date;
import java.util.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.Query;
import tn.esprit.entities.Event;
import tn.esprit.entities.Category;
import tn.esprit.tools.MaConnexion;

public class EventService implements NewInterface<Event> {
    Connection cnx;
    String sql="";

    public EventService() throws SQLException {
        cnx=MaConnexion.getInstance().getCnx();
    } 

   /*     private Connection cnx;
    
    public EventService() {
        try {
            cnx = MaConnexion.getInstance().getCnx();
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    } */
    
    


/*
@Override
    public void ajouter(Event p) {
        sql = "INSERT INTO event(nom, spot , duration, date, category_id) VALUES (?, ?, ?, ?, ?) ";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, p.getNom());
            ste.setInt(2, p.getSpot());
            ste.setString(3, p.getDuration());
            ste.setDate(4, new java.sql.Date(p.getDate().getTime()));
            ste.setInt(5, p.getCategory().getId());

            ste.executeUpdate();
            System.out.println("event ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    } */
    
    
    @Override
    public void ajouter(Event p) {
        sql = "INSERT INTO event(nom, spot , duration, date, category_id) VALUES (?, ?, ?, ?, ?) ";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, p.getNom());
            ste.setInt(2, p.getSpot());
            ste.setString(3, p.getDuration());
            ste.setDate(4, new java.sql.Date(p.getDate().getTime()));
            ste.setInt(5, p.getCategory().getId());

            ste.executeUpdate();
            System.out.println("event ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    

@Override
public List<Event> afficher() {
    List<Event> eventList = new ArrayList<>();
    try {
        String req = "SELECT e.*, c.titre AS category_titre FROM Event e JOIN Category c ON e.category_id = c.id ";

        Statement st = cnx.createStatement();
        ResultSet res = st.executeQuery(req);
        while (res.next()) {
            Event event = new Event();
            //event.setId(res.getInt("id"));
            event.setNom(res.getString("nom"));
            event.setSpot(res.getInt("spot"));
            event.setDuration(res.getString("duration"));
            event.setDate(res.getDate("date"));
            Category category = new Category();
            category.setTitre(res.getString("category_titre"));
            event.setCategory(category);
            eventList.add(event);
        }
    } catch (SQLException ex) {
        Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
    }
    return eventList;
}




    
    
    public void supprimer(Event e) {
    String sql = "DELETE FROM event WHERE id = ?";
    try {
        PreparedStatement pstmt = cnx.prepareStatement(sql);
        pstmt.setInt(1, e.getId());
        int rowsDeleted = pstmt.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Category supprimé !");
        } else {
            System.out.println("Category introuvable !");
        }
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
    }    
        
       
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

    }       

    
    public void supprimer(int selectedId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
    public void updateSpotCountInDatabase(int eventId, int newSpotCount, Connection connection) throws SQLException {
        String sql = "UPDATE event SET spot = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, newSpotCount);
            statement.setInt(2, eventId);
            statement.executeUpdate();
        }
    }



    public List<Event> getAllEvents() throws SQLException {
        List<Event> events = new ArrayList<>();
        String query = "SELECT * FROM events";
        PreparedStatement statement = cnx.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Event event = new Event();
            event.setId(resultSet.getInt("id"));
            event.setNom(resultSet.getString("nom"));
            event.setSpot(resultSet.getInt("spot"));
            event.setDuration(resultSet.getString("duration"));
            event.setDate(resultSet.getDate("date"));
            // You may also need to retrieve and set the category for each event
            events.add(event);
        }
        return events;
    }
    
    
 
}

  

    

