/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.util.List;
import tn.esprit.entities.Shop;

/**
 *
 * @author Fayechi
 */
public interface NewInterface<T> {
    public void ajouter(T p);
    public List<T> afficher();
    public void supprimer(T p);
    //public void ajouter(Shop s, String imagePath);
    
}
