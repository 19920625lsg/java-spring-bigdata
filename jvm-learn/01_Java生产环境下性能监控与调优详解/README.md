# [Java生产环境下性能监控与调优详解](https://coding.imooc.com/learn/list/241.html)

+ 本课程将为你讲解如何在生产环境下对Java应用做性能监控与调优
+ 通过本课程，你将掌握多种性能监控工具应用，学会定位并解决诸如内存溢出、cpu负载飙高等问题
+ 学会线上代码调试，Tomcat、Nginx，GC调优等手段
+ 读懂JVM字节码指令，分析源码背后原理，提升应对线上突发状况的能力

整个课程有个人整理成博客了

+ [基于JDK命令行工具的监控](https://blog.51cto.com/zero01/2138982)
+ [基于JVisualVM的可视化监控](https://blog.51cto.com/zero01/2141942)
+ [基于Btrace的监控调试](https://blog.51cto.com/zero01/2143096)
+ [Tomcat性能监控与调优](https://blog.51cto.com/zero01/2145077)
+ [Nginx性能监控与调优](https://blog.51cto.com/zero01/2147459)
+ [JVM层GC调优（上）](https://blog.51cto.com/zero01/2150115)
+ [JVM层GC调优（下）](https://blog.51cto.com/zero01/2150696)
+ [Java底层：GC相关](https://blog.51cto.com/zero01/2421570)
+ [JVM字节码与Java代码层调优](https://blog.51cto.com/zero01/2151262)

## [课程目录](https://coding.imooc.com/learn/list/241.html)

+ 第1章 课程介绍（提供问答区答疑解惑）
  + 1-1 课前必读（不看会错过一个亿）
  + 1-2 为什么学习这门课程？  (12:46)

+ 第2章 基于JDK命令行工具的监控
  + 2-1 JVM的参数类型  (07:55)
  + 2-2 查看JVM运行时参数  (06:44)
  + 2-3 jstat查看JVM统计信息  (11:56)
  + 2-4 演示内存溢出  (12:09)
  + 2-5 导出内存映像文件  (06:02)
  + 2-6 MAT分析内存溢出  (05:54)
  + 2-7 jstack与线程的状态  (06:21)
  + 2-8 jstack实战死循环与死锁  (12:57)

+ 第3章 基于JVisualVM的可视化监控
  + 3-1 监控本地java进程  (10:59)
  + 3-2 监控远程的java进程  (08:15)

+ 第4章 基于Btrace的监控调试
  + 4-1 btrace入门  (12:57)
  + 4-2 拦截构造函数、同名函数  (08:35)
  + 4-3 拦截返回值、异常、行号  (12:40)
  + 4-4 拦截复杂参数、坏境变量、正则匹配拦截  (07:28)
  + 4-5 注意事项  (01:30)

+ 第5章 Tomcat性能监控与调优
  + 5-1 tomcat远程debug  (11:31)
  + 5-2 tomcat-manager监控  (07:20)
  + 5-3 psi-probe监控  (09:22)
  + 5-4 tomcat优化  (11:20)

+ 第6章 Nginx性能监控与调优
  + 6-1 nginx安装  (11:04)
  + 6-2 ngx_http_stub_status监控连接信息  (05:15)
  + 6-3 ngxtop监控请求信息  (05:08)
  + 6-4 nginx-rrd图形化监控  (14:15)
  + 6-5 nginx优化  (12:42)

+ 第7章 JVM层GC调优
  + 7-1 JVM的内存结构  (16:46)
  + 7-2 常见的垃圾回收算法  (08:55)
  + 7-3 垃圾收集器-1  (12:31)
  + 7-4 垃圾收集器-2  (21:10)
  + 7-5 GC日志格式详解  (20:17)
  + 7-6 可视化工具分析GC日志  (12:31)
  + 7-7 ParallelGC调优  (14:12)
  + 7-8 G1调优  (11:16)
  + 7-9 本章小结  (03:26)

+ 第8章 JVM字节码与Java代码层调优
  + 8-1 jvm字节码指令-1  (11:47)
  + 8-2 jvm字节码指令-2  (08:46)
  + 8-3 i++与++i  (04:16)
  + 8-4 字符串+拼接  (04:51)
  + 8-5 Try-Finally字节码  (03:21)
  + 8-6 String Constant Variable  (11:33)
  + 8-7 常用代码优化方法-1  (09:46)
  + 8-8 常用代码优化方法-2  (13:43)
