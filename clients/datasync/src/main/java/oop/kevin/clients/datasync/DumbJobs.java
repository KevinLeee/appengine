package oop.kevin.clients.datasync;

import org.quartz.*;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: MichaelLee
 * Date: 12-12-4
 * Time: 下午4:54
 * <p/>
 */
public class DumbJobs implements Job {
    String jobSays;
    float myFloatValue;
    ArrayList state;

    public DumbJobs() {
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey jobKey = context.getJobDetail().getKey();
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        String jobSays = jobDataMap.getString("jobSays");
        float myFloatValue = jobDataMap.getFloat("myFloatValue");
        System.err.println("Instance " + jobKey + " of DumbJobs says: " + jobSays + ", and val is: " + myFloatValue);
    }

    public void setJobSays(String jobSays) {
        this.jobSays = jobSays;
    }

    public void setMyFloatValue(float myFloatValue) {
        this.myFloatValue = myFloatValue;
    }

    public void setState(ArrayList state) {
        this.state = state;
    }
}
