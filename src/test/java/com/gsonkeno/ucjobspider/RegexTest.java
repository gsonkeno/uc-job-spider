package com.gsonkeno.ucjobspider;

import org.junit.Test;

public class RegexTest {
    @Test
    public void test(){
        String m = "##dhafaf";
        System.out.println(m.matches("^(#).*"));
        System.out.println(m.matches("^(##).*"));
    }
}
