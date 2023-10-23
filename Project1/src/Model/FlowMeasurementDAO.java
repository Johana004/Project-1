package Model;

import Controller.Validations;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


/* Data Access Object (DAO) for managing flow measurements in the system.
 */
public class FlowMeasurementDAO {
    
    
    public FlowMeasurementDAO() {
    }

     /* Creates a new flow measurement record in the database.
      The FlowMeasurement object to be created.
     */
    public void create(FlowMeasurement flowMeasurement) {

        DBConnection db = new DBConnection();
        String consultaSQL = "INSERT INTO flow_measurements (capacity, method, observation, date, weather, done, nascent_id, samplingsite_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setDouble(1, flowMeasurement.getCapacity());
            ps.setString(2, flowMeasurement.getMethod());
            ps.setString(3, flowMeasurement.getObservation());
            ps.setDate(4, new java.sql.Date(flowMeasurement.getDate().getTime()));
            ps.setString(5, flowMeasurement.getWeather());
            ps.setString(6, flowMeasurement.getDone());
            ps.setInt(7, flowMeasurement.getNascent_id());
            ps.setInt(8, flowMeasurement.getSamplingsite_id());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Se insertó correctamente la medicion ");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se insertó correctamente la medicion, error: " + e.toString());
        } finally {
            db.disconnect();
        }
    }

    /*
     Reads and retrieves all flow measurement records from the database.
     A list of FlowMeasurement objects containing the retrieved measurements.
     */
    public List<FlowMeasurement> read() {

        DBConnection db = new DBConnection();
        List<FlowMeasurement> flowMeasurement = new ArrayList<>();
        String sql = "SELECT * FROM flow_measurements";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Double capacity = resultSet.getDouble("capacity");
                String method = resultSet.getString("method");
                String observation = resultSet.getString("observation");
                Date bDate = resultSet.getDate("date");
                String weather = resultSet.getString("weather");
                String done = resultSet.getString("Done");
                int nascentID = resultSet.getInt("nascent_id");
                int samplingsiteID = resultSet.getInt("samplingsite_id");
                flowMeasurement.add(new FlowMeasurement(id,capacity , method, observation ,bDate, weather, done,nascentID, samplingsiteID));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return flowMeasurement;
    }
    
     public List<FlowMeasurement> readAdmin(int entity_id) {
        DBConnection db = new DBConnection();
        List<FlowMeasurement> flowMeasurement = new ArrayList<>();
        String sql = "SELECT * FROM flow_measurements WHERE entity_id = ?";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ps.setInt(1, entity_id);  // Establece el entity_id del usuario que ha iniciado sesión
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Double capacity = resultSet.getDouble("capacity");
                String method = resultSet.getString("method");
                String observation = resultSet.getString("observation");
                Date bDate = resultSet.getDate("date");
                String weather = resultSet.getString("weather");
                String done = resultSet.getString("Done");
                int nascentID = resultSet.getInt("nascent_id");
                int samplingsiteID = resultSet.getInt("samplingsite_id");
                flowMeasurement.add(new FlowMeasurement(id,capacity , method, observation ,bDate, weather, done,nascentID, samplingsiteID));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return flowMeasurement;
    }    
     
      /*
     Updates an existing flow measurement record in the database.
     The FlowMeasurement object containing the updated information.
     */
    public void update(FlowMeasurement flowMeasurement) {

        DBConnection db = new DBConnection();
        String consultaSQL = "UPDATE flow_measurements SET capacity=?, method=?, observation=? ,date=?, weather=?, done=?, nascent_id=?, samplingsite_id=? WHERE id=?";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setDouble(1, flowMeasurement.getCapacity());
            ps.setString(2, flowMeasurement.getMethod());
            ps.setString(3, flowMeasurement.getObservation());
            ps.setDate(4, new java.sql.Date(flowMeasurement.getDate().getTime()));
            ps.setString(5, flowMeasurement.getWeather());
            ps.setString(6, flowMeasurement.getDone());
            ps.setInt(7, flowMeasurement.getNascent_id());
            ps.setInt(8, flowMeasurement.getSamplingsite_id());
            ps.setInt(9, flowMeasurement.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Modificación Exitosa");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se modificó, error:" + e.toString());
        }finally {
            db.disconnect();
        }
        
    }
    
    /*
      Deletes a flow measurement record from the database.
      The ID of the flow measurement record to be deleted.
     */
    public void delete(int id) {

        DBConnection db = new DBConnection();

        String consultaSQL = "DELETE FROM flow_measurements WHERE id=?";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setInt(1, id);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Se eliminó correctamente la medicion");

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "No se pudo eliminar, error: " + e.toString());
        }finally {
            db.disconnect();
        } 
    }
    /* Reorganizes the IDs of flow measurement records in the database to ensure they are sequential.
     */
     public void reorganizarIDs() {
        DBConnection db = new DBConnection();

        // SQL query to retrieve all IDs of flow measurements sorted
        String consultaSQL = "SELECT id FROM flow_measurements ORDER BY id";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(consultaSQL); ResultSet resultSet = preparedStatement.executeQuery())  {

            int nuevoID = 1;
            while (resultSet.next()) {
                int antiguoID = resultSet.getInt("id");
                if (nuevoID != antiguoID) {
                    try (PreparedStatement updateStatement = db.getConnection().prepareStatement("UPDATE flow_measurements SET id = ? WHERE id = ?")) {
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
    
     /*
      Validates the provided method and observation for flow measurements.
      The method to validate.
      The observation to validate.
      True if the method and observation are valid; false otherwise.
     */
     public boolean Validar(String method, String Observation) {
        Validations validacion = new Validations();

        if (!validacion.validarLetras(method)) {
            JOptionPane.showMessageDialog(null, " El metodo debe contener solo letras.");
            return false;
        }
        if (!validacion.validarLetras(Observation)) {
            JOptionPane.showMessageDialog(null, " La obseveracion debe contener solo letras.");
            return false;
        }

        return true;
    }

     /*
      Checks if the provided text fields are empty.
     An array of JTextField objects to check for emptiness.
      True if any of the text fields is empty; false if all are filled.
     */
    public boolean ValidateEmptyFields(JTextField[] textFields) {
        for (JTextField textField : textFields) {
            if (textField.getText().trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }


}
