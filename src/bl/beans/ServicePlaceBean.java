package bl.beans;

import org.mongodb.morphia.annotations.Entity;

/**
 * Created by Administrator on 14-3-14.
 */
@Entity(value = "serviceplace")
public class ServicePlaceBean extends Bean {
    private String code="";
    private int type = 0; // 0 院内 含有颜色显示信息  1 院外 含有坐标信息
    private String color="white"; //RGB颜色值 default 白色

    private float longitude; //经度坐标
    private float latitude;  //纬度坐标

    private String serviceicon="";

    private int sequence;

    public String getServiceicon() {
        return serviceicon;
    }

    public void setServiceicon(String serviceicon) {
        this.serviceicon = serviceicon;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public int getType() {
        return type;
    }



    public void setType(int type) {
        this.type = type;
    }

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
