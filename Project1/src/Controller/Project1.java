
package Controller;

import Model.DBConnection;
import View.Login;

public class Project1 {

    
    public static void main(String[] args) {
        
        /*DBConnection db = new DBConnection();
        db.getConnection();
        db.disconnect();*/
        Login log = new Login();
        log.setVisible(true);
    }
    
}
