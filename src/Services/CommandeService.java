/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Commande;
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
public class CommandeService implements InterfaceCrud<Commande> {
    Connection connexion;
    Statement stm;

    public CommandeService () {
        connexion = MaConnexion.getInstance().getCnx();
    }

    @Override
    public void ajouter(Commande c) throws SQLException {
        // Vérification de la validité de la quantité
    if(c.getQuantite() < 1) {
        throw new IllegalArgumentException("La quantité doit être supérieure à 0");
    }

String req = "INSERT INTO commande (`nom`,`adresse`,`quantite`,`telephone`,`product_id`,`note`) VALUES ( '"
                +c.getNom()+ "', '" + c.getAdresse()+ "', '" + c.getQuantite()+ "','"+c.getTelephone() +"','" + c.getProduct_id()+"','"+c.getNote() +"') ";
 
        stm = connexion.createStatement();
        stm.executeUpdate(req);
        System.out.println("Commande ajoutee !");    }

    @Override
    public void supprimerParId(int product_id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(String nom) throws SQLException {
String req = "DELETE FROM commande WHERE `nom`=?";
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setString(1,nom);
            ps.executeUpdate();
            System.out.println("Commande supprimee !");    }

      public void modifier(Commande c, int x) {
try {
            String req ; 
            req = "UPDATE `commande` SET  `nom`=?,`adresse`=?,`quantite`=?, `telephone`=? WHERE id='"+x+"'";
            PreparedStatement ps = connexion.prepareStatement(req);
           // ps.setInt(1, l.getId());
            ps.setString(1,c.getNom());
            ps.setString(2, c.getAdresse());
            ps.setInt(3, c.getQuantite());
            ps.setInt(4, c.getTelephone());
            

            ps.executeUpdate();
            System.out.println("commande est modifié");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }
}


    @Override
    public List<Commande> afficher() throws SQLException {
List<Commande> commandes = new ArrayList<>();
        String req = "select * from commande";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
           Commande c = new Commande(
                   rst.getInt("id"),
                   rst.getInt("product_id"),
                   rst.getString("nom"),
                   rst.getString("adresse"),
                   rst.getInt("quantite"),
                   rst.getInt("telephone"));
                   rst.getInt("note");

            commandes.add(c);
        }
        return commandes;    }

      public List<Commande> RechercherCommande(String nom ) {
     List<Commande> list = new ArrayList<>();
        String req = " SELECT * FROM commande WHERE nom='"+nom+"'";
        try {
            ResultSet rst = stm.executeQuery(req);
            stm = connexion.createStatement();
            rst = stm.executeQuery(req);
            while (rst.next()) {
              Commande l = new Commande();
                l.setId(rst.getInt(1));
                l.setNom(rst.getString("nom"));
                l.setAdresse(rst.getString("adresse"));
                l.setQuantite(rst.getInt("quantite"));
                l.setTelephone(rst.getInt("telephone"));
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
      public List<Commande> filtrerListe(List<Commande> liste, String recherche) {
    List<Commande> listeFiltree = new ArrayList<>();
    for (Commande commande : liste) {
        if (commande.getNom().contains(recherche)) {
            listeFiltree.add(commande);
            //System.out.println("filtrage");
        }
    }
    return listeFiltree;
}

}
