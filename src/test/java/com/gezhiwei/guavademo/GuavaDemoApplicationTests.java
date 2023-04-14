package com.gezhiwei.guavademo;

import com.gezhiwei.guavademo.service.ControllerService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GuavaDemoApplicationTests {

    @Autowired
    private ControllerService controllerService;

    @Test
    void contextLoads() {
    }

    @SneakyThrows
    @Test
    void test1() {
        controllerService.test();
    }
}
