package com.example.demo.entity;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @DESC: 封装DNS统计结果的对象
 * @Auther: Anryg
 * @Date: 2020/9/10 15:58
 */
@Data
@Getter
@Setter
public class StatisticsReturnEntity<T extends JSON> {
    private int returnCode;
    private int costTime;
    private T data;
}
