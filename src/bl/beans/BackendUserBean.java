/**
 * @author gudong
 * @since Date: Mar 12, 2014
 */
package bl.beans;

import org.mongodb.morphia.annotations.Entity;

/**
 * @author gudong
 * 
 */
@Entity(value = "backend_user")
public class BackendUserBean extends Bean {

  private String password;
  private Integer type = 0; // 1=admin

    //默认是打开的,设置后，此用户只可以看到新注册
    private boolean onlySeeNewUser = true;

    public boolean isOnlySeeNewUser() {
        return onlySeeNewUser;
    }

    public void setOnlySeeNewUser(boolean onlySeeNewUser) {
        this.onlySeeNewUser = onlySeeNewUser;
    }

    public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

}
