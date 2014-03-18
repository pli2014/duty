package wechat.menu;

import net.sf.json.JSONObject;

import java.util.List;

/**
 * Created by wangronghua on 14-3-18.
 */
public class WechatMenu {

  public List<WechatButton> getButton() {
    return button;
  }

  public void setButton(List<WechatButton> button) {
    this.button = button;
  }

  private List<WechatButton> button;

  public String toJson() {
    JSONObject object = JSONObject.fromObject(this);
    return object.toString();
  }

}
