package wechat.servicemessage;

import net.sf.json.JSONObject;
import wechat.accessmanagement.AccessTokenManager;
import wechat.HttpClientHelper;
import wechat.utils.URLManager;

/**
 * Created by wangronghua on 14-3-19.
 */
public class ServiceMessageUtils {

  public static void sendMessage(ServiceMessage message) {
    String url = URLManager.getUrl_ServiceMessage(AccessTokenManager.getToken());
    JSONObject object = JSONObject.fromObject(message);
    HttpClientHelper.post(url, object.toString());
  }
}
