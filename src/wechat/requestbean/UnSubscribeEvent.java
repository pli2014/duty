package wechat.requestbean;

import wechat.BaseEvent;
import wechat.BaseMessage;
import wechat.messagehandler.MessageHandler;

/**
 * Created by wangronghua on 14-3-15.
 */
public class UnSubscribeEvent extends BaseEvent{
  public BaseMessage accept(MessageHandler handler) {
    return handler.handle(this);
  }
}
