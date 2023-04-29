/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Avis;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author arafet
 */
public class ServiceAvis implements IService<Avis> {
    Connection cnx;

    public ServiceAvis() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(Avis t) throws SQLException {
         String req = "INSERT INTO avis(text_avis,rate_avis,id_utilisateur) VALUES('"
               + t.getText_avis()+ "','" + t.getRate_avis()+  "'," + t.getId_utilisateur() + ")";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
       
    }

    @Override
//    public void modifier(Avis t) throws SQLException {String req = "UPDATE avis SET text_avis = ?,  rate_avis = ?where id_avis = ?";
//        PreparedStatement ps = cnx.prepareStatement(req);
//        ps.setString(1, t.getText_avis());
//        ps.setFloat(2, t.getRate_avis());
//        ps.setInt(3, t.getId_avis());
//        ps.executeUpdate();
//       
//    }
    
    public void modifier(Avis t) throws SQLException {
        String req = "UPDATE avis SET text_avis = ?,  rate_avis =? where id_avis=?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getText_avis());
        ps.setFloat(2, t.getRate_avis());
        ps.setInt(3, t.getId_avis());

        ps.executeUpdate();
       
    }

    @Override
    public boolean supprimer(Avis t) throws SQLException {
        boolean ok = false;
        try {
            String req = "DELETE FROM avis WHERE id_avis = "+t.getId_avis();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        ok = true;
        } catch (SQLException ex) {
            System.out.println("error in delete " + ex);
        }
        return ok;
    }

    @Override
    public List<Avis> recuperer() throws SQLException {
         List<Avis> avis = new ArrayList<>();
        String s = "select * from avis ";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Avis i = new Avis();
            i.setId_avis(rs.getInt("id_avis"));
            i.setText_avis(rs.getString("Text_avis" ));
            i.setDate_avis(rs.getString("date_avis"));
            i.setRate_avis(rs.getFloat("rate_avis"));
            i.setId_utilisateur(rs.getInt("id_utilisateur"));        
            avis.add(i);    
        }
        return avis;
        
    }

//    public void modifier(float parseFloat, String text) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
}
