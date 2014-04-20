package bl.beans;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Transient;

import java.util.Date;

/**
 * Created by wangronghua on 14-3-15.
 */
@Entity(value = "user_serviceplace")
public class UserServiceBean extends Bean{
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getServicePlaceId() {
    return servicePlaceId;
  }

  public void setServicePlaceId(String servicePlaceId) {
    this.servicePlaceId = servicePlaceId;
  }

  public String getCheckInMethod() {
    return checkInMethod;
  }

  public void setCheckInMethod(String checkInMethod) {
    this.checkInMethod = checkInMethod;
  }

  public Date getCheckInTime() {
    return checkInTime;
  }

  public void setCheckInTime(Date checkInTime) {
    this.checkInTime = checkInTime;
  }

  public Date getCheckOutTime() {
    return checkOutTime;
  }

  public void setCheckOutTime(Date checkOutTime) {
    this.checkOutTime = checkOutTime;
  }


  public ServicePlaceBean getServicePlaceBean() {
    return servicePlaceBean;
  }

  public void setServicePlaceBean(ServicePlaceBean servicePlaceBean) {
    this.servicePlaceBean = servicePlaceBean;
  }

  public VolunteerBean getUserBean() {
    return userBean;
  }

  public void setUserBean(VolunteerBean userBean) {
    this.userBean = userBean;
  }

  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public String getPrecision() {
    return precision;
  }

  public void setPrecision(String precision) {
    this.precision = precision;
  }

  public String getCheckOutLatitude() {
    return checkOutLatitude;
  }

  public void setCheckOutLatitude(String checkOutLatitude) {
    this.checkOutLatitude = checkOutLatitude;
  }

  public String getCheckOutLongitude() {
    return checkOutLongitude;
  }

  public void setCheckOutLongitude(String checkOutLongitude) {
    this.checkOutLongitude = checkOutLongitude;
  }

  public String getCheckOutPrecision() {
    return checkOutPrecision;
  }

  public void setCheckOutPrecision(String checkOutPrecision) {
    this.checkOutPrecision = checkOutPrecision;
  }

  @Indexed
  private String userId;
  @Indexed
  private String servicePlaceId;
  private String checkInMethod; //0:普通签入, 1:微信签入

  private String latitude;
  private String longitude;
  private String precision;
  private String checkOutLatitude;

  private String checkOutLongitude;
  private String checkOutPrecision;
  private Date checkInTime;
  private Date checkOutTime;

  @Transient
  private VolunteerBean userBean;
  @Transient
  private ServicePlaceBean servicePlaceBean;

    private double distance=Integer.MAX_VALUE;
    private String description="未知距离";

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
