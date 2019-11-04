3、使用git服务器结合Config搭建分布式配置中心

    访问方式（一定要注意语法，如果有问题，会出错）
    多种访问路径，可以通过启动日志去查看
    例子 http://localhost:9100/product-service.yml

    /{name}-{profiles}.properties
    /{name}-{profiles}.yml
    /{name}-{profiles}.json
    /{label}/{name}-{profiles}.yml

    name 服务器名称
    profile 环境名称，开发、测试、生产
    lable 仓库分支、默认master分支



4、分布式配置中心客户端使用实战
	简介：微服务里面客户端接入配置中心实战
		官方文档：http://cloud.spring.io/spring-cloud-config/single/spring-cloud-config.html#_spring_cloud_config_client

		1、加入依赖
			 <dependency>
	            <groupId>org.springframework.cloud</groupId>
	            <artifactId>spring-cloud-config-client</artifactId>
	        </dependency>

	     2、修改对应服务的配置文件,把application.yml 改为 bootstrap.yml

			#服务的名称
			spring:
			  application:
			    name: product-service
			  #指定从哪个配置中心读取
			  cloud:
			    config:
			      discovery:
			        service-id: CONFIG-SERVER
			        enabled: true
			      profile: test
			      #建议用lable去区分环境，默认是lable是master分支
			      #label: test

		注意点：
			1.配置文件要用bootstrap.yml
			2.默认读取文件名是 服务名称


5、高级篇幅消息总线整合配置中心架构流程图
		简介：讲解消息总线Bus结合config组件搭建配置中心项目架构图和操作流程

			启动
			rabbitmq： docker run -d -p 5672:5672 -p 15672:15672 rabbitmq:management
			rabbitmq默认是5672,所以改为5672端口

			1、config-client加入依赖

		        <!--配置中心结合消息队列-->

		        <dependency>
		            <groupId>org.springframework.boot</groupId>
		            <artifactId>spring-boot-starter-actuator</artifactId>
		        </dependency>

		        <dependency>
		            <groupId>org.springframework.cloud</groupId>
		            <artifactId>spring-cloud-starter-bus-amqp</artifactId>
		        </dependency>



			官方文档：http://cloud.spring.io/spring-cloud-bus/single/spring-cloud-bus.html#_bus_refresh_endpoint
			文档里面 暴露端点 management.endpoints.web.exposure.include=bus-refresh

			2、在配置文件中增加关于RabbitMQ的连接(如果是本机，则可以直接启动，采用默认连接配置)
				spring:
				  rabbitmq:
				    host: localhost
				    port: 5672
				    username: guest
				    password: guest

				#暴露全部的监控信息
				management:
				  endpoints:
				    web:
				      exposure:
				        include: "*"

			3、需要刷新配置的地方，增加注解
				@RefreshScope

			4、访问验证 post方式：
				http://localhost:8773/actuator/bus-refresh


			5、动态刷新配置: 在开发和测试环境使用，尽量少在生产环境使用