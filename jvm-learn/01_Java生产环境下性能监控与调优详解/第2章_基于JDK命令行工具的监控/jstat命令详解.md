# jstat命令详解

> 对Java应用程序的资源和性能进行实时的命令行的监控，包括了对Heap size和垃圾回收状况的监控

参考博文

+ [JVM内存模型，基础的基础，重中之重](https://www.jianshu.com/p/5946c0a414b5)
+ [jvm 性能调优工具之 jstat](https://www.jianshu.com/p/213710fb9e40)
+ [jstat官方文档](https://docs.oracle.com/javase/8/docs/technotes/tools/unix/jstat.html)

依次讲解如下的option

+ 1.类加载
  + `-class`: 显示ClassLoad的相关信息
+ 2.GC垃圾回收
  + `-gc`: 显示和gc相关的堆信息
  + `-gccapacity`: 显示各个代的容量以及使用情况
  + `-gccause`: 显示垃圾回收的相关信息（通-gcutil）,同时显示最后一次或当前正在发生的垃圾回收的诱因
  + `-gcmetacapacity`: 显示metaspace的大小
  + `-gcnew`: 显示新生代信息
  + `-gcnewcapacity`: 显示新生代大小和使用情况
  + `-gcold`: 显示老年代和永久代的信息
  + `-gcoldcapacity`: 显示老年代的大小
  + `-gcutil`: 显示垃圾收集信息
+ 3.JIT编译信息
  + `-compiler`: 显示JIT编译的相关信息
  + `-printcompilation`: 输出JIT编译的方法信息


先看下这个博客，JVM中堆和栈的区别：https://www.cnblogs.com/Jashinck/p/10480776.html

每个命令回显的字段含义参考下图进行理解：
![JVM内存结构](https://i.loli.net/2020/04/09/vweqB1PKI8WSifr.jpg)

## 1、类加载

### 1.1 `-class`

> 显示加载class的数量，以及所占空间等信息

使用格式如下：

```shell
jstat -class <pid> # pid可由jps命令查到
```

使用范例如下：

```shell
➜  /Users/liangshanguang/Program/Java/jvm-learn git:(master) ✗   jps
33755 Launcher
33693
39119 Jps
➜  /Users/liangshanguang/Program/Java/jvm-learn git:(master) ✗   jstat -class 33693
Loaded  Bytes    Unloaded  Bytes       Time
 44635 91052.7      225    306.3      77.50
```

回显的字段阐述如下：

+ `Loaded` : 已经装载的类的数量
+ `Bytes` : 装载类所占用的字节数
+ `Unloaded`：已经卸载类的数量
+ `Bytes`：卸载类的字节数
+ `Time`：装载和卸载类所花费的时间

## 2、GC垃圾回收

### 2.1 `-gc`

> 显示gc相关的堆信息，查看gc的次数，及时间

```shell
jstat –gc <pid>
```

返回如下：

```shell
/Users/liangshanguang/Program/Java/jvm-learn git:(master) ✗   jstat -gc 33693
 S0C      S1C    S0U    S1U     EC       EU        OC         OU        MC      MU        CCSC   CCSU      YGC     YGCT    FGC    FGCT     GCT
15616.0 15616.0  0.0   669.9  125312.0 28129.7   312868.0   153762.6  256300.0 239084.2 36500.0 32303.5    109    1.951     23   4.502    6.453
```

字段详解如下：

+ S0C ：`S0 Capacity`, 年轻代中第一个survivor（幸存区）的容量 （字节）
+ S1C ：`S1 Capacity`, 年轻代中第二个survivor（幸存区）的容量 (字节)
+ S0U ：`S0 Used`, 年轻代中第一个survivor（幸存区）目前已使用空间 (字节)
+ S1U ：`S1 Used`, 年轻代中第二个survivor（幸存区）目前已使用空间 (字节)
+ EC  ：`Eden Capacity`, 年轻代中Eden（伊甸园）的容量 (字节)
+ EU  ：`Eden Used`, 年轻代中Eden（伊甸园）目前已使用空间 (字节)
+ OC  ：`Old Capacity`, Old代的容量 (字节)
+ OU  ：`Old Used`, Old代目前已使用空间 (字节)
+ MC  ：`Metaspace Capacity`, metaspace(元空间)的容量 (字节)
+ MU  ：`Metaspace Used`, metaspace(元空间)目前已使用空间 (字节)
+ YGC ：`Young GC`, 从应用程序启动到采样时`年轻代(Young)`中gc次数
+ YGCT：`Young GC Time`, 从应用程序启动到采样时年轻代中gc所用时间(s)
+ FGC ：`Full GC`, 从应用程序启动到采样时old代(全gc)gc次数
+ FGCT：`Full GC Time`, 从应用程序启动到采样时old代(全gc)gc所用时间(s)
+ GCT ：`GC Time`, 从应用程序启动到采样时gc用的总时间(s)

### 2.2 `-gccapacity`

> 可以显示，VM内存中三代（young,old,perm）对象的使用和占用大小

格式如下：

```shell
jstat -gccapacity <pid>
```

执行范例如下：

```shell
➜  /Users/liangshanguang/Program/Java/jvm-learn git:(master) ✗   jstat -gccapacity 33693
 NGCMN    NGCMX     NGC     S0C      S1C     EC        OGCMN      OGCMX       OGC        OC         MCMN     MCMX      MC     CCSMN    CCSMX     CCSC    YGC    FGC 
 43648.0 340736.0 156544.0 15616.0 15616.0 125312.0    87424.0  1756416.0   312868.0   312868.0      0.0  1269760.0 256300.0   0.0   1048576.0  36500.0  109     23
```

+ NGCMN ：`New Generation Capacity MiN`，年轻代(young)中初始化(最小)的大小(字节)
+ NGCMX ：`New Generation Capacity NaX`，年轻代(young)的最大容量 (字节)
+ NGC ：`New Generation Capacity`，年轻代(young)中当前的容量 (字节)
+ S0C ：`S0 Capacity`，年轻代中第一个survivor（幸存区）的容量 (字节)
+ S1C ：`S1 Capacity`，年轻代中第二个survivor（幸存区）的容量 (字节)
+ EC ：`Eden Capacity`，年轻代中Eden（伊甸园）的容量 (字节)
+ OGCMN ：`Old Generation Capacity MiN`，old代中初始化(最小)的大小 (字节)
+ OGCMX ：`Old Generation Capacity MaX`，old代的最大容量(字节)
+ OGC：`Old Generation Capacity`，old代当前新生成的容量 (字节)
+ OC ：`Old Capacity`，Old代的容量 (字节)
+ MCMN：`Metaspace Capacity MiN`，metaspace(元空间)中初始化(最小)的大小 (字节)
+ MCMX ：`Metaspace Max`，metaspace(元空间)的最大容量 (字节)
+ MC ：`Metaspace Capacity`，metaspace(元空间)当前新生成的容量 (字节)
+ CCSMN ：`Compress Class Space MiN`，最小压缩类空间大小
+ CCSMX ：`Compress Class Space MaX`，最大压缩类空间大小
+ CCSC ：`Compress Class Space`，当前压缩类空间大小
+ YGC ：`Young GC`，从应用程序启动到采样时年轻代中gc次数
+ FGC：`Full GC`，从应用程序启动到采样时old代(全gc)gc次数

### 2.3 `-gcmetacapacity`

```shell
➜  /Users/liangshanguang/Program/Java/jvm-learn git:(master) ✗   jstat -gcmetacapacity 33693
   MCMN       MCMX        MC       CCSMN      CCSMX       CCSC     YGC   FGC    FGCT     GCT   
   0.0      1269760.0   256300.0    0.0     1048576.0    36500.0   109   23    4.502    6.453
```

+ `MCMN`:`Metaspace Capacity MiN`，最小元数据容量
+ `MCMX`:`Metaspace Capacity MaX`，最大元数据容量
+ `MC`：`Metaspace Capacity`，当前元数据空间大小
+ `CCSMN`：`Compress Class Space MiN`，最小压缩类空间大小
+ `CCSMX`：`Compress Class Space MaX`，最大压缩类空间大小
+ `CCSC`：`Compress Class Space`，当前压缩类空间大小
+ `YGC`：`Young GC`，从应用程序启动到采样时年轻代中gc次数
+ `FGC`：`Full GC`，从应用程序启动到采样时old代(全gc)gc次数
+ `FGCT`：`Full GC Time`，从应用程序启动到采样时old代(全gc)gc所用时间(s)
+ `GCT`：`GC Time`，从应用程序启动到采样时gc用的总时间(s)

### 2.4 `-gcnew`

```shell
➜  /Users/liangshanguang/Program/Java/jvm-learn git:(master) ✗   jstat -gcnew 33693
 S0C    S1C       S0U    S1U   TT  MTT  DSS      EC       EU       YGC     YGCT  
15616.0 15616.0   0.0    669.9  6   6  7808.0  125312.0 110917.7   109     1.951
```

+ `S0C`：`S0 Capacity`，年轻代中第一个survivor（幸存区）的容量 (字节)
+ `S1C`：`S1 Capacity`，年轻代中第二个survivor（幸存区）的容量 (字节)
+ `S0U`：`S0 Used`，年轻代中第一个survivor（幸存区）目前已使用空间 (字节)
+ `S1U`：`S1 Used`，年轻代中第二个survivor（幸存区）目前已使用空间 (字节)
+ `TT`：`Tenuring Threshold`，持有次数限制
+ `MTT`：`Max Tenuring Threshold`，最大持有次数限制
+ `DSS`：`Desired Survivor Size`，期望的幸存区大小
+ `EC`：`Eden Capacity`，年轻代中Eden（伊甸园）的容量 (字节)
+ `EU`：`Eden Used`，年轻代中Eden（伊甸园）目前已使用空间 (字节)
+ `YGC`：`Young GC`，从应用程序启动到采样时年轻代中gc次数
+ `YGCT`：`Young GC Time`，从应用程序启动到采样时年轻代中gc所用时间(s)

### 2.5 `-gcnewcapacity`

```shell
➜  /Users/liangshanguang/Program/Java/jvm-learn git:(master) ✗   jstat -gcnewcapacity 33693
  NGCMN      NGCMX       NGC      S0CMX     S0C     S1CMX     S1C       ECMX        EC      YGC   FGC 
 43648.0   340736.0    156544.0  34048.0  15616.0  34048.0  15616.0   272640.0   125312.0   109    23
```

+ `NGCMN`：`New Generation Capacity MiN`，年轻代(young)中初始化(最小)的大小(字节)
+ `NGCMX`：`New Generation Capacity MaX`，年轻代(young)的最大容量 (字节)
+ `NGC`：`New Generation Capacity`，年轻代(young)中当前的容量 (字节)
+ `S0CMX`：`S0 Capacity MaX`，年轻代中第一个survivor（幸存区）的最大容量 (字节)
+ `S0C`：`S0 Capacity`, 年轻代中第一个survivor（幸存区）的容量 (字节)
+ `S1CMX`：`S1 Capacity MaX`，年轻代中第二个survivor（幸存区）的最大容量 (字节)
+ `S1C`：`S1 Capacity`, 年轻代中第二个survivor（幸存区）的容量 (字节)
+ `ECMX`：`Eden Capacity MaX`，年轻代中Eden（伊甸园）的最大容量 (字节)
+ `EC`：`Eden Capacity`，年轻代中Eden（伊甸园）的容量 (字节)
+ `YGC`：`Young GC`，从应用程序启动到采样时年轻代中gc次数
+ `FGC`：`Full GC`，从应用程序启动到采样时old代(全gc)gc次数

### 2.6 `-gcold`

```shell
➜  /Users/liangshanguang/Program/Java/jvm-learn git:(master) ✗   jstat -gcold 33693
   MC       MU      CCSC     CCSU       OC          OU         YGC    FGC    FGCT     GCT
256300.0 239084.2  36500.0  32303.5    312868.0    153762.6    109    23    4.502    6.453
```

+ `MC` ：`Metaspace Capacity`, metaspace(元空间)的容量 (字节)
+ `MU`：`Metaspace Used`, metaspace(元空间)目前已使用空间 (字节)
+ `CCSC`:`Compressed Class Space Capacity`, 压缩类空间大小
+ `CCSU`:`Compressed Class Space Used`, 压缩类空间使用大小
+ `OC`：`Old Capacity`，Old代的容量 (字节)
+ `OU`：`Old Used`，Old代目前已使用空间 (字节)
+ `YGC`：`Young GC`，从应用程序启动到采样时年轻代中gc次数
+ `FGC`：`Full GC`，从应用程序启动到采样时old代(全gc)gc次数
+ `FGCT`：`Full GC Time`，从应用程序启动到采样时old代(全gc)gc所用时间(s)
+ `GCT`：`GC Time`，从应用程序启动到采样时gc用的总时间(s)

### 2.7 `-gcoldcapacity`

```shell
➜  /Users/liangshanguang/Program/Java/jvm-learn git:(master) ✗   jstat -gcoldcapacity 33693
   OGCMN       OGCMX        OGC         OC       YGC    FGC    FGCT     GCT   
   87424.0   1756416.0    312868.0    312868.0   110    23    4.502    6.461
```

+ `OGCMN`：`Old Generation Capacity MiN`, old代中初始化(最小)的大小 (字节)
+ `OGCMX`：`Old Generation Capacity MaX`, old代的最大容量(字节)
+ `OGC`：`Old Generation Capacity`，old代当前新生成的容量 (字节)
+ `OC`：`Old Capacity`，Old代的容量 (字节)
+ `YGC`：`Young GC`，从应用程序启动到采样时年轻代中gc次数
+ `FGC`：`Full GC`, 从应用程序启动到采样时old代(全gc)gc次数
+ `FGCT`：`Full GC Time`, 从应用程序启动到采样时old代(全gc)gc所用时间(s)
+ `GCT`:`GC Time`, 从应用程序启动到采样时gc用的总时间(s)

### 2.8 `-gcutil`

```shell
➜  /Users/liangshanguang/Program/Java/jvm-learn git:(master) ✗   jstat -gcutil 33693
   S0     S1     E      O      M     CCS     YGC     YGCT    FGC    FGCT     GCT
  5.83   0.00   4.84  49.15  93.29  88.50    110    1.959    23    4.502    6.461
```

+ `S0`：`Survivor 0`, 年轻代中第一个survivor(幸存区)已使用的占当前容量百分比
+ `S1`：`Survivor 1`, 年轻代中第二个survivor(幸存区)已使用的占当前容量百分比
+ `E`：`Eden`, 年轻代中Eden（伊甸园）已使用的占当前容量百分比
+ `O`：`Old`, old代已使用的占当前容量百分比
+ `M`：`Metaspace`, perm代已使用的占当前容量百分比
+ `YGC`：`Young GC`, 从应用程序启动到采样时年轻代中gc次数
+ `YGCT`：`Young GC Time`, 从应用程序启动到采样时年轻代中gc所用时间(s)
+ `FGC`：`Full GC`, 从应用程序启动到采样时old代(全gc)gc次数
+ `FGCT`：`Full GC Time`, 从应用程序启动到采样时old代(全gc)gc所用时间(s)
+ `GCT`：`GC Time`, 从应用程序启动到采样时gc用的总时间(s)

### 2.9 `-gccause`

```shell
 /Users/liangshanguang/Program/Java/jvm-learn git:(master) ✗   jstat -gccause 33693
 S0      S1     E      O      M     CCS      YGC     YGCT    FGC    FGCT     GCT        LGCC            GCC
 5.83   0.00  10.06  49.15  93.29  88.50     110    1.959    23    4.502    6.461  Allocation Failure   No GC
```

+ `S0`：`Survivor 0`, 年轻代中第一个survivor(幸存区)已使用的占当前容量百分比
+ `S1`：`Survivor 1`, 年轻代中第二个survivor(幸存区)已使用的占当前容量百分比
+ `E`：`Eden`, 年轻代中Eden（伊甸园）已使用的占当前容量百分比
+ `O`：`Old`, old代已使用的占当前容量百分比
+ `M`：`Metaspace`, perm代已使用的占当前容量百分比
+ `CCS`: `Compressed Class Space`，压缩类空间
+ `YGC`：`Young GC`, 从应用程序启动到采样时年轻代中gc次数
+ `YGCT`：`Young GC Time`, 从应用程序启动到采样时年轻代中gc所用时间(s)
+ `FGC`：`Full GC`, 从应用程序启动到采样时old代(全gc)gc次数
+ `FGCT`：`Full GC Time`, 从应用程序启动到采样时old代(全gc)gc所用时间(s)
+ `GCT`：`GC Time`, 从应用程序启动到采样时gc用的总时间(s)
+ `LGCC`：`Last GC Cause`,最后一次GC原因
+ `GCC`：`GC Cause`, 当前GC原因（No GC 为当前没有执行GC）

## 3、JIT编译

### 3.1 `-compiler`

> 显示VM实时编译(JIT)的数量等信息

使用格式如下：

```shell
jstat -compiler <pid> # pid可由jps命令得到
```

使用范例如下：

```shell
➜  /Users/liangshanguang/Program/Java/jvm-learn git:(master) ✗   jstat -compiler 33693
Compiled Failed Invalid   Time       FailedType  FailedMethod
 21436     0       0      150.44          0
```

+ `Compiled`：编译任务执行数量
+ `Failed`：编译任务执行失败数量
+ `Invalid` ：编译任务执行失效数量
+ `Time` ：编译任务消耗时间
+ `FailedType`：最后一个编译失败任务的类型
+ `FailedMethod`：最后一个编译失败任务所在的类及方法

### 3.2 `-printcompilation`

```shell
➜  /Users/liangshanguang/Program/Java/jvm-learn git:(master) ✗   jstat -printcompilation 33693
Compiled  Size  Type Method
21507     94     1   java/nio/channels/spi/AbstractSelector begin
```

+ `Compiled`：编译任务的数目
+ `Size`：方法生成的字节码的大小
+ `Type`：编译类型
+ `Method`:类名和方法名用来标识编译的方法。类名使用/做为一个命名空间分隔符。方法名是给定类中的方法。上述格式是由`-XX:+PrintComplation`选项进行设置的
