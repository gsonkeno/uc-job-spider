package com.gsonkeno.ucjobspider.service.impl;

import com.gsonkeno.ucjobspider.model.Job;
import com.gsonkeno.ucjobspider.model.RawJob;
import com.gsonkeno.ucjobspider.service.AnalysisService;
import org.apache.commons.collections.ListUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
            //按文件生成的时间顺序进行排序
            Arrays.sort(files,(x,y)->{return x.getName().compareTo(y.getName());});

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat standardSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            TreeMap<String, List<RawJob>> dayJobsMap = new TreeMap<>();
            for ( File csvFile : files){
                System.out.println(csvFile.getName());
                BufferedReader br = new BufferedReader(new FileReader(csvFile));
                br.readLine();
                String jobDetail;
                List<RawJob> jobList = new ArrayList<>();
                while ((jobDetail = br.readLine()) != null){
                    String[] jobFields = jobDetail.split(",");
                    String jobName = jobFields[0];
                    String publisher = jobFields[1];

                    Date publishDate =  sdf.parse(jobFields[2]);
                    String jobPosition = jobFields[3];
                    String jobYears = jobFields[4];
                    String education = jobFields[5];
                    String salary = jobFields[7];
                    RawJob job = new RawJob(jobName, publisher, publishDate, jobPosition, jobYears, education, salary);
                    jobList.add(job);
                }
                dayJobsMap.put(csvFile.getName(),jobList);
            }

            Iterator<String> iterator = dayJobsMap.keySet().iterator();
            String previousFileName = "" ;
            List<RawJob> previousJobList = new ArrayList<>();
            String nextFileName = "";
            List<RawJob> nextJobList = new ArrayList<>();
            while ( iterator.hasNext()){
                if (previousFileName.equals("")){
                    previousFileName = iterator.next();
                    previousJobList = dayJobsMap.get(previousFileName);
                }else {
                    previousFileName = nextFileName;
                    previousJobList = nextJobList;
                }
                if (iterator.hasNext()){
                    nextFileName = iterator.next();
                    nextJobList = dayJobsMap.get(nextFileName);
                    List subtract = ListUtils.subtract(nextJobList, previousJobList);
                    System.out.println(previousFileName + "--->" + nextFileName);
                    System.out.println(subtract);
                    System.out.println(subtract.size());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
