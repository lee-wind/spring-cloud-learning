# spring-cloud-learning
spring-cloud学习手稿

我的环境：VirtualBox虚拟机+Centos7系统+IDEA+Postman

(项目各模块都已运行成功)

服务发现：Consul(安装后 执行 consul agent -dev)

服务提供者：  
    provider1(http://localhost:8081/hi?name=wind)  
    provider2(http://localhost:8082/hi?name=wind)

服务消费者(Feign用于负载均衡)：  
    consumer1(http://localhost:8086/hi?name=wind)  
    consumer2(http://localhost:8087/hi?name=wind)

断路器局和监控(Hystrix Turbine)：  
    provider3(http://localhost:8083/hi?name=wind)
    provider4(http://localhost:8084/hi?name=wind)
    Turbine(http://localhost:8085/turbine.stream  
            http://localhost:8085/hystrix)

网关(Gateway):  
    gateway(localhost:8095/gateway/login?userId=21  
            localhost:8095/consumer2/hi?name=wind  
            localhost:8095/consumer1/hi?name=wind(headers需要带Authorization,值为login返回的值(JWT)))

高可用分布式配置：  
    config-server(http://localhost:8090/foo/dev)  
    config-client(http://localhost:8092/foo)
    
参考博客：https://blog.csdn.net/forezp/article/details/70148833