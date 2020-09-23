package com.example.demo.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.ReturnStatus;
import com.example.demo.entity.StatisticsReturnEntity;

/**
 * @DESC: 根据正常或者错误返回对象
 * @Auther: Anryg
 * @Date: 2020/9/10 17:46
 */
public class ReturnResponseUtils {

    /**
     * @DESC: 生成正常的对象返回
     * */
    public static StatisticsReturnEntity getSuccessReturn(JSON data){
        StatisticsReturnEntity<JSON> result = new StatisticsReturnEntity<>();
        result.setData(data);
        result.setReturnCode(ReturnStatus.OK.code);
        return result;
    }

    /**
     * @DESC: 生成请求路径错误时返回的对象
     * */
    public static StatisticsReturnEntity getErrorPathReturn(){
        StatisticsReturnEntity<JSON> result = new StatisticsReturnEntity<>();
        result.setData(new JSONObject(0));
        result.setReturnCode(ReturnStatus.PATHNOTFOUND.code);
        return result;
    }

    /**
     * @DESC: 生成请求过程中参数错误的对象
     * */
    public static StatisticsReturnEntity getErrorParasReturn(){
        StatisticsReturnEntity<JSON> result = new StatisticsReturnEntity<>();
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("msg", "request paras is illegal,please check it");
        jSONArray.add(jSONObject);
        result.setData(jSONArray);
        result.setReturnCode(ReturnStatus.PARASERROR.code);
        //result.setCostTime(time+" ms");
        return result;
    }

    /**
     * @DESC: 生成请求错误的对象
     * */
    public static StatisticsReturnEntity getErrorReturn(Throwable e/*,int costTime*/){
        StatisticsReturnEntity<JSON> result = new StatisticsReturnEntity<>();
        JSONObject errorJson = new JSONObject(1);
        errorJson.put("error",e.getMessage());
        result.setData(errorJson);
        result.setReturnCode(ReturnStatus.ERROR.code);
        //result.setCostTime(costTime);/*花的时间在切面中处理*/
        return result;
    }

    /**
     * @DESC: 生成请求没有权限的对象
     * */
    public static StatisticsReturnEntity getNoAuthReturn(){
        StatisticsReturnEntity<JSON> result = new StatisticsReturnEntity<>();
        result.setData(new JSONObject(0));
        result.setReturnCode(ReturnStatus.UNAUTHORIZED.code);
        return result;
    }
}
