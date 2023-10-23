
package Model;

public class Nascent {
    private int id,province_id, district_id, entity_id, canton_id;
    private String name, address,description ;
    private double latitude,length;
    
   
    
    public Nascent (){}
    
     public Nascent(String name, String address,double latitude, double length, String description, int province_id , int canton_id , int district_id, int entity_id ){

        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.length = length;
        this.description = description;
        this.province_id = province_id;
        this.canton_id = canton_id;
        this.district_id = district_id;
        this.entity_id = entity_id;
        
    }

    public Nascent(int id, String name, String address, double latitude,double length, String description, int province_id , int canton_id , int district_id, int entity_id){
        this.id = id;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.length = length;
        this.description = description;
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
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the length
     */
    public double getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(double length) {
        this.length = length;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }



}

