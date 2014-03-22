package wechat.utils;

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
  }
}
