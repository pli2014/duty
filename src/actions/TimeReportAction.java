package actions;

import bl.beans.ServicePlaceBean;
import bl.beans.UserServiceBean;
import bl.beans.VolunteerBean;
import bl.constants.BusTieConstant;
import bl.instancepool.SingleBusinessPoolManager;
import bl.mongobus.ServicePlaceBusiness;
import bl.mongobus.UserServiceBusiness;
import bl.mongobus.VolunteerBusiness;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONObject;
import vo.report.ActiveTimeReportVo;
import vo.table.TableDataVo;
import vo.table.TableQueryVo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by wangronghua on 14-3-16.
 */
public class TimeReportAction extends BaseAction implements ModelDriven<TableQueryVo> {
  ServicePlaceBusiness servicePlaceBus = (ServicePlaceBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_SERVICEPLACE);
  UserServiceBusiness userServiceBus = (UserServiceBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_USERSERVICE);
  VolunteerBusiness volunteerBus = (VolunteerBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_VOLUNTEER);

  List<ServicePlaceBean> servicePlaces = null;
  List<ActiveTimeReportVo> activeTimeReportVos;

  private TableQueryVo model;
  private String name;
  private String servicePlaceId;
  private boolean day = true;
  private boolean month = true;
  private boolean year = true;

  public String getActiveReport(){
    servicePlaces = (List<ServicePlaceBean>)servicePlaceBus.getAllLeaves().getResponseData();
    return SUCCESS;
  }

  public String getActiveReportData(){
    List<ActiveTimeReportVo> result = new ArrayList<ActiveTimeReportVo>();
    // get filters
    Map<String, String> filterMap = getModel().getFilter();
    name = filterMap.get("name");
    servicePlaceId = filterMap.get("servicePlaceId");

    List<String> serviceIdList = new ArrayList<String>();
    if(null != servicePlaceId) {
      serviceIdList.add(servicePlaceId);
    }

//    Pattern pattern = Pattern.compile("^.*" + personName+ ".*$", // as SQL:  like " '%" + personName + "%' "
//    Pattern.CASE_INSENSITIVE);
//    query.filter("name", pattern);

    long count = volunteerBus.getCount(getModel());

    List<VolunteerBean> beanList = (List<VolunteerBean>)volunteerBus.query(getModel()).getAaData();
    List<String> idList = new ArrayList<String>();
    for(VolunteerBean bean: beanList) {
      idList.add(bean.getId());
    }
    Map<String, Map> beanMap = userServiceBus.statisticTime(userServiceBus.getLeavesByUserIds(idList, serviceIdList));

    for(VolunteerBean bean: beanList) {
      ActiveTimeReportVo vo = new ActiveTimeReportVo();
      vo.setName(bean.getName());
      Map hourMap = beanMap.get(bean.getId());
      if(null != hourMap) {
        Long dayHours = (Long)hourMap.get(Calendar.DAY_OF_MONTH);
        Long monthHours = (Long)hourMap.get(Calendar.MONTH);
        Long yearHours = (Long)hourMap.get(Calendar.YEAR);
        Long totalHours = (Long)hourMap.get(Calendar.ALL_STYLES);
        vo.setDayHours((int)(dayHours!=null?dayHours:0l)/3600000);
        vo.setMonthHours((int) (monthHours != null ? monthHours : 0l) / 3600000);
        vo.setYearHours((int) (yearHours != null ? yearHours : 0l) /3600000);
        vo.setTotalHours((int) (totalHours != null ? totalHours : 0l) / 3600000);
      }
      result.add(vo);
    }
    
    TableDataVo table = new TableDataVo();
    table.setsEcho(getModel().getSEcho());
    table.setiTotalDisplayRecords(count);
    table.setiTotalRecords(count);
    table.setAaData(result);
    // json
    JSONObject jsonObject = JSONObject.fromObject(table);
    writeJson(jsonObject);
    return null;
  }

  public String getUserReport(){
    return SUCCESS;
  }

  public String getServiceReport(){
    return SUCCESS;
  }

  public String getUserDailyReport(){
    return SUCCESS;
  }

  public String getUserMonthlyReport(){
    return SUCCESS;
  }

  public String getServiceDailyReport(){
    return SUCCESS;
  }

  public String getServiceMonthlyReport(){
    return SUCCESS;
  }

  public List<ServicePlaceBean> getServicePlaces() {
    return servicePlaces;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isDay() {
    return day;
  }

  public void setDay(boolean day) {
    this.day = day;
  }

  public boolean isMonth() {
    return month;
  }

  public void setMonth(boolean month) {
    this.month = month;
  }

  public boolean isYear() {
    return year;
  }

  public void setYear(boolean year) {
    this.year = year;
  }

  public String getServicePlaceId() {
    return servicePlaceId;
  }

  public void setServicePlaceId(String servicePlaceId) {
    this.servicePlaceId = servicePlaceId;
  }

  public TableQueryVo getModel() {
    if (model == null) {
      model = new TableQueryVo();
    }
    return model;
  }

  public void setModel(TableQueryVo model) {
    this.model = model;
  }

  public void setServicePlaces(List<ServicePlaceBean> servicePlaces) {
    this.servicePlaces = servicePlaces;
  }

  public List<ActiveTimeReportVo> getActiveTimeReportVos() {
    return activeTimeReportVos;
  }

  public void setActiveTimeReportVos(List<ActiveTimeReportVo> activeTimeReportVos) {
    this.activeTimeReportVos = activeTimeReportVos;
  }

}
