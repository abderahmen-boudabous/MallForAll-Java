package tn.esprit.services;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import javax.activation.DataSource;
import tn.esprit.entities.Event;
import tn.esprit.entities.Shop;
import tn.esprit.entities.Participant;
import tn.esprit.tools.MaConnexion;

public class ParticipantService {
    

    
        private final Connection cnx;

    public ParticipantService() throws SQLException {
        this.cnx=MaConnexion.getInstance().getCnx();;
    }

    
    
    
public List<Event> getAllEventNames() throws SQLException {
    List<Event> eventNames = new ArrayList<>();
    String query = "SELECT nom,id FROM event";
    PreparedStatement statement = cnx.prepareStatement(query);
    ResultSet resultSet = statement.executeQuery();
    while (resultSet.next()) {
        Event p=new Event(resultSet.getString("nom"), resultSet.getInt("id"));
        eventNames.add(p);
    }
    statement.close();
    resultSet.close();
    return eventNames;
    
}  
    
    
/*
public void addParticipantToEvent(String name, Participant participant) throws SQLException {
    if (!checkShopName(name)) {
        System.out.println("Error: Shop with name " + name + " does not exist.");
        return;
    }
    else {
        String query = "INSERT INTO participants (seller_name, event_id) VALUES (?, ?)";
        PreparedStatement statement = cnx.prepareStatement(query);
        statement.setString(1, participant.getSellerName());
        statement.setInt(2, participant.getEvent().getId());
        statement.executeUpdate();
        statement.close();

        // Update the spot count for the event
        String updateQuery = "UPDATE event SET spot = spot - 1 WHERE id = ?";
        PreparedStatement updateStatement = cnx.prepareStatement(updateQuery);
        updateStatement.setInt(1, participant.getEvent().getId());
        updateStatement.executeUpdate();
        updateStatement.close();

        System.out.println("Participant added to event!");
    }
}
*/

public void addParticipantToEvent(Participant participant) throws SQLException {
    String query = "INSERT INTO participants (seller_name, event_id) VALUES (?, ?)";
    PreparedStatement statement = cnx.prepareStatement(query);
    statement.setString(1, participant.getSellerName());
    statement.setInt(2, participant.getEvent().getId());
    statement.executeUpdate();
    statement.close();
    
    // Update the spot count for the event
    String updateQuery = "UPDATE event SET spot = spot - 1 WHERE id = ?";
    PreparedStatement updateStatement = cnx.prepareStatement(updateQuery);
    updateStatement.setInt(1, participant.getEvent().getId());
    updateStatement.executeUpdate();
    updateStatement.close();
    
    System.out.println("Participant added to event!");
}  




public List<Map<String, Object>> getParticipantsWithEventNames() {
    List<Map<String, Object>> participantList = new ArrayList<>();
    try {
        String req = "SELECT p.id, e.nom AS event_name, p.seller_name FROM participants p JOIN event e ON p.event_id = e.id";
        Statement st = cnx.createStatement();
        ResultSet res = st.executeQuery(req);
        while (res.next()) {
            Map<String, Object> participant = new HashMap<>();
            participant.put("id", res.getInt("id"));
            participant.put("event_name", res.getString("event_name"));
            participant.put("seller_name", res.getString("seller_name"));
            participantList.add(participant);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ParticipantService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return participantList;
}









public boolean checkShopName(String name) {
    boolean exists = false;
    try {
        String query = "SELECT * FROM Shop WHERE name = ?";
        PreparedStatement ps = cnx.prepareStatement(query);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            exists = true;
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return exists;
}




public List<Participant> getAllParticipants() {
    List<Participant> participants = new ArrayList<>();
    try {
        String query = "SELECT seller_name, event_id FROM Participants ";
        PreparedStatement ps = cnx.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Event event = new Event();
            event.setId(rs.getInt("event_id"));
            String sellerName = rs.getString("seller_name");
            Participant participant = new Participant(event, sellerName);
            participants.add(participant);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return participants;
}








    
    
}
