
package Model;


public class User {
    private int id,entity_id,role_id;
    private String name,first_name,second_name,email,password;
    
    
    
    public User(){}
    
        //Constructor for the User class.
        public User(String name, String first_name,String second_name, String email, String password, int entity_id, int role_id){
        
            this.name = name;
            this.first_name = first_name;
            this.second_name = second_name;
            this.email = email;
            this.password = password;
            this.entity_id = entity_id;
            this.role_id = role_id;
      }

        public User(int id, String name, String first_name,String second_name, String email, String password, int entity_id, int role_id){
        
            this.id = id;
            this.name = name;
            this.first_name = first_name;
            this.second_name = second_name;
            this.email = email;
            this.password = password;
            this.entity_id = entity_id;
            this.role_id = role_id;
      }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the first_name
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * @param first_name the first_name to set
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * @return the second_name
     */
    public String getSecond_name() {
        return second_name;
    }

    /**
     * @param second_name the second_name to set
     */
    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the entity_id
     */
    public int getEntity_id() {
        return entity_id;
    }

    /**
     * @param entity_id the entity_id to set
     */
    public void setEntity_id(int entity_id) {
        this.entity_id = entity_id;
    }

    /**
     * @return the role_id
     */
    public int getRole_id() {
        return role_id;
    }

    /**
     * @param role_id the role_id to set
     */
    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getFirstName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }



}






