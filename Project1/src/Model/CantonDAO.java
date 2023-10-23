package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CantonDAO {

    public CantonDAO() {
    }

    //Retrieves a list of canton objects from the database.
    public List<Canton> read(String provinceId) {
        DBConnection db = new DBConnection();
        List<Canton> cantons = new ArrayList<>();
        String sql = "SELECT id, name, province_id FROM cantons WHERE province_id = ?";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ps.setString(1, provinceId);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String provinceIdFromDB = resultSet.getString("province_id");
                cantons.add(new Canton(id, name, provinceIdFromDB));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return cantons;
    }


    
    ////Retrieves the ID of a canton based on its name from the database.
    public int getIDCanton(String name) {
        int value = 0;
        DBConnection db = new DBConnection();
        String sql = "SELECT id FROM cantons WHERE name = ?";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                value = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return value;
    }
    
    //Retrieves the name of a canton based on its ID from the database.
     public String getNameCanton(int id) {
        String value = "";
        DBConnection db = new DBConnection();
        String sql = "SELECT name FROM cantons WHERE id = ?";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                value = resultSet.getString("name");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return value;
    }

     ////Obtains the ID of a canton based on its name from the database.
   public String obtenerCanton(String selectedCanton) {
        // Realiza una consulta a la base de datos para obtener el ID de la provincia basado en el nombre
        String sql = "SELECT id FROM cantons WHERE name = ?";
        DBConnection db = new DBConnection();
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ps.setString(1, selectedCanton);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Devuelve null si no se encontró el ID
    }
    
}
