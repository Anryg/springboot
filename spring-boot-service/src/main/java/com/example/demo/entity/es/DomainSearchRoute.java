package com.example.demo.entity.es;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * @DESC:
 * @Auther: Anryg
 * @Date: 2020/11/11 15:48
 */
@Data
@Document(indexName = "domain_search_route3.0")
public class DomainSearchRoute implements Serializable {

    @Id
    private String id;
    private String rdtype;
    private String query;
    private String answer;
    private Object routes;
}
