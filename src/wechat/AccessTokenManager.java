package wechat;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

import java.util.Map;

/**
 * Created by wangronghua on 14-3-9.
 */
public class AccessTokenManager {
  protected final static Logger LOG = LoggerFactory.getLogger(AccessTokenManager.class);

  private static String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                              + Property.APP_ID + "&secret=" + Property.APP_SECRET;
  private static String TOKEN = "";
  private static long EXPIRE_TIMESTAMP = 0;

  public synchronized static String getToken() {
    if(EXPIRE_TIMESTAMP > System.currentTimeMillis()) {
      return TOKEN;
    }
    Map resultMap = HttpClientHelper.get(url);
    if(null != resultMap.get(Constants.ERR_CODE)) {
      LOG.error("Error while getting token from server, errcode:#0", String.valueOf(resultMap.get(Constants.ERR_CODE)));
    } else if(null != resultMap.get(Constants.ACCESS_TOKEN)){
      TOKEN = (String)resultMap.get(Constants.ACCESS_TOKEN);
      String expires = (String)resultMap.get(Constants.EXPIRES_IN);
      EXPIRE_TIMESTAMP = System.currentTimeMillis() + (Integer.valueOf(expires) - 1000) * 1000;
    }
    return TOKEN;
  }

  public static void main(String[] args) {
    AccessTokenManager m = new AccessTokenManager();
    System.out.println(m.getToken());
  }
}
