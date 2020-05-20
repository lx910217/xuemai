package com.gx.web.sendMess;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class HttpDemo {
    public static void main(String[] args) throws Exception {

        String host = "112.74.179.106:8080";
        String userCode = "chuangmai";
        String userPwd = "2018cm";
        String numbers = "13222100055,13585835040";
        String msgContent = "【study】为您孩子提供更开阔的思维想象力和创造力，免费体验试听课程吧 程吧 http://t.cn/Ewsbjow 退订回T";
        String charset = "GBK";

        StringBuffer urlSb = new StringBuffer();
        urlSb.append("http://").append(host).append("/Message.sv?method=sendMsg");
        urlSb.append("&userCode=").append(userCode);
        urlSb.append("&userPwd=").append(userPwd);
        urlSb.append("&numbers=").append(numbers);
        
        //编码
        urlSb.append("&msgContent=").append(URLEncoder.encode(msgContent, charset));
        urlSb.append("&charset=").append(charset);

        String fullUrlStr = urlSb.toString();
        System.out.println("http开始发送：" + fullUrlStr);
        String result = "-1";
        try {
            int idx = fullUrlStr.indexOf("?");
            String str = fullUrlStr.substring(0, idx);
            String param = fullUrlStr.substring(idx + 1);
            URL url = new URL(str);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoInput(true);
            con.setDoOutput(true);
            OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream(), charset);
            out.write(param);
            out.flush();
            out.close();

            BufferedReader reader;
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            result = reader.readLine();
            System.out.println("http发送返回结果：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}