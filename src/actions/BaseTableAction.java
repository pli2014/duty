/**
 * 
 */
package actions;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import vo.table.TableDataVo;
import vo.table.TableHeaderVo;
import vo.table.TableInitVo;
import vo.table.TableQueryVo;
import bl.common.TableBusinessInterface;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

/**
 * Base Table Action
 * 
 * @author gudong
 * @since $Date:2014-02-20$
 */
public abstract class BaseTableAction<B extends TableBusinessInterface> extends BaseAction implements ModelDriven<TableQueryVo> {
  private static Logger log = LoggerFactory.getLogger(BaseTableAction.class);
  protected TableQueryVo model;
  protected TableBusinessInterface business;
  public static final String INDEX_SUCCESS = "tableIndex";

  /**
   * The Action Prefix that will be append action. like : getRequest().getContextPath() + "/datatable".
   * 
   * @return
   */
  public abstract String getActionPrex();

    /**
     *  In the TableIndex.jsp, there is a operation named "添加", sometime, need to be brought some parameters.
     * @return
     */
  public String getAddButtonParameter(){
      return "";
  }
  /**
   * 
   * @return
   */
  public String getCustomJs() {
    return null;
  };

  public String getTableTitle() {
    return null;
  }

  public String getTableId() {
    return this.getClass().getSimpleName() + "_table";
  }

  /**
   * 
   * @return
   */
  public abstract TableInitVo getTableInit();

  /**
   * 
   * @return
   */
  public B getBusiness() {
    if (business == null) {
      ParameterizedType t = (ParameterizedType) (this.getClass().getGenericSuperclass());
      Type[] ts = t.getActualTypeArguments();
      try {
        business = (B) ((Class<B>) ts[0]).newInstance();
      } catch (InstantiationException e) {
        log.error("get business error!", e);
        business = null;
      } catch (IllegalAccessException e) {
        log.error("get business error!", e);
        business = null;
      }
    }
    return (B) business;
  }

  @Override
  public TableQueryVo getModel() {
    if (model == null) {
      model = new TableQueryVo();
    }
    model.getFilter().put("isDeleted_!=", true);
    return model;
  }

  /**
   * 
   * @return
   * @throws Exception
   */
  public String index() throws Exception {
    return INDEX_SUCCESS;
  }

  /**
   * initTable
   * 
   * @return
   * @throws Exception
   */
  public String initTable() throws Exception {
    // json
    JsonConfig config = new JsonConfig();
    config.setExcludes(new String[] { "searchOptions" });
    TableInitVo ti = getTableInit();
    TableHeaderVo createTime = new TableHeaderVo("createTime", "创建时间");
    createTime.setbSearchable(false);
    ti.getAoColumns().add(createTime);
    TableHeaderVo modifyTime = new TableHeaderVo("modifyTime", "更新时间");
    modifyTime.setbSearchable(false);
    ti.getAoColumns().add(modifyTime);
    JSONObject jsonObject = JSONObject.fromObject(ti, config);
    writeJson(jsonObject);
    return null;
  }

  /**
   * queryTable
   * 
   * @return
   * @throws Exception
   */
  public String queryTable() throws Exception {
    long count = getBusiness().getCount(getModel());
    TableDataVo table = getBusiness().query(getModel());
    table.setsEcho(getModel().getSEcho());
    table.setiTotalDisplayRecords(count);
    table.setiTotalRecords(count);

    // json
    JSONObject jsonObject = JSONObject.fromObject(table);
    writeJson(jsonObject);
    return null;
  }

  /**
   * 
   * @return
   * @throws Exception
   */
  public String add() throws Exception {
    return SUCCESS;
  }

  /**
   * 
   * @return
   * @throws Exception
   */
  public String edit() throws Exception {
    return SUCCESS;
  }

  /**
   * 
   * @return
   * @throws Exception
   */
  public String delete() throws Exception {
    return SUCCESS;
  }

  /**
   * 
   * @return
   * @throws Exception
   */
  public String save() throws Exception {
    return SUCCESS;
  }
}
