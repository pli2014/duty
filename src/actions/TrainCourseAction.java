package actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.bson.types.ObjectId;

import vo.table.TableDataVo;
import vo.table.TableHeaderVo;
import vo.table.TableInitVo;
import vo.table.TableQueryVo;
import webapps.WebappsConstants;
import bl.beans.TrainCourseBean;
import bl.beans.VolunteerBean;
import bl.beans.VolunteerTrainCourseBean;
import bl.common.BusinessResult;
import bl.mongobus.TrainCourseBusiness;
import bl.mongobus.VolunteerTrainCourseBusiness;

/**
 * Created by peter on 14-3-14.
 */
public class TrainCourseAction extends BaseTableAction<TrainCourseBusiness> {

	@Override
	public String getActionPrex() {
		return getRequest().getContextPath() + "/trainCourse";
	}

	@Override
	public String getCustomJs() {
		return getRequest().getContextPath() + "/js/trainCourse.js";
	}

	@Override
	public TableInitVo getTableInit() {
		TableInitVo init = new TableInitVo();
		init.getAoColumns().add(
				new TableHeaderVo("name", "课程名称").disableSearch());
		init.getAoColumns()
				.add(new TableHeaderVo("status", "状态").addSearchOptions(
						new String[][] { { "-1", "0", "1", "2" },
								{ "----", "创建", "开始", "完成" } }).disableSearch());
		init.getAoColumns().add(
				new TableHeaderVo("description", "描述", false).disableSearch());
		return init;
	}

	@Override
	public TableQueryVo getModel() {
		TableQueryVo model = super.getModel();
		model.getFilter().put("status", TrainCourseBean.STATUS_START + "");
		return model;
	}

	/**
	 * queryTable
	 * 
	 * @return
	 * @throws Exception
	 */
	public String queryMyTrainCourse() throws Exception {
		TrainCourseBusiness trainCourseBusiness = new TrainCourseBusiness();
		VolunteerTrainCourseBusiness volunteerCourseBusiness = new VolunteerTrainCourseBusiness();
		VolunteerBean volunteer = (VolunteerBean) getSession().getAttribute(
				WebappsConstants.LOGIN_USER_SESSION_ID);

		TableQueryVo volunteerTrainCourseModel = new TableQueryVo();
		volunteerTrainCourseModel.getFilter().put("volunteerId",
				volunteer.get_id());

		long count = volunteerCourseBusiness
				.getCount(volunteerTrainCourseModel);
		TableDataVo table = volunteerCourseBusiness
				.query(volunteerTrainCourseModel);
		List<VolunteerTrainCourseBean> volunteerTrainCourseList = table
				.getAaData();
		BusinessResult result;
		if (volunteerTrainCourseList != null) {
			for (VolunteerTrainCourseBean volunteerTrainCourseBean : volunteerTrainCourseList) {
				if (volunteerTrainCourseBean.getTraincourseId() != null) {
					result = trainCourseBusiness
							.getLeaf(volunteerTrainCourseBean
									.getTraincourseId().toString());
					if (result != null && result.getResponseData() != null) {
						volunteerTrainCourseBean
								.setTrainCourse((TrainCourseBean) result
										.getResponseData());
					}
				}
			}
		}

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
	public String receive() throws Exception {
		String[] trainCourseIds = getIds();
		if (trainCourseIds != null) {
			VolunteerTrainCourseBusiness volunteerCourseBusiness = new VolunteerTrainCourseBusiness();
			VolunteerBean volunteer = (VolunteerBean) getSession()
					.getAttribute(WebappsConstants.LOGIN_USER_SESSION_ID);

			VolunteerTrainCourseBean volunteerTrainCourseBean;
			Map filterMap = new HashMap();
			for (String trainCourseId : trainCourseIds) {
				if (trainCourseId == null)
					continue;
				filterMap.put("volunteerId", volunteer.get_id());
				filterMap.put("traincourseId", new ObjectId(trainCourseId));
				List list = volunteerCourseBusiness.queryDataByCondition(
						filterMap, null);
				if (list.size() == 0) {
					volunteerTrainCourseBean = new VolunteerTrainCourseBean();
					volunteerTrainCourseBean.setVolunteerId(volunteer.get_id());
					volunteerTrainCourseBean.setTraincourseId(new ObjectId(
							trainCourseId));
					volunteerCourseBusiness
							.createLeaf(volunteerTrainCourseBean);
				}
			}
		}
		return SUCCESS;
	}

	@Override
	public String queryTable() throws Exception {
		TrainCourseBusiness trainCourseBusiness = new TrainCourseBusiness();
		VolunteerTrainCourseBusiness volunteerCourseBusiness = new VolunteerTrainCourseBusiness();
		VolunteerBean volunteer = (VolunteerBean) getSession().getAttribute(
				WebappsConstants.LOGIN_USER_SESSION_ID);

		Map filter = new HashMap();
		filter.put("volunteerId", volunteer.get_id());

		List<VolunteerTrainCourseBean> volunteerTrainCourseList = volunteerCourseBusiness
				.queryDataByCondition(filter, null);
		if (volunteerTrainCourseList != null
				&& volunteerTrainCourseList.size() > 0) {
			ObjectId[] trainCourseId = new ObjectId[volunteerTrainCourseList
					.size()];
			for (int i = 0; i < volunteerTrainCourseList.size(); i++) {
				trainCourseId[i] = volunteerTrainCourseList.get(i).getTraincourseId();
			}
			getModel().getFilter().put("_id_nin", trainCourseId);
		}

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
}
