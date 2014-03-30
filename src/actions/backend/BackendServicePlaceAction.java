package actions.backend;

import bl.beans.ServicePlaceBean;
import bl.constants.BusTieConstant;
import bl.instancepool.SingleBusinessPoolManager;
import bl.mongobus.ServicePlaceBusiness;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.commons.beanutils.BeanUtils;
import org.bson.types.ObjectId;

import util.ServerContext;
import vo.table.TableHeaderVo;
import vo.table.TableInitVo;
import vo.table.TableQueryVo;

import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * Created by peter on 14-3-14.
 */
public class BackendServicePlaceAction extends BaseBackendAction<ServicePlaceBusiness> {
    List<ServicePlaceBean> servicePlaces = null;
    ServicePlaceBean servicePlace = null;
    ServicePlaceBusiness sp = (ServicePlaceBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_SERVICEPLACE);
    private int type = 0;
    private String[] serviceicons = null;

    public String[] getServiceicons() {
        return serviceicons;
    }

    public void setServiceicons(String[] serviceicons) {
        this.serviceicons = serviceicons;
    }

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
        if (this.getId() != null) {
            sp.deleteLeaf(this.getId());
        }
        return ActionSupport.SUCCESS;
    }

    public String servicePlaceAddEdit() {
        //read file list
        File iconList = new File(ServerContext.getValue("realserviceplacedirectory"));
        if(iconList.isDirectory() && iconList.exists()){
            String[] list = iconList.list();
            //convert to virtual path within tomcat service.
            serviceicons = new String[list.length];
            String vitual = ServerContext.getValue("vitualserviceiplacedirectory");
            for (int i = 0; i < list.length; i++) {
                serviceicons[i] = vitual+ list[i];
            }
        }
        if (this.getId() != null) {
            String id = this.getId();
            this.servicePlace = (ServicePlaceBean) this.sp.getLeaf(id).getResponseData();
        }
        return ActionSupport.SUCCESS;
    }

    public String servicePlaceSubmit() throws Exception{
        String id = this.servicePlace.getId();
        if (id != null && !id.isEmpty()) {
            ServicePlaceBean originalBean = (ServicePlaceBean) this.sp.getLeaf(id).getResponseData();
            ServicePlaceBean newBean = (ServicePlaceBean) originalBean.clone();
            BeanUtils.copyProperties(newBean, this.servicePlace);
            sp.updateLeaf(originalBean, newBean);
        } else {
            this.servicePlace.set_id(ObjectId.get());
            this.sp.createLeaf(this.servicePlace);
        }
        return ActionSupport.SUCCESS;
    }

    @Override
    public String getActionPrex() {
        return getRequest().getContextPath() + "/backend/serviceplace";
    }

    @Override
    public String getCustomJs() {
        return super.getCustomJs();
        //return getRequest().getContextPath() + "/js/serviceplace.js";
    }

    @Override
    public TableInitVo getTableInit() {
        TableInitVo init = new TableInitVo();
        init.getAoColumns().add(new TableHeaderVo("sequence", "显示序号").enableSearch());
        init.getAoColumns().add(new TableHeaderVo("code", "地点编码").enableSearch());
        init.getAoColumns().add(new TableHeaderVo("name", "地点名称").enableSearch());
        return init;
    }

    @Override
    public TableQueryVo getModel() {
        TableQueryVo model = super.getModel();
        model.getFilter().put("type", this.type + "");
        return model;
    }

    @Override
    public String getAddButtonParameter(){
         return "type="+this.type;
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

    @Override
    public String getTableTitle() {
        if (this.type == 0)
            return "<ul class=\"breadcrumb\"><li>服务管理</li><li class=\"active\">院内地点</li></ul>";
        else
            return "<ul class=\"breadcrumb\"><li>服务管理</li><li class=\"active\">院外地点</li></ul>";
    }
}
