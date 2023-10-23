/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.District;
import Model.DistrictDAO;
import Model.Samplingsite;
import Model.SamplingsiteDAO;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextArea;




/**
 *
 * @author Cliente
 */
public class CTRLDistrict {
   DistrictDAO DC = new DistrictDAO();
   SamplingsiteDAO SD =new SamplingsiteDAO();
    
   public void loadDistrict(JComboBox c){
       
       List<District> Districts = this.DC.read1();
       DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (District District : Districts) {
            model.addElement(District.getName());
        }
        c.setModel(model);
    }
   public void showSamplingsitesForSelectedDistrict(JComboBox districtComboBox, JTextArea samplingsitesTextArea) {
    String selectedDistrictName = districtComboBox.getSelectedItem().toString();

    // Obtén la lista de sitios de muestreo para el distrito seleccionado
    List<Samplingsite> samplingsites = SD.getSamplingsitesForDistrict(selectedDistrictName);

    // Limpia el JTextArea antes de mostrar los sitios de muestreo
    samplingsitesTextArea.setText("");

    // Muestra los sitios de muestreo en el JTextArea
    for (Samplingsite samplingsite : samplingsites) {
        samplingsitesTextArea.append(samplingsite.getName() + "\n");
    }
}

}
