
package Model;


public class Samplingsite {
     
    private int id, province_id,district_id,canton_id,entity_id ;
    private String name;
            
 public Samplingsite(){}
 
 
  
 public Samplingsite(String name, int province_id, int canton_id,int district_id, int entity_id){

     this.name = name;
     this.province_id = province_id;
     this.canton_id = canton_id;
     this.district_id = district_id;
     this.entity_id = entity_id;
       
 }
 
 
 public Samplingsite(int id, String name, int province_id,int canton_id,int district_id, int entity_id){
 
     this.id = id;
     this.name = name;
     this.province_id = province_id;
     this.canton_id = canton_id;
     this.district_id = district_id;
     this.entity_id = entity_id;
       
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
    public int getProvince_id() {
        return province_id;
    }

    /**
     * @param province_id the province_id to set
     */
    public void setProvince_id(int province_id) {
        this.province_id = province_id;
    }

    /**
     * @return the district_id
     */
    public int getDistrict_id() {
        return district_id;
    }

    /**
     * @param district_id the district_id to set
     */
    public void setDistrict_id(int district_id) {
        this.district_id = district_id;
    }

    /**
     * @return the canton_id
     */
    public int getCanton_id() {
        return canton_id;
    }

    /**
     * @param canton_id the canton_id to set
     */
    public void setCanton_id(int canton_id) {
        this.canton_id = canton_id;
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
  
 
 
}
