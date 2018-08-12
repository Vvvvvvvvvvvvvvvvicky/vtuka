package club.vtuka.tuka.common;

import club.vtuka.tuka.constant.SysConstant;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by Vicky on 2018/5/3.
 */
public class HttpClientUtils {

    private final static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    private final static String GET_METHOD = "GET";
    private final static String POST_METHOD = "POST";

    /**
     * 发送GET请求
     * @param url
     * @param headers
     * @param params
     * @return
     */
    public static String sendGet(String url, Map<String, String> headers, Map<String, String> params){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        StringBuilder requestUrl = new StringBuilder(url);
        String result = "";

        if(params != null && params.size() > 0){
            requestUrl.append("?");
            for(Map.Entry<String, String> param : params.entrySet()){
                requestUrl.append(param.getKey() + "=" + param.getValue() + "&");
            }
            url = requestUrl.subSequence(0, requestUrl.length() - 1).toString();
        }
        logger.debug("[url: "+url+" ,method: "+GET_METHOD+" ]");
        logger.debug("Header\n");
        HttpGet httpGet = new HttpGet(url);
        if(headers != null && headers.size() > 0){
            for (Map.Entry<String, String> header:headers.entrySet()) {
                httpGet.addHeader(header.getKey(), header.getValue());
                logger.debug(header.getKey()+" : "+header.getValue());
            }
        }
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            if(response.getStatusLine().getStatusCode() == 200){
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity);
            }
        } catch (IOException e) {
            logger.error("网络请求出错，请检查原因");
        }finally{
            try {
                if(response!=null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                logger.error("网络关闭错误，请检查原因");
            }
        }
        return result;
    }

    /**
     * 发送POST请求
     * @param url
     * @param headers
     * @param params
     * @return
     */
    public static String sendPost(String url, Map<String, String> headers, Map<String, String> params){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";
        HttpPost httpPost = new HttpPost(url);

        if(params != null && params.size() > 0){
            List<NameValuePair> paramList = new ArrayList<>();
            for(Map.Entry<String, String> param : params.entrySet()){
                paramList.add(new BasicNameValuePair(param.getKey(), param.getValue()));
            }
            logger.debug("[url: "+url+" , method: "+ POST_METHOD+" ]");
            try{
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(entity);
            } catch (UnsupportedEncodingException e) {
                logger.error("不支持的编码");
            }
        }
        if(headers != null && headers.size() > 0){
            logger.debug("Header\n");
            for(Map.Entry<String, String> header : headers.entrySet()){
                httpPost.addHeader(header.getKey(), header.getValue());
                logger.debug(header.getKey()+" : "+header.getValue());
            }
        }
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, SysConstant.DEFAULT_CHARSET);
        } catch (IOException e) {
            logger.error("网络请求出错，请检查原因");
        }finally{
            try {
                if(response != null){
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                logger.error("网络关闭错误");
            }
        }
        return  result;
    }

    public static String sendPostJson(String url, String json, Map<String, String> headers){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";
        HttpPost httpPost = new HttpPost(url);
        StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
        httpPost.setEntity(stringEntity);
        logger.debug("[url: "+url+" ,method: "+POST_METHOD+" ,json:"+json+" ]");

        if(headers != null && headers.size() >0 ){
            logger.debug("Header\n");
            for(Map.Entry<String, String> header : headers.entrySet()){
                httpPost.addHeader(header.getKey(), header.getValue());
                logger.debug(header.getKey()+ " : " + header.getValue());
            }
        }

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, SysConstant.DEFAULT_CHARSET);
        } catch (IOException e) {
            logger.error("网络请求出错，请检查原因");
        } finally {
            try {
                if(response != null){
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                logger.error("网络关闭错误");
            }
        }
        return result;
    }
}
