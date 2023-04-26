package tn.esprit.services;

import tn.esprit.entities.Product;
import tn.esprit.tools.MaConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.entities.Shop;

public class ProductService implements NewInterface<Product> {

    Connection cnx;
    String sql="";

    public ProductService() {
        cnx= MaConnexion.getInstance().getCnx();
    }

    @Override
    public void ajouter(Product p) {
        sql = "INSERT INTO product(name, price, type, stock, shop_id, photo) VALUES (?, ?, ?, ?, ?, ?) ";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, p.getName());
            ste.setFloat(2, p.getPrice());
            ste.setString(3, p.getType());
            ste.setInt(4, p.getStock());
            ste.setInt(5, p.getShop().getId());
            ste.setString(6, p.getPhoto());
            

            ste.executeUpdate();
            System.out.println("Product ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Product> afficher() {
        List<Product> products = new ArrayList<>();
        sql="select * from product";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs=ste.executeQuery(sql);
            while(rs.next()){
                Product p = new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getFloat("price"),
                        rs.getString("type"),
                        rs.getInt("stock"));
                products.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return products;
    }
    
    
    public List<Product> afficherr() {
    List<Product> productList = new ArrayList<>();
    try {
        String req = "SELECT p.*, s.name AS shop_name FROM Product p JOIN Shop s ON p.shop_id = s.id";
        Statement st = cnx.createStatement();
        ResultSet res = st.executeQuery(req);
        while (res.next()) {
            Product product = new Product();
            product.setId(res.getInt("id"));
            product.setName(res.getString("name"));
            product.setPrice(res.getFloat("price"));
            product.setType(res.getString("type"));
            product.setStock(res.getInt("stock"));
            Shop shop = new Shop();
            shop.setId(res.getInt("shop_id"));
            shop.setName(res.getString("shop_name"));
            product.setShop(shop);
            productList.add(product);
        }
    } catch (SQLException ex) {
        Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
    }
    return productList;
}

    @Override
    public void supprimer(Product p) {
        sql="delete from product where id="+p.getId();
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Product supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //@Override
    public void update(Product p) {
        sql = "update product set name=?, price=?, type=?, stock=? , shop_id= ? where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, p.getName());
            ste.setFloat(2, p.getPrice());
            ste.setString(3, p.getType());
            ste.setInt(4, p.getStock());
            ste.setInt(5, p.getShop().getId());
            ste.setInt(6, p.getId());
            

            int rowsUpdated = ste.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Product mis à jour !");
            } else {
                System.out.println("Erreur : La mise à jour a échoué.");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Product findById(int id) {
        Product product = null;
        sql = "SELECT * FROM product WHERE id = ?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);
            ResultSet rs = ste.executeQuery();
            if (rs.next()) {
                product = new Product(rs.getInt("id"),
                                rs.getString("name"),
                                rs.getFloat("price"),
                                rs.getString("type"),
                                rs.getInt("stock"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return product;
    }

}
