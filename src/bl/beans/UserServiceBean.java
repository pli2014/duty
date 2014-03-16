package bl.beans;

import org.mongodb.morphia.annotations.Entity;
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

  private String userId;
  private String servicePlaceId;
  private String checkInMethod; //0:普通签入, 1:微信签入
  private Date checkInTime;
  private Date checkOutTime;

  @Transient
  private VolunteerBean userBean;
  @Transient
  private ServicePlaceBean servicePlaceBean;
}
