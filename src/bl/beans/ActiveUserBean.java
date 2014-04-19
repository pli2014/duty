package bl.beans;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Indexed;

import java.util.Date;

/**
 * Created by wangronghua on 14-3-15.
 */
@Entity(value = "activeuser")
public class ActiveUserBean extends Bean {
  public static final int STATUS_WECHAT = 1;
  public static final int STATUS_CLIENT = 0;

  @Indexed
  private String userId;
  private String servicePlaceId;
  private int status = 0;   //-1:离线, 0:普通签入, 1:微信签入
  private Date checkInTime;
  private Date checkOutTime;
  private String latitude;
  private String longitude;
  private String precision;

  private transient VolunteerBean volunteer;

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

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public Date getCheckOutTime() {
    return checkOutTime;
  }

  public void setCheckOutTime(Date checkOutTime) {
    this.checkOutTime = checkOutTime;
  }

  public Date getCheckInTime() {
    return checkInTime;
  }

  public void setCheckInTime(Date checkInTime) {
    this.checkInTime = checkInTime;
  }

  public VolunteerBean getVolunteer() {
    return volunteer;
  }

  public void setVolunteer(VolunteerBean volunteer) {
    this.volunteer = volunteer;
  }

  public String getPrecision() {
    return precision;
  }

  public void setPrecision(String precision) {
    this.precision = precision;
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


}
