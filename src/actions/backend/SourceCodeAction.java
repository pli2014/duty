package actions.backend;

import bl.beans.SourceCodeBean;
import bl.mongobus.SourceCodeBusiness;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.beanutils.BeanUtils;
import org.bson.types.ObjectId;
import vo.table.TableHeaderVo;
import vo.table.TableInitVo;

/**
 * Created by peter on 06-04-14.
 */
public class SourceCodeAction extends BaseBackendAction<SourceCodeBusiness> {
    private SourceCodeBean sourceCode = null;

    public SourceCodeBean getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(SourceCodeBean sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String delete() {
        if (getIds() != null) {
            for (String id : getIds()) {
                getBusiness().deleteLeaf(id);
            }
        }
        return ActionSupport.SUCCESS;
    }

    public String add() {
        return ActionSupport.SUCCESS;
    }

    public String edit() {
        this.sourceCode = (SourceCodeBean) getBusiness().getLeaf(getId()).getResponseData();
        return ActionSupport.SUCCESS;
    }

    public String save() throws Exception {
        String id = this.sourceCode.getId();
        if (id != null && !id.isEmpty()) {
            SourceCodeBean originalBean = (SourceCodeBean) getBusiness().getLeaf(id).getResponseData();
            SourceCodeBean newBean = (SourceCodeBean) originalBean.clone();
            BeanUtils.copyProperties(newBean, this.sourceCode);
            getBusiness().updateLeaf(originalBean, newBean);
        } else {
            this.sourceCode.set_id(ObjectId.get());
            getBusiness().createLeaf(this.sourceCode);
        }
        return ActionSupport.SUCCESS;
    }


    @Override
    public String getActionPrex() {
        return getRequest().getContextPath() + "/backend/sourcecode";
    }

    @Override
    public TableInitVo getTableInit() {
        TableInitVo init = new TableInitVo();
        TableHeaderVo th = new TableHeaderVo("code", "编码");
        th.setbSortable(true);
        th.enableSearch();
        init.getAoColumns().add(th);
        init.getAoColumns().add(new TableHeaderVo("name", "名称").enableSearch());
        return init;
    }

    @Override
    public String getCustomJs() {
        return null;
    }

    @Override
    public String getTableTitle() {
        return "<ul class=\"breadcrumb\"><li>系统管理</li><li class=\"active\">来源编码</li></ul>";
    }
}
