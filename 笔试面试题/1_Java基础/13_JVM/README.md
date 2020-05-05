# 13_JVM

## 一、填空题

## 二、判断题

## 三、单选题
### 1.JVM内存不包含如下哪个部分(`D`)
+ A.Stacks
+ B.PC寄存器
+ C.Heap
+ D.Heap Frame

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12779837

JVM内存五大区域：
![JVM内存五大区域](images/JVM内存五大区域.png)

### 2.下面关于垃圾收集的描述哪个是错误的？（`D`）
+ A.使用垃圾收集的程序不需要明确释放对象
+ B.现代垃圾收集能够处理循环引用问题
+ C.垃圾收集能提高程序员效率
+ D.使用垃圾收集的语言没有内在泄漏问题

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12779699

+ A、GC机制可以让开发人员必须要明确何时去释放什么样的对象。虽然有System.gc()，提醒JVM进行一个Full GC，但是到底什么时候执行，都不是人为能控制得。
+ B、GC最常见得问题就是循环引用得问题，对于最常见得引用计数法而言，就没有方法处理这个问题。但是JVM使用得是可达性分析，所以可以处理。
  + 1.引用计数法，给每一个对象上都放一个引用计数器，有引用得时候就+1，失去引用去减1，但是这个样子还是没有办法处理循环引用得问题，所谓得循环引用就是一个类中的属性有其他引用类型，假设一个这个类的某个实例中的某个引用类型的属性指向了某个对象，就算你把这个实例置为null，也还是有引用的。
  + 2.所谓的可达性分析，就是使用一系列可以作为GC Roots的对象作为起点，从这些节点往下搜索，搜索所走过的路径称为引用链，当一个对象到GC Roots没有任何引用链的时候，就证明这个对象不可用。也就是不可达，所以被判定为可回收。
  
  可以作为GC Roots 的对象有：虚拟机栈中引用的对象，方法区的静态属性引用的对象，常量引用的对象和本地方法栈中JNI（Java Native Interface）引用的对象。
再详细的，给一个我博客的链接，可以看看 http://bbblemon.top/2018/04/23/%E6%B7%B1%E5%85%A5%E4%BA%86%E8%A7%A3JVM/
+ C、没毛病吧
+ D、OOM和内存泄露都还是有得,访问资源文件，流不关闭，访问数据库等连接不关闭也是
  + 1. OOM，内存溢出，是说原来得内存不够用了
  + 2. 内存泄漏，说得一个对象没用了，但是不能被回收

### 3.关于java编译和运行命令叙述不正确的是？  (`A`)
+ A.运行“java Scut.class”
+ B.运行“java Scut”
+ C.运行“javac Scut.java”的输出文件是Scut.class
+ D.java这个命令的运行对象是Scut.class

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12779605

+ A  错误 运行命令是 java + 你的 Java 程序的名字但是不加后缀 所以这道题错在多了 .class这个后缀
+ B  正确
+ C  javac 是编译命令，后跟 你的 Java 程序名字加后缀，也就是 YourClassName.java 所以答案正确
+ D JVM （Java 虚拟机）运行的是编译后的字节码文件（以.class为后缀的文件），也就是 YourClassName.class 所以答案正确
个人的一点看法，有问题还请指出，谢谢

## 四、多选题
### 1.关于运行时常量池，下列哪个说法是正确的？(`BCD`)
+ 运行时常量池大小受栈区大小的影响
+ 运行时常量池大小受方法区大小的影响
+ 存放了编译时期生成的各种字面量
+ 存放编译时期生成的符号引用

> 解读：https://www.nowcoder.com/profile/934336/myFollowings/detail/12779917

运行时常量池（Runtime Constant Pool）是方法区的一部分。Class文件中除了有类的版本、字段、方法、接口等描述信息外，还有一项信息是常量池（Constant Pool Table）,也就是说，每个class都有一个运行时常量池，用于存放编译器生成的各种字面量和符号引用，这部分内容将在类加载后进入方法区的运行时常量池中存放

### 2.判断一块内存空间是否符合垃圾收集器收集的标准有哪些？（`ABD`）
+ A.给对象赋予了空值null,以下再没有调用过
+ B.对象重新分配了内存空间
+ C.给对象赋予了空值null
+ D.给对象赋予了新值

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12741527

在java语言中，判断一块内存空间是否符合垃圾收集器收集标准的标准只有两个：
+ 1.给对象赋值为null，以下没有调用过。
+ 2.给对象赋了新的值，重新分配了内存空间

### 3.jvm中垃圾回收分为scanvenge gc和full GC，其中full GC触发的条件可能有哪些（`CDE`）
+ A.栈空间满
+ B.年轻代空间满
+ C.老年代满
+ D.持久代满
+ E.System.gc()

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/5366782

![垃圾回收的过程](images/垃圾回收的过程.png)

+ 1.新生代：
  + （1）所有对象创建在新生代的Eden区，当Eden区满后触发新生代的Minor GC，将Eden区和非空闲Survivor区存活的对象复制到另外一个空闲的Survivor区中。
  + （2）保证一个Survivor区是空的，新生代Minor GC就是在两个Survivor区之间相互复制存活对象，直到Survivor区满为止。
+ 2.老年代：
    当Survivor区也满了之后就通过Minor GC将对象复制到老年代。老年代也满了的话，就将触发Full GC，针对整个堆（包括新生代、老年代、持久代）进行垃圾回收。
+ 3.持久代：
    持久代如果满了，将触发Full GC



## 五、问答题

