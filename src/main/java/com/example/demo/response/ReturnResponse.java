package com.example.demo.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.ReturnStatus;
import com.example.demo.entity.StatisticsReturnEntity;

/**
 * @DESC: 根据正常或者错误返回对象
 * @Auther: Anryg
 * @Date: 2020/9/10 17:46
 */
public class ReturnResponse {

    /**
     * @DESC: 生成正常的对象返回
     * */
    public static StatisticsReturnEntity getSuccessReturn(JSON data,int costTime){
        StatisticsReturnEntity<JSON> result = new StatisticsReturnEntity<>();
        result.setData(data);
        result.setReturnCode(ReturnStatus.OK.code);
        result.setCostTime(costTime);
        return result;
    }

    /**
     * @DESC: 生成结果为空的对象
     * */
    public static StatisticsReturnEntity getEmptyReturn(int costTime){
        StatisticsReturnEntity<JSON> result = new StatisticsReturnEntity<>();
        result.setData(new JSONObject(0));
        result.setReturnCode(ReturnStatus.NOTFOUND.code);
        result.setCostTime(costTime);
        return result;
    }

    /**
     * @DESC: 生成请求错误的对象
     * */
    public static StatisticsReturnEntity getErrorReturn(Throwable e,int costTime){
        StatisticsReturnEntity<JSON> result = new StatisticsReturnEntity<>();
        JSONObject errorJson = new JSONObject(1);
        errorJson.put("error",e.getMessage());
        result.setData(errorJson);
        result.setReturnCode(ReturnStatus.ERROR.code);
        result.setCostTime(costTime);
        return result;
    }

    /**
     * @DESC: 生成请求没有权限的对象
     * */
    public static StatisticsReturnEntity getNoAuthReturn(int costTime){
        StatisticsReturnEntity<JSON> result = new StatisticsReturnEntity<>();
        result.setData(new JSONObject(0));
        result.setReturnCode(ReturnStatus.UNAUTHORIZED.code);
        result.setCostTime(costTime);
        return result;
    }
}
