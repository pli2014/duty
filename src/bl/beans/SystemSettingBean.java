package bl.beans;

import org.mongodb.morphia.annotations.Entity;

/**
 * Created by Administrator on 14-3-18.
 */
@Entity(value = "systemsetting")
public class SystemSettingBean extends Bean {
    private String maptoken="URs4GQ1uMjGhGK4kfub7lXUt";
    private String city="南京";
    private String introduction;

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

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
