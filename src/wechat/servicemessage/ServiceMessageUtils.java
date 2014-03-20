package wechat.servicemessage;

import net.sf.json.JSONObject;
import wechat.AccessTokenManager;
import wechat.HttpClientHelper;
import wechat.URLManager;

import java.io.ByteArrayInputStream;

/**
 * Created by wangronghua on 14-3-19.
 */
public class ServiceMessageUtils {

  public static void sendMessage(ServiceMessage message) {
    String url = URLManager.getUrl_ServiceMessage(AccessTokenManager.getToken());
    JSONObject object = JSONObject.fromObject(message);
    HttpClientHelper.post(url, new ByteArrayInputStream(object.toString().getBytes()));
  }
}
