package com.basicauth.api.v1.healthcheck;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * The interface Health check api.
 */
@RestController
public interface HealthCheckApi {
    /**
     * Gets health.
     *
     * @return the health
     */
    @GetMapping("/app/health")
    @ResponseBody
    String getHealth();
}

