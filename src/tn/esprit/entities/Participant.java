package tn.esprit.entities;

public class Participant {
    private int id;
    private Event event;
    private String sellerName;

    public Participant() {}

    public Participant(int id, Event event, String sellerName) {
        this.id = id;
        this.event = event;
        this.sellerName = sellerName;
    }

    public Participant(Event event, String sellerName) {
        this.event = event;
        this.sellerName = sellerName;
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

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", event=" + event +
                ", sellerName='" + sellerName + '\'' +
                '}';
    }
}
