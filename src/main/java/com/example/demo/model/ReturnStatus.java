package com.example.demo.model;

/**
 * @DESC: 定义页面请求的返回状态值
 * @Auther: Anryg
 * @Date: 2020/9/10 17:32
 */
public enum ReturnStatus {
    OK(200),/**请求成功*/
    ERROR(500),/**请求失败*/
    PARASERROR(400), /**请求参数错误*/
    PATHNOTFOUND(404),/**请求不存在的路径*/
    UNAUTHORIZED(401);/**请求没有权限*/

    public int code;

    ReturnStatus(int code){
        this.code = code;
    }
}
