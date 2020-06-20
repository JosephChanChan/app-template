package com.joseph.template;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

/**
 * @author Joseph
 * @since 2020-06-06 13:04
 */
public class MainTest {


    public static void main(String[] args) throws IOException {
        String canonicalPath = ResourceUtils.getFile("classpath:").getCanonicalPath();
        System.out.println(canonicalPath);
    }
}
