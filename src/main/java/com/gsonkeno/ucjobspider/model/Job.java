package com.gsonkeno.ucjobspider.model;

import java.util.Date;

/**
 * 发布职位实体
 */
public class Job {
    private String jobName;
    private String publisher;
    private Date publishDate;
    /**工作地点**/
    private String jobPosition;
    /**工作年限**/
    private Integer jobYears;
    /**学历**/
    private String education;
    private Integer minSalary;
    private Integer maxSalary;

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

    public Integer getJobYears() {
        return jobYears;
    }

    public void setJobYears(Integer jobYears) {
        this.jobYears = jobYears;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Integer getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Integer minSalary) {
        this.minSalary = minSalary;
    }

    public Integer getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Integer maxSalary) {
        this.maxSalary = maxSalary;
    }
}
