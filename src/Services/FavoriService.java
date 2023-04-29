/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Favori;
import Utils.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bettaibi Walid
 */
public class FavoriService {
        Connection connexion;
    Statement stm;

    public FavoriService () {
        connexion = MaConnexion.getInstance().getCnx();
    }

    public void ajouter(Favori c) throws SQLException {
String req = "INSERT INTO favori (`product_id`) VALUES ( '"
                 + c.getProduct_id()+"') ";
 
        stm = connexion.createStatement();
        stm.executeUpdate(req);
        System.out.println("fav ajoutee !");    }

    public void supprimerParId(int product_id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void supprimer(int id) throws SQLException {
String req = "DELETE FROM favori WHERE `id`=?";
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("list favori supprimee !");    }

      public void modifier(Favori c, int x) {
try {
            String req ; 
            req = "UPDATE `favori` SET  `product_id`=?  WHERE id='"+x+"'";
            PreparedStatement ps = connexion.prepareStatement(req);
           // ps.setInt(1, l.getId());
            ps.setInt(1,c.getProduct_id());
            

            ps.executeUpdate();
            System.out.println("commande est modifié");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }
}


    public List<Favori> afficher() throws SQLException {
List<Favori> favoris = new ArrayList<>();
        String req = "select * from favori";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
           Favori c = new Favori(
                   rst.getInt("id"),
                   rst.getInt("product_id"));

            favoris.add(c);
        }
        return favoris;    }
    
    
          public List<Favori> RechercherFavori(Integer id ) {
     List<Favori> list = new ArrayList<>();
        String req = " SELECT * FROM favori WHERE i=d'"+id+"'";
        try {
            ResultSet rst = stm.executeQuery(req);
            stm = connexion.createStatement();
            rst = stm.executeQuery(req);
            while (rst.next()) {
              Favori l = new Favori();
                l.setId(rst.getInt(1));
               
                l.setProduct_id(rst.getInt("product_id"));
                                l.setNote(rst.getInt("note"));
                //
                list.add(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list; 
    }

    public List<Favori> filtrerListe(List<Favori> liste, String recherche) {
    List<Favori> listeFiltree = new ArrayList<>();
    for (Favori favori : liste) {
        
            listeFiltree.add(favori);
            //System.out.println("filtrage");
        
    }
    return listeFiltree;
}

    


}
