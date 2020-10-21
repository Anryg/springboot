package com.example.demo.config;

/**
 * @DESC: 项目中需要用到的组件基础配置
 * @Auther: Anryg
 * @Date: 2020/10/10 14:47
 */
public class BasicProperties {
    private ElasticsearchProperty elasticsearch;
    private RedisProperty redis;
    private KafkaProperty kafka;
    private ZookeeperProperty zookeeper;

    public ElasticsearchProperty getElasticsearch() {
        return elasticsearch;
    }

    public void setElasticsearch(ElasticsearchProperty elasticsearch) {
        this.elasticsearch = elasticsearch;
    }

    public RedisProperty getRedis() {
        return redis;
    }

    public void setRedis(RedisProperty redis) {
        this.redis = redis;
    }

    public KafkaProperty getKafka() {
        return kafka;
    }

    public void setKafka(KafkaProperty kafka) {
        this.kafka = kafka;
    }

    public ZookeeperProperty getZookeeper() {
        return zookeeper;
    }

    public void setZookeeper(ZookeeperProperty zookeeper) {
        this.zookeeper = zookeeper;
    }
}
