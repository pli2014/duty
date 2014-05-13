package util;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerContext {
    protected static Logger LOG = LoggerFactory.getLogger(ServerContext.class);
    private final static Properties prop = new Properties();

    public static void init(InputStream input) {
        // 从流中加载properties文件信息
        try {
            prop.load(input);
        } catch (Exception e) {
            LOG.error("this exception [{}]", e.getMessage());
        }
    }

    public static String getValue(String key) {
        if (prop != null) {
            return prop.getProperty(key);
        }
        return "";
    }

    public static void putValue(String key, String value) {
        if(prop != null) {
          prop.put(key, value);
        } else {
          LOG.error("ServerContext property is not ready for Wechat!");
        }
    }

    public static String getDBFlag(String key) {
      String result = getValue(key);
      return "".equals(result) ? "form" : result;
    }

    public static boolean isWechatLocalDebug() {
        if (null != prop) {
            return "true".equals(getValue("wechat.debug.local"));
        }
        return false;
    }
}
