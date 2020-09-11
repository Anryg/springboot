package com.example.demo.entity;

/**
 * @DESC: 定义页面请求的返回状态值
 * @Auther: Anryg
 * @Date: 2020/9/10 17:32
 */
public enum ReturnStatus {
    OK(200),/**请求成功*/
    ERROR(500),/**请求失败*/
    NOTFOUND(404),/**请求结果为空*/
    UNAUTHORIZED(401);/**请求没有权限*/

    public int code;

    ReturnStatus(int code){
        this.code = code;
    }
}
