package entities;

public class Rec {
    private int id;
        private int id_utilisateur;
    private String sujet;
    private String contenu;
    private String user_r;
    private String email;
    private String reponse;
    private String etat ;
    private String date;

    private RecT rect;

    public Rec() {
    }

    public Rec(int id, String sujet, String contenu, String user_r, String email,RecT rect) {
        this.id = id;
        this.sujet = sujet;
        this.contenu = contenu;
        this.user_r = user_r;
        this.email = email;
        this.rect = rect;
    }
        public Rec( String reponse, String etat,int id) {
       
        this.reponse = reponse;
        this.etat = etat;
         this.id = id;
    }
       public Rec(int id, String sujet, String contenu, String user_r,String reponse, String email,RecT rect) {
        this.id = id;
        this.sujet = sujet;
        this.contenu = contenu;
        this.user_r = user_r;
        this.reponse = reponse;
        this.email = email;
        this.rect = rect;
    }
    
    public Rec(int id, String sujet, String contenu, String user_r, String reponse) {
        this.id = id;
        this.sujet = sujet;
        this.contenu = contenu;
        this.user_r = user_r;
        this.reponse = reponse;
        
    }
    
    public Rec(String sujet, String contenu, String user_r, String email,RecT rect) {
        this.sujet = sujet;
        this.contenu = contenu;
        this.user_r = user_r;
        this.email = email;
        this.rect = rect;
    }
   
    
    
        public Rec(String sujet, String contenu, String user_r, String reponse) {
        this.sujet = sujet;
        this.contenu = contenu;
        this.user_r = user_r;
           this.reponse = reponse;
        this.email = email;
     
    }

   public Rec(int id_utilisateur, String sujet, String contenu) {
        this.id_utilisateur = id_utilisateur;
        this.sujet = sujet;
        this.contenu = contenu;
    } 

   

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    public int getId_utilisateur() {
        return id_utilisateur;
    }

    /**
     * @param id the id to set
     */
    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }
    /**
     * @return the sujet
     */
    public String getSujet() {
        return sujet;
    }

    /**
     * @param sujet the sujet to set
     */
    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    /**
     * @return the contenu
     */
    public String getContenu() {
        return contenu;
    }

    /**
     * @param contenu the contenu to set
     */
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    /**
     * @return the user_r
     */
    public String getUser_r() {
        return user_r;
    }

    /**
     * @param user_r the user_r to set
     */
    public void setUser_r(String user_r) {
        this.user_r = user_r;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the reponse
     */
    public String getReponse() {
        return reponse;
    }

    /**
     * @param reponse the reponse to set
     */
    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    /**
     * @return the etat
     */
    public String getEtat() {
        return etat;
    }

    /**
     * @param etat the etat to set
     */
    public void setEtat(String etat) {
        this.etat = etat;
    }

        public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }
    /**
     * @return the rect
     */
    public RecT getRect() {
        return rect;
    }

    /**
     * @param rect the rect to set
     */
    public void setRect(RecT RecT) {
        this.rect = RecT;
    }
    

            @Override
    public String toString() {
        return "Rec{" +
                "id=" + getId() +
                ", sujet='" + sujet + '\'' +
                ", contenu=" + contenu +
                  ", id_utilisateur=" + id_utilisateur +

                ", user_r='" + user_r + '\'' +
               
                ", reponse=" + reponse +
                 ", email=" + email +
                  ", date=" + date +
                ", rect='" + rect +
                '}';
    }
}
