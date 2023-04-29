/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Commande;
import java.sql.SQLException;
import java.util.List;

/**
 *
 *  @author Bettaibi Walid
 *   @param <T>

 */

public interface InterfaceCrud<T> {
      void ajouter(T t) throws SQLException;
       void supprimerParId(int product_id )throws SQLException;

    void supprimer(String nom )throws SQLException;
  public void modifier(T t, int x);
    List<T> afficher() throws SQLException;
}
