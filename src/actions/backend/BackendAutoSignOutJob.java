package actions.backend;

import bl.beans.ActiveUserBean;
import bl.common.BusinessResult;
import bl.constants.BusTieConstant;
import bl.instancepool.SingleBusinessPoolManager;
import bl.mongobus.ActiveUserBusiness;
import bl.mongobus.UserServiceBusiness;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.DBUtils;
import util.QuartzManager;

import java.util.List;

/**
 * Created by pli on 2015/4/19.
 * 自动签出的job
 */
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class BackendAutoSignOutJob implements Job {
    ActiveUserBusiness activeUserBus = (ActiveUserBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_ACTIVEUSER);
    UserServiceBusiness userServiceBus = (UserServiceBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_USERSERVICE);

    protected static Logger LOG = LoggerFactory.getLogger(BackendAutoSignOutJob.class);

    public BackendAutoSignOutJob() {
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        LOG.warn(context.toString());
        //多线程共享变量
        DBUtils.setDBFlag(context.getJobDetail().getJobDataMap().getString(QuartzManager.DBFLAG));
        BusinessResult businessResult = activeUserBus.getAllLeaves();
        if (businessResult.success()) {
            List<ActiveUserBean> list = (List<ActiveUserBean>) businessResult.getResponseData();
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    //自动签出
                    userServiceBus.checkOut(list.get(i).getUserId());
                    LOG.warn("auto sign out userId {}", list.get(i).getUserId());
                }
            }
        }
    }
}
