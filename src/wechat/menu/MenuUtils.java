package wechat.menu;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import util.ServerContext;
import wechat.AccessTokenManager;
import wechat.Constants;
import wechat.HttpClientHelper;
import wechat.URLManager;

import java.io.*;
import java.util.Map;

/**
 * Created by wangronghua on 14-3-18.
 */
public class MenuUtils {
  protected final static Logger LOG = LoggerFactory.getLogger(HttpClientHelper.class);

  public static boolean create(InputStream in) {
    Map resultMap = HttpClientHelper.post(URLManager.getUrl_MenuCreate(AccessTokenManager.getToken()), in);
    if(null != resultMap && resultMap.get(Constants.ERR_CODE) == 0) {
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
