package Model;

/**
 * A class representing an 'Entity' entity.
 */
public class Entity {
    private int id, legal_ID, telephone;
    private String name, mail, address, description;

    /**
      Default constructor for the Entity class.
     */
    public Entity() {
    }

    /**
     * Constructor for creating an Entity with specific attributes.
        The legal ID of the entity.
         The name of the entity.
        The email address of the entity.
      The telephone number of the entity.
        The address of the entity.
     A description of the entity.
     */
    public Entity(int legal_ID, String name, String mail, int telephone, String address, String description) {
        this.legal_ID = legal_ID;
        this.name = name;
        this.mail = mail;
        this.telephone = telephone;
        this.address = address;
        this.description = description;
    }

    /**
      Constructor for creating an Entity with an ID and specific attributes.
            The unique ID of the entity.
        The legal ID of the entity.
            The name of the entity.
        The email address of the entity.
      The telephone number of the entity.
        The address of the entity.
    A description of the entity.
     */
    public Entity(int id, int legal_ID, String name, String mail, int telephone, String address, String description) {
        this.id = id;
        this.legal_ID = legal_ID;
        this.name = name;
        this.mail = mail;
        this.telephone = telephone;
        this.address = address;
        this.description = description;
    }

    /**
      Get the unique ID of the Entity.
     The unique ID of the Entity.
     */
    public int getId() {
        return id;
    }

    /**
      Set the unique ID of the Entity.
     
     The unique ID of the Entity.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
      Get the legal ID of the Entity.
     
      The legal ID of the Entity.
     */
    public int getLegal_ID() {
        return legal_ID;
    }

    /**
      Set the legal ID of the Entity.
     
      The legal ID of the Entity.
     */
    public void setLegal_ID(int legal_ID) {
        this.legal_ID = legal_ID;
    }

    /**
      Get the name of the Entity.
     
      The name of the Entity.
     */
    public String getName() {
        return name;
    }

    /**
      Set the name of the Entity.
     
      The name of the Entity.
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
      Get the telephone number of the Entity.
     
      The telephone number of the Entity.
     */
    public int getTelephone() {
        return telephone;
    }

    /**
     Set the telephone number of the Entity.
     
      The telephone number of the Entity.
     */
    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    /**
      Get the address of the Entity.
     
      The address of the Entity.
     */
    public String getAddress() {
        return address;
    }

    /**
      Set the address of the Entity.
     
      The address of the Entity.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
      Get the description of the Entity.
     
      The description of the Entity.
     */
    public String getDescription() {
        return description;
    }

    /**
      Set the description of the Entity.
     
      description The description of the Entity.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the email address of the Entity.
     *
      The email address of the Entity.
     */
    public String getMail() {
        return mail;
    }

    /**
     Set the email address of the Entity.
      The email address of the Entity.
     */
    public void setMail(String mail) {
        this.mail = mail;
    }
}
