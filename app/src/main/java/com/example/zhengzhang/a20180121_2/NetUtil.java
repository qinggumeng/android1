package com.example.zhengzhang.a20180121_2;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by zhengzhang on 2018/1/21.
 */

public class NetUtil {
    public static String get(String urlStr){
        try {
            URL url = new URL(urlStr);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            BufferedReader br =
                    new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuffer sb = new StringBuffer();


            String str;

            while((str=br.readLine())!=null){
                sb.append(str);
            }

          //  System.out.println(sb.toString());


            br.close();

            conn.disconnect();

            return sb.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static  void main(String[] args){
        NetUtil.get("https://api.m.jd.com/api?callback=jQuery68717&appid=pc&functionId=mixer&t=1516538306646&body=%7B%22pin%22%3A%22%22%2C%22p%22%3A504000%2C%22uuid%22%3A%2215153357932791412551591%22%2C%22lid%22%3A%221%22%2C%22lim%22%3A10%2C%22skus%22%3A%22%22%2C%22ck%22%3A%22ipLoction%22%2C%22ec%22%3A%22utf-8%22%2C%22c1%22%3A%226728%22%2C%22c2%22%3A%226740%22%2C%22c3%22%3A%226964%22%2C%22hi%22%3A%22brand%3A%2Cprice%3A%2Cpage%3A2%22%7D&jsonp=jQuery68717&_=1516538306646");
    }

}
