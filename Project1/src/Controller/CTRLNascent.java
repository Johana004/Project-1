
package Controller;

import Model.Canton;
import Model.CantonDAO;
import Model.District;
import Model.DistrictDAO;
import Model.Entity;
import Model.EntityDAO;
import Model.Nascent;
import Model.NascentDAO;
import Model.Province;
import Model.ProvinceDAO;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;



public class CTRLNascent {
    NascentDAO ND = new NascentDAO();
    ProvinceDAO PD = new ProvinceDAO();
    CantonDAO CD =new CantonDAO();
    DistrictDAO DD = new DistrictDAO();
    EntityDAO ED = new EntityDAO();
    int id;
    int EntityID,ProvinceID,CantonID,DistrictID;
    
     //Loads nascent data into a JTable from a database and displays it.
    public void loadDataNascent(JTable table) {

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(model);
        table.setRowSorter(order);
        model.setRowCount(0);
        List<Nascent> Nascents = ND.read();
        for (Nascent nascent : Nascents) {
            Object[] row = {nascent.getId(), nascent.getName(), nascent.getAddress(),
                nascent.getLatitude(), nascent.getLength(),nascent.getDescription(),this.PD.getNameProvinces(nascent.getProvince_id()),this.CD.getNameCanton(nascent.getCanton_id()),
            this.DD.getNameDistricts(nascent.getDistrict_id()),this.ED.getNameEntity(nascent.getEntity_id())};
            model.addRow(row);
        }
    }

    ////Loads nascent data into a JTable from a database and displays it
    public void loadDataUserAdmin(JTable table, int entity_id) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(model);
        table.setRowSorter(order);
        model.setRowCount(0);

        List<Nascent> Nascents = ND.readAdmin(entity_id);

