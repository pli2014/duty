package bl.mongobus;

import bl.beans.SystemSettingBean;
import bl.beans.VolunteerBean;
import bl.common.BeanContext;
import bl.common.BusinessResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.ServerContext;
import wechat.utils.Constants;

import java.util.List;

/**
 * Created by peter on 14-3-18.
 */
public class SystemSettingBusiness extends MongoCommonBusiness<BeanContext, SystemSettingBean> {
    private static Logger log = LoggerFactory.getLogger(VolunteerBusiness.class);

    public SystemSettingBusiness() {
        //this.dbName = "form";
        this.clazz = SystemSettingBean.class;
    }

    public SystemSettingBean getLeaf() {
      List<SystemSettingBean> ssBean = (List<SystemSettingBean>) this.getAllLeaves().getResponseData();
      if (ssBean == null || ssBean.size() == 0) {
        SystemSettingBean newSys = new SystemSettingBean();
        newSys.setName("系统设定");
        this.createLeaf(newSys);
        return newSys;
      } else {
        return ssBean.get(0);
      }
    }
    public void loadServerContext(){
      List<SystemSettingBean> ssBean = (List<SystemSettingBean>) this.getAllLeaves().getResponseData();
      if( ssBean!= null && ssBean.size() > 0) {
        SystemSettingBean setting = ssBean.get(0);
        String appID = setting.getAppID();
        if(null != appID) {
          ServerContext.putValue(Constants.APP_ID, appID);
        }

        String appsecret = setting.getAppsecret();
        if(null != appsecret) {
          ServerContext.putValue(Constants.APP_SECRET, appsecret);
        }

        String apptoken = setting.getApptoken();
        if(null != apptoken) {
          ServerContext.putValue(Constants.APP_TOKEN, apptoken);
        }

        String domainname = setting.getDomainname();
        if(null != domainname) {
          ServerContext.putValue(Constants.DOMAIN_NAME, domainname);
        }

        boolean debugMode = setting.isDebugMode();
        if(debugMode) {
          ServerContext.putValue(Constants.WECHAT_DEBUG, "true");
        } else {
          ServerContext.putValue(Constants.WECHAT_DEBUG, "false");
        }
      }
    }
}
