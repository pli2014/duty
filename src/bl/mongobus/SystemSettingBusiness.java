package bl.mongobus;

import bl.beans.SystemSettingBean;
import bl.beans.VolunteerBean;
import bl.common.BeanContext;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

/**
 * Created by peter on 14-3-18.
 */
public class SystemSettingBusiness extends MongoCommonBusiness<BeanContext, SystemSettingBean> {
    private static Logger log = LoggerFactory.getLogger(VolunteerBusiness.class);

    public SystemSettingBusiness() {
        this.dbName = "form";
        this.clazz = SystemSettingBean.class;
    }
}
