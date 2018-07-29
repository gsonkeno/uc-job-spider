package com.gsonkeno.ucjobspider;

import org.junit.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileTest {
    //测试文件路径
    @Test
    public void test(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");

        File file = new File("day_result/" + sdf.format(date) + ".csv");
        System.out.println(file.getAbsolutePath());

        file = new File("day_result");
        System.out.println(file.getAbsolutePath());

    }
}
