
package Controller;

import Model.Entity;
import Model.EntityDAO;
import Model.Nascent;
import Model.NascentDAO;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class CTRLEntity {
    
    EntityDAO ED = new EntityDAO();
    NascentDAO ND = new NascentDAO();
    int id;
    
    /*
     Loads entity data into a JTable.
     The JTable to populate with entity data.
     */
    public void loadDataEntitys(JTable table) {

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(model);
        table.setRowSorter(order);
        model.setRowCount(0);
        List<Entity> entitys = ED.read();
        for (Entity Entitys : entitys) {
            Object[] row = {Entitys.getId(), Entitys.getLegal_ID(), Entitys.getName(),
                Entitys.getMail(), Entitys.getTelephone(),Entitys.getAddress(),Entitys.getDescription()};
            model.addRow(row);
        }
    }
    
     /*
      Adds a new entity to the database.
      The legal ID of the entity.
     The name of the entity.
    The email of the entity.
     The telephone number of the entity.
      The address of the entity.
       The description of the entity.
     */
    public void addEntity(JTextField legalID, JTextField name, JTextField mail, JTextField telephone, JTextField address, JTextField description) {
        JTextField[] campos = {legalID, name, mail, telephone, address, description};
        String Name = name.getText();
        String LegalID = legalID.getText();
        String Mail = mail.getText();
        String Telephone = telephone.getText();
        String Address = address.getText();
        String Description = description.getText();

        if (ED.ValidateEmptyFields(campos)) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos.");
            return;
        }
        if (!ED.Validar(LegalID, Name, Mail, Telephone, Address, Description)) {
            return;
        }

        this.ED.create(new Entity(Integer.parseInt(legalID.getText()), name.getText(), mail.getText(), Integer.parseInt(telephone.getText()), address.getText(), description.getText()));
        this.ED.reorganizarIDs();

    }
    
    /*
      Updates an existing entity in the database.
      The legal ID of the entity.
      The name of the entity.
     The email of the entity.
      The telephone number of the entity.
     The address of the entity.
      The description of the entity.
     */
     public void updateEntity(JTextField legalID ,JTextField name, JTextField mail, JTextField telephone, JTextField address, JTextField description) {
       JTextField[] campos = {legalID, name, mail, telephone, address, description};
        String Name = name.getText();
        String LegalID = legalID.getText();
        String Mail = mail.getText();
        String Telephone = telephone.getText();
        String Address = address.getText();
        String Description = description.getText();

        if (ED.ValidateEmptyFields(campos)) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos.");
            return;
        }
        if (!ED.Validar(LegalID, Name, Mail, Telephone, Address, Description)) {
            return;
        }

        this.ED.create(new Entity(Integer.parseInt(legalID.getText()), name.getText(), mail.getText(), Integer.parseInt(telephone.getText()), address.getText(), description.getText()));
        this.ED.reorganizarIDs();
    }
     
     /**
     * Deletes the selected entity from the database.
     */
      public void deleteEntity(){
        this.ED.delete(this.id);
        this.ED.reorganizarIDs();
    }
      
       /*
     Selects and displays the details of a row in the JTable.
     The JTable containing entity data.
      The legal ID field.
     The name field.
      The email field.
      The telephone field.
     The address field.
      The description field.
     */
    public void selectedRow(JTable table, JTextField legalID ,JTextField name, JTextField mail, JTextField telephone, JTextField address, JTextField description) {
        try {
            int row = table.getSelectedRow();
            if (row >= 0) {
                this.id = Integer.parseInt(table.getValueAt(row, 0).toString());
                legalID.setText((table.getValueAt(row, 1).toString()));
                name.setText((table.getValueAt(row, 2).toString()));
                mail.setText((table.getValueAt(row, 3).toString()));
                telephone.setText((table.getValueAt(row, 4).toString()));
                address.setText((table.getValueAt(row, 5).toString()));
                description.setText((table.getValueAt(row, 6).toString()));
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de seleccion, error: " + e.toString());
        }
    }
    
    /*
      Clears the input fields for legal ID, name, mail, telephone, address, and description.
     The legal ID field.
      The name field.
       The telephone field.
      The address field.
      The description field.
     */
    public void clearFields(JTextField legalID ,JTextField name, JTextField mail, JTextField telephone, JTextField address, JTextField description) {
        name.setText("");
        legalID.setText("");
        mail.setText("");
        telephone.setText("");
        address.setText("");
        description.setText("");
    }
    
     /* Loads the entity names into a JComboBox.
     comboBox The JComboBox to populate with entity names.
     */
     public void loadEntity(JComboBox c) {
        List<Entity> entitys = this.ED.read();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Entity entity : entitys) {
            model.addElement(entity.getName());
        }
        c.setModel(model);
    }
   

    /*
      Displays the nacientes associated with the selected entity in a JTextArea.
      entityComboBox The JComboBox containing entity names.
      nacientesTextArea The JTextArea to display nacientes.
     */
     public void showNacientesForSelectedEntity(JComboBox entityComboBox, JTextArea nacientesTextArea) {
        String selectedEntityName = entityComboBox.getSelectedItem().toString();

        // Obtén la lista de nacientes asociadas a la entidad seleccionada
        List<String> Nascents = ND.getNacientesForEntity(selectedEntityName);

        // Limpia el JTextArea antes de mostrar las nacientes
        nacientesTextArea.setText("");

        // Muestra las nacientes en el JTextArea
        for (String Nascent : Nascents) {
            nacientesTextArea.append(Nascent + "\n");
        }
    }

}
