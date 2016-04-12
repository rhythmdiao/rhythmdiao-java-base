#Build Status
[![Build Status](https://travis-ci.org/rhythmdiao/rhythmdiao-java-base.svg?branch=master)](https://travis-ci.org/rhythmdiao/rhythmdiao-java-base)
[![Coverage Status](https://coveralls.io/repos/rhythmdiao/rhythmdiao-java-base/badge.svg?branch=master&service=github)](https://coveralls.io/github/rhythmdiao/rhythmdiao-java-base?branch=master)
#Features 特征
*   easy-to-use embedded jetty 8 server and daemon assembler for building and running application
*   嵌入式jetty服务器以及daemon模式构建应用程序
*   provides annotated handlers to demonstrate simple restful like API, handler switch
*   Restful风格自定义接口，注解方式注射请求参数，接口降级开关
*   wrapped json and xml result to generate http response
*   json和xml格式请求响应
*   wrapped local cache and request path map to manage application level cache and uri
*   封装过的本地缓存管理器和接口管理器，使用guava
*   wrapped http client to fetch data
*   封装过的http client用于远程调用
*   thread pool with monitor
*   自定义线程池，监视任务提交
*   build passing
*   构建通过
*   and more!
*   更多

#Getting Started 开始
*   rhythmdiao-core, the core functionality offered by the framework
*   core包提供框架核心功能
*   rhythmdiao-client, a wrapped module of apache http client
*   client包封装了一个http client
*   rhythmdiao-sample, example of handlers
*   sample包提供了框架接口样例
*   rhythmdiao-leetcode, ignore this, just for code practice
*   leetcode包是个人练习，不用管
*   rhythmdiao-launcher, main module of the framework, launching it as an application 
*   launcher包封装了启动主模块和配置文件，可以当做主程序启动应用   

#Usage 使用
For handler, you can simply do as below
```java
public
@Controller
@RestfulHandler(target = "/test", method = HttpMethods.GET)
class TestHandler extends BaseHandler {
    @RequestHeader//request header injection,注入请求头到变量
    private int field1;

    @RequestParameter//request parameter injection,注入请求参数到变量
    private String field2;

    @CookieParameter//cookie value injection,注入cookie到变量
    private String field3;

    public Parser execute() {
        Result result = new Result();
        //do something
        //return a parser as return type with result
        LogUtil.info(LOG, "for test:{}", "hello world");
        return new GsonParser(result);
    }
}
```
--------------------
For http client, you can simply do as below
```java
    HttpGetClient httpGetClient = new HttpGetClient("http", "localhost:8080");
    HttpProperty property = new HttpProperty();
    property
            .setHeader("field1", "123")
            .setParameter("field2", "test");
    String response = httpGetClient.execute("/test", property);
    //do something with the response
```

#License
*   Copyright(c) 2014-2016 Yuxing Ma

#Author
*   rhythmdiao@163.com
*   If any questions, freely contact me
