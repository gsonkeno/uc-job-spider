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

        try {
            File jobChangeFile = new File("jobChange.md");
            if (!jobChangeFile.exists()){
                jobChangeFile.createNewFile();
            }
            String lastFileName = getLastChangeEndFileName(jobChangeFile);

            File rootDirectory = new File("day_result");
            File[] files = rootDirectory.listFiles((dir, name) -> {
                return (lastFileName == null ?  name.endsWith("csv")
                        :name.endsWith("csv")&&name.compareTo(lastFileName)>=0);
            });

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

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


            String previousFileName = "" ;
            List<RawJob> previousJobList = new ArrayList<>();
            String nextFileName = "";
            List<RawJob> nextJobList = new ArrayList<>();


            Iterator<String> iterator = dayJobsMap.keySet().iterator();
            FileWriter fw = new FileWriter(jobChangeFile, true);

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
                    List<RawJob> addJobs = ListUtils.subtract(nextJobList, previousJobList);
                    List<RawJob> remJobs = ListUtils.subtract(previousJobList, nextJobList);
                    System.out.println(previousFileName + "--->" + nextFileName);
                    System.out.println(addJobs);
                    System.out.println(remJobs);

                    fw.write("\r\n");
                    fw.write("# " + previousFileName + "->" + nextFileName + "\r\n");

                    if (addJobs.size() > 0) {
                        fw.write("## + \r\n");
                    }
                    for (RawJob addJob : addJobs){
                        fw.write("- " + addJob.toCsvString() + "\r\n");
                    }

                    if (remJobs.size() > 0){
                        fw.write("## - \r\n");
                    }
                    for (RawJob remJob : remJobs){
                        fw.write("- " + remJob.toCsvString() + "\r\n");
                    }
                }
            }
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取最后一次工作变化的最后文件名
     * @param mdFile
     * @return
     */
    private String getLastChangeEndFileName(File mdFile){
        String lastFileName = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(mdFile));
            String line;
            while ((line = br.readLine())!= null){
                if (line.matches("^(#).*") && !line.matches("^(##).*")){
                    lastFileName = line.split("->")[1];
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastFileName;
    }
}
