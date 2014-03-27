package interceptor;

import actions.BaseAction;
import actions.wechat.WechatBaseAuthAction;
import bl.beans.VolunteerBean;
import bl.constants.BusTieConstant;
import bl.exceptions.MiServerException;
import bl.instancepool.SingleBusinessPoolManager;
import bl.mongobus.VolunteerBusiness;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import util.ServerContext;
import wechat.access.AccessToken;
import wechat.access.AccessTokenManager;
import wechat.user.UerManager;
import wechat.user.UserInfo;

import java.util.Map;

/**
 * Created by wangronghua on 14-3-27.
 */
public class WechatInterceptor extends AbstractInterceptor {

  protected static Logger log = LoggerFactory.getLogger(WechatInterceptor.class);

  @Override
  public String intercept(ActionInvocation invocation) throws Exception {

    Map<String, Object> parameters = invocation.getInvocationContext().getParameters();

    if(invocation.getAction() instanceof WechatBaseAuthAction) {
      WechatBaseAuthAction action = (WechatBaseAuthAction) invocation.getAction();
      String openID = null;
      String wechatUser = null;
      if(ServerContext.isWechatLocalDebug()) {
        openID = "test";
        wechatUser = "test";
      } else {
        String code = (String)parameters.get("code");
        if(null != code) {
          AccessToken token = AccessTokenManager.getAccessToken(code);
          UserInfo info = UerManager.getUserInfo(token.getAccess_token(), token.getOpenid());
          if (null != info) {
            openID = info.getOpenid();
            wechatUser = info.getNickname();
          } else {
            action.addActionError("发生位置错误，获取用户授权失败！");
            return "message";
          }
        }
      }

      action.setOpenID(openID);
      action.setWechatUser(wechatUser);

      if(null != openID) {
        VolunteerBean volunteer;
        VolunteerBusiness vb = (VolunteerBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_VOLUNTEER);
        volunteer = vb.getVolunteerBeanByOpenID(openID);
        if(null != volunteer) {
          action.setVolunteer(volunteer);
          if(null == wechatUser) {
            wechatUser = volunteer.getWechat();
            action.setWechatUser(wechatUser);
          }
        }
      }
    }

    String result = "message";
    try {
      result = invocation.invoke();
    } catch (MiServerException e) {
      log.error("Exception happened:", e);
      if(invocation.getAction() instanceof BaseAction) {
        ((BaseAction)invocation.getAction()).addActionError(e.getMessage());
      }
    }
    return result;
  }
}
