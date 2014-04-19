package wechat.utils;

import wechat.request.LocationEvent;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangronghua on 14-4-19.
 */
public class LocationCache {

  private static ConcurrentHashMap<String, LocationEvent> dataMap = new ConcurrentHashMap<String, LocationEvent>();
  private LocationCache(){}

  static {
    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        while(!Thread.interrupted()) {
          try {
            Thread.sleep(60000);
            //todo remove over time data
          } catch (InterruptedException e) {
            break;
          }
        }
      }
    }) ;
    thread.setDaemon(true);
    thread.start();
  }

  public static void putLocation(LocationEvent locationEvent) {
    dataMap.put(locationEvent.getFromUserName(), locationEvent);
  }

  public static LocationEvent getLocation(String openID) {
    return dataMap.get(openID);
  }
}
