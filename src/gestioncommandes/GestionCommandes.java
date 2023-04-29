/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestioncommandes;

import Entity.Commande;
import Entity.Favori;
import Services.CommandeService;
import Services.FavoriService;
import java.sql.SQLException;

/**
 *
 * @author Bettaibi Walid
 */
public class GestionCommandes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        

        
        
        //CommandeService cs = new CommandeService();
        //Commande c = new Commande(1,17,"xxxxxxx","xxxx", 5, 3431, 4);
        //cs.ajouter(c);
        //System.out.println(cs.afficher());
        //cs.supprimer("dlai");
        //cs.modifier(c,24);
        
        
        
        
        FavoriService fs = new FavoriService();
        Favori f = new Favori(50,17);
        fs.ajouter(f);
        //fs.supprimer("1");
        //fs.modifier(f,24);
        // fs.supprimer(26);
        //System.out.println(fs.afficher());
        
       
    }
    
}
