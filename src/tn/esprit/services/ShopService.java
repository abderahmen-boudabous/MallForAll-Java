package tn.esprit.services;

import tn.esprit.entities.Shop;
import tn.esprit.tools.MaConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShopService implements NewInterface<Shop> {

    Connection cnx;
    String sql="";

    public ShopService() {
        cnx= MaConnexion.getInstance().getCnx();
    }

    @Override
    public void ajouter(Shop s) {
        sql = "INSERT INTO shop(name, description, email, user_s, date, img) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, s.getName());
            ste.setString(2, s.getDescription());
            ste.setString(3, s.getEmail());
            ste.setString(4, s.getUser_s());
            ste.setDate(5, s.getDate());
            ste.setString(6, s.getImg());

            ste.executeUpdate();
            System.out.println("Shop ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void updateLike(int shopId, int newLikeValue) {
    String sql = "UPDATE shop SET `like`=? WHERE id=?";
    System.out.println("SQL: " + sql);

    try {
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setInt(1, newLikeValue);
        ps.setInt(2, shopId);
        ps.executeUpdate();
        System.out.println("Like updated successfully!");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

public void updateDislike(int shopId, int newDislikeValue) {
    String sql = "UPDATE shop SET `dislike`=? WHERE id=?";

    try {
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setInt(1, newDislikeValue);
        ps.setInt(2, shopId);
        ps.executeUpdate();
        System.out.println("Dislike updated successfully!");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

    
    
   @Override
public List<Shop> afficher() {
    List<Shop> shops = new ArrayList<>();
    sql = "SELECT * FROM shop";
    try {
        Statement ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery(sql);
        while (rs.next()) {
            Shop s = new Shop(rs.getString("name"), rs.getString("description"),
                    rs.getString("email"), rs.getString("user_s"), rs.getDate("date"), rs.getString("img"),
                    rs.getInt("like"), rs.getInt("dislike"));
            shops.add(s);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return shops;
}


    @Override
    public void supprimer(Shop s) {
        sql="delete from shop where id="+s.getId();
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Shop supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //@Override
    public void update(Shop s) {
        sql = "update shop set name=?, description=?, email=?, user_s=?, date=? where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, s.getName());
            ste.setString(2, s.getDescription());
            ste.setString(3, s.getEmail());
            ste.setString(4, s.getUser_s());
            ste.setDate(5, s.getDate());
            ste.setInt(6, s.getId());

            int rowsUpdated = ste.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Shop mis à jour !");
            } else {
                System.out.println("Erreur : La mise à jour a échoué.");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Shop findById(int id) {
    Shop shop = null;
    sql = "SELECT * FROM shop WHERE id = ?";
    try {
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setInt(1, id);
        ResultSet rs = ste.executeQuery();
        if (rs.next()) {
            shop = new Shop(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getString("email"),
                            rs.getString("user_s"),
                            rs.getDate("date"));
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return shop;
}

    

}
