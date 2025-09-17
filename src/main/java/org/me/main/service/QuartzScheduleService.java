package org.me.main.service;

import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.springframework.stereotype.Service;

import java.util.TimeZone;

@Service
@RequiredArgsConstructor
public class QuartzScheduleService {
    private final Scheduler scheduler;

    public void scheduleEmailJob(Long scheduleId, String cron, String to, String subject, String body) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(EmailJobService.class)
                .withIdentity("emailJob-" + scheduleId, "email-jobs")
                .usingJobData("receiverEmail", to)
                .usingJobData("subject", subject)
                .usingJobData("body", body)
                .storeDurably()
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("emailTrigger-" + scheduleId, "email-triggers")
                .withSchedule(CronScheduleBuilder.cronSchedule(cron)
                        .inTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh")))
                .forJob(jobDetail)
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
    }

    public void deleteEmailJob(Long scheduleId) throws SchedulerException {
        scheduler.deleteJob(new JobKey("emailJob-" + scheduleId, "email-jobs"));
    }
}
