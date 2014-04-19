package wechat.utils;

import bl.constants.BusTieConstant;
import bl.instancepool.SingleBusinessPoolManager;
import bl.mongobus.SystemSettingBusiness;
import wechat.message.*;

/**
 * Created by wangronghua on 14-3-19.
 */
public class WechatContext {

  public static void init() {
    MessageBus.get().addHandler(new SubscribeEventHandler());
    MessageBus.get().addHandler(new UnSubscriberEventHandler());
    MessageBus.get().addHandler(new LocationEventHandler());
    /**load system setting from mongo db**/
    SystemSettingBusiness ssb = (SystemSettingBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_SYSTEMSETTING);
    ssb.loadServerContext();
  }
}
