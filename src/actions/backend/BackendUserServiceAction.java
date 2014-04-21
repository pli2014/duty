package actions.backend;

import bl.mongobus.UserServiceBusiness;
import vo.table.TableHeaderVo;
import vo.table.TableInitVo;
import vo.table.TableQueryVo;

/**
 * Created by peter on 06-04-21.
 */
public class BackendUserServiceAction extends BaseBackendAction<UserServiceBusiness> {

    @Override
    public String getActionPrex() {
        return getRequest().getContextPath() + "/backend/userservice";
    }
    @Override
    public TableQueryVo getModel() {
        if (model == null) {
            model = new TableQueryVo();
        }
        //默认按着签入的降序显示在TableIndex.jsp
        model.getSort().remove("userName");
        model.getSort().put("checkInTime","desc");
        model.getFilter().put("isDeleted_!=", true);
        return model;
    }
    @Override
    public TableInitVo getTableInit() {
        TableInitVo init = new TableInitVo();
        init.getAoColumns().add(new TableHeaderVo("userName", "志愿者").enableSearch());
        init.getAoColumns().add(new TableHeaderVo("userCode", "工号").enableSearch());
        init.getAoColumns().add(new TableHeaderVo("checkInMethod", "签到方式"));
        init.getAoColumns().add(new TableHeaderVo("servicePlaceName", "服务地点").enableSearch());
        init.getAoColumns().add(new TableHeaderVo("checkInTime", "签入时间"));
        init.getAoColumns().add(new TableHeaderVo("checkOutTime", "签出时间"));
        return init;
    }

    @Override
    public String getCustomJsp() {
        return "/pages/userservice/userservicegrid.jsp";
    }

    @Override
    public String getTableTitle() {
        return "<ul class=\"breadcrumb\"><li>工时管理</li><li class=\"active\">签到记录</li></ul>";
    }
}
