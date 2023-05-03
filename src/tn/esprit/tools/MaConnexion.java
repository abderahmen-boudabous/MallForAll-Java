package tn.esprit.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MaConnexion {
private Connection cnx;
    public final String url ="jdbc:mysql://localhost:3306/mall";
    public final String user="root";
    public final String pwd ="";
    public static MaConnexion ct;
    private MaConnexion() {
        try {
            cnx = DriverManager.getConnection(url, user, pwd);
            System.out.println("Connexion etablie !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }       
    }
    public static MaConnexion getInstance(){
        if(ct==null){
            ct = new MaConnexion();
        }
        return ct;
    }

   /* public Connection getCnx() throws SQLException {
        return DriverManager.getConnection(url, user, pwd);
    }   */
    public Connection getCnx() {
        return cnx;
    }    
        public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPwd() {
        return pwd;
    }
}