        for (Nascent nascent : Nascents) {
            Object[] row = {
                nascent.getId(),
                nascent.getName(),
                nascent.getAddress(),
                nascent.getLatitude(),
                nascent.getLength(),
                nascent.getDescription(),
                this.PD.getNameProvinces(nascent.getProvince_id()),
                this.CD.getNameCanton(nascent.getCanton_id()),
                this.DD.getNameDistricts(nascent.getDistrict_id()),
                this.ED.getNameEntity(nascent.getEntity_id())
            };

            model.addRow(row);
        }
    }


    //his method is used to add a nascent based on the information provided in the text fields
    public void addNascent(JTextField name, JTextField address, JTextField latitude, JTextField length, JTextField description) {
        //Validaciones aqui
        JTextField[] campos = {name, address, latitude, length, description};
        String Name = name.getText();
        String Address = address.getText();
        String Latitude = latitude.getText();
        String lLength = length.getText();
        String Description = description.getText();
        if (ND.ValidateEmptyFields(campos)) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos.");
            return;
        }
        if (!ND.Validar(Name, Address, Latitude, lLength, Description)) {
            return;
        }

        this.ND.create(new Nascent(name.getText(), address.getText(), Double.parseDouble(latitude.getText()), Double.parseDouble(length.getText()), description.getText(), this.ProvinceID, this.CantonID, this.DistrictID, this.EntityID));
        this.ND.reorganizarIDs();

    }

    //This method is used to update an existing nascent based on the information provided in the text fields.
    public void UpdateNascent(JTextField name, JTextField address, JTextField latitude, JTextField length, JTextField description) {
        //Validaciones aqui
        JTextField[] campos = {name, address, latitude, length, description};
        String Name = name.getText();
        String Address = address.getText();
        String Latitude = latitude.getText();
        String lLength = length.getText();
        String Description = description.getText();
        if (ND.ValidateEmptyFields(campos)) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos.");
            return;
        }
        if (!ND.Validar(Name, Address, Latitude, lLength, Description)) {
            return;
        }

        this.ND.update(new Nascent(this.id, name.getText(), address.getText(), Double.parseDouble(latitude.getText()), Double.parseDouble(length.getText()), description.getText(), this.ProvinceID, this.CantonID, this.DistrictID, this.EntityID));
        this.ND.reorganizarIDs();

    }
    
    //his method is used to delete a nascent based on the ID.
    public void deleteNascent(){
        this.ND.delete(this.id);
        this.ND.reorganizarIDs();
    }
    
    //This method is used to populate the text fields and combo boxes with data from the selected row of a JTable. 
    public void selectedRow(JTable table, JTextField name, JTextField address, JTextField latitude, JTextField length, JTextField description,JComboBox province,JComboBox canton,JComboBox district,JComboBox entity) {
        try {
            int row = table.getSelectedRow();
            if (row >= 0) {
                this.id = Integer.parseInt(table.getValueAt(row, 0).toString());
                name.setText((table.getValueAt(row, 1).toString()));
                address.setText((table.getValueAt(row, 2).toString()));
                latitude.setText((table.getValueAt(row, 3).toString()));
                length.setText((table.getValueAt(row, 4).toString()));
                description.setText((table.getValueAt(row, 5).toString()));
                province.setSelectedItem((table.getValueAt(row, 6).toString()));
                canton.setSelectedItem((table.getValueAt(row, 7).toString()));
                district.setSelectedItem((table.getValueAt(row, 8).toString()));
                entity.setSelectedItem((table.getValueAt(row, 9).toString()));
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de seleccion, error: " + e.toString());
        }
    }
    
    //This method is used to clear the text fields related to the entity, 
    public void clearFields(JTextField name, JTextField address, JTextField latitude, JTextField length, JTextField description) {
        name.setText("");
        address.setText("");
        latitude.setText("");
        length.setText("");
        description.setText("");
    }
    
    //This method is used to get the ID of the selected entity in the JComboBox.
    public void getIdEntity(JComboBox entity){
        this.EntityID = this.ED.getIDEntity(entity.getSelectedItem().toString());
    }
    
    //This method loads entities into the JComboBox 
    public void loadEntity(JComboBox c){
       List<Entity> entitys = this.ED.read();
       DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Entity entity : entitys) {
            model.addElement(entity.getName());
        }
        c.setModel(model);
    }
    //This method is similar to the first one but loads entities based on the entity_id.
    public void loadEntityAdmin(JComboBox c, int entity_id) {
    List<Entity> entitys = this.ED.readAdmin(entity_id);
    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
    c.setModel(model); // Limpia el JComboBox
    
    for (Entity entity : entitys) {
        model.addElement(entity.getName());
    }
}
    //This method is used to get the ID of the selected province in the JComboBox.
    public void getIdProvince(JComboBox province){
        this.ProvinceID = this.PD.getIDProvinces(province.getSelectedItem().toString());
    }
    
    //This method loads provinces into the provided JComboBox.
    public void loadProvince(JComboBox c){
       List<Province> province = this.PD.read();
       DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Province provinces : province) {
            model.addElement(provinces.getName());
        }
        c.setModel(model);
    }

    //This method is used to get the ID of the selected canton in the JComboBox.
    public void getIdCanton(JComboBox canton) {
        this.CantonID = this.CD.getIDCanton(canton.getSelectedItem().toString());
    }

    //This method loads cantons into the provided JComboBox based on the selected province.
    public void loadCanton(JComboBox c, String provinceId) {
        List<Canton> canton = this.CD.read(provinceId); // Aquí pasas el ID de la provincia
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Canton cantons : canton) {
            model.addElement(cantons.getName());
        }
        c.setModel(model);
    }

    //This method is used to get the ID of the selected district in the JComboBox.
    public void getIdDistrict(JComboBox district) {
        this.DistrictID = this.DD.getIDDistricts(district.getSelectedItem().toString());
    }

    //method is used to load districts into the provided JComboBox based on the selected canton 
    public void loadDistrict(JComboBox c,String CantonID) {
        List<District> district = this.DD.read(CantonID);
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (District districts : district) {
            model.addElement(districts.getName());
        }
        c.setModel(model);
    }
}
