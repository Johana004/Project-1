
package Controller;

import Model.DBConnection;

public class Project1 {

    
    public static void main(String[] args) {
        
        DBConnection db = new DBConnection();
        db.getConnection();
        db.disconnect();
    }
    
}
