package util;

import org.apache.commons.lang.StringUtils;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;

/**
 * Created by pli on 2015/4/19.
 */
public class QuartzManager {
    private static SchedulerFactory sf = new StdSchedulerFactory();
    private static String JOB_GROUP_NAME = "dutyjob-group";
    private static String TRIGGER_GROUP_NAME = "dutytrigger-group";
    protected static Logger LOG = LoggerFactory.getLogger(QuartzManager.class);
    protected static Scheduler sched = null;
    public static final String DBFLAG = "dbFlag";

    static {
        try {
            sched = sf.getScheduler();
        } catch (Exception e) {

        }
    }
    /** */
    /**
     * 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
     *
     * @param jobName        任务名
     * @param job            任务
     * @param cronExpression 时间设置，参考quartz说明文档
     * @throws SchedulerException
     * @throws ParseException
     */
    public synchronized static void addOrModifyJob(String jobName, Class<? extends Job> job, String cronExpression) {
        if (StringUtils.isEmpty(cronExpression) || cronExpression.startsWith("*")) {
            LOG.error("error expression {} {}", jobName, cronExpression);
            //避免性能影响，第一个字符不能是*,表示每秒执行
            return;
        }
        LOG.warn("addOrModifyJob {} {}", jobName, cronExpression);
        try {
            JobDetail jobDetail = sched.getJobDetail(new JobKey(jobName + DBUtils.getDBFlag(), JOB_GROUP_NAME));
            if (jobDetail == null) {
                jobDetail = JobBuilder.newJob(job).withIdentity(new JobKey(jobName + DBUtils.getDBFlag(), JOB_GROUP_NAME)).build();//任务名，任务组，任务执行类
            }
            //设置DB flag到JOB上下文
            jobDetail.getJobDataMap().put(QuartzManager.DBFLAG, DBUtils.getDBFlag());
            //触发器
            Trigger trigger = sched.getTrigger(new TriggerKey(jobName + DBUtils.getDBFlag(), TRIGGER_GROUP_NAME));
            if (trigger != null) {
                //modify
                modifyJob(jobName, cronExpression);
            } else {
                Trigger triggerNew = TriggerBuilder.newTrigger().withIdentity(jobName + DBUtils.getDBFlag(), TRIGGER_GROUP_NAME)
                        .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();
                sched.scheduleJob(jobDetail, triggerNew);
            }
            //启动
            if (!sched.isShutdown() && !sched.isStarted()) {
                sched.start();
            } else if (sched.isStarted()) {
                sched.resumeAll();
            }
        } catch (Exception e) {
            LOG.error("error", e);
        }

    }


    /** */
    /**
     * 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
     *
     * @param jobName
     * @param cronExpression
     * @throws SchedulerException
     * @throws ParseException
     */
    public synchronized static void modifyJob(String jobName, String cronExpression) {
        LOG.warn("modifyJob {} {}", jobName, cronExpression);
        try {
            Trigger trigger = sched.getTrigger(new TriggerKey(jobName + DBUtils.getDBFlag(), TRIGGER_GROUP_NAME));
            if (trigger != null) {
                trigger = trigger.getTriggerBuilder().newTrigger().withIdentity(jobName + DBUtils.getDBFlag(), TRIGGER_GROUP_NAME).withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();
                sched.rescheduleJob(new TriggerKey(jobName + DBUtils.getDBFlag(), TRIGGER_GROUP_NAME), trigger);
            }
        } catch (Exception e) {
            LOG.error("error", e);
        }
    }

    /** */
    /**
     * 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
     *
     * @param jobName
     * @param cronExpression
     * @throws SchedulerException
     * @throws ParseException
     */
    public static void deleteJob(String jobName, String cronExpression) {
        LOG.warn("deleteJob {} {}", jobName, cronExpression);
        try {
            sched.deleteJob(new JobKey(jobName + DBUtils.getDBFlag(), JOB_GROUP_NAME));
        } catch (Exception e) {
            LOG.error("error", e);
        }
    }

    public static void stopScheduler() {
        LOG.warn("stopping stopScheduler");
        try {
            sched.clear();
            sched.shutdown();
        } catch (Exception e) {
            LOG.error("error", e);
        }
        LOG.warn("finish stopScheduler");
    }

    public static void main(String[] args) {
        DBUtils.setDBFlag("form");
        QuartzManager.addOrModifyJob("pli", MyJob.class, "0/5 * * * * ?");
        try {
            Thread.sleep(10000);
            QuartzManager.addOrModifyJob("pli", MyJob.class, "0/2 * * * * ?");
            Thread.sleep(5000);
            QuartzManager.addOrModifyJob("pli", MyJob.class, "0/5 * * * * ?");
            //QuartzManager.stopScheduler();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
