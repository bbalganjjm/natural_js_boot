package common.scheduler;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author Goldman Kim( bbalganjjm@gmail.com )
 * @since 2018.12.05
 */
@Component
public class JobManager {

    private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Async
    public void executeJob1() {
        logger.debug("scheduled job start...");
        logger.debug("scheduled job end...");
    }

}
