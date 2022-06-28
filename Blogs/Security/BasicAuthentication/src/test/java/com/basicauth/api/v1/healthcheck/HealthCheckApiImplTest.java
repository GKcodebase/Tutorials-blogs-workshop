package com.basicauth.api.v1.healthcheck;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class HealthCheckApiImplTest {

    @InjectMocks
    HealthCheckApiImpl impl = new HealthCheckApiImpl();

    @Test
    public void getHealthCheckSuccess(){
        assertEquals("OK",impl.getHealth());
    }
}