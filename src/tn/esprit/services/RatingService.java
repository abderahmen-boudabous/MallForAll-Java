package tn.esprit.services;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.entities.Event;
import tn.esprit.entities.Rating;
import tn.esprit.tools.MaConnexion;



public class RatingService {
    private final Connection conn;

    public RatingService() throws SQLException {
        this.conn=MaConnexion.getInstance().getCnx();;
    }





    
   
public List<Event> getAllEventNames() throws SQLException {
    List<Event> eventNames = new ArrayList<>();
    String query = "SELECT nom,id FROM event";
    PreparedStatement statement = conn.prepareStatement(query);
    ResultSet resultSet = statement.executeQuery();
    while (resultSet.next()) {
        Event p=new Event(resultSet.getString("nom"), resultSet.getInt("id"));
        eventNames.add(p);
    }
    statement.close();
    resultSet.close();
    return eventNames;
    
}  



    
   

    public void addRating(Rating rating) throws SQLException {
        String query = "INSERT INTO rating (event_id, rating) VALUES (?, ?)";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, rating.getEvent().getId());
        statement.setInt(2, rating.getRating());
        statement.executeUpdate();
        statement.close();
} 

    public double getAverageRating(Event event) throws SQLException {
        String query = "SELECT AVG(rating) FROM rating WHERE event_id = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, event.getId());
        ResultSet resultSet = statement.executeQuery();
        double averageRating = 0;
        if (resultSet.next()) {
            averageRating = resultSet.getDouble(1);
        }
        statement.close();
        resultSet.close();
        return averageRating;
    }
}
