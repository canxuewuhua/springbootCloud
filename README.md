# springbootCloud
# 参考网址：https://blog.csdn.net/huqigang/article/details/79359467
#服务治理
 服务治理可以说是微服务架构中最为核心和基础的模块，主要用来实现各个微服务实例的自动化注册与发现。
# 服务注册
 在服务治理框架中，通常都会构建一个注册中心，每个服务单元都向注册中心登记自己提供的服务，将主机与端口号、版本号、
 通信协议等一些附加信息告知注册中心，注册中心按服务名分类组织服务清单。
 服务注册中心还需要以心跳的方式去监测清单中的服务是否可用，若不可用，从服务清单中剔除，达到排除故障服务的效果。
# 服务发现
  在服务治理框架下运作，服务间的调用不再通过指定具体的实例地址来实现，而是通过向服务名发起请求调用实现。
# Eureka服务端
  即为服务注册中心。 
  如果以集群模式部署，当集群中有分片出现故障时，Eureka就会转入自我保护模式。它允许在分片故障期间继续提供服务的发现与注册，
  当故障分片恢复运行的时候，集群中的其他分片会把它们的状态再次同步回来。
# Eureka客户端
 主要处理服务的注册与发现，在应用程序运行时，客户端向注册中心注册自身提供的服务并周期性地发送心跳来更新它的服务租约。 
 同时它也能从服务端查询当前注册的服务信息，并把它们缓存到本地并周期性地刷新服务状态。
 
 
 # 拜托!面试不要再问我Spring Cloud底层原理
   参考网址：http://developer.51cto.com/art/201811/586513.htm
   
# 注意 ： 使用RestTemplate的方式调用远程的接口，和传统的RPC 有所区别

# 20200206 三个eureka组成的集群 三个生产者 一个消费者
   目前实现了在消费者中访问 http://localhost:9001/user/getUserName
   由于在消费者端配置了负载均衡 刷新页面后负载均衡轮询，返回不同的数据源信息
  
# 20200220 自定义负载均衡算法
# 20200223 負載均衡Ribbon，可以自定义负载均衡的算法，比如说默认的是轮询算法，我们改为
# 每个服务访问5次，换下一个服务（3个服务），这个功能我在代码中并没有实现，如果想实现可参考
# https://www.bilibili.com/video/av76020761?p=12

# 202002231634 feign 主要是社区 大家都习惯面向接口编程 调用微服务访问两种方法
# 1、微服务名字【ribbon】 2、接口和注解【feign】 
# 2、关键点在api项目中写一个接口类，该接口类上要标注 @FeignClient(value = "MICROSERVICE-PROVIDER-USER")
# 3、抽象方法上的路径需要是服务提供者的访问路径
# 讲解视频的作者倾向于使用restful风格的代码，本实例使用的是注解和接口

 #准备讲解服务熔断的内容202002232008 服务雪崩 多个服务之间调用的时候
   Hystrix能保证一个依赖出问题后，不会导致整体服务失败，避免级联故障，。提高分布式系统的弹性
   服务降级 服务熔断 服务限流 接近实时的监控
   熔断机制是对应雪崩效应的一种微服务链路保护机制 5秒内20次调用失败，就会启动熔断机制
   
   在Hystrix项目的启动类上添加上下面的注解
   @EnableCircuitBreaker // 添加对熔断的支持
   
   202002251715实现了服务熔断，但是视频中消费者去调服务提供者，提供者使用api中的interface再去调服务者，没有调通
   自己使用消费者去调服务提供者，在服务提供者的方法中添加 @HystrixCommand(fallbackMethod = "hystrixGet")去调用备用方法，成功
    
   # 20200226服务降级
   服务降级在客户端  服务熔断在服务端
   
   服务降级功能已经实现
   实现步骤为：
   1、使用API项目DeptClientService接口中添加fallbackFactory = DeptClientServiceFallBackFactory.class
   2、新写一个工厂类 DeptClientServiceFallBackFactory，该类的作用是：如果服务提供方突然服务宕机了，此时客户端会提示在工厂类
      中写的提示，不至于服务瘫痪返回500
   3、在consumer的feign项目中
      启动类上添加（这是是关键，不然启动不了服务）
      @ComponentScans(value =  { @ComponentScan(value = "com.kuang"), @ComponentScan(value = "com.springcloud")})
      @ComponentScan 指定要扫描的package
      
      服务熔断 服务端  某个服务超时或者异常，引起熔断，类似保险丝
      服务降级 客户端 从整体网站请求负载考虑，当某个服务熔断或者关闭之后，服务将不再被调用 ,此时我们在客户端 可以准备一个
                      fallbackfactory，返回一个默认的值，整体的服务水平下降了，但是好歹能用
      服务熔断以及降级已经理解
      
      
  #20200226 服务监控
  Hystrix Dashboard 
  写一个监控该页面，往里面放服务 
  几个服务提供者需要添加监控的依赖
  在ProviderHystrixApplication_8001类里面写一个servlet
  测试结果：http://localhost:9003/hystrix 页面显示豪猪页面
  http://localhost:8001/actuator/hystrix.stream 显示ping后的数据 monitor stream 显示图形的实时监控健康情况
  一直loading中，没有看到实际的结果，可能哪里配置的不对，监控的整理先到这儿
  
  没看到实际结果，是因为：需要调用feign 服务API（即provider hystrix的controller），必须是hystrix的provider，再次查看界面
  就可以了
  #202002261751 Zuul路由网关
  
 
  路由网关很简单，只需要导入zuul的依赖，在ZuulApplication_9527启动类上开启 @EnableZuulProxy 注解
  然后在yml文件中设置可以访问的url地址组成，将原有服务名进行转化为一个不是真实的名称，以便安全
  也可以设置公共的前缀，比如：prefix: /qiang ，在原有的url再加上这个"/qiang"
  
  路由网关结束