package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.aop.annotation.LogAnnotation;
import com.example.demo.dao.config.ElasticsearchProperty;
import com.example.demo.dao.es.DomainSearchRouteRepository;
import com.example.demo.entity.es.DomainSearchRoute;
import com.example.demo.model.StatisticsReturnEntity;
import com.example.demo.model.ReturnResponseUtils;
import com.example.demo.service.ElasticsearchService;
import com.example.demo.service.TestService;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

/**
 * @DESC:
 * @Auther: Anryg
 * @Date: 2020/9/7 19:53
 */
@RestController
@Slf4j
@RequestMapping(path = {"/test","/dev"})
//@ConfigurationProperties(value = "config2") 可以不用
@RefreshScope/**配置修改热生效*/
//@Profile()
public class TestController {

    @Autowired
    //ElasticsearchRestTemplate esTemplate;
    DomainSearchRouteRepository esRepository;
    //BasicESService<DomainSearchRoute> domainSearchRouteService;

    @Autowired
    UserService userService;

    /*@Autowired
    HttpServletRequest request;*/

    /*@Autowired
    HttpSession httpSession;*/

    /*@Autowired
    RedisTemplate redisTemplate;*/

    @Value(value = "${kafka.hosts}")
    private String kafka;
    
    @Autowired
    TestService service;

    @Autowired
    private ElasticsearchProperty esProperty;

    @Autowired
    ElasticsearchService esService;

    @LogAnnotation(desc = "调用fun1...")
    @RequestMapping(path = "/fun1")
    public StatisticsReturnEntity fun1(@RequestBody String str){
        StatisticsReturnEntity result;
        try {
            throw new Exception("手动抛异常...");
        } catch (Exception e) {
            result = ReturnResponseUtils.getErrorReturn(e);
            log.error(e.getMessage(),e);
        }
        return result;
    }

    @PostMapping(path = "/fun2")
    public String fun2(){
        return service.service("xyz");
    }


    @LogAnnotation(desc = "调用fun4")
    @RequestMapping(path = "/fun4")
    public Object fun4(@RequestBody String index) throws IOException {
        StatisticsReturnEntity result;
        String resultJson = esService.simpleQuery(index, "");
        result = ReturnResponseUtils.getSuccessReturn(JSON.parseArray(resultJson));
        return result;
    }


    @LogAnnotation(desc = "调用fun6...")
    @RequestMapping(path = "/fun6")
    public String fun6(){
        return esProperty.getName();
    }

    @LogAnnotation(desc = "调用fun8...")
    @RequestMapping(path = "/fun8")
    public String fun8(){
        return "fun8";
    }

    @LogAnnotation(desc = "调用fun9...")
    @RequestMapping(path = "/fun9")
    public String fun9(){
        return "fun9";
    }


    @LogAnnotation(desc = "调用fun7...")
    @RequestMapping(path = "/fun7")
    public String fun7(@RequestParam String str1, @RequestParam String str2){
        return str1 + str2;
    }

    @LogAnnotation(desc = "调用fun10...")
    @RequestMapping(path = "/fun10")
    public DomainSearchRoute fun10(){
        //return domainSearchRouteService.getCount();
        //Map<String, Object> mapping = esTemplate.indexOps(IndexCoordinates.of("dns_client_request_rank")).getMapping();
        //return mapping.toString();
        Optional<DomainSearchRoute> doc = esRepository.findById("09a492fc60b4401e8237ed04196c81ee.fp.measure.office.com_AAAA");
        return doc.get();
    }

    @LogAnnotation(desc = "调用fun11...")
    @RequestMapping(path = "/fun11")
    @Cacheable(cacheNames = "testCache",key = "fun11")
    public String fun11(){
        //JedisConnectionFactory jedisFactory = (JedisConnectionFactory) redisTemplate.getConnectionFactory();
        //RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        //config.setDatabase(3);
        //Object result = redisTemplate.opsForValue().get("domain-success");
        return null;
    }


    @LogAnnotation(desc = "调用fun12...")
    @RequestMapping(path = "/fun12")
    public String fun12(HttpSession session){/**该调用就会显示声明session*/
        Object sessionAttribute = session.getAttribute("abc");
        if (null == sessionAttribute){
            session.setAttribute("abc","Anryg");
        }
        return session.getAttribute("abc").toString();
    }



}
