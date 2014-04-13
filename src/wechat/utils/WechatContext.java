package wechat.utils;

import bl.constants.BusTieConstant;
import bl.instancepool.SingleBusinessPoolManager;
import bl.mongobus.SystemSettingBusiness;
import wechat.message.EventHandler;
import wechat.message.MessageBus;
import wechat.message.SubscribeEventHandler;

/**
 * Created by wangronghua on 14-3-19.
 */
public class WechatContext {

  public static void init() {
    EventHandler handler = new SubscribeEventHandler();
    MessageBus.get().addHandler(handler);

    /**load system setting from mongo db**/
    SystemSettingBusiness ssb = (SystemSettingBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_SYSTEMSETTING);
    ssb.loadServerContext();
  }
}
