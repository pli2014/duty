package actions;

import bl.beans.UserBean;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

/**
 * Created by wangronghua on 14-3-8.
 */
public class VolunteerAction extends ActionSupport {
  private String code;
  private String name;
  private String state;
  private List<UserBean> volunteers;

  public String query(){
    return SUCCESS;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public List<UserBean> getVolunteers() {
    return volunteers;
  }

  public void setVolunteers(List<UserBean> volunteers) {
    this.volunteers = volunteers;
  }

}
