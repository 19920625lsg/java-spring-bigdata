# 4-3 买家类目-service

## 关于@Service和@Transactional注解的使用

+ `@Sevice`和`@Transactional`注解应该放在实现类`xxxServiceImpl`类上
+ 在Controller和Test类中中注入Service时应该注入`xxxService`而不是`xxxServiceImpl`
