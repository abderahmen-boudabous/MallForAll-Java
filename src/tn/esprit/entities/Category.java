package tn.esprit.entities;



public class Category {
    private int id;
    private String titre;
    private String description;
    // Other properties as needed

    public Category() {
    }

    public Category(int id, String titre, String description) {
        this.id = id;
        this.titre = titre;
        this.description = description;
    }
    
        public Category(String titre, String description) {
        this.titre = titre;
        this.description = description;
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
    
        public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Other getters and setters as needed

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", titre='" + titre +
                ", description'" + description + '\'' +
                '}';
    }
}
