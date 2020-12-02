package com.example.demo.entity.es;

/**
 * @DESC:
 * @Auther: Anryg
 * @Date: 2020/11/13 16:07
 */
public enum EDomainSearchRoute {
    ID("id"),
    QUERY("query"),
    ANSWER("answer");

    private String field;

    EDomainSearchRoute(String field){
        this.field = field;
    }

    public String getField(){
        return this.field;
    }
}
