package com.gsonkeno.ucjobspider.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 发布职位实体
 */
public class Job {
    /**职位名称**/
    private String jobName;
    /**发布者**/
    private String publisher;
    /**发布时间**/
    private Date publishDate;
    /**工作地点**/
    private String jobPosition;
    /**工作年限**/
    private String jobYears;
    /**学历**/
    private String education;
    /**爬虫时间**/
    private Date crawlTime;
    /**薪水**/
    private String salary;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public String getJobYears() {
        return jobYears;
    }

    public void setJobYears(String jobYears) {
        this.jobYears = jobYears;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Date getCrawlTime() {
        return crawlTime;
    }

    public void setCrawlTime(Date crawlTime) {
        this.crawlTime = crawlTime;
    }

    public Job(String jobName, String publisher, Date publishDate, String jobPosition, String jobYears, String education, Date crawlTime, String salary) {
        this.jobName = jobName;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.jobPosition = jobPosition;
        this.jobYears = jobYears;
        this.education = education;
        this.crawlTime = crawlTime;
        this.salary = salary;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        SimpleDateFormat standardSdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        return "Job{" +
                "jobName='" + jobName + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publishDate=" + sdf.format(publishDate) +
                ", jobPosition='" + jobPosition + '\'' +
                ", jobYears='" + jobYears + '\'' +
                ", education='" + education + '\'' +
                ", crawlTime=" + standardSdf.format(crawlTime) +
                ", salary='" + salary + '\'' +
                '}';
    }
    public String toCsvString(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        SimpleDateFormat standardSdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        return  jobName + "," +
                publisher + "," +
                sdf.format(publishDate) + "," +
                jobPosition + "," +
                jobYears + "," +
                education + "," +
                standardSdf.format(crawlTime) + "," +
                salary;
    }
}
