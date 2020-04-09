# jstat命令详解

> 对Java应用程序的资源和性能进行实时的命令行的监控，包括了对Heap size和垃圾回收状况的监控

参考博文

+ [JVM内存模型，基础的基础，重中之重](https://www.jianshu.com/p/5946c0a414b5)
+ [jvm 性能调优工具之 jstat](https://www.jianshu.com/p/213710fb9e40)

依次讲解如下的option

+ 类加载
  + `-class`: 显示ClassLoad的相关信息
+ GC垃圾回收
  + `-gc`: 显示和gc相关的堆信息
  + `-gccapacity`: 显示各个代的容量以及使用情况
  + `-gccause`: 显示垃圾回收的相关信息（通-gcutil）,同时显示最后一次或当前正在发生的垃圾回收的诱因
  + `-gcmetacapacity`: 显示metaspace的大小
  + `-gcnew`: 显示新生代信息
  + `-gcnewcapacity`: 显示新生代大小和使用情况
  + `-gcold`: 显示老年代和永久代的信息
  + `-gcoldcapacity`: 显示老年代的大小
  + `-gcutil`: 显示垃圾收集信息
+ JIT编译信息
  + `-compiler`: 显示JIT编译的相关信息
  + `-printcompilation`: 输出JIT编译的方法信息

## 类加载

### `-class`

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

## GC垃圾回收

### `-gc`

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

### `-gccapacity`

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

+ NGCMN   ：年轻代(young)中初始化(最小)的大小(字节)
+ NGCMX    ：年轻代(young)的最大容量 (字节)
+ NGC    ：年轻代(young)中当前的容量 (字节)
+ S0C  ：年轻代中第一个survivor（幸存区）的容量 (字节)
+ S1C  ：    年轻代中第二个survivor（幸存区）的容量 (字节)
+ EC    ：年轻代中Eden（伊甸园）的容量 (字节)
+ OGCMN     ：old代中初始化(最小)的大小 (字节)
+ OGCMX      ：old代的最大容量(字节)
+ OGC：old代当前新生成的容量 (字节)
+ OC     ：Old代的容量 (字节)
+ MCMN：metaspace(元空间)中初始化(最小)的大小 (字节)
+ MCMX ：metaspace(元空间)的最大容量 (字节)
+ MC ：metaspace(元空间)当前新生成的容量 (字节)
+ CCSMN：最小压缩类空间大小
+ CCSMX：最大压缩类空间大小
+ CCSC：当前压缩类空间大小
+ YGC   ：从应用程序启动到采样时年轻代中gc次数
+ FGC：从应用程序启动到采样时old代(全gc)gc次数

### `-gcmetacapacity`

```shell
➜  /Users/liangshanguang/Program/Java/jvm-learn git:(master) ✗   jstat -gcmetacapacity 33693
   MCMN       MCMX        MC       CCSMN      CCSMX       CCSC     YGC   FGC    FGCT     GCT   
   0.0      1269760.0   256300.0    0.0     1048576.0    36500.0   109   23    4.502    6.453
```

+ `MCMN`:最小元数据容量
+ `MCMX`：最大元数据容量
+ `MC`：当前元数据空间大小
+ `CCSMN`：最小压缩类空间大小
+ `CCSMX`：最大压缩类空间大小
+ `CCSC`：当前压缩类空间大小
+ `YGC`：从应用程序启动到采样时年轻代中gc次数
+ `FGC`：从应用程序启动到采样时old代(全gc)gc次数
+ `FGCT`：从应用程序启动到采样时old代(全gc)gc所用时间(s)
+ `GCT`：从应用程序启动到采样时gc用的总时间(s)

### `-gcnew`

```shell
➜  /Users/liangshanguang/Program/Java/jvm-learn git:(master) ✗   jstat -gcnew 33693
 S0C    S1C       S0U    S1U   TT  MTT  DSS      EC       EU       YGC     YGCT  
15616.0 15616.0   0.0    669.9  6   6  7808.0  125312.0 110917.7   109     1.951
```

+ `S0C`：年轻代中第一个survivor（幸存区）的容量 (字节)
+ `S1C`：年轻代中第二个survivor（幸存区）的容量 (字节)
+ `S0U`：年轻代中第一个survivor（幸存区）目前已使用空间 (字节)
+ `S1U`：年轻代中第二个survivor（幸存区）目前已使用空间 (字节)
+ `TT`：持有次数限制
+ `MTT`：最大持有次数限制
+ `DSS`：期望的幸存区大小
+ `EC`：年轻代中Eden（伊甸园）的容量 (字节)
+ `EU`：年轻代中Eden（伊甸园）目前已使用空间 (字节)
+ `YGC`：从应用程序启动到采样时年轻代中gc次数
+ `YGCT`：从应用程序启动到采样时年轻代中gc所用时间(s)

### `-gcnewcapacity`

```shell
➜  /Users/liangshanguang/Program/Java/jvm-learn git:(master) ✗   jstat -gcnewcapacity 33693
  NGCMN      NGCMX       NGC      S0CMX     S0C     S1CMX     S1C       ECMX        EC      YGC   FGC 
 43648.0   340736.0    156544.0  34048.0  15616.0  34048.0  15616.0   272640.0   125312.0   109    23
```

+ `NGCMN`：年轻代(young)中初始化(最小)的大小(字节)
+ `NGCMX`：年轻代(young)的最大容量 (字节)
+ `NGC`：年轻代(young)中当前的容量 (字节)
+ `S0CMX`：年轻代中第一个survivor（幸存区）的最大容量 (字节)
+ `S0C`：年轻代中第一个survivor（幸存区）的容量 (字节)
+ `S1CMX`：年轻代中第二个survivor（幸存区）的最大容量 (字节)
+ `S1C`：年轻代中第二个survivor（幸存区）的容量 (字节)
+ `ECMX`：年轻代中Eden（伊甸园）的最大容量 (字节)
+ `EC`：年轻代中Eden（伊甸园）的容量 (字节)
+ `YGC`：从应用程序启动到采样时年轻代中gc次数
+ `FGC`：从应用程序启动到采样时old代(全gc)gc次数

### `-gcold`

```shell
➜  /Users/liangshanguang/Program/Java/jvm-learn git:(master) ✗   jstat -gcold 33693
   MC       MU      CCSC     CCSU       OC          OU         YGC    FGC    FGCT     GCT
256300.0 239084.2  36500.0  32303.5    312868.0    153762.6    109    23    4.502    6.453
```

+ `MC` ：metaspace(元空间)的容量 (字节)
+ `MU`：metaspace(元空间)目前已使用空间 (字节)
+ `CCSC`:压缩类空间大小
+ `CCSU`:压缩类空间使用大小
+ `OC`：Old代的容量 (字节)
+ `OU`：Old代目前已使用空间 (字节)
+ `YGC`：从应用程序启动到采样时年轻代中gc次数
+ `FGC`：从应用程序启动到采样时old代(全gc)gc次数
+ `FGCT`：从应用程序启动到采样时old代(全gc)gc所用时间(s)
+ `GCT`：从应用程序启动到采样时gc用的总时间(s)

### `-gcoldcapacity`

```shell
➜  /Users/liangshanguang/Program/Java/jvm-learn git:(master) ✗   jstat -gcoldcapacity 33693
   OGCMN       OGCMX        OGC         OC       YGC    FGC    FGCT     GCT   
   87424.0   1756416.0    312868.0    312868.0   110    23    4.502    6.461
```

+ `OGCMN`：old代中初始化(最小)的大小 (字节)
+ `OGCMX`：old代的最大容量(字节)
+ `OGC`：old代当前新生成的容量 (字节)
+ `OC`：Old代的容量 (字节)
+ `YGC`：从应用程序启动到采样时年轻代中gc次数
+ `FGC`：从应用程序启动到采样时old代(全gc)gc次数
+ `FGCT`：从应用程序启动到采样时old代(全gc)gc所用时间(s)
+ `GCT`:从应用程序启动到采样时gc用的总时间(s)

### `-gcutil`

```shell
➜  /Users/liangshanguang/Program/Java/jvm-learn git:(master) ✗   jstat -gcutil 33693
   S0     S1     E      O      M     CCS     YGC     YGCT    FGC    FGCT     GCT
  5.83   0.00   4.84  49.15  93.29  88.50    110    1.959    23    4.502    6.461
```

+ `S0`：年轻代中第一个survivor(幸存区)已使用的占当前容量百分比
+ `S1`：年轻代中第二个survivor(幸存区)已使用的占当前容量百分比
+ `E`：年轻代中Eden（伊甸园）已使用的占当前容量百分比
+ `O`：old代已使用的占当前容量百分比
+ `M`：perm代已使用的占当前容量百分比
+ `YGC`：从应用程序启动到采样时年轻代中gc次数
+ `YGCT`：从应用程序启动到采样时年轻代中gc所用时间(s)
+ `FGC`：从应用程序启动到采样时old代(全gc)gc次数
+ `FGCT`：从应用程序启动到采样时old代(全gc)gc所用时间(s)
+ `GCT`：从应用程序启动到采样时gc用的总时间(s)

### `-gccause`

```shell
 /Users/liangshanguang/Program/Java/jvm-learn git:(master) ✗   jstat -gccause 33693
 S0     S1     E      O      M     CCS      YGC     YGCT    FGC    FGCT     GCT    LGCC                 GCC
 5.83   0.00  10.06  49.15  93.29  88.50    110    1.959    23    4.502    6.461  Allocation Failure   No GC
```

+ `LGCC`：最后一次GC原因
+ `GCC`：当前GC原因（No GC 为当前没有执行GC）

## JIT编译

### `-compiler`

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

### `-printcompilation`

```shell
➜  /Users/liangshanguang/Program/Java/jvm-learn git:(master) ✗   jstat -printcompilation 33693
Compiled  Size  Type Method
21507     94     1   java/nio/channels/spi/AbstractSelector begin
```

+ `Compiled`：编译任务的数目
+ `Size`：方法生成的字节码的大小
+ `Type`：编译类型
+ `Method`:类名和方法名用来标识编译的方法。类名使用/做为一个命名空间分隔符。方法名是给定类中的方法。上述格式是由`-XX:+PrintComplation`选项进行设置的
