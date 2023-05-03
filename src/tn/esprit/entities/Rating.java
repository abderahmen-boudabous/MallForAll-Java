
package tn.esprit.entities;

public class Rating {
    private int id;
    private Event event;
    private int rating;

    public Rating() {
    }

    public Rating(int id, Event event, int rating) {
        this.id = id;
        this.event = event;
        this.rating = rating;
    }
    
    public Rating(Event event, int rating) {
    this.event = event;
    this.rating = rating;
}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
