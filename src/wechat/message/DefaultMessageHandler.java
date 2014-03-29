package wechat.message;

import bl.beans.VolunteerBean;
import bl.constants.BusTieConstant;
import bl.instancepool.SingleBusinessPoolManager;
import bl.mongobus.UserServiceBusiness;
import bl.mongobus.VolunteerBusiness;
import wechat.BaseEvent;
import wechat.BaseMessage;
import wechat.request.*;
import wechat.response.TextResponse;
import wechat.response.VoiceResponse;

/**
 * Created by wangronghua on 14-3-11.
 */
public class DefaultMessageHandler implements MessageHandler {

  @Override
  public BaseMessage handle(BaseMessage message) {
    return null;
  }

  @Override
  public BaseMessage handle(TextRequest text) {
    TextResponse response = new TextResponse();
    response.setToUserName(text.getFromUserName());
    response.setFromUserName(text.getToUserName());
    response.setCreateTime(System.currentTimeMillis() / 1000);
    response.setContent(text.getContent());
    return response;
  }

  @Override
  public BaseMessage handle(VoiceRequest voice) {
    VoiceResponse response = new VoiceResponse();
    response.setToUserName(voice.getFromUserName());
    response.setFromUserName(voice.getToUserName());
    response.setCreateTime(System.currentTimeMillis() / 1000);
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
    response.setCreateTime(System.currentTimeMillis() / 1000);
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
    if (event.getEventKey().equals("ME_TIMECARD")) {
      TextResponse response = new TextResponse();
      response.setToUserName(event.getFromUserName());
      response.setFromUserName(event.getToUserName());
      response.setCreateTime(System.currentTimeMillis() / 1000);
      // 直接把当月工时，当年工时发给用户就好了
      UserServiceBusiness userServiceBus = (UserServiceBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_USERSERVICE);
      VolunteerBusiness volunteerBus = (VolunteerBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_VOLUNTEER);

      VolunteerBean volunteer = volunteerBus.getVolunteerBeanByOpenID(event.getFromUserName());
      String text = "获取用户信息失败!";
      if (volunteer != null) {
        double hoursForMonth = userServiceBus.getServicedHoursForCurrentMonth(volunteer.getId());
        double hoursForYear = userServiceBus.getServicedHoursForCurrentMonth(volunteer.getId());
        text = "您当月服务" + hoursForMonth + "小时，当年服务" + hoursForYear + "小时.";
      }
      response.setContent(text);
      return response;
    }
    return null;
  }

  @Override
  public BaseMessage handle(ViewEvent event) {
    return null;
  }
}
