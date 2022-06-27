package com.basicauth.api.v1.healthcheck;

import org.springframework.stereotype.Component;

/**
 * The Health check api implementation.
 */
@Component
public class HealthCheckApiImpl implements HealthCheckApi{
    /**
     * Gets health.
     *
     * @return the health
     */
    @Override
    public String getHealth() {
        return "OK";
    }
}
