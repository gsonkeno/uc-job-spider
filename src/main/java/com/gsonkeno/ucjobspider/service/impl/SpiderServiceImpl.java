package com.gsonkeno.ucjobspider.service.impl;

import com.gsonkeno.ucjobspider.model.Job;
import com.gsonkeno.ucjobspider.service.SpiderService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class SpiderServiceImpl implements SpiderService {

    @Override
    public void spider(){
        String url = "https://www.zhipin.com/gongsir/19b7e3949cb97add03F42Nw~.html";
        Document doc;
        List<Job> jobs = new ArrayList<>();
        int pageCount = 1;
        int pagePointer = 0;
        while (pagePointer ++ < pageCount){
            try {
                doc = Jsoup.connect(url + "?page=" + pagePointer).get();
                pageCount = Integer.valueOf(doc.select(".company-stat>span>a>b").text())/15 + 1;

                Elements jobListElements = doc.getElementsByClass("job-list");
                Element jobListElement = jobListElements.get(0);
                Elements jobLis = jobListElement.getElementsByTag("li");
                for (Element jobLi: jobLis){
                    String jobName = jobLi.select(".job-title").text();

                    String salary = jobLi.select("span.red").text();
                    String publisher = jobLi.select(".info-publis>h3").text();
                    String publishDate = jobLi.select(".info-publis>p").text().
                            replaceAll("发布于","2018年");

                    String html = jobLi.select(".info-primary>p").html();

                    int firstIndex = html.indexOf("<em class=\"vline\"></em>")  ;
                    int firstIndexEnd = html.indexOf("<em class=\"vline\"></em>") + "<em class=\"vline\"></em>".length() ;
                    int secondIndex = html.lastIndexOf("<em class=\"vline\"></em>");
                    int secondIndexEnd = html.lastIndexOf("<em class=\"vline\"></em>")+ "<em class=\"vline\"></em>".length() ;
                    String jobPosition = html.substring(0, firstIndex);
                    String jobYears = html.substring(firstIndexEnd,secondIndex);
                    String education = html.substring(secondIndexEnd);


                    Job job = buildJob(jobName, salary, publisher, publishDate, jobPosition, jobYears, education);
                    jobs.add(job);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(jobs);
        writeFile(jobs);
    }

    private Job buildJob(String jobName, String salary, String publisher, String publishDate, String jobPosition,
                         String jobYears, String education){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        Date publishDatePost = null;
        Date now = new Date();
        try {
            publishDatePost = dateFormat.parse(publishDate);
            if (publishDatePost.compareTo(now)>0){
                Calendar c = Calendar.getInstance();
                c.setTime(publishDatePost);
                c.add(Calendar.YEAR,-1);
                publishDatePost = c.getTime();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Job(jobName,publisher,publishDatePost,jobPosition,jobYears,education,now,salary);
    }

    private void writeFile(List<Job> jobs){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");

        File file = new File(sdf.format(date) + ".csv");
        try {
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            fw.write("职位名称,发布者,发布时间,工作地点,工作年限要求,学历,爬虫时间,薪水范围\n");
            for (Job job: jobs){
                fw.write(job.toCsvString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
