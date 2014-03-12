package wechat;

import java.util.Map;

/**
 * Created by wangronghua on 14-3-10.
 */
public class DispatcherService {

  private static final String MSG_TYPE_TEXT = "text";
  private static final String MSG_TYPE_IMAGE = "image";
  private static final String MSG_TYPE_VOICE = "voice";
  private static final String MSG_TYPE_VIEDO = "video";
  private static final String MSG_TYPE_LOCATION = "location";
  private static final String MSG_TYPE_LINK = "link";
  private static final String MSG_TYPE_EVENT = "event";

  public String process(Map rMap) {
    String msgType = (String)rMap.get(Constants.MSG_TYPE);

    return "";
  }
}
