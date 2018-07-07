CREATE DATABASE uc_job_spider DEFAULT charset utf8 COLLATE utf8_general_ci;

USE  uc_job_spider;
CREATE TABLE job(
 id BIGINT NOT NULL AUTO_INCREMENT,
 job_name VARCHAR(100),
 publisher VARCHAR(100),
 publish_date DATE,
 job_position VARCHAR(100),
 job_years VARCHAR(20),
 education VARCHAR(20),
 crawl_time DATETIME,
 salary VARCHAR(50),
 PRIMARY KEY (id),
 KEY crawl_time(crawl_time),
 KEY publish_date(publish_date)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;