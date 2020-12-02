package com.example.demo.dao.es;

import com.example.demo.entity.es.DomainSearchRoute;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @DESC: 定义通过
 * @Auther: Anryg
 * @Date: 2020/11/11 16:01
 */
@Repository
public interface DomainSearchRouteRepository extends ElasticsearchRepository<DomainSearchRoute, String>/**必须指定具体的类型*/ {
}
