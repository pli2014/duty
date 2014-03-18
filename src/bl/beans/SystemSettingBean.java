package bl.beans;

/**
 * Created by Administrator on 14-3-18.
 */
public class SystemSettingBean extends Bean {
    private String maptoken="URs4GQ1uMjGhGK4kfub7lXUt";
    private String city="南京";

    public String getMaptoken() {
        return maptoken;
    }

    public void setMaptoken(String maptoken) {
        this.maptoken = maptoken;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
