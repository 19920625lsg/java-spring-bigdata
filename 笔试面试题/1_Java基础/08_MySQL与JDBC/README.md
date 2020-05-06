# 08_MySQL与JDBC

## 一、填空题

## 二、判断题

## 三、单选题
### 1.以下描述正确的是（`B`）
+ A.CallableStatement是PreparedStatement的父接口
+ B.PreparedStatement是CallableStatement的父接口
+ C.CallableStatement是Statement的父接口
+ D.PreparedStatement是Statement的父接口

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12777630
![java_sql_Statement中的继承关系](images/java_sql_Statement中的继承关系.png)

## 四、多选题
### 1.以下可以正确获取结果集的有(`AD`)
+ A.
  ```java
  Statement sta=con.createStatement();
  ResultSet rst=sta.executeQuery(“select * from book”);
  ```
+ B.
  ```java
  Statement sta=con.createStatement(“select * from book”);
  ResultSet rst=sta.executeQuery();
  ```
+ C.
  ```java
  PreparedStatement pst=con.prepareStatement();
  ResultSet rst=pst.executeQuery(“select * from book”);
  ```
+ D.
  ```java
  PreparedStatement pst=con.prepareStatement(“select * from book”);
  ResultSet rst=pst.executeQuery();
  ```

> 解答： https://www.nowcoder.com/profile/934336/myFollowings/detail/3793532

+ C:创建Statement是不传参的，PreparedStatement是需要传入sql语句

链接：https://www.nowcoder.com/questionTerminal/7457f88577b64e99814772bb9ac9aff2
来源：牛客网

说一下preparedStatement和statement的区别与联系：在JDBC应用中,如果你已经是稍有水平开发者,你就应该始终以PreparedStatement代替Statement.也就是说,在任何时候都不要使用Statement。   
PreparedStatement 接口继承 Statement, PreparedStatement 实例包含已编译的 SQL 语句， 所以其执行速度要快于 Statement 对象。 Statement为一条Sql语句生成执行计划， 如果要执行两条sql语句
`select colume from table where colume=1;select colume from table where colume=2;` 会生成两个执行计划 一千个查询就生成一千个执行计划！ 
PreparedStatement用于使用绑定变量重用执行计划 select colume from table where colume=:x; 通过set不同数据只需要生成一次执行计划，可以重用

## 五、问答题