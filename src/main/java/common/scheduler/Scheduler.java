package common.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author KIM HWANG MAN( bbalganjjm@gmail.com )
 * @since 2018.12.05
 */
@Component
public class Scheduler {

    @Autowired
    private JobManager jobManager;

    // @Scheduled(fixedDelay = 1800000)
    @Scheduled(cron = "30 * * * * MON-FRI")
    public void doSchedule() {
        jobManager.executeJob1();
    }

}
