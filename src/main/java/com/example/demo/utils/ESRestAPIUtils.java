package com.example.demo.utils;

import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @DESC: 查询（操作）ES的REST 低阶api工具类
 * @Auther: Anryg
 * @Date: 2020/9/11 16:54
 */
@Slf4j
public class ESRestAPIUtils {
    private static volatile RestClientBuilder restClientBuilder;
    private static volatile RequestOptions commonOptions;

    private ESRestAPIUtils(){/**确保模式*/}

    /**
     * @DESC: 获取RestClientBuilder对象，懒汉模式，单列模式
     * */
    private static RestClientBuilder getBuilder(){
        if (null == restClientBuilder){
            synchronized (ESRestAPIUtils.class){
                if (null == restClientBuilder){
                    try {
                        restClientBuilder = RestClient.builder(assembleESHost().toArray(new HttpHost[3]));
                        Header[] header = new Header[]{new BasicHeader("header", "value")};
                        restClientBuilder.setDefaultHeaders(header);
                    } catch (Exception e) {
                        log.error("",e);
                    }
                }
            }
        }
        return restClientBuilder;
    }

    /**
     * @DESC: 获取RequestOptions对象，懒汉模式，单列模式
     * */
    private static RequestOptions getCommonOptions(){
        if (null == commonOptions){
            synchronized (ESRestAPIUtils.class){
                if (null == commonOptions){
                    try {
                        RequestOptions.Builder requestOptionBuilder = RequestOptions.DEFAULT.toBuilder();
                        requestOptionBuilder.setHttpAsyncResponseConsumerFactory(
                                new HttpAsyncResponseConsumerFactory.HeapBufferedResponseConsumerFactory(800 * 1024 * 1024));
                        commonOptions = requestOptionBuilder.build();
                    } catch (Exception e) {
                        log.error("",e);
                    }
                }
            }
        }
        return commonOptions;
    }

    /**
     * @return : 返回请求后的json字符串
     * @DESC: 可接受所有对于ES的查询操作，包括数据写入、删除、一般的条件查询和聚合查询
     */
    public static String query(String queryJson, String method, String endpoint) {
        String resultJson = "";
        RestClient restClient = ESRestAPIUtils.getBuilder().build();
        //     .setMaxRetryTimeoutMillis(10 * 60 * 1000)/**设置查询超时时长，默认30秒*/.build();
        HttpEntity entity = new StringEntity(queryJson, ContentType.APPLICATION_JSON);//说明提供的是一个json格式的query
        Response response;
        try {
            Request request = new Request(method, endpoint);
            request.setEntity(entity);
            request.addParameter("pretty", "true");
            request.setOptions(ESRestAPIUtils.getCommonOptions());
            response = restClient.performRequest(request);
            resultJson = EntityUtils.toString(response.getEntity(), "UTF-8");
            return resultJson;
        } catch (IOException e) {
            log.error("查询请求失败..." + queryJson, e);
            return resultJson;
        } finally {
            try {
                restClient.close();
            } catch (IOException e) {
                log.error("Rest客户端关闭失败...", e);
            }
        }
    }

    /**
     * @DESC: 组装ES的hosts为HttpHost
     */
    private static List<HttpHost> assembleESHost() {
        ArrayList<HttpHost> esHostList = new ArrayList<>();
        //String[] esAddrArray = ConfigUtils.getSingleConf("es/cluster.nodes").split(",");
        String[] esAddrArray = new String[]{"hdp01","hdp02","hdp03"};
        for (String es : esAddrArray)
            esHostList.add(new HttpHost(es, Integer.valueOf("9201")));
        return esHostList;
    }
}
