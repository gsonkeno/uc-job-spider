package com.gsonkeno.ucjobspider.service.impl;

import com.gsonkeno.ucjobspider.service.AnalysisService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;

@Service
public class AnalysisServiceImpl implements AnalysisService {
    @Override
    public void analysis() {
        URL resource = getClass().getResource("/");
        System.out.println(resource);

        File file = new File("");
        try {
            String rootPath = file.getCanonicalPath();
            File rootDirectory = new File(rootPath);
            File[] files = rootDirectory.listFiles((dir, name) -> {
                return name.endsWith("csv");
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
