
package tn.esprit.tests;

import java.util.List;
import static javafx.application.Application.launch;
import tn.esprit.entities.Category;
import tn.esprit.entities.Event;
import tn.esprit.entities.Personne;
import tn.esprit.services.EventService;
import tn.esprit.services.CategoryEventService;
import tn.esprit.tools.MaConnexion;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;






public class main {
    public static void main(String[] args) {

              // launch(args);
/*
    CategoryEventService categoryService = new CategoryEventService();
    Category category1 = new Category("Category 1", "Category 1 description");
    Category category2 = new Category("Category 2", "Category 2 description");

    categoryService.ajouter(category1);
    categoryService.ajouter(category2);

    List<Category> categories = categoryService.afficher();
    for (Category category : categories) {
        System.out.println(category.getId() + " - " + category.getTitre() + " - " + category.getDescription());
    }
*/
        
        
 /*

    EventService EventService = new EventService();
    List<Event> events = EventService.afficher();
    for (Event event : events) {
       System.out.println(event);
   }
  
*/
       //-------------- tri -------- ----------------------

/*
EventService service = new EventService();

// Display all events sorted by date
List<Event> sortedEvents = service.trierParDate();
for (Event e : sortedEvents) {
    System.out.println(e);
}
*/

       //--------------- delete ---------------------
/*
// create an instance of Event with an existing id
Event eventToDelete = new Event();
eventToDelete.setId(135); 

// create an instance of EventService
EventService eventService = new EventService();

eventService.supprimer(eventToDelete);

List<Event> events = eventService.afficher();
for (Event e : events) {
    System.out.println(e);
}
*/



    //   --------------------- ajoute ----------------------------- 
    
    
/*    
            EventService eventService = new EventService();

            // create a new Category object and add it to the database


            // create a new Event object and set its attributes
            Event event = new Event();
           // event.getCategory().setId(13);
            event.setNom("My Event");
            event.setSpot(22);
            event.setDuration("2 hours");
            //event.setDate(new Date());

            // add the event using the event service
            eventService.ajouter(event);

            // display the list of events
            List<Event> events = eventService.afficher();
            for (Event e : events) {
                System.out.println(e);
            }
   */     
    
    
    
    //___________________________________ _ Category Event _ ___________________________________________________________//    

       
    /*
   

    CategoryEventService categoryService = new CategoryEventService();
    
    // create a new category
    Category category = new Category("Category 13", "Description of category 1");
    //categoryService.ajouter(category);
    
    // display all categories
   
   
    List<Category> categories = categoryService.afficher();
    for (Category c : categories) {
        System.out.println(c);
    }
    
    // delete the category
   /* 
    CategoryEventService CategoryEventService = new CategoryEventService();

        category.setId(47);
        CategoryEventService.supprimer(category);

 
  
        // update the category
    Category updatedCategory = new Category(48, "Category 1 (updated)", "Description 1 (updated)");
    categoryService.update(updatedCategory);
*/
     
        
    }
}
