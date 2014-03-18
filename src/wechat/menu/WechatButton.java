package wechat.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangronghua on 14-3-18.
 */
public class WechatButton {

  private String name;
  private String type;
  private String key;
  private String url;
  private List<WechatButton> sub_button;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public List<WechatButton> getSub_button() {
    return sub_button;
  }

  public void setSub_button(List<WechatButton> sub_button) {
    this.sub_button = sub_button;
  }

  public  void addSubButton(WechatButton button) {
    if(null != sub_button) {
      sub_button.add(button);
    } else  {
      sub_button = new ArrayList<WechatButton>();
      sub_button.add(button);
    }
  }
}
