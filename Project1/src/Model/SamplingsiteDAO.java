
package Model;

import Controller.Validations;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import Model.Samplingsite;
import javax.swing.JTextField;

public class SamplingsiteDAO {
    
    public SamplingsiteDAO(){
    }
  
    public void create(Samplingsite Samplingsite) {

        DBConnection db = new DBConnection();
        String consultaSQL = "INSERT INTO sampling_sites (name, province_id, canton_id, district_id, entity_id) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setString(1, Samplingsite.getName());
            ps.setInt(2, Samplingsite.getProvince_id());
            ps.setInt(3, Samplingsite.getCanton_id());
            ps.setInt(4, Samplingsite.getDistrict_id());
            ps.setInt(5, Samplingsite.getEntity_id());

            ps.execute();
            JOptionPane.showMessageDialog(null, "Se insertó correctamente el sitio de muestreo");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se insertó correctamente el sitio de muestreo, error: " + e.toString());
        } finally {
            db.disconnect();
        }
    }

    public List<Samplingsite> read() {

        DBConnection db = new DBConnection();
        List<Samplingsite> Samplingsites = new ArrayList<>();
        String sql = "SELECT * FROM sampling_sites";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int province_id = resultSet.getInt("province_id");
                int canton_id = resultSet.getInt("canton_id");
                int district_id = resultSet.getInt("district_id");
                int entity_id = resultSet.getInt("entity_id");
                Samplingsites.add(new Samplingsite(id, name, province_id,canton_id,district_id, entity_id));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return Samplingsites;
    }
    
    public List<Samplingsite> readAdmin(int entity_id) {
        DBConnection db = new DBConnection();
        List<Samplingsite> Samplingsites = new ArrayList<>();
        String sql = "SELECT * FROM sampling_sites WHERE entity_id = ?";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ps.setInt(1, entity_id);  // Establece el entity_id del usuario que ha iniciado sesión
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int province_id = resultSet.getInt("province_id");
                int canton_id = resultSet.getInt("canton_id");
                int district_id = resultSet.getInt("district_id");
                int entityid = resultSet.getInt("entity_id");
                Samplingsites.add(new Samplingsite(id, name, province_id,canton_id,district_id, entityid));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return Samplingsites;
    }

    public void update(Samplingsite Samplingsite) {

        DBConnection db = new DBConnection();
        String consultaSQL = "UPDATE sampling_sites SET name=?, province_id=?, canton_id=?, district_id=?, entity_id=? WHERE id=?";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            
            ps.setString(1, Samplingsite.getName());
            ps.setInt(2, Samplingsite.getProvince_id());
            ps.setInt(3, Samplingsite.getCanton_id());
            ps.setInt(4, Samplingsite.getDistrict_id());
            ps.setInt(5, Samplingsite.getEntity_id());
            ps.setInt(6, Samplingsite.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Modificación Exitosa");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se modificó, error:" + e.toString());
        } finally {
            db.disconnect();
        }

    }

    public void delete(int id) {

        DBConnection db = new DBConnection();

        String consultaSQL = "DELETE FROM sampling_sites WHERE id=?";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setInt(1, id);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Se eliminó correctamente el sitio de muestreo");

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "No se pudo eliminar, error: " + e.toString());
        } finally {
            db.disconnect();
        }

    }
    
     public void reorganizarIDs() {
        DBConnection db = new DBConnection();

        // Consulta SQL para obtener todos los IDs de los estudiantes ordenados
        String consultaSQL = "SELECT id FROM sampling_sites ORDER BY id";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(consultaSQL); ResultSet resultSet = preparedStatement.executeQuery())  {

            int nuevoID = 1;
            while (resultSet.next()) {
                int antiguoID = resultSet.getInt("id");
                if (nuevoID != antiguoID) {
                    try (PreparedStatement updateStatement = db.getConnection().prepareStatement("UPDATE sampling_sites SET id = ? WHERE id = ?")) {
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
    public int getIDSampling (String name) {
        int value = 0;
        DBConnection db = new DBConnection();
        String sql = "SELECT id FROM sampling_sites WHERE name = ?";
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
    
     public String getNameSampling(int id) {
        String value = "";
        DBConnection db = new DBConnection();
        String sql = "SELECT name FROM sampling_sites WHERE id = ?";
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
    
     
    public List<Samplingsite> getSamplingsitesForDistrict(String districtName) {
    DBConnection db = new DBConnection();
    List<Samplingsite> samplingsites = new ArrayList<>();
    String sql = "SELECT * FROM sampling_sites WHERE district_id = (SELECT id FROM districts WHERE name = ?)";

    try {
        PreparedStatement ps = db.getConnection().prepareStatement(sql);
        ps.setString(1, districtName);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int province_id = resultSet.getInt("province_id");
            int canton_id = resultSet.getInt("canton_id");
            int district_id = resultSet.getInt("district_id");
            int entity_id = resultSet.getInt("entity_id");
            samplingsites.add(new Samplingsite(id, name, province_id, canton_id, district_id, entity_id));
        }
    } catch (SQLException e) {
        System.err.println("Error: " + e.getMessage());
    } finally {
        db.disconnect();
    }
    return samplingsites;
}
    
     public boolean Validar(String name) {
        Validations validacion = new Validations();

        if (!validacion.validarLetras(name)) {
            JOptionPane.showMessageDialog(null, " El nombre debe contener solo letras.");
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
