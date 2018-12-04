package com.wondertek.baiying.util;

import com.alibaba.fastjson.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by zhaobicheng on 2018/1/17.
 * 使用java代码模拟http Post请求
 */
public class PostTest {

    public static void main(String[] args){
        String add_url = "http://127.0.0.1:8080/";
        URL url = null;
        try {
            url = new URL(add_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type","application/json");
            connection.connect();
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());

            JSONObject obj = new JSONObject();
            obj.put("code", 200);
            obj.put("success", true);
            outputStream.writeBytes(obj.toString());
            outputStream.flush();
            outputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
