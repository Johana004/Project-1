package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DistrictDAO {

    //Retrieves a list of District objects from the database.
    public List<District> read1() {
        DBConnection db = new DBConnection();
        List<District> Districts = new ArrayList<>();
        String sql = "SELECT * from districts";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int cantonId = resultSet.getInt("canton_id");
                Districts.add(new District(id, name, cantonId));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return Districts;
    }

    //Retrieves a list of District objects associated with a specific Canton ID from the database.
    public List<District> read(String CantonID) {
        DBConnection db = new DBConnection();
        List<District> Districts = new ArrayList<>();
        String sql = "SELECT id, name, canton_id FROM districts WHERE canton_id = ?";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);

            // Establece el valor del parámetro CantonID
            ps.setString(1, CantonID);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int canton_id = resultSet.getInt("canton_id");

                Districts.add(new District(id, name, canton_id));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return Districts;
    }

    //////Retrieves the ID of a district based on its name from the database.
    public int getIDDistricts(String name) {
        int value = 0;
        DBConnection db = new DBConnection();
        String sql = "SELECT id FROM districts WHERE name = ?";
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
    
     //Retrieves the name of a district based on its ID from the database.
     public String getNameDistricts(int id) {
        String value = "";
        DBConnection db = new DBConnection();
        String sql = "SELECT name FROM districts WHERE id = ?";
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

}
