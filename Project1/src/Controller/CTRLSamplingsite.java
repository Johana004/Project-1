package Controller;

import Model.Canton;
import Model.CantonDAO;
import Model.District;
import Model.DistrictDAO;
import Model.Entity;
import Model.EntityDAO;
import Model.Province;
import Model.ProvinceDAO;
import Model.Samplingsite;
import Model.SamplingsiteDAO;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Cliente
 */
public class CTRLSamplingsite {
    SamplingsiteDAO SSD = new SamplingsiteDAO();
    ProvinceDAO PD = new ProvinceDAO();
    CantonDAO CD =new CantonDAO();
    DistrictDAO DD = new DistrictDAO();
    EntityDAO ED = new EntityDAO();
    int id;
    int EntityID,ProvinceID,CantonID,DistrictID;
    
    
    
    
        public void loadDataSamplingsite(JTable table) {

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(model);
        table.setRowSorter(order);
        model.setRowCount(0);
        List<Samplingsite> sampling = SSD.read();
        for (Samplingsite samplingsite : sampling) {
            Object[] row = {samplingsite.getId(), samplingsite.getName(),this.PD.getNameProvinces(samplingsite.getProvince_id()),this.CD.getNameCanton(samplingsite.getCanton_id()),
            this.DD.getNameDistricts(samplingsite.getDistrict_id()),this.ED.getNameEntity(samplingsite.getEntity_id())};
            model.addRow(row);
        }
    }
      public void loadDataAdmin(JTable table, int entity_id) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(model);
        table.setRowSorter(order);
        model.setRowCount(0);

        List<Samplingsite> sampling = SSD.readAdmin(entity_id);  // Llama al método read2 con el entity_id proporcionado

        for (Samplingsite samplingsite : sampling) {
            Object[] row = {
                samplingsite.getId(),
                samplingsite.getName(),
                this.PD.getNameProvinces(samplingsite.getProvince_id()),
                this.CD.getNameCanton(samplingsite.getCanton_id()),
                this.DD.getNameDistricts(samplingsite.getDistrict_id()),
                this.ED.getNameEntity(samplingsite.getEntity_id()),};
            model.addRow(row);
        }
    }

    public void addSamplingsite(JTextField name) {
        // Validaciones aquí
        JTextField[] campos = {name};
        String Name = name.getText();
        if (SSD.ValidateEmptyFields(campos)) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos.");
            return;
        }
        if (!SSD.Validar(Name)) {
            return;
        }

        this.SSD.create(new Samplingsite(name.getText(), this.ProvinceID, this.CantonID, this.DistrictID, this.EntityID));
        this.SSD.reorganizarIDs();

    }

    public void updateSamplingsite(JTextField name) {
        JTextField[] campos = {name};
        String Name = name.getText();
        if (SSD.ValidateEmptyFields(campos)) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos.");
            return;
        }
        if (!SSD.Validar(Name)) {
            return;
        }

        this.SSD.create(new Samplingsite(name.getText(), this.ProvinceID, this.CantonID, this.DistrictID, this.EntityID));
        this.SSD.reorganizarIDs();

    }


    public void deleteSamplingsite() {
        this.SSD.delete(this.id);
    }

    public void selectedRow(JTable table, JTextField name, JComboBox province, JComboBox canton, JComboBox distrito, JComboBox entity) {
        try {
            int row = table.getSelectedRow();
            if (row >= 0) {
                this.id = Integer.parseInt(table.getValueAt(row, 0).toString());
                name.setText(table.getValueAt(row, 1).toString());
                province.setSelectedItem(table.getValueAt(row, 2).toString());
                canton.setSelectedItem(table.getValueAt(row, 3).toString());
                distrito.setSelectedItem(table.getValueAt(row, 4).toString());
                entity.setSelectedItem(table.getValueAt(row, 5).toString());

            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de selección, error: " + e.toString());
        }
    }
    
    public void ClearFields(JTextField name){
        name.setText("");
    }

    public void getIdEntity(JComboBox entity){
        this.EntityID = this.ED.getIDEntity(entity.getSelectedItem().toString());
    }

    public void loadEntity(JComboBox c) {
        List<Entity> entitys = this.ED.read();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Entity entity : entitys) {
            model.addElement(entity.getName());
        }
        c.setModel(model);
    }

    public void loadEntityAdmin(JComboBox c, int entity_id) {
        List<Entity> entitys = this.ED.readAdmin(entity_id);
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        c.setModel(model); // Limpia el JComboBox

        for (Entity entity : entitys) {
            model.addElement(entity.getName());
        }
    }

    public void getIdProvince(JComboBox Province) {
        this.ProvinceID = this.PD.getIDProvinces(Province.getSelectedItem().toString());
    }
    public void loadProvince(JComboBox c){
       List<Province> Provinces = this.PD.read();
       DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Province provinces : Provinces) {
            model.addElement(provinces.getName());
        }
        c.setModel(model);
    }

    public void getIdCanton(JComboBox canton){
        this.CantonID = this.CD.getIDCanton(canton.getSelectedItem().toString());
    }

    public void loadCanton(JComboBox c, String provinceId) {
        List<Canton> canton = this.CD.read(provinceId); // Aquí pasas el ID de la provincia
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Canton cantons : canton) {
            model.addElement(cantons.getName());
        }
        c.setModel(model);
    }

    public void getIdDistrict(JComboBox district) {
        this.DistrictID = this.DD.getIDDistricts(district.getSelectedItem().toString());
    }

    public void loadDistrict(JComboBox c,String CantonID) {
        List<District> district = this.DD.read(CantonID);
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (District districts : district) {
            model.addElement(districts.getName());
        }
        c.setModel(model);
    }
     public void loadSampling(JComboBox c){
       List<Samplingsite> Samplingsites = this.SSD.read();
       DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Samplingsite sites : Samplingsites) {
            model.addElement(sites.getName());
        }
        c.setModel(model);
    }

}
