package com.example.demo.dao.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @DESC: 查询（操作）ES的REST 低阶api工具类
 * @Auther: Anryg
 * @Date: 2020/9/11 16:54
 */
@Component(value = "esRestAPIUtils")
//@ConfigurationProperties(prefix = "elasticsearch")
@RefreshScope /**确保热生效*/
@Slf4j
public class ESRestAPIUtils {
    private static String esHosts /*= (String[])ConfigFactory.getBean("esHosts")*/;
    private static int esPort;
    private static volatile RestClientBuilder restClientBuilder;
    private static volatile RequestOptions commonOptions;

    public/*必须public，作为bean，构造方法必须对容器可见*/ ESRestAPIUtils(){}

    @Value(value = "${elasticsearch.nodes}") /**不能作用于静态变量，也不能作用于静态方法，无法注入*/
    public void setEsHosts(String esHosts){
        this.esHosts = esHosts;
    }

    @Value(value = "${elasticsearch.http_port}")
    public void setEsPort(int esPort){
        this.esPort = esPort;
    }

    /**
     * @DESC: 因为autowire的不能作用于静态成员变量
     * */
/*    @Autowired
    public void setEsHosts(String[] esHosts) {
        this.esHosts = esHosts;
    }*/

    /**
     * @DESC: 获取RestClientBuilder对象，懒汉模式，单列模式
     * */
    private static RestClientBuilder getBuilder(){
        if (null == restClientBuilder){
            synchronized (ESRestAPIUtils.class){
                if (null == restClientBuilder){
                    restClientBuilder = RestClient.builder(assembleESHost().toArray(new HttpHost[3]));
                    Header[] header = new Header[]{new BasicHeader("header", "value")};
                    restClientBuilder.setDefaultHeaders(header);
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
                    RequestOptions.Builder requestOptionBuilder = RequestOptions.DEFAULT.toBuilder();
                    requestOptionBuilder.setHttpAsyncResponseConsumerFactory(
                            new HttpAsyncResponseConsumerFactory.HeapBufferedResponseConsumerFactory(800 * 1024 * 1024));
                    commonOptions = requestOptionBuilder.build();
                }
            }
        }
        return commonOptions;
    }

    /**
     * @DESC: 普通查询，非聚合方式。根据提供的索引名，以及查询语句返回查询结果的json array
     * */
    public static JSON searchByQueryJson(String index, String queryJson) throws IOException {
        JSONObject responseJSON = null;
        //try {
            responseJSON = JSON.parseObject(query(queryJson,"POST",index + "/_search"));
        /*} catch (Exception e) {
            log.error("查询{}报错...",queryJson);
        }*/
        JSONArray hitsArray = JSON.parseArray(JSON.parseObject(responseJSON.getString("hits")).getString("hits"));
        int totalHits = hitsArray.size();
        JSONArray resultArray = new JSONArray(totalHits);
        Iterator<Object> hitsItor = hitsArray.iterator();
        while (hitsItor.hasNext()){
            JSONObject nextJson = JSON.parseObject(hitsItor.next().toString());
            JSONObject targetJson = JSON.parseObject(nextJson.getString("_source"));
            resultArray.add(targetJson);
        }
        return resultArray;
    }

    /**
     * @return : 返回请求后的json字符串
     * @DESC: 可接受所有对于ES的查询操作，包括数据写入、删除、一般的条件查询和聚合查询
     */
    public static String query(String queryJson, String method, String endpoint) throws IOException {
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
        /*} catch (IOException e) {
            log.error("查询请求失败..." + queryJson, e);
            return resultJson;*/
        } finally {
            restClient.close();
        }
    }

    /**
     * @DESC: 组装ES的hosts为HttpHost
     */
    private static List<HttpHost> assembleESHost() {
        ArrayList<HttpHost> esHostList = new ArrayList<>();
        //String[] esAddrArray = ConfigUtils.getSingleConf("es/cluster.nodes").split(",");
        for (String es : esHosts.split(",")) esHostList.add(new HttpHost(es, esPort));
        return esHostList;
    }
}
