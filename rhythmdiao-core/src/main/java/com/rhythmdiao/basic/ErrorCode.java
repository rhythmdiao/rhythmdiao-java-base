package com.rhythmdiao.basic;

/**
 * @author rhythmdiao
 * 错误码
 */

public enum ErrorCode {
    /**
     *
     */
    OK("00000", "一切ok", "正确执行后的返回"),
    UP("A0001", "用户端错误", "一级宏观错误码"),
    UP_REGISTER("A0100", "用户注册错误", "二级宏观错误码"),
    UP_LOGIN("A0200", "用户登录异常", "二级宏观错误码"),
    UP_AUTH("A0300", "访问权限异常", "二级宏观错误码"),
    UP_REQUEST("A0400", "用户请求参数错误", "二级宏观错误码"),
    UP_ACTION("A0500", "用户请求服务错误", "二级宏观错误码"),
    UP_RESOURCE("A0600", "用户资源错误", "二级宏观错误码"),
    UP_FILE("A0700", "用户上传文件异常", "二级宏观错误码"),
    UP_VERSION("A0800", "用户当前版本异常", "二级宏观错误码"),
    UP_PRIVACY("A0900", "用户隐私未授权", "二级宏观错误码"),
    UP_EQUIPMENT("A1000", "用户设备异常", "二级宏观错误码"),
    IN("B0001", "系统执行出错", "一级宏观错误码"),
    IN_TIMEOUT("B0100", "系统执行超时", "二级宏观错误码"),
    IN_TOLERATION("B0200", "系统容灾功能被触发", "二级宏观错误码"),
    IN_RESOURCE("B0300", "系统资源异常", "二级宏观错误码"),
    DOWN("C0001", "调用第三方服务出错", "一级宏观错误码"),
    DOWN_MIDDLEWARE("C0100", "中间件服务出错", "二级宏观错误码"),
    DOWN_TIMEOUT("C0200", "第三方系统执行超时", "二级宏观错误码"),
    DOWN_DATABASE("C0300", "数据库服务出错", "二级宏观错误码"),
    DOWN_TOLERATION("C0400", "第三方容灾系统被触发", "二级宏观错误码"),
    DOWN_INFORM("C0500", "通知服务出错", "二级宏观错误码");


    /**
     * 错误码
     */
    private String code;
    /**
     * 中文描述
     */
    private String msg;
    /**
     * 说明
     */
    private String desc;

    ErrorCode(String code, String msg, String desc) {
    }
}
