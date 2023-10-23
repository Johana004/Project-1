
package Controller;

import Model.Entity;
import Model.EntityDAO;
import Model.Role;
import Model.RoleDAO;
import Model.User;
import Model.UserDAO;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class CTRLUser {
    
    UserDAO ud = new UserDAO();
    EntityDAO ed = new EntityDAO();
    RoleDAO rd = new RoleDAO();
    int id;
    int EntityID;
    int RoleID;
    
    
    //Loads user data into a JTable from a database and displays it.
    public void loadDataUser(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(model);
        table.setRowSorter(order);
        model.setRowCount(0);

        List<User> users = ud.readUsers(0, 0); // Cargar todos los usuarios

        for (User user : users) {
            Object[] row = {
                user.getId(),
                user.getName(),
                user.getFirst_name(),
                user.getSecond_name(),
                user.getEmail(),
                user.getPassword(),
                this.ed.getNameEntity(user.getEntity_id()),
                this.rd.getNameRole(user.getRole_id())
            };
            model.addRow(row);
        }
    }

    //Loads user data into a JTable from a database for a specific entity and displays it.
    public void loadDataUserAdmin(JTable table, int entity_id) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(model);
        table.setRowSorter(order);
        model.setRowCount(0);

        List<User> users = ud.readUsers(entity_id, 0); // Cargar usuarios basados en entity_id

        for (User user : users) {
            Object[] row = {
                user.getId(),
                user.getName(),
                user.getFirst_name(),
                user.getSecond_name(),
                user.getEmail(),
                user.getPassword(),
                this.ed.getNameEntity(user.getEntity_id()),
                this.rd.getNameRole(user.getRole_id())
            };
            model.addRow(row);
        }
    }

    //loads user data into a JTable from a database for a specific user and displays it.
    public void loadDataUserDigitador(JTable table, int userId) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(model);
        table.setRowSorter(order);
        model.setRowCount(0);

        List<User> users = ud.readUsers(0, userId); // Cargar un usuario específico basado en userId

        for (User user : users) {
            Object[] row = {
                user.getId(),
                user.getName(),
                user.getFirst_name(),
                user.getSecond_name(),
                user.getEmail(),
                user.getPassword(),
                this.ed.getNameEntity(user.getEntity_id()),
                this.rd.getNameRole(user.getRole_id())
            };
            model.addRow(row);
        }
    }

    
    //Adds a new user to the database based on the input from text fields.
    public void addUser(JTextField name, JTextField first_name, JTextField second_name, JTextField email, JTextField password) {
        //Validaciones aqui
        JTextField[] campos = {name, first_name, second_name, email, password};
        String Name = name.getText();
        String First_name = first_name.getText();
        String Second_name = second_name.getText();
        String Email = email.getText();
        String Password = password.getText();

        if (ud.ValidateEmptyFields(campos)) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos.");
            return;
        }
        if (!ud.Validar(Name, First_name, Second_name, Email, Password)) {
            return;
        }

        this.ud.create(new User(name.getText(), first_name.getText(), second_name.getText(), email.getText(), password.getText(), this.EntityID, this.RoleID));
        this.ud.reorganizarIDs();

    }

    //Updates an existing user's information in the database based on input from text fields.
    public void updateUser(JTextField name, JTextField first_name, JTextField second_name, JTextField email, JTextField password) {
        //Validaciones aqui
        JTextField[] campos = {name, first_name, second_name, email, password};
        String Name = name.getText();
        String First_name = first_name.getText();
        String Second_name = second_name.getText();
        String Email = email.getText();
        String Password = password.getText();

        if (ud.ValidateEmptyFields(campos)) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos.");
            return;
        }
        if (!ud.Validar(Name, First_name, Second_name, Email, Password)) {
            return;
        }

        this.ud.update(new User(this.id, name.getText(), first_name.getText(), second_name.getText(), email.getText(), password.getText(), this.EntityID, this.RoleID));
        this.ud.reorganizarIDs();

    }
    
    //Deletes the current user from the database and reorganizes user IDs.
    public void deleteUser(){
        this.ud.delete(this.id);
        this.ud.reorganizarIDs();
    }
    
    //Populates input fields and combo boxes with data from the selected row in the JTable.
    public void selectedRow(JTable table, JTextField name, JTextField first_name, JTextField second_name, JTextField email, JTextField password,JComboBox entity, JComboBox role) {
        try {
            int row = table.getSelectedRow();
            if (row >= 0) {
                this.id = Integer.parseInt(table.getValueAt(row, 0).toString());
                name.setText((table.getValueAt(row, 1).toString()));
                first_name.setText((table.getValueAt(row, 2).toString()));
                second_name.setText((table.getValueAt(row, 3).toString()));
                email.setText((table.getValueAt(row, 4).toString()));
                password.setText((table.getValueAt(row, 5).toString()));
                entity.setSelectedItem((table.getValueAt(row, 6).toString()));
                role.setSelectedItem((table.getValueAt(row, 7).toString()));
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de seleccion, error: " + e.toString());
        }
    }
    
    //Clears the content of the provided text fields
    public void clearFields(JTextField name, JTextField first_name, JTextField second_name, JTextField email, JTextField password) {
        name.setText("");
        first_name.setText("");
        second_name.setText("");
        email.setText("");
        password.setText("");
    }
    
    //his method is used to get the ID of the selected entity in the JComboBox.
    public void getIdEntity(JComboBox entity) {
        this.EntityID = this.ed.getIDEntity(entity.getSelectedItem().toString());
    }

    //This method loads all entities from the database and populates them into the JComboBox provided as an argument. 
    public void loadEntity(JComboBox c) {
        List<Entity> entitys = this.ed.read();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Entity entity : entitys) {
            model.addElement(entity.getName());
        }
        c.setModel(model);
    }

    //This method loads entities that are associated with a specific entity_id into the JComboBox 
    public void loadEntityAdmin(JComboBox c, int entity_id) {
        List<Entity> entitys = this.ed.readAdmin(entity_id);
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        c.setModel(model); // Limpia el JComboBox

        for (Entity entity : entitys) {
            model.addElement(entity.getName());
        }
    }

    //This method is used to load entities specifically for a digitador based on the userId. 
    public void loadEntityDigitador(JComboBox c, int userId) {
        List<Entity> entities = this.ed.readDigitador(userId);  // Llama al nuevo método loadUserEntity

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        c.setModel(model); // Limpia el JComboBox

        for (Entity entity : entities) {
            model.addElement(entity.getName());
        }
    }

   //his method is used to get the ID of the selected role in the JComboBox. 
    public void getIdRole(JComboBox role) {
        this.RoleID = this.rd.getIDRole(role.getSelectedItem().toString());
    }

    //This method loads roles into the JComboBox
    public void loadRole(JComboBox c, boolean isAdmin, boolean isDigitador) {
        List<Role> roles = this.rd.read();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

        for (Role role : roles) {
            if ((isAdmin && (role.getName().equals("Administrador") || role.getName().equals("Digitador")))
                    || (isDigitador && role.getName().equals("Digitador"))
                    || (!isAdmin && !isDigitador)) {
                model.addElement(role.getName());
            }
        }

        c.setModel(model);
    }

}
