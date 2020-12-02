package com.example.demo.service;

import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @DESC: 提供操作ES索引的公共抽象方法
 * @Auther: Anryg
 * @Date: 2020/11/11 16:39
 */
public interface BasicESService<T> {

    long getCount();

    T save(T t);

    void delete(T t);

    Iterable<T> search(QueryBuilder query);

    Page<T> search(QueryBuilder query, Pageable pageable);

}
