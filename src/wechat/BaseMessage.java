package wechat;

import java.util.Map;

/**
 * Created by wangronghua on 14-3-11.
 */
public class BaseMessage {

  private String ToUserName;
  private String FromUserName;
  private long CreateTime;
  private String MsgType;



  public void fill(Map paraMap) {
    this.setToUserName((String)paraMap.get("ToUserName"));
    this.setFromUserName((String)paraMap.get("FromUserName"));
    this.setCreateTime((Long)paraMap.get("CreateTime"));
    this.setMsgType((String)paraMap.get("MsgType"));
  }

  public String getToUserName() {
    return ToUserName;
  }

  public void setToUserName(String toUserName) {
    ToUserName = toUserName;
  }

  public String getFromUserName() {
    return FromUserName;
  }

  public void setFromUserName(String fromUserName) {
    FromUserName = fromUserName;
  }

  public long getCreateTime() {
    return CreateTime;
  }

  public void setCreateTime(long createTime) {
    CreateTime = createTime;
  }

  public String getMsgType() {
    return MsgType;
  }

  public void setMsgType(String msgType) {
    MsgType = msgType;
  }

}
