package com.example.demo.entity;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

/**
 * @DESC: 封装DNS统计结果的对象
 * @Auther: Anryg
 * @Date: 2020/9/10 15:58
 */
@Data
public class StatisticsReturnEntity<T extends JSON> {
    private int returnCode;
    private int costTime;
    private T data;
}
