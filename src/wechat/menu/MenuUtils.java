package wechat.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.ServerContext;
import wechat.access.AccessTokenManager;
import wechat.utils.Constants;
import wechat.HttpClientHelper;
import wechat.utils.URLManager;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by wangronghua on 14-3-18.
 */
public class MenuUtils {
  protected final static Logger LOG = LoggerFactory.getLogger(HttpClientHelper.class);

  public static String getRedirectUrl(String url) throws UnsupportedEncodingException {
    return ServerContext.getValue("domainname") + "/wechat/redirect.action?url=" + URLEncoder.encode(url, "UTF-8");
  }

  public static String getOAuthUrl(String url) throws UnsupportedEncodingException {
    return URLManager.getUrl_OAuthRedirect(ServerContext.getValue("domainname") + url, ServerContext.getValue("appID"));
  }

  public static boolean create(InputStream in) {
    Map resultMap = HttpClientHelper.post(URLManager.getUrl_MenuCreate(AccessTokenManager.getToken()), in);
    if(null != resultMap && (Integer)(resultMap.get(Constants.ERR_CODE)) == 0) {
      return true;
    }
    return false;
  }

  public static boolean create(File file) {
    try {
      InputStream inputStream = new FileInputStream(file);
      return create(inputStream);
    } catch (FileNotFoundException e) {
      LOG.error("File not found", e);
      return false;
    }
  }

  public static boolean create(WechatMenu menu) {
    if(null != menu) {
      String jsonMenu = menu.toJson();
      return create(new ByteArrayInputStream(jsonMenu.getBytes()));
    } else {
      LOG.error("Menu object is null!");
      return false;
    }

  }
}
