package com.gsonkeno.ucjobspider;

import com.gsonkeno.ucjobspider.service.AnalysisService;
import com.gsonkeno.ucjobspider.service.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UcJobSpiderApplication implements ApplicationRunner {

    @Autowired
    private SpiderService service;
    @Autowired
    private AnalysisService analysisService;

    public static void main(String[] args) {
        SpringApplication.run(UcJobSpiderApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //service.spider();
        analysisService.analysis();
    }
}
