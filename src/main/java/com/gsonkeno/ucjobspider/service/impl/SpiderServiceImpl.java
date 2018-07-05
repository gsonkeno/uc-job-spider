package com.gsonkeno.ucjobspider.service.impl;

import com.gsonkeno.ucjobspider.service.SpiderService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SpiderServiceImpl implements SpiderService {

    @Override
    public void spider(){
        String url = "https://www.zhipin.com/gongsir/19b7e3949cb97add03F42Nw~.html";
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
            System.out.println(doc);
            System.out.println("******************************");
            Elements jobListElements = doc.getElementsByClass("job-list");
            Element jobListElement = jobListElements.get(0);

            Elements jobLis = jobListElement.getElementsByTag("li");

            System.out.println(jobLis.get(0));
            for (Element jobLi: jobLis){
                String jobName = jobLi.select(".job-title").text();
                System.out.println(jobName);

                String salary = jobLi.select("span.red").text();
                System.out.println(salary);

                String p = jobLi.select(".info-primary>p").text();
                List<String> strings = jobLi.select(".info-primary>p").eachText();
                String html = jobLi.select(".info-primary>p").html();
                System.out.println(p);
                System.out.println(strings);
                System.out.println(html);
                int firstIndex = html.indexOf("<em class=\"vline\"></em>")  ;
                int firstIndexEnd = html.indexOf("<em class=\"vline\"></em>") + "<em class=\"vline\"></em>".length() ;
                int secondIndex = html.lastIndexOf("<em class=\"vline\"></em>");
                int secondIndexEnd = html.lastIndexOf("<em class=\"vline\"></em>")+ "<em class=\"vline\"></em>".length() ;
                System.out.println(html.substring(0,firstIndex));
                System.out.println(html.substring(firstIndexEnd,secondIndex));
                System.out.println(html.substring(secondIndexEnd));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
