# 项目优化

## JPA与Mybatis

+ 小项目用JPA
+ 大项目用MyBatis

### JPA建表用sql语句，不要用类注解

> 类注解很麻烦，而且默认的很多属性太反人类

### 表与表之间尽量不用外键，最好用代码逻辑代替外键

> JPA的外键是用@OneToMany和@ManyToOne，尽量忘掉这两个东西

## 用压测模拟并发

> 参考文章：https://www.cnblogs.com/lishuyi/p/5808661.html

+ 使用简易工具 `Apache ab`
+ `ab -n 100 -c 100 http://www.baidu.com`
  > -n表示发出100个请求;-c表示100个并发
+ `ab -t 60 -c 100 http://www.baidu.com`
  > -t表示60s;-c表示100个并发。即60s内100个并发不停地进行请求

## redis使用

> 参考教程：http://www.redis.cn