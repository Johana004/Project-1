
package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProvinceDAO {
    
    
    //Retrieves a list of Province objects from the database.
    public List<Province> read() {
        DBConnection db = new DBConnection();
        List<Province> provinces = new ArrayList<>();
        String sql = "SELECT * from provinces";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                provinces.add(new Province(id, name));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return provinces;
    }
    
    //Retrieves the ID of a province based on its name from the database.
    public int getIDProvinces(String name) {
        int value = 0;
        DBConnection db = new DBConnection();
        String sql = "SELECT id FROM provinces WHERE name = ?";
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
    
    //Retrieves the name of a province based on its ID from the database.
     public String getNameProvinces(int id) {
        String value = "";
        DBConnection db = new DBConnection();
        String sql = "SELECT name FROM provinces WHERE id = ?";
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

     //Obtains the ID of a province based on its name from the database.
    public String obtenerProvince(String selectedProvince) {
        // Realiza una consulta a la base de datos para obtener el ID de la provincia basado en el nombre
        String sql = "SELECT id FROM provinces WHERE name = ?";
        DBConnection db = new DBConnection();
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ps.setString(1, selectedProvince);
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
