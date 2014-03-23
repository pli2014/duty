/**
 * 
 */
package bl.mongobus;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;

import util.StringUtil;
import vo.table.TableDataVo;
import vo.table.TableQueryVo;
import webapps.WebappsConstants;
import bl.beans.VolunteerBean;
import bl.common.BeanContext;
import bl.common.BusinessResult;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

/**
 * @author gudong
 * @since $Date:2014-02-20$
 */
public class VolunteerBusiness extends MongoCommonBusiness<BeanContext, VolunteerBean> {
  private static Logger log = LoggerFactory.getLogger(VolunteerBusiness.class);

  public VolunteerBusiness() {
    this.dbName = "form";
    this.clazz = VolunteerBean.class;
  }

  /**
   * 通过工号获取志愿者
   * 
   * @param code
   *          工号
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

  /**
   * 通过身份证号码获取志愿者
   * 
   * @param code
   *          工号
   * @return
   */
  public VolunteerBean getVolunteerBeanByIdentityCard(String identityCard) {
    Map filter = new HashMap();
    filter.put("identityCard", identityCard);

    List<VolunteerBean> result = super.queryDataByCondition(filter, null);
    if (result != null && result.size() > 0) {
      return result.get(0);
    } else {
      return null;
    }
  }

  /**
   * 
   * @param volunteer
   * @return
   */
  public BusinessResult save(VolunteerBean volunteer, ServletContext context) {
    BusinessResult result = new BusinessResult();
    if (StringUtils.isBlank(volunteer.getId())) {
      VolunteerBean volunteerTmp = getVolunteerBeanByIdentityCard(volunteer.getIdentityCard());
      if (volunteerTmp != null) {
        result.addError("身份证号码已经被注册");
        return result;
      }
      volunteer.set_id(ObjectId.get());
      volunteer.setPassword(StringUtil.toMD5(volunteer.getPassword()));
      result = createLeaf(volunteer);
      context.setAttribute(WebappsConstants.UNVERIFIED_VOLUNTEER_KEY, getUnVerifiedVolunteers());
      return result;
    } else {
      VolunteerBean volunteerTmp = (VolunteerBean) getVolunteerBeanByIdentityCard(volunteer.getIdentityCard());
      if (volunteerTmp != null && !volunteerTmp.getId().equals(volunteer.getId())) {
        result.addError("身份证号码已经被注册");
        return result;
      }
      VolunteerBean origUser = (VolunteerBean) getLeaf(volunteer.getId().toString()).getResponseData();
      volunteer.setPassword(origUser.getPassword());
      try {
        BeanUtils.copyProperties(origUser, volunteer);
        return updateLeaf(origUser, origUser);
      } catch (IllegalAccessException e) {
        e.printStackTrace();
        result.addError(e);
      } catch (InvocationTargetException e) {
        e.printStackTrace();
        result.addError(e);
      }
      return result;
    }
  }

  /**
   * 通过OpenID获取志愿者
   * 
   * @param openID
   * 
   * @return
   */
  public VolunteerBean getVolunteerBeanByOpenID(String openID) {
    Map filter = new HashMap();
    filter.put("openID", openID);

    List<VolunteerBean> result = super.queryDataByCondition(filter, null);
    if (result != null && result.size() > 0) {
      return result.get(0);
    } else {
      return null;
    }
  }

  /**
   * 
   * @return
   */
  public TableDataVo getUnVerifiedVolunteers() {
    TableQueryVo query = new TableQueryVo();
    query.getFilter().put("status", VolunteerBean.REGISTERED);
    query.getFilter().put("isDeleted_!=", true);
    query.setIDisplayStart(0);
    query.setIDisplayLength(7);

    long count = getCount(query);
    TableDataVo dataVo = query(query);
    dataVo.setiTotalDisplayRecords(count);
    dataVo.setiTotalRecords(count);
    return dataVo;
  }

  /**
   * 
   * @return
   */
  public TableDataVo getUnInterviewedVolunteers() {
    TableQueryVo query = new TableQueryVo();
    query.getFilter().put("status", VolunteerBean.VIERFIED);
    query.getFilter().put("isDeleted_!=", true);
    query.setIDisplayStart(0);
    query.setIDisplayLength(7);
    long count = getCount(query);
    TableDataVo dataVo = query(query);
    dataVo.setiTotalDisplayRecords(count);
    dataVo.setiTotalRecords(count);
    return dataVo;
  }
}
