package Model;

import java.util.Date;

/* flow measurement record in the system.
 */
public class FlowMeasurement {
    private int id, Nascent_id, samplingsite_id;
    private String method, observation, weather, Done;
    private Date date;
    private Double capacity;

    public FlowMeasurement() {
    }

    /*
     Constructs a FlowMeasurement object with specific parameters.
     The measurement capacity.
      The measurement method.
     Additional observations related to the measurement.
      The date when the measurement was recorded.
       Weather conditions during the measurement.
      Indicates if the measurement is completed.
       The ID of the associated nascent.
      The ID of the associated sampling site.
     */
    public FlowMeasurement(Double capacity, String method, String observation, Date date, String weather, String done, int Nascent_id, int samplingsite_id) {
        this.capacity = capacity;
        this.method = method;
        this.observation = observation;
        this.date = date;
        this.weather = weather;
        this.Done = done;
        this.Nascent_id = Nascent_id;
        this.samplingsite_id = samplingsite_id;
    }

    /*
      Constructs a FlowMeasurement object with specific parameters, including an ID.
      The unique identifier of the measurement record.
    The measurement capacity.
     The measurement method.
     Additional observations related to the measurement.
     The date when the measurement was recorded.
     Weather conditions during the measurement.
     Indicates if the measurement is completed.
     The ID of the associated nascent.
     The ID of the associated sampling site.
     */
    public FlowMeasurement(int id, Double capacity, String method, String observation, Date date, String weather, String done, int Nascent_id, int samplingsite_id) {
        this.id = id;
        this.capacity = capacity;
        this.method = method;
        this.observation = observation;
        this.date = date;
        this.weather = weather;
        this.Done = done;
        this.Nascent_id = Nascent_id;
        this.samplingsite_id = samplingsite_id;
    }

    /*
      Gets the unique identifier of the flow measurement record.
      The ID of the measurement record.
     */
    public int getId() {
        return id;
    }

    /*
      Sets the unique identifier of the flow measurement record.
      The ID to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /*
      Gets the method used for the measurement.
      The measurement method.
     */
    public String getMethod() {
        return method;
    }

    /*
      Sets the method used for the measurement.
      The measurement method to set.
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /*
     Gets the measurement capacity.
     The measurement capacity.
     */
    public Double getCapacity() {
        return capacity;
    }

    /*
    Sets the measurement capacity.
     The measurement capacity to set.
     */
    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    /*
     Gets additional observations related to the measurement.
      Additional observations.
     */
    public String getObservation() {
        return observation;
    }

    /*
    Sets additional observations related to the measurement.
    Additional observations to set.
     */
    public void setObservation(String observation) {
        this.observation = observation;
    }

    /*
     Gets the date when the measurement was recorded.
     The date of the measurement.
     */
    public Date getDate() {
        return date;
    }

    /*
     Sets the date when the measurement was recorded.
     The date of the measurement to set.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /*
     Gets the weather conditions during the measurement.
     Weather conditions.
     */
    public String getWeather() {
        return weather;
    }

    /*
      Sets the weather conditions during the measurement.
      Weather conditions to set.
     */
    public void setWeather(String weather) {
        this.weather = weather;
    }

    /*
      Gets the ID of the associated nascent.
      The Nascent ID.
     */
    public int getNascent_id() {
        return Nascent_id;
    }

    /*
      Sets the ID of the associated nascent.
      The Nascent ID to set.
     */
    public void setNascent_id(int Nascent_id) {
        this.Nascent_id = Nascent_id;
    }

    /*
     Gets the ID of the associated sampling site.
     The sampling site ID.
     */
    public int getSamplingsite_id() {
        return samplingsite_id;
    }

    /*
      Sets the ID of the associated sampling site.
      The sampling site ID to set.
     */
    public void setSamplingsite_id(int samplingsite_id) {
        this.samplingsite_id = samplingsite_id;
    }

    /*
     Gets the completion status of the measurement.
     The completion status.
     */
    public String getDone() {
        return Done;
    }

    /*
     Sets the completion status of the measurement.
      Done The completion status to set.
     */
    public void setDone(String Done) {
        this.Done = Done;
    }
}
