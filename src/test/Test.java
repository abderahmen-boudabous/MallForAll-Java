/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Avis;
import entities.Reclamations;
import java.sql.SQLException;
import services.ServiceAvis;
import services.ServiceReclamation;

/**
 *
 * @author arafet
 */
public class Test {

    public static void main(String[] args) {
        try {
            Reclamations t  = new Reclamations(1,"verifier connextion le site","resevation" );
            ServiceReclamation ps = new ServiceReclamation();
            ps.ajouter(t);
           // ps.modifier(t);
            //ps.supprimer(t);
            //System.out.println(ps.recuperer());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
//        try {
//            Avis p = new Avis("bonnesite", 2.56f, 1);
//            ServiceAvis ps = new ServiceAvis();
//            ps.ajouter(p);
//            //ps.modifier(p);
//            //ps.supprimer(p);
//            //System.out.println(ps.recuperer(p));
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
    }

}
