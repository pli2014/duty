package actions.backend;

import bl.beans.VolunteerBean;
import bl.constants.BusTieConstant;
import bl.instancepool.SingleBusinessPoolManager;
import bl.mongobus.VolunteerBusiness;
import bl.mongobus.VolunteerTrainCourseBusiness;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.DBUtils;
import util.QuartzManager;
import vo.table.TableQueryVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pli on 2015/4/19.
 * 计算志愿者的培训次数
 */
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class BackendTrainRecordJob implements Job {
    private VolunteerBusiness VOLBUS = (VolunteerBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_VOLUNTEER);
    private VolunteerTrainCourseBusiness vtcBusiness = (VolunteerTrainCourseBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_VOLUNTEERTRAINCOURSE);
    protected static Logger LOG = LoggerFactory.getLogger(BackendTrainRecordJob.class);

    public BackendTrainRecordJob() {
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        LOG.warn(context.toString());
        //多线程共享变量
        DBUtils.setDBFlag(context.getJobDetail().getJobDataMap().getString(QuartzManager.DBFLAG));
        Map filter = new HashMap();
        //性能考虑，采用翻页的方法来查询用户数据
        TableQueryVo tableQueryVo = new TableQueryVo();
        for (int ii = 0; ii < 10000; ii++) {
            tableQueryVo.setFilter(filter);
            tableQueryVo.setIDisplayStart(ii * 100);
            tableQueryVo.setIDisplayLength(100);
            tableQueryVo.getSort().put("code", "asc");
            List<VolunteerBean> list = VOLBUS.query(tableQueryVo, false).getAaData();
            if (list != null && list.size() > 0) {
                LOG.warn("job is calculating {} numbers", list.size());
                for (int i = 0; i < list.size(); i++) {
                    VolunteerBean volunteerBean = list.get(i);
                    VolunteerBean newBean = (VolunteerBean) volunteerBean.clone();

                    Map trainFilter = new HashMap();
                    //通过面试的人，才可以有培训可能
                    trainFilter.put("status", 1);
                    trainFilter.put("volunteerId", newBean.getId());
                    long trainResult = vtcBusiness.countDataByCondition(trainFilter);
                    newBean.setTrainCounter(trainResult);
                    VOLBUS.updateLeafSimple(volunteerBean, newBean);
                }
            } else {
                break;
            }
        }
    }
}
