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

### 2.下面有关jdbc statement的说法错误的是？(`C`)
+ A.JDBC提供了Statement、PreparedStatement 和 CallableStatement三种方式来执行查询语句，其中 Statement 用于通用查询， PreparedStatement 用于执行参数化查询，而 CallableStatement则是用于存储过程
+ B.对于PreparedStatement来说，数据库可以使用已经编译过及定义好的执行计划，由于 PreparedStatement 对象已预编译过，所以其执行速度要快于 Statement 对象”
+ C.PreparedStatement中，“?” 叫做占位符，一个占位符可以有一个或者多个值
+ D.PreparedStatement可以阻止常见的SQL注入式攻击

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/3507630

DBC statement中的PReparedStatement的占位符对应着即将与之对应当值，并且一个占位符只能对应一个值，如果能对应多个就会引起混淆。sql语句是确定的，那么一个占位符必定只能对应一个值
扩展：
+ 1.Statement、PreparedStatement和CallableStatement都是接口(interface)。 
+ 2.Statement继承自Wrapper、PreparedStatement继承自Statement、CallableStatement继承自PreparedStatement。 
+ 3. 各个Statement的特点
  + Statement接口提供了执行语句和获取结果的基本方法； 
  + PreparedStatement接口添加了处理 IN 参数的方法； 
  + CallableStatement接口添加了处理 OUT 参数的方法。 
4. 各个Statement的优点
  + Statement: 普通的不带参的查询SQL；支持批量更新,批量删除; 
  + PreparedStatement: 可变参数的SQL,编译一次,执行多次,效率高; 安全性好，有效防止Sql注入等问题; 支持批量更新,批量删除; 
  + CallableStatement: 继承自PreparedStatement,支持带参数的SQL操作; 支持调用存储过程,提供了对输出和输入/输出参数(INOUT)的支持; 

Statement每次执行sql语句，数据库都要执行sql语句的编译 ， 最好用于仅执行一次查询并返回结果的情形，效率高于PreparedStatement。 

PreparedStatement是预编译的，使用PreparedStatement有几个好处 
+ 1.在执行可变参数的一条SQL时，PreparedStatement比Statement的效率高，因为DBMS预编译一条SQL当然会比多次编译一条SQL的效率要高。 
+ 2.安全性好，有效防止Sql注入等问题。 
+ 3.对于多次重复执行的语句，使用PreparedStament效率会更高一点，并且在这种情况下也比较适合使用batch； 
+ 4.代码的可读性和可维护性


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