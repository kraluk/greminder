package com.kraluk.greminder.rest;

import com.kraluk.greminder.util.Version;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

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

    private final AtomicInteger counter = new AtomicInteger(0);

    @RequestMapping(path = "/ping", method = RequestMethod.GET)
    public String ping(HttpServletRequest request) {
        String ipAddress = request.getHeader(FORWARD_HEADER);

        log.debug("Invoked by '{}'", ipAddress == null ? request.getRemoteAddr() : ipAddress);
        int pong = counter.addAndGet(1);
        log.debug("Returning pong '{}'...", pong);

        return String.format("pong (%s)", pong);
    }

    @RequestMapping(path = "/version", method = RequestMethod.GET)
    public String version() {
        return Version.VERSION;
    }
}
