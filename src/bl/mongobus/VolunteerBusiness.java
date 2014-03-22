/**
 * 
 */
package bl.mongobus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bl.beans.VolunteerBean;
import bl.common.BeanContext;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

/**
 * @author gudong
 * @since $Date:2014-02-20$
 */
public class VolunteerBusiness extends
		MongoCommonBusiness<BeanContext, VolunteerBean> {
	private static Logger log = LoggerFactory
			.getLogger(VolunteerBusiness.class);

	public VolunteerBusiness() {
		this.dbName = "form";
		this.clazz = VolunteerBean.class;
	}

	/**
	 * 通过工号获取志愿者
	 * 
	 * @param code
	 *            工号
	 * @return
	 */
	public VolunteerBean getVolunteerBeanByCode(String code) {
		Map filter = new HashMap();
		filter.put("code", code);

		List<VolunteerBean> result = super.queryDataByCondition(filter, null);
		if (result != null && result.size() > 0) {
			return result.get(0);
		} else {
			return null;
		}
	}
}
