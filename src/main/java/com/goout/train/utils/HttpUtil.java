package com.goout.train.utils;

import com.alibaba.fastjson.JSONObject;
import com.goout.train.exception.ServiceException;
import com.goout.train.enums.PlatformErrorCode;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class HttpUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    private static final int TIMEOUT = (int) TimeUnit.SECONDS.toMillis(150);
    private static final String BASE="https://kyfw.12306.cn/otn/leftTicket/init";

    public static void main(String[] args) {
    }
    private static String getCookie(){
        String ss = doGet(BASE);
        System.out.println(ss);
        return ss;
    }

    public static String doGet(String httpUrl) {
        HttpURLConnection connection = null;
        InputStream is = null;
        OutputStream os = null;
        BufferedReader br = null;
        String result = null;
        try {
            URL url = new URL(httpUrl);
            // 通过远程url连接对象打开连接
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接请求方式
            connection.setRequestMethod("GET");
            // 设置连接主机服务器超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取主机服务器返回数据超时时间：60000毫秒
            connection.setReadTimeout(60000);

            // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
            connection.setDoOutput(true);
            // 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
            connection.setDoInput(true);
            // 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
//            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            connection.setRequestProperty("enmsToken", token);
            // 设置鉴权信息：Authorization: Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0
            connection.setRequestProperty("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
            // 通过连接对象获取一个输出流
            os = connection.getOutputStream();
            // 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
            // 通过连接对象获取一个输入流，向远程读取
            if (connection.getResponseCode() == 200) {

                is = connection.getInputStream();
                // 对输入流对象进行包装:charset根据工作项目组的要求来设置
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

                StringBuffer sbf = new StringBuffer();
                String temp = null;
                // 循环遍历一行一行读取数据
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 断开与远程地址url的连接
            connection.disconnect();
        }
        return result;
    }

    /**
     * 执行HTTP请求  <p>get请求时data传null</p>
     *
     * @param url    url完整地址
     * @param method Connection.Method.GET  Connection.Method.POST
     * @param data   请求参数的JSON对象
     * @return HTTP接口返回值
     */
    public static String request(String url, Connection.Method method, JSONObject data) {
        logger.info("======request url={} data={}", url, data);
        String result;
        try {
            Connection conn = Jsoup.connect(url)
                    .timeout(TIMEOUT)
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .header("Connection", "close")
                    .header("Cookie", "JSESSIONID=60DAC42DF6903915FE06C854F8BB1EEB; route=495c805987d0f5c8c84b14f60212447d; BIGipServerotn=2564227338.64545.0000; RAIL_EXPIRATION=1642166013380; RAIL_DEVICEID=UZ6IRmoX3P91l4ljlx6rRoWny-rHOVPqq2LuyE3uGS9-KDWVKVyN2xOs6XnhV21mrPmS-0C2Ac6G0K62PFBentVEohK52kvh-oEfF7Lz4KmcUrwN0mdyRIqCJRxyj9T5J-Gbk8DeZaqnFs3JXOhuoeoR4BqWA9Ky; BIGipServerpassport=971505930.50215.0000; guidesStatus=off; highContrastMode=defaltMode; cursorStatus=off; current_captcha_type=Z; _jc_save_wfdc_flag=dc; BIGipServerportal=3067347210.16671.0000; _jc_save_toDate=2022-01-12; BIGipServersearch=1842675978.36895.0000; _jc_save_fromDate=2022-01-20; _jc_save_fromStation=%u5927%u8FDE%2CDLT; _jc_save_toStation=%u5317%u4EAC%2CBJP")
                    //.header("Cookie", "JSESSIONID=104178E578E4A7F2FFE875F270C63D84; route=495c805987d0f5c8c84b14f60212447d; BIGipServerotn=2564227338.64545.0000; RAIL_EXPIRATION=1642166013380; RAIL_DEVICEID=UZ6IRmoX3P91l4ljlx6rRoWny-rHOVPqq2LuyE3uGS9-KDWVKVyN2xOs6XnhV21mrPmS-0C2Ac6G0K62PFBentVEohK52kvh-oEfF7Lz4KmcUrwN0mdyRIqCJRxyj9T5J-Gbk8DeZaqnFs3JXOhuoeoR4BqWA9Ky; BIGipServerpassport=971505930.50215.0000; guidesStatus=off; highContrastMode=defaltMode; cursorStatus=off; current_captcha_type=Z; _jc_save_toDate=2022-01-11; _jc_save_wfdc_flag=dc; BIGipServerportal=3067347210.16671.0000; _jc_save_fromDate=2022-01-12; _jc_save_toStation=%u4E0A%u6D77%u8679%u6865%2CAOH; _jc_save_fromStation=%u5317%u4EAC%u5357%2CVNP")
                    //.header("Cookie", "JSESSIONID=C634CCFE89D2A5FC70E84A9EC104FF53; tk=bNmGdZ7N_cW_FBPRhMEzvZ14LDKsSS1uJLP2q-mkt80gas1s0; route=495c805987d0f5c8c84b14f60212447d; BIGipServerotn=2564227338.64545.0000; RAIL_EXPIRATION=1642166013380; RAIL_DEVICEID=UZ6IRmoX3P91l4ljlx6rRoWny-rHOVPqq2LuyE3uGS9-KDWVKVyN2xOs6XnhV21mrPmS-0C2Ac6G0K62PFBentVEohK52kvh-oEfF7Lz4KmcUrwN0mdyRIqCJRxyj9T5J-Gbk8DeZaqnFs3JXOhuoeoR4BqWA9Ky; BIGipServerpassport=971505930.50215.0000; guidesStatus=off; highContrastMode=defaltMode; cursorStatus=off; current_captcha_type=Z; _jc_save_toDate=2022-01-11; _jc_save_wfdc_flag=dc; BIGipServerportal=3067347210.16671.0000; _jc_save_fromDate=2022-01-12; _jc_save_toStation=%u4E0A%u6D77%u8679%u6865%2CAOH; _jc_save_fromStation=%u5317%u4EAC%u5357%2CVNP; uKey=6fef9e4ccbf56e409bda5daca1047c80d1040e59faa6b989656288a855477057")
                    //.header("Cookie","JSESSIONID=C634CCFE89D2A5FC70E84A9EC104FF53; tk=bNmGdZ7N_cW_FBPRhMEzvZ14LDKsSS1uJLP2q-mkt80gas1s0; route=495c805987d0f5c8c84b14f60212447d; BIGipServerotn=2564227338.64545.0000; RAIL_EXPIRATION=1642166013380; RAIL_DEVICEID=UZ6IRmoX3P91l4ljlx6rRoWny-rHOVPqq2LuyE3uGS9-KDWVKVyN2xOs6XnhV21mrPmS-0C2Ac6G0K62PFBentVEohK52kvh-oEfF7Lz4KmcUrwN0mdyRIqCJRxyj9T5J-Gbk8DeZaqnFs3JXOhuoeoR4BqWA9Ky; BIGipServerpassport=971505930.50215.0000; guidesStatus=off; highContrastMode=defaltMode; cursorStatus=off; uKey=6fef9e4ccbf56e409bda5daca1047c8011a08560cf5f84b5047955f858463867; current_captcha_type=Z; _jc_save_toStation=%u4E0A%u6D77%2CSHH; _jc_save_toDate=2022-01-11; _jc_save_wfdc_flag=dc; BIGipServerportal=3067347210.16671.0000; _jc_save_fromStation=%u5317%u4EAC%2CBJP; _jc_save_fromDate=2022-01-12")
                    .followRedirects(true)
                    .ignoreContentType(true);

            if (data != null) {
                conn.requestBody(data.toJSONString());
            }
            Connection.Response response = conn.method(method).execute();

            int code = response.statusCode();
            if (code == HttpStatus.OK.value() || code == HttpStatus.FOUND.value()) {
                result = response.body();
            } else {
                logger.error("执行url={}返回非200/302值, statusCode={}", url, response.statusCode());
                throw new ServiceException(PlatformErrorCode.SERVICE_INTERNAL_ERROR);
            }
            if(result.contains("网络")){
                return null;
            }
            logger.info("======request code={} result={}", code, result);
        } catch (IOException e) {
            logger.error("执行" + url + "出错, data=" + data, e);
            throw new ServiceException(PlatformErrorCode.SERVICE_INTERNAL_ERROR, e);
        }
        if(result.contains("网络")){
            return null;
        }
        return result;
    }

}
