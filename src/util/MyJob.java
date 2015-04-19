package util;

import org.quartz.*;

/**
 * Created by limin.llm on 2015/4/19.
 */
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        System.out.println(context);
    }
}
