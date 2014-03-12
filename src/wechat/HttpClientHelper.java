package wechat;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangronghua on 14-3-10.
 */
public class HttpClientHelper {
  protected final static Logger LOG = LoggerFactory.getLogger(HttpClientHelper.class);

  public static Map get(String url) {
    Map resultMap = new HashMap();
    CloseableHttpClient httpclient = HttpClients.createDefault();
    HttpGet httpget = new HttpGet(url);
    try {
      HttpResponse response = httpclient.execute(httpget);
      String resultStr = EntityUtils.toString(response.getEntity());
      JSONObject object = JSONObject.fromObject(resultStr);
      resultMap = (Map)JSONObject.toBean(object, Map.class);
    } catch (IOException e) {
      LOG.error(e.getMessage());
    } finally {
      try {
        httpclient.close();
      } catch (IOException e) {
        LOG.error(e.getMessage());
      }
    }
    return resultMap;
  }

  public static void post(String url) {

  }
}
