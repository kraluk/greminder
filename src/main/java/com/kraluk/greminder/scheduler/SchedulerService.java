package com.kraluk.greminder.scheduler;

import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Core Scheduler Service for whole application
 *
 * @author lukasz
 */
@Service
@Slf4j
public class SchedulerService {
    private static final int DEFAULT_FIXED_DELAY = 50000;

    @Scheduled(fixedDelay = DEFAULT_FIXED_DELAY)
    public void remind() {
        log.debug("Invoked scheduled operation.");
    }
}
