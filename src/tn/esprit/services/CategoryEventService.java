package tn.esprit.services;

import tn.esprit.entities.Category;
import tn.esprit.tools.MaConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.management.Query;

public class CategoryEventService implements NewInterface<Category> {


        Connection cnx;
    String sql="";

    public CategoryEventService() {
        cnx= MaConnexion.getInstance().getCnx();
    }
    
    
    public List<Category> listerCategories() {
        List<Category> categories = new ArrayList<>();
        sql = "SELECT * FROM category";
        try {
            Statement stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Category c = new Category();
                c.setId(rs.getInt("id"));
                c.setTitre(rs.getString("titre"));
                c.setDescription(rs.getString("description"));
                categories.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return categories;
    }

    
    public void ajouter(Category c) {
        sql = "INSERT INTO category(titre, description) VALUES (?, ?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, c.getTitre());
            ste.setString(2, c.getDescription());

            ste.executeUpdate();
            System.out.println("Category ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Category> afficher() {
        List<Category> categories = new ArrayList<>();
        sql="select * from category";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs=ste.executeQuery(sql);
            while(rs.next()){
                Category c = new Category(rs.getInt("id"),
                        rs.getString("titre"),
                        rs.getString("description"));
                categories.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return categories;
    }

    @Override
    public void supprimer(Category c) {
        sql="delete from category where id="+c.getId();
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Category supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    } 
    


     public void update(Category c) {
        sql = "update category set titre=?, description=? where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, c.getTitre());
            ste.setString(2, c.getDescription());
            ste.setInt(3, c.getId());

            int rowsUpdated = ste.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Category mis à jour !");
            } else {
                System.out.println("Erreur : La mise à jour a échoué.");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    
    
}
