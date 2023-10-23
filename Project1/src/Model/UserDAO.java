
package Model;

import Controller.Validations;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class UserDAO {
   
    public UserDAO() {
    }

    /*Creates a new user record in the database.
     The User object containing user information to be inserted.*/
    public void create(User User) {

        DBConnection db = new DBConnection();
        String consultaSQL = "INSERT INTO users (name,first_name, second_name, email, password, entity_id, role_id ) VALUES (?, ?, ?, ?,?,?,?)";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setString(1, User.getName());
            ps.setString(2, User.getFirst_name());
            ps.setString(3, User.getSecond_name());
            ps.setString(4, User.getEmail());
            ps.setString(5, User.getPassword());
            ps.setInt(6, User.getEntity_id());
            ps.setInt(7, User.getRole_id());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Se insertó correctamente el usuario ");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se insertó correctamente el usuario, error: " + e.toString());
        } finally {
            db.disconnect();
        }
    }

    /*Retrieves a list of User objects from the database based on specified criteria.
     The entity ID to filter users by (0 if not used for filtering).  
     The user ID to filter users by (0 if not used for filtering).
     A list of User objects matching the specified criteria.*/

  public List<User> readUsers(int entity_id, int userId) {
        DBConnection db = new DBConnection();
        List<User> users = new ArrayList<>();
        String sql;

        if (entity_id > 0) {
            sql = "SELECT * FROM users WHERE entity_id = ?";
        } else if (userId > 0) {
            sql = "SELECT * FROM users WHERE id = ?";
        } else {
            sql = "SELECT * FROM users"; // Para cargar todos los usuarios
        }

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);

            if (entity_id > 0) {
                ps.setInt(1, entity_id);
            } else if (userId > 0) {
                ps.setInt(1, userId);
            }

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String first_name = resultSet.getString("first_name");
                String second_name = resultSet.getString("second_name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                int role_id = resultSet.getInt("role_id");
                int entityid = resultSet.getInt("entity_id");
                users.add(new User(id, name, first_name, second_name, email, password, entityid, role_id));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return users;
    }
    
    //Updates user information in the database based on the provided User object.
    public void update(User User) {

        DBConnection db = new DBConnection();
        String consultaSQL = "UPDATE users SET name=?, first_name=?, second_name=?, email=?, password=?, entity_id=?, role_id=? WHERE id=?";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setString(1, User.getName());
            ps.setString(2, User.getFirst_name());
            ps.setString(3, User.getSecond_name());
            ps.setString(4, User.getEmail());
            ps.setString(5, User.getPassword());
            ps.setInt(6, User.getEntity_id());
            ps.setInt(7, User.getRole_id());
            ps.setInt(8, User.getId());
            ps.executeUpdate();  // Utiliza executeUpdate en lugar de execute para sentencias de actualización.

            JOptionPane.showMessageDialog(null, "Modificación Exitosa");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se modificó, error:" + e.toString());
        } finally {
            db.disconnect();
        }
    }
    
    //Deletes a user from the database based on the provided user ID.
 
    public void delete(int id) {

        DBConnection db = new DBConnection();

        String consultaSQL = "DELETE FROM users WHERE id=?";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setInt(1, id);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Se eliminó correctamente el usuario ");

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "No se pudo eliminar, error: " + e.toString());
        } finally {
            db.disconnect();
        }
    }
    
    //Reorganizes user IDs in the database to ensure they are sequential starting from 1.
    public void reorganizarIDs() {
        DBConnection db = new DBConnection();

        // Consulta SQL para obtener todos los IDs de los estudiantes ordenados
        String consultaSQL = "SELECT id FROM users ORDER BY id";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(consultaSQL); ResultSet resultSet = preparedStatement.executeQuery())  {

            int nuevoID = 1;
            while (resultSet.next()) {
                int antiguoID = resultSet.getInt("id");
                if (nuevoID != antiguoID) {
                    try (PreparedStatement updateStatement = db.getConnection().prepareStatement("UPDATE users SET id = ? WHERE id = ?")) {
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
    //Retrieves a User object from the database based on the provided username.
    public User getUserByUsername(String username) {
    DBConnection db = new DBConnection();
    User user = null;
    String sql = "SELECT * FROM users WHERE name = ?";
    
    try {
        PreparedStatement ps = db.getConnection().prepareStatement(sql);
        ps.setString(1, username);
        ResultSet resultSet = ps.executeQuery();
        
        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String first_name = resultSet.getString("first_name");
            String second_name = resultSet.getString("second_name");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            int entity_id = resultSet.getInt("entity_id");
            int role_id = resultSet.getInt("role_id");
            user = new User(id, name, first_name, second_name, email, password, entity_id, role_id);
        }
    } catch (SQLException e) {
        System.err.println("Error: " + e.getMessage());
    } finally {
        db.disconnect();
    }
    
    return user;
}
    //Method to validate fields
    public boolean Validar(String name, String first_name, String second_name, String email, String password) {
        Validations validacion = new Validations();

        if (!validacion.validarCorreo(email)) {
            JOptionPane.showMessageDialog(null, "Error en el formato");
            return false;
        }

        if (!validacion.validarLetras(name)) {
            JOptionPane.showMessageDialog(null, " El nombre debe contener solo letras.");
            return false;
        }
        if (!validacion.validarLetras(first_name)) {
            JOptionPane.showMessageDialog(null, " El apellido debe contener solo letras.");
            return false;
        }
        if (!validacion.validarLetras(second_name)) {
            JOptionPane.showMessageDialog(null, " El apellido debe contener solo letras.");
            return false;
        }

        if (!validacion.validarAlfanumericos(password)) {
            JOptionPane.showMessageDialog(null, "La contraseña debe contener solo números y letras");
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