package com.example.demo.service.impl;


import com.example.demo.dao.es.DomainSearchRouteRepository;
import com.example.demo.entity.es.DomainSearchRoute;
import com.example.demo.service.BasicESService;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;

/**
 * @DESC:
 * @Auther: Anryg
 * @Date: 2020/11/11 16:55
 */
@Service
public class DomainSearchRouteImpl implements BasicESService<DomainSearchRoute> {

    @Autowired
    DomainSearchRouteRepository esRepository;
    //BasicESRepository<DomainSearchRoute> esRepository;

    @Override
    public long getCount() {
        return esRepository.count();
    }

    @Override
    public DomainSearchRoute save(DomainSearchRoute domainSearchRoute) {
        return esRepository.save(domainSearchRoute);
    }

    @Override
    public void delete(DomainSearchRoute domainSearchRoute) {
        esRepository.delete(domainSearchRoute);
    }

    @Override
    public Iterable<DomainSearchRoute> search(QueryBuilder query) {
        return esRepository.search(query);
    }

    @Override
    public Page<DomainSearchRoute> search(QueryBuilder query, Pageable pageable) {
        return esRepository.search(query,pageable);
    }
}
