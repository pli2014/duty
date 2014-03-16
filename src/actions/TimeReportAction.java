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
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import vo.report.ActiveTimeReportVo;
import vo.table.TableDataVo;
import vo.table.TableQueryVo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

  private int selectYear;

  private String selectYearDate;
  private List<Integer> yearList;
  private String jsonData;
  private String jsonLabels;
  private String jsonYKeys;

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

  public String getUserDailyReport() throws ParseException {
    Calendar cal = Calendar.getInstance();

    List dataList = new ArrayList();
    List<String> labelList = new ArrayList<String>();
    List<String> yKeysList = new ArrayList<String>();

    if(null != name && StringUtils.isNotEmpty(selectYearDate)) {
      VolunteerBean volunteer = (VolunteerBean)volunteerBus.getLeafByName(name).getResponseData();
      if(null != volunteer) {
        SimpleDateFormat parsesdf = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //set label
        labelList.add(name);
        yKeysList.add(name);
        //set ykeys
        Date yearDate = parsesdf.parse(selectYearDate);
        cal.setTime(yearDate);
        Date start = cal.getTime();
        cal.add(Calendar.MONTH, 1);
        Date end = cal.getTime();
        List<String> userIdList = new ArrayList<String>();
        userIdList.add(volunteer.getId());
        List<UserServiceBean> beanList = userServiceBus.queryUserServices(userIdList, null, start, end);
        Map<String, Long> valueMap = this.formatData(beanList, sdf);

        cal.add(Calendar.DAY_OF_MONTH, -1);
        int daySize = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(start);
        for(int index = 0; index < daySize; index ++) {
          String time = sdf.format(cal.getTime());
          Long value = valueMap.get(time);
          Map monthMap = new HashMap();
          monthMap.put("time", time);
          monthMap.put(name, value!=null?value:0l);
          dataList.add(monthMap);
          cal.add(Calendar.DAY_OF_MONTH, 1);
        }
      }
    }

    jsonData = JSONArray.fromObject(dataList).toString();
    jsonLabels = JSONArray.fromObject(labelList).toString();
    jsonYKeys = JSONArray.fromObject(yKeysList).toString();
    return SUCCESS;
  }

  public String getUserMonthlyReport(){
    Calendar cal = Calendar.getInstance();
    yearList = new ArrayList<Integer>();
    int y = cal.get(Calendar.YEAR);
    for(int index = 0; index < 10; index ++) {
      yearList.add(y-index);
    }

    List dataList = new ArrayList();
    List<String> labelList = new ArrayList<String>();
    List<String> yKeysList = new ArrayList<String>();

    if(null != name && selectYear > 0) {
      VolunteerBean volunteer = (VolunteerBean)volunteerBus.getLeafByName(name).getResponseData();
      if(null != volunteer) {
        //set label
        labelList.add(name);
        yKeysList.add(name);
        //set ykeys
        cal = initDate(cal);
        cal.set(Calendar.YEAR, selectYear);
        Date start = cal.getTime();
        cal.add(Calendar.YEAR, 1);
        Date end = cal.getTime();

        List<String> userIdList = new ArrayList<String>();
        userIdList.add(volunteer.getId());
        List<UserServiceBean> beanList = userServiceBus.queryUserServices(userIdList, null, start, end);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Map<String, Long> valueMap = this.formatData(beanList, sdf);
        cal.setTime(start);
        for(int index = 0; index < 12; index ++) {
          String time = sdf.format(cal.getTime());
          Long value = valueMap.get(time);
          Map monthMap = new HashMap();
          monthMap.put("time", time);
          monthMap.put(name, value!=null?value:0l);
          dataList.add(monthMap);
          cal.add(Calendar.MONTH, 1);
        }
      }
    }

    jsonData = JSONArray.fromObject(dataList).toString();
    jsonLabels = JSONArray.fromObject(labelList).toString();
    jsonYKeys = JSONArray.fromObject(yKeysList).toString();
    return SUCCESS;
  }

  private Map<String, Long> formatData(List<UserServiceBean> beanList, SimpleDateFormat sdf){
    Map<String, Long> result = new HashMap<String, Long>();
    for(UserServiceBean bean: beanList) {
      String time = sdf.format(bean.getCheckOutTime());
      Long value = (Long)result.get(time);
      if(null ==  value) {
        value = 0l;
      }
      result.put(time, (bean.getCheckOutTime().getTime() - bean.getCheckInTime().getTime() + value ));
    }
    return result;
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

  private Calendar initDate(Calendar cal) {
    cal.set(Calendar.MILLISECOND, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.HOUR, 0);
    cal.set(Calendar.DAY_OF_MONTH, 1);
    cal.set(Calendar.MONTH, 0);
    return cal;
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



  public String getJsonYKeys() {
    return jsonYKeys;
  }

  public void setJsonYKeys(String jsonYKeys) {
    this.jsonYKeys = jsonYKeys;
  }

  public int getSelectYear() {
    return selectYear;
  }

  public void setSelectYear(int selectYear) {
    this.selectYear = selectYear;
  }

  public List<Integer> getYearList() {
    return yearList;
  }

  public void setYearList(List<Integer> yearList) {
    this.yearList = yearList;
  }

  public String getJsonData() {
    return jsonData;
  }

  public void setJsonData(String jsonData) {
    this.jsonData = jsonData;
  }

  public String getJsonLabels() {
    return jsonLabels;
  }

  public void setJsonLabels(String jsonLabels) {
    this.jsonLabels = jsonLabels;
  }

  public String getSelectYearDate() {
    return selectYearDate;
  }

  public void setSelectYearDate(String selectYearDate) {
    this.selectYearDate = selectYearDate;
  }
}
