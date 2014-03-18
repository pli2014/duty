package testwechat;

import util.ServerContext;
import wechat.menu.MenuUtils;
import wechat.menu.WechatButton;
import wechat.menu.WechatMenu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangronghua on 14-3-18.
 */
public class TestMenuUtils {
  public static void main(String[] args) throws FileNotFoundException {
    FileInputStream f = new FileInputStream(new File("/Users/wangronghua/workspace/duty/srcresources/server.properties"));
    ServerContext.init(f);

    WechatMenu menu = new WechatMenu();
    WechatButton button1 = new WechatButton();
    button1.setName("志愿服务");

    WechatButton subbutton11 = new WechatButton();
    subbutton11.setName("服务签入");
    subbutton11.setType("view");
    subbutton11.setUrl("http://www.baidu.com");

    WechatButton subbutton12 = new WechatButton();
    subbutton12.setName("服务签出");
    subbutton12.setType("view");
    subbutton12.setUrl("http://www.baidu.com");

    WechatButton subbutton13 = new WechatButton();
    subbutton13.setName("谁在这里");
    subbutton13.setType("view");
    subbutton13.setUrl("http://www.baidu.com");

    WechatButton button2 = new WechatButton();
    button2.setName("我");

    WechatButton subbutton21 = new WechatButton();
    subbutton21.setName("我的工时");
    subbutton21.setType("click");
    subbutton21.setKey("ME_TIMECARD");

    WechatButton subbutton22 = new WechatButton();
    subbutton22.setName("我的荣誉");
    subbutton22.setType("view");
    subbutton22.setUrl("http://www.baidu.com");

    WechatButton subbutton23 = new WechatButton();
    subbutton23.setName("我的培训");
    subbutton23.setType("view");
    subbutton23.setUrl("http://www.baidu.com");

    WechatButton subbutton24 = new WechatButton();
    subbutton24.setName("我的资料");
    subbutton24.setType("view");
    subbutton24.setUrl("http://www.baidu.com");

    WechatButton button3 = new WechatButton();
    button3.setName("志愿动态");

    WechatButton subbutton31 = new WechatButton();
    subbutton31.setName("志愿者招募");
    subbutton31.setType("view");
    subbutton31.setUrl("http://www.baidu.com");

    WechatButton subbutton32 = new WechatButton();
    subbutton32.setName("我的建议");
    subbutton32.setType("view");
    subbutton32.setUrl("http://www.baidu.com");

    WechatButton subbutton33 = new WechatButton();
    subbutton33.setName("当前活动");
    subbutton33.setType("view");
    subbutton33.setUrl("http://www.baidu.com");

    WechatButton subbutton34 = new WechatButton();
    subbutton34.setName("服务介绍1");
    subbutton34.setType("view");
    subbutton34.setUrl("http://www.baidu.com");

    button1.addSubButton(subbutton11);
    button1.addSubButton(subbutton12);
    button1.addSubButton(subbutton13);

    button2.addSubButton(subbutton21);
    button2.addSubButton(subbutton22);
    button2.addSubButton(subbutton23);
    button2.addSubButton(subbutton24);

    button3.addSubButton(subbutton31);
    button3.addSubButton(subbutton32);
    button3.addSubButton(subbutton33);
    button3.addSubButton(subbutton34);

    List<WechatButton> buttonList = new ArrayList<WechatButton>();
    buttonList.add(button1);
    buttonList.add(button2);
    buttonList.add(button3);

    menu.setButton(buttonList);
    MenuUtils.create(menu);
  }
}
