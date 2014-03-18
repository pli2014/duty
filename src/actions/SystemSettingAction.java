package actions;

import bl.beans.ServicePlaceBean;
import bl.beans.SystemSettingBean;
import bl.constants.BusTieConstant;
import bl.instancepool.SingleBusinessPoolManager;
import bl.mongobus.ServicePlaceBusiness;
import bl.mongobus.SystemSettingBusiness;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import common.Constants;
import org.apache.commons.beanutils.BeanUtils;

import java.util.List;

/**
 * Created by peter on 14-3-18.
 */
public class SystemSettingAction extends ActionSupport {
    private SystemSettingBean systemSetting;
    private static SystemSettingBusiness ssb = (SystemSettingBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_SYSTEMSETTING);

    public SystemSettingBean getSystemSetting() {
        return systemSetting;
    }

    public void setSystemSetting(SystemSettingBean systemSetting) {
        this.systemSetting = systemSetting;
    }

    /**
     * In this system, it should be a unique object for handing global setting.
     *
     * @return SystemSettingBean
     */
    public synchronized static SystemSettingBean init() {
        List<SystemSettingBean> ssBean = (List<SystemSettingBean>) ssb.getAllLeaves().getResponseData();
        if (ssBean == null || ssBean.size() == 0) {
            SystemSettingBean newSys = new SystemSettingBean();
            newSys.setName("系统设定");
            ssb.createLeaf(newSys);
            return newSys;
        } else {
            return ssBean.get(0);
        }
    }

    public String modify() {
        this.systemSetting = (SystemSettingBean) ActionContext.getContext().getApplication().get(Constants.GLOBALSETTING);
        return SUCCESS;
    }

    public String save() {
        try {
            SystemSettingBean originalBean = (SystemSettingBean) ssb.getLeaf(this.systemSetting.getId()).getResponseData();
            SystemSettingBean newBean = (SystemSettingBean) originalBean.clone();
            BeanUtils.copyProperties(newBean, this.systemSetting);
            ssb.updateLeaf(originalBean, newBean);
            ActionContext.getContext().getApplication().put(Constants.GLOBALSETTING, newBean);
            super.addActionMessage("系统参数设定保存成功");
        } catch (Exception e) {
            LOG.error("this exception [#0]", e.getMessage());
        }
        return SUCCESS;
    }
}
