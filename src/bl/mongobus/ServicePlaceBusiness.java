package bl.mongobus;

import bl.beans.ServicePlaceBean;
import bl.common.BeanContext;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

public class ServicePlaceBusiness extends MongoCommonBusiness<BeanContext, ServicePlaceBean> {
    private static Logger LOG = LoggerFactory.getLogger(ServicePlaceBusiness.class);
    public ServicePlaceBusiness() {
        this.clazz = ServicePlaceBean.class;
    }
}
