package wechat.requestbean;

import wechat.BaseMessage;
import wechat.messagehandler.MessageHandler;

/**
 * Created by wangronghua on 14-3-11.
 */
public class LinkRequest extends BaseMessage {

  public BaseMessage accept(MessageHandler handler) {
    return handler.handle(this);
  }
}
