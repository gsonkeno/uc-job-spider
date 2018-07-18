package com.gsonkeno.ucjobspider;

import com.gsonkeno.ucjobspider.service.AnalysisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UcJobSpiderApplicationTests {

    @Autowired
    private AnalysisService service;
    @Test
    public void contextLoads() {
        service.analysis();
    }

}
