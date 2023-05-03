package tn.esprit.services;

import java.util.List;
import tn.esprit.entities.Category;

public interface NewInterface<T> {
    //public void ajouter(T t);
    public List<T> afficher();
   // public void supprimer(T t);

    void ajouter(T object);

   // public List<Category> lister();


}
