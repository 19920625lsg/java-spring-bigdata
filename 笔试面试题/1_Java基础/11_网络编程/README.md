# 11_网络编程

## 一、填空题

## 二、判断题

## 三、单选题
### 1.在socket编程中，可以使用方法（`B`）获取本机的ip地址
+ getInetAddress()
+ getLocalAddress()
+ getReuseAddress()
+ getLocalPort()

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12777869

+ getInetAddress获取主机名和IP地址
+ getLocalAddress()顾名思义，获取本地IP地址
+ getReuseAddress()返回布尔类型，表示复用地址
+ getLocalPort()API 的说明：“Returns the Internet Protocol (IP) port number of the interface on which the request was received. ”

### 2.关于 Socket 通信编程，以下描述正确的是：（`C`）
+ A.客户端通过new ServerSocket()创建TCP连接对象
+ B.客户端通过TCP连接对象调用accept()方法创建通信的Socket对象
+ C.客户端通过new Socket()方法创建通信的Socket对象
+ D.服务器端通过new ServerSocket()创建通信的Socket对象

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/3524001

+ 客户端通过new Socket()方法创建通信的Socket对象
+ 服务器端通过new ServerSocket()创建TCP连接对象  accept接纳客户端请求

## 四、多选题

## 五、问答题