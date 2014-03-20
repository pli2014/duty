package wechat.utils;

import wechat.messagehandler.EventHandler;
import wechat.messagehandler.MessageBus;
import wechat.messagehandler.SubscribeEventHandler;

/**
 * Created by wangronghua on 14-3-19.
 */
public class WechatContext {

  public static void init() {
    EventHandler handler = new SubscribeEventHandler();
    MessageBus.get().addHandler(handler);
  }
}
