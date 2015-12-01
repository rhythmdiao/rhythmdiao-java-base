##Build Status
[![Build Status](https://travis-ci.org/Rhythmdiao/rhythmdiao-java-base.svg)](https://travis-ci.org/Rhythmdiao/rhythmdiao-java-base)

##Features 特征
--------------------
*   easy-to-use embed jetty server and deamons assembler for building and running application
*   嵌入式jetty服务器以及deamon模式构建应用程序
*   provides annotated handlers to demonstrate simple restful webservice API, handler switch
*   Restful风格自定义接口，注解方式注射请求参数，接口降级开关
*   wrapped json and xml result to generate http response
*   json和xml格式请求响应
*   wrapped cache and path map to manage application level cache and uri
*   封装过的缓存管理器，使用guava
*   custom HTTP clients to fetch data from outside
*   封装过的Http client用于远程调用
*   thread pool with monitor
*   自定义线程池，监视任务提交
*   build passing
*   构建通过
*   and more!
*   更多

##Getting Started 开始
--------------------
*   rhythmdiao-core, the core functionality offered by the framework
*   core包提供框架核心功能
*   rhythmdiao-client, a wrapped module of http client 
*   client包封装了一个http client
*   rhythmdiao-sample, example of handlers
*   sample包提供了框架接口样例
*   rhythmdiao-launcher, main module of the framework, launching it as an application 
*   launcher包封装了启动主模块和配置文件，可以当做主程序启动应用   

##Usage 使用
--------------------
In your code for handlers, you can simply do as below
```java
@Controller
@RestfulHandler(target = "/test", method = HttpMethods.GET)
public class TestHandler extends BaseHandler {
    @RequestHeader//request header injection,注入请求头到变量
    private int field1;

    @RequestParameter//request parameter injection,注入请求参数到变量
    private String field2;

    @CookieParameter//cookie value injection,注入cookie到变量
    private String field3;

    public AbstractResult execute() {
        AbstractResult result = new JsonResult();
        //do something
        return result;
    }
    //setter is nesscery，变量修改器不能少，注射必要
    public void setField1(int field1) {
        this.field1 = field1;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }
}
```
In your code for http client, you can simply do as below
```java
        HttpGetClient httpGetClient = new HttpGetClient("http", "localhost:8080");
        HttpRequest httpRequest = new HttpRequest();
        httpRequest
                .setHeader("field1", "123")
                .setParameter("field2", "test");
        String response = httpGetClient.execute("/test", httpRequest);
        //do sothing with the response
```
