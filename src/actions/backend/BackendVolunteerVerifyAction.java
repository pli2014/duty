/**
 * 
 */
package actions.backend;

import vo.table.TableQueryVo;

/**
 * @author gudong
 * @since $Date:2014-02-10$
 */
public class BackendVolunteerVerifyAction extends BackendVolunteerAction {
  @Override
  public TableQueryVo getModel() {
    // 0=已注册、1=已审核、2=已面试、3=正在服务期、4=已注销
    super.getModel().getFilter().put("status", "0");
    return super.getModel();
  }
}
