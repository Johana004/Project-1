
package Model;

import Controller.Validations;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class NascentDAO {
    
    public NascentDAO() {
    }

    /*Creates a new nascent record in the database.
     The nascent object containing user information to be inserted.*/
    public void create(Nascent nascent) {

        DBConnection db = new DBConnection();
        String consultaSQL = "INSERT INTO nascents (name, address, latitude,length, description, province_id, canton_id, district_id, entity_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setString(1, nascent.getName());
            ps.setString(2, nascent.getAddress());
            ps.setDouble(3, nascent.getLatitude());
            ps.setDouble(4, nascent.getLength());
            ps.setString(5, nascent.getDescription());
            ps.setInt(6, nascent.getProvince_id());
            ps.setInt(7, nascent.getCanton_id());
            ps.setInt(8, nascent.getDistrict_id());
            ps.setInt(9, nascent.getEntity_id());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Se insertó correctamente la naciente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se insertó correctamente la naciente, error: " + e.toString());
        } finally {
            db.disconnect();
        }
    }
     //Retrieves a list of Nascent objects from the database.
    // A list of Nascent objects containing information about nascents.
    public List<Nascent> read() {

        DBConnection db = new DBConnection();
        List<Nascent> nascents = new ArrayList<>();
        String sql = "SELECT * FROM nascents";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                Double latitude = resultSet.getDouble("latitude");
                Double length = resultSet.getDouble("length");
                String description = resultSet.getString("description");
                int provinceID = resultSet.getInt("province_id");
                int cantonID = resultSet.getInt("canton_id");
                int districtID = resultSet.getInt("district_id");
                int entityID = resultSet.getInt("entity_id");
                nascents.add(new Nascent(id, name, address, latitude, length, description, provinceID, cantonID, districtID, entityID));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return nascents;
    }

    /*Retrieves a list of Nascent objects associated with a specific entity ID from the database.
      entity_id The entity ID to filter Nascent objects by.
      A list of Nascent objects associated with the specified entity ID.*/
    public List<Nascent> readAdmin(int entity_id) {
        DBConnection db = new DBConnection();
        List<Nascent> nascents = new ArrayList<>();
        String sql = "SELECT * FROM nascents WHERE entity_id = ?";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ps.setInt(1, entity_id);  // Establece el entity_id del usuario que ha iniciado sesión
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                Double latitude = resultSet.getDouble("latitude");
                Double length = resultSet.getDouble("length");
                String description = resultSet.getString("description");
                int provinceID = resultSet.getInt("province_id");
                int cantonID = resultSet.getInt("canton_id");
                int districtID = resultSet.getInt("district_id");
                int entityID = resultSet.getInt("entity_id");
                nascents.add(new Nascent(id,name,address,latitude,length,description ,provinceID,cantonID,districtID,entityID ));
           
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return nascents;
    }
    //Updates nascent information in the database based on the provided nascent object.
    public void update(Nascent nascent) {

        DBConnection db = new DBConnection();
        String consultaSQL = "UPDATE nascents SET name=?, address=? ,latitude=?, length=? , description=?, province_id=?, canton_id=?, district_id=?, entity_id=? WHERE id=?";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setString(1, nascent.getName());
            ps.setString(2, nascent.getAddress());
            ps.setDouble(3, nascent.getLatitude());
            ps.setDouble(4, nascent.getLength());
            ps.setString(5, nascent.getDescription());
            ps.setInt(6, nascent.getProvince_id());
            ps.setInt(7, nascent.getCanton_id());
            ps.setInt(8, nascent.getDistrict_id());
            ps.setInt(9, nascent.getEntity_id());
            ps.setInt(10, nascent.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Modificación Exitosa");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se modificó, error:" + e.toString());
        }finally {
            db.disconnect();
        }
        
    }
     //Deletes a nascent from the database based on the provided nascent ID.
     public void delete(int id) {

        DBConnection db = new DBConnection();

        String consultaSQL = "DELETE FROM nascents WHERE id=?";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setInt(1, id);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Se eliminó correctamente la naciente");

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "No se pudo eliminar, error: " + e.toString());
        }finally {
            db.disconnect();
        } 
    }
     
     //Reorganizes user IDs in the database to ensure they are sequential starting from 1.
     public void reorganizarIDs() {
        DBConnection db = new DBConnection();

        // Consulta SQL para obtener todos los IDs de los estudiantes ordenados
        String consultaSQL = "SELECT id FROM nascents ORDER BY id";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(consultaSQL); ResultSet resultSet = preparedStatement.executeQuery())  {

            int nuevoID = 1;
            while (resultSet.next()) {
                int antiguoID = resultSet.getInt("id");
                if (nuevoID != antiguoID) {
                    try (PreparedStatement updateStatement = db.getConnection().prepareStatement("UPDATE nascents SET id = ? WHERE id = ?")) {
                        updateStatement.setInt(1, nuevoID);
                        updateStatement.setInt(2, antiguoID);
                        updateStatement.executeUpdate();
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "Error al actualizar el ID: " + e.toString());
                    }
                }
                nuevoID++;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener los IDs: " + e.toString());
        } finally {
            db.disconnect();
        }
    }
    
     //Retrieves the ID of a Nascent based on its name from the database.
     public int getIDNascent(String name) {
        int value = 0;
        DBConnection db = new DBConnection();
        String sql = "SELECT id FROM nascents WHERE name = ?";
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
    
     //Retrieves the name of a Nascent based on its ID from the database.
     public String getNameNascent(int id) {
        String value = "";
        DBConnection db = new DBConnection();
        String sql = "SELECT name FROM nascents WHERE id = ?";
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

     //Retrieves a list of Nascent names associated with a specific entity from the database.
    public List<String> getNacientesForEntity(String name) {
        List<String> Nascents = new ArrayList<>();
        DBConnection db = new DBConnection();

        // Realiza la consulta SQL para obtener las nacientes de la entidad
        String sql = "SELECT name FROM nascents WHERE entity_id = (SELECT id FROM entitys WHERE name = ?)";

        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Nascents.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener nacientes: " + e.toString());
        } finally {
            db.disconnect();
        }

        return Nascents;
    }
    
    ////Method to validate fields
     public boolean Validar(String name, String address, String latitude, String length, String description) {
        Validations validacion = new Validations();

       if (!validacion.validarLetras(address)) {
            JOptionPane.showMessageDialog(null, " El Direccion debe contener solo letras.");
            return false;
        }

        if (!validacion.validarLetras(name)) {
            JOptionPane.showMessageDialog(null, " El nombre debe contener solo letras.");
            return false;
        }
        if (!validacion.validarLetras(description)) {
            JOptionPane.showMessageDialog(null, " El descripcion debe contener solo letras.");
            return false;
        }
        if (!validacion.validarNumeros(latitude)) {
            JOptionPane.showMessageDialog(null, " El latitud debe contener solo numeros.");
            return false;
        }

        if (!validacion.validarNumeros(length)) {
            JOptionPane.showMessageDialog(null, " El longitud debe contener solo numeros.");
            return false;
        }
        return true;
     }
    public boolean ValidateEmptyFields(JTextField[] textFields) {
        for (JTextField textField : textFields) {
            if (textField.getText().trim().isEmpty()) {
                return true; 
            }
        }
        return false;
    }

}
