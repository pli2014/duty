package wechat.messagehandler;

import wechat.BaseEvent;
import wechat.BaseMessage;
import wechat.requestbean.*;
import wechat.responsebean.TextResponse;
import wechat.responsebean.VoiceResponse;

/**
 * Created by wangronghua on 14-3-11.
 */
public class DefaultMessageHandler implements MessageHandler{

  @Override
  public BaseMessage handle(BaseMessage message) {
    return null;
  }

  @Override
  public BaseMessage handle(TextRequest text) {
    TextResponse response = new TextResponse();
    response.setToUserName(text.getFromUserName());
    response.setFromUserName(text.getToUserName());
    response.setCreateTime(System.currentTimeMillis()/1000);
    response.setContent(text.getContent());
    return response;
  }

  @Override
  public BaseMessage handle(VoiceRequest voice) {
    VoiceResponse response = new VoiceResponse();
    response.setToUserName(voice.getFromUserName());
    response.setFromUserName(voice.getToUserName());
    response.setCreateTime(System.currentTimeMillis()/1000);
    response.setMediaId(voice.getMediaId());
    return response;
  }

  @Override
  public BaseMessage handle(VideoRequest video) {
    return null;
  }

  @Override
  public BaseMessage handle(ImageRequest image) {
    return null;
  }

  @Override
  public BaseMessage handle(LinkRequest link) {
    return null;
  }

  @Override
  public BaseMessage handle(LocationRequest location) {
    return null;
  }

  @Override
  public BaseMessage handle(BaseEvent event) {
    return null;
  }

  @Override
  public BaseMessage handle(SubscribeEvent event) {
    TextResponse response = new TextResponse();
    response.setToUserName(event.getFromUserName());
    response.setFromUserName(event.getToUserName());
    response.setCreateTime(System.currentTimeMillis()/1000);
    response.setContent("欢迎使用千年老二测试系统！");
    return response;
  }

  @Override
  public BaseMessage handle(UnSubscribeEvent event) {
    return null;
  }

  @Override
  public BaseMessage handle(LocationEvent event) {
    return null;
  }

  @Override
  public BaseMessage handle(ClickEvent event) {
    return null;
  }

  @Override
  public BaseMessage handle(ViewEvent event) {
    return null;
  }
}
