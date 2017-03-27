package com.kraluk.greminder.rest;

import com.kraluk.greminder.util.Version;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;

/**
 * Simple Maintenance REST endpoint
 *
 * @author lukasz
 */
@RestController
@Slf4j
public class MaintenanceResource {
    private static final String FORWARD_HEADER = "X-FORWARDED-FOR";

    // Just for DEV purposes, in prod environment could be potentially dangerous
    private final AtomicLong counter = new AtomicLong(0);

    @GetMapping("/ping")
    public String ping(HttpServletRequest request) {
        Optional<String> ipAddress = Optional.ofNullable(request.getHeader(FORWARD_HEADER));

        log.debug("Invoked by '{}'", ipAddress.orElse(request.getRemoteAddr()));
        long pong = counter.addAndGet(1);
        log.debug("Returning pong '{}'...", pong);

        return String.format("pong (%s)", pong);
    }

    @GetMapping("/version")
    public String version() {
        return Version.VERSION;
    }
}
