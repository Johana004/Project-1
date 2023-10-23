package Model;

import Controller.Validations;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/* A Data Access Object (DAO) class for handling operations related to 'Entity' entities in the database.
 */
public class EntityDAO {

    public EntityDAO() {
    }

    /* new Entity in the database.
    Entity The Entity object to be created.
     */
    public void create(Entity Entity) {
        DBConnection db = new DBConnection();
        String consultaSQL = "INSERT INTO entitys (legal_ID, name, mail, telephone, address, description) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setInt(1, Entity.getLegal_ID());
            ps.setString(2, Entity.getName());
            ps.setString(3, Entity.getMail());
            ps.setInt(4, Entity.getTelephone());
            ps.setString(5, Entity.getAddress());
            ps.setString(6, Entity.getDescription());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Entity inserted successfully");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to insert Entity, error: " + e.toString());
        } finally {
            db.disconnect();
        }
    }

    /**
     Retrieves a list of all Entity objects from the database.
     A list of all Entity objects.
     */
    public List<Entity> read() {
        DBConnection db = new DBConnection();
        List<Entity> Entitys = new ArrayList<>();
        String sql = "SELECT * FROM entitys";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int legal_ID = Integer.parseInt(resultSet.getString("legal_ID"));
                String name = resultSet.getString("name");
                String mail = resultSet.getString("mail");
                int telephone = resultSet.getInt("telephone");
                String address = resultSet.getString("address");
                String description = resultSet.getString("description");
                Entitys.add(new Entity(id, legal_ID, name, mail, telephone, address, description));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return Entitys;
    }

    /**
     Retrieves a list of Entity objects associated with a specific entity ID.
     The ID of the entity for which to fetch Entity objects.
     list of Entity objects associated with the specified entity.
     */
    public List<Entity> readAdmin(int entity_id) {
        DBConnection db = new DBConnection();
        List<Entity> Entitys = new ArrayList<>();
        String sql = "SELECT * FROM entitys WHERE id = ?";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ps.setInt(1, entity_id);  // Sets the entity_id of the logged-in user
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int legal_ID = Integer.parseInt(resultSet.getString("legal_ID"));
                String name = resultSet.getString("name");
                String mail = resultSet.getString("mail");
                int telephone = resultSet.getInt("telephone");
                String address = resultSet.getString("address");
                String description = resultSet.getString("description");
                Entitys.add(new Entity(id, legal_ID, name, mail, telephone, address, description));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return Entitys;
    }

    /**
      Retrieves a list of Entity objects associated with a specific user ID (Digitador).
     The ID of the user (Digitador) for which to fetch Entity objects.
     A list of Entity objects associated with the specified user.
     */
    public List<Entity> readDigitador(int idUsser) {
        DBConnection db = new DBConnection();
        List<Entity> Entitys = new ArrayList<>();
        String sql = "SELECT * FROM entitys WHERE id = (SELECT entity_id FROM users WHERE id = ?)";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ps.setInt(1, idUsser);  // Sets the entity_id of the logged-in user
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int legal_ID = Integer.parseInt(resultSet.getString("legal_ID"));
                String name = resultSet.getString("name");
                String mail = resultSet.getString("mail");
                int telephone = resultSet.getInt("telephone");
                String address = resultSet.getString("address");
                String description = resultSet.getString("description");
                Entitys.add(new Entity(id, legal_ID, name, mail, telephone, address, description));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return Entitys;
    }

    /**
      Updates an existing Entity in the database.
     The Entity object to be updated.
     */
    public void update(Entity Entity) {
        DBConnection db = new DBConnection();
        String consultaSQL = "UPDATE entitys SET legal_ID=?, name=?, mail=?, telephone=?, address=?,description=? WHERE id=?";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setInt(1, Entity.getLegal_ID());
            ps.setString(2, Entity.getName());
            ps.setString(3, Entity.getMail());
            ps.setInt(4, Entity.getTelephone());
            ps.setString(5, Entity.getAddress());
            ps.setString(6, Entity.getDescription());
            ps.setInt(7, Entity.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Update successful");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Update failed, error: " + e.toString());
        } finally {
            db.disconnect();
        }
    }

    /*
     Deletes an Entity from the database based on its ID.
     The ID of the Entity to be deleted.
     */
    public void delete(int id) {
        DBConnection db = new DBConnection();
        String consultaSQL = "DELETE FROM entitys WHERE id=?";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setInt(1, id);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Entity deleted successfully");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to delete Entity, error: " + e.toString());
        } finally {
            db.disconnect();
        }
    }

    /*
      Reorganizes the IDs of entities in the database to ensure consecutive and unique IDs.
     */
    public void reorganizarIDs() {
        DBConnection db = new DBConnection();
        String consultaSQL = "SELECT id FROM entitys ORDER BY id";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(consultaSQL); ResultSet resultSet = preparedStatement.executeQuery()) {
            int nuevoID = 1;
            while (resultSet.next()) {
                int antiguoID = resultSet.getInt("id");
                if (nuevoID != antiguoID) {
                    try (PreparedStatement updateStatement = db.getConnection().prepareStatement("UPDATE entitys SET id = ? WHERE id = ?")) {
                        updateStatement.setInt(1, nuevoID);
                        updateStatement.setInt(2, antiguoID);
                        updateStatement.executeUpdate();
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "Error updating ID: " + e.toString());
                    }
                }
                nuevoID++;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching IDs: " + e.toString());
        } finally {
            db.disconnect();
        }
    }

    /**
      Retrieves the ID of an Entity based on its name.
      The name of the Entity to find.
      The ID of the Entity if found; otherwise, 0.
     */
    public int getIDEntity(String name) {
        int value = 0;
        DBConnection db = new DBConnection();
        String sql = "SELECT id FROM entitys WHERE name = ?";
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

    /*
      Retrieves the name of an Entity based on its ID.
      The ID of the Entity to find.
      The name of the Entity if found; otherwise, an empty string.
     */
    public String getNameEntity(int id) {
        String value = "";
        DBConnection db = new DBConnection();
        String sql = "SELECT name FROM entitys WHERE id = ?";
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

    /*
      Validates the input data for creating or updating an Entity.
      The legal ID of the Entity.
      The name of the Entity.
      The email address of the Entity.
      The telephone number of the Entity.
       The address of the Entity.
      The description of the Entity.
      True if the input data is valid; otherwise, false.
     */
    public boolean Validar(String legal_id, String name, String mail, String telephone, String address, String description) {
        Validations validacion = new Validations();

        if (!validacion.validarLetras(name)) {
            JOptionPane.showMessageDialog(null, "Name should contain only letters.");
            return false;
        }
        if (!validacion.validarCorreo(mail)) {
            JOptionPane.showMessageDialog(null, "Email format error.");
            return false;
        }
        if (!validacion.validarLetras(address)) {
            JOptionPane.showMessageDialog(null, "Address should contain only letters.");
            return false;
        }
        if (!validacion.validarCedulaJuridica(legal_id)) {
            JOptionPane.showMessageDialog(null, "Format should be d-d{3}-d{6}.");
            return false;
        }
        if (!validacion.validarTelefono(telephone)) {
            JOptionPane.showMessageDialog(null, "Telephone should contain only numbers.");
            return false;
        }
        if (!validacion.validarLetras(description)) {
            JOptionPane.showMessageDialog(null, "Description should contain only letters.");
            return false;
        }
        return true;
    }

    /*
      Checks if any of the input text fields are empty.
     An array of JTextField objects to check for emptiness.
     True if any of the text fields are empty; otherwise, false.
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
