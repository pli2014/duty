package actions;

import bl.beans.ServicePlaceBean;
import bl.constants.BusTieConstant;
import bl.instancepool.SingleBusinessPoolManager;
import bl.mongobus.ServicePlaceBusiness;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.beanutils.BeanUtils;
import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.List;

/**
 * Created by peter on 14-3-14.
 */
public class ServicePlaceAction extends ActionSupport {
    List<ServicePlaceBean> servicePlaces = null;
    ServicePlaceBean servicePlace = null;
    ServicePlaceBusiness sp = (ServicePlaceBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_SERVICEPLACE);
    private int type = 0;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String servicePlaceList() {
        HashMap<String,Integer> filterByType = new HashMap<String,Integer>();
        filterByType.put("type",this.type);
        this.servicePlaces = (List<ServicePlaceBean>) sp.queryDataByCondition(filterByType, null);
        return ActionSupport.SUCCESS;
    }

    public String servicePlaceDelete() {
        if (this.servicePlace.get_id() != null) {
            sp.deleteLeaf(this.servicePlace.getId());
        }
        return ActionSupport.SUCCESS;
    }

    public String servicePlaceAddEdit() {
        if (this.servicePlace != null) {
            String id = this.servicePlace.getId();
            this.servicePlace = (ServicePlaceBean) this.sp.getLeaf(id).getResponseData();
        }
        return ActionSupport.SUCCESS;
    }

    public String servicePlaceSubmit() {
        String id = this.servicePlace.getId();
        try {
            if (id != null && !id.isEmpty()) {
                ServicePlaceBean originalBean = (ServicePlaceBean) this.sp.getLeaf(id).getResponseData();
                ServicePlaceBean newBean = (ServicePlaceBean) originalBean.clone();
                BeanUtils.copyProperties(newBean, this.servicePlace);
                sp.updateLeaf(originalBean, newBean);
            } else {
                this.servicePlace.set_id(ObjectId.get());
                this.sp.createLeaf(this.servicePlace);
            }

        } catch (Exception e) {
            LOG.error("this exception [#0]", e.getMessage());
        }
        return ActionSupport.SUCCESS;
    }

    public List<ServicePlaceBean> getServicePlaces() {
        return servicePlaces;
    }

    public void setServicePlaces(List<ServicePlaceBean> servicePlaces) {
        this.servicePlaces = servicePlaces;
    }

    public ServicePlaceBean getServicePlace() {
        return servicePlace;
    }

    public void setServicePlace(ServicePlaceBean servicePlace) {
        this.servicePlace = servicePlace;
    }
}
