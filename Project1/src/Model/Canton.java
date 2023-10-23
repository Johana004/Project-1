
package Model;


public class Canton {
    private int id;
    private String name,province_id;
    
    
    
    public Canton(){}
    
     public Canton(String name, String province_id){
        this.name= name;
        this.province_id= province_id;
    
    
    }
    
    public Canton(int id, String name, String province_id){
        this.id = id;
        this.name= name;
        this.province_id= province_id;
    
    
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
     * @return the province_id
     */
    public String getProvince_id() {
        return province_id;
    }

    /**
     * @param province_id the province_id to set
     */
    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }
    
    
}
