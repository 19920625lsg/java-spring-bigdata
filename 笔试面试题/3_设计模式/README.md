# 3_设计模式

## 填空题

## 判断题

## 单选题
### 1.Java数据库连接库JDBC用到哪种设计模式?（`B`）
+ A.生成器
+ B.桥接模式
+ C.抽象工厂
+ D.单例模式

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/4361013

桥接模式：
定义 ：将抽象部分与它的实现部分分离，使它们都可以独立地变化。
意图 ：将抽象与实现解耦。
桥接模式所涉及的角色
+ 1、Abstraction ：定义抽象接口，拥有一个Implementor类型的对象引用
+ 2、RefinedAbstraction ：扩展Abstraction中的接口定义
+ 3、Implementor ：是具体实现的接口，Implementor和RefinedAbstraction接口并不一定完全一致，实际上这两个接口可以完全不一样Implementor提供具体操作方法，而Abstraction提供更高层次的调用
+ 4、ConcreteImplementor ：实现Implementor接口，给出具体实现

Jdk中的桥接模式：JDBC
JDBC连接 数据库 的时候，在各个数据库之间进行切换，基本不需要动太多的代码，甚至丝毫不动，原因就是JDBC提供了统一接口，每个数据库提供各自的实现，用一个叫做数据库驱动的程序来桥接就行了

## 多选题
### 1.单例模式中，两个基本要点是(`AC`)
+ A.构造函数私有
+ B.静态工厂方法
+ C.唯一实例
+ D.以上都不对

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12779882


## 问答题