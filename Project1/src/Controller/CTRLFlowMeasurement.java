
package Controller;

import Model.FlowMeasurement;
import Model.FlowMeasurementDAO;
import Model.Nascent;
import Model.NascentDAO;
import Model.Province;
import Model.Samplingsite;
import Model.SamplingsiteDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;
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
 * @author harol
 */
public class CTRLFlowMeasurement {

    FlowMeasurementDAO FMT = new FlowMeasurementDAO();
    SamplingsiteDAO SSD = new SamplingsiteDAO();
    NascentDAO NCT = new NascentDAO();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    int id;
    int nascent_id,samplingsite_id;


    public void loadDataFlowMeasurements(JTable table) {

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(model);
        table.setRowSorter(order);
        model.setRowCount(0);
        List<FlowMeasurement> flowMeasurements = FMT.read();
        for (FlowMeasurement measurement : flowMeasurements) {
            Object[] row = {measurement.getId(), measurement.getCapacity(), measurement.getMethod(),
                measurement.getObservation(), measurement.getDate(), measurement.getWeather(), measurement.getDone(),
               this.NCT.getNameNascent( measurement.getNascent_id()), this.SSD.getNameSampling(measurement.getSamplingsite_id())};
            model.addRow(row);
        }

    }
    public void loadDataFlowAdmin(JTable table, int entity_id) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(model);
        table.setRowSorter(order);
        model.setRowCount(0);

        List<FlowMeasurement> flowMeasurements = FMT.readAdmin(entity_id);  // Llama al método read con el entity_id proporcionado

        for (FlowMeasurement measurement : flowMeasurements) {
            Object[] row = {
                measurement.getId(),
                measurement.getCapacity(),
                measurement.getMethod(),
                measurement.getObservation(),
                measurement.getDate(),
                measurement.getWeather(),
                 measurement.getDone(),
                this.NCT.getNameNascent( measurement.getNascent_id()),
                this.SSD.getNameSampling(measurement.getSamplingsite_id())
            };
            model.addRow(row);
        }
    }

    public int capacidadRAMAleatoria(int minCapacidad, int maxCapacidad) {
        Random rand = new Random();
        int capacidadRAM = rand.nextInt(maxCapacidad - minCapacidad + 1) + minCapacidad;
        return capacidadRAM;
    }

    public LocalDate fechaAleatoria2022() {
        Random rand = new Random();
        int minDay = (int) LocalDate.of(2022, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(2022, 12, 31).toEpochDay();
        long randomDay = minDay + rand.nextInt(maxDay - minDay);

        return LocalDate.ofEpochDay(randomDay);
    }

    public void addFlowMeasurement(JTextField method, JTextField observation, JComboBox weather, JComboBox done) {
        LocalDate localDate = fechaAleatoria2022(); // Obtén la fecha aleatoria como LocalDate
        Date utilDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()); // Convierte LocalDate a java.util.Date
        java.sql.Date Date = new java.sql.Date(utilDate.getTime()); // Convierte java.util.Date a java.sql.Date
        int minCapacidadRAM = 0; // Capacidad RAM mínima en GB
        int maxCapacidadRAM = 1000; // Capacidad RAM máxima en GB
        int capacidad = capacidadRAMAleatoria(minCapacidadRAM, maxCapacidadRAM); // Genera capacidad RAM aleatoria
        String Weather = (String) weather.getSelectedItem();
        String Done = (String) done.getSelectedItem();
        
        JTextField[] campos = {method,observation};
        String Method = method.getText();
        String Observation = observation.getText();
        
        if (FMT.ValidateEmptyFields(campos)) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos.");
            return;
        }
        if (!FMT.Validar(Method,Observation)) {
            return;
        }
        this.FMT.create(new FlowMeasurement(Double.valueOf(capacidad), method.getText(), observation.getText(), Date, Weather, Done, this.nascent_id, this.samplingsite_id));
        this.FMT.reorganizarIDs();
    }

    public void updateflowMeasurement(JTextField capacity, JTextField method, JTextField observation, JTextField bdate, JComboBox weather, JComboBox done){
        JTextField[] campos = {method, observation};
        String Method = method.getText();
        String Observation = observation.getText();

        if (FMT.ValidateEmptyFields(campos)) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos.");
            return;
        }
        if (!FMT.Validar(Method, Observation)) {
            return;
        }
        try {
            Date date = dateFormat.parse(bdate.getText());
            String Weather = (String) weather.getSelectedItem();
            String Done = (String) done.getSelectedItem();
            this.FMT.update(new FlowMeasurement(this.id, Double.valueOf(capacity.getText()), method.getText(), observation.getText(), date, Weather, Done, this.nascent_id, this.samplingsite_id));
            this.FMT.reorganizarIDs();
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Error de formato, el indicado es año-mes-día : ");
        }
    }

    public void deleteFlow() {
        this.FMT.delete(this.id);
        this.FMT.reorganizarIDs();
    }

    public void selectedRow(JTable table, JTextField capacity, JTextField method, JTextField observation, JTextField bdate, JComboBox weather, JComboBox done, JComboBox nascent, JComboBox sites) {
        try {
            int row = table.getSelectedRow();
            if (row >= 0) {
                this.id = Integer.parseInt(table.getValueAt(row, 0).toString());
                capacity.setText((table.getValueAt(row, 1).toString()));
                method.setText((table.getValueAt(row, 2).toString()));
                observation.setText((table.getValueAt(row, 3).toString()));
                bdate.setText((table.getValueAt(row, 4).toString()));
                weather.setSelectedItem((table.getValueAt(row, 5).toString()));
                done.setSelectedItem((table.getValueAt(row, 6).toString()));
                nascent.setSelectedItem((table.getValueAt(row, 7).toString()));
                sites.setSelectedItem((table.getValueAt(row, 8).toString()));
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de seleccion, error: " + e.toString());
        }
    }

    public void clearFields(JTextField capacity, JTextField method, JTextField observation, JTextField bdate) {
        capacity.setText("");
        method.setText("");
        observation.setText("");
        bdate.setText("");
    }
     public void loadSampling(JComboBox c){
       List<Samplingsite> Samplingsites = this.SSD.read();
       DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Samplingsite samplingsite : Samplingsites) {
            model.addElement(samplingsite.getName());
        }
        c.setModel(model);
    }

    public void getIdSampling(JComboBox Sampling) {
        this.samplingsite_id = this.SSD.getIDSampling(Sampling.getSelectedItem().toString());
    }
    
     public void loadNascent(JComboBox c){
       List<Nascent> Nascents = this.NCT.read();
       DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Nascent nascents : Nascents) {
            model.addElement(nascents.getName());
        }
        c.setModel(model);
    }

    public void getIdNascent(JComboBox Nascent) {
        this.nascent_id = this.NCT.getIDNascent(Nascent.getSelectedItem().toString());
    }
     

}
