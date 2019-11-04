# 第8章 JVM字节码与Java代码层调优

## 8.1~8.2 jvm字节码指令

### javap的选项参数详解

```shell
javap
用法: javap <options> <classes>
其中, 可能的选项包括:
  -help  --help  -?        输出此用法消息
  -version                 版本信息
  -v  -verbose             输出附加信息
  -l                       输出行号和本地变量表
  -public                  仅显示公共类和成员
  -protected               显示受保护的/公共类和成员
  -package                 显示程序包/受保护的/公共类
                           和成员 (默认)
  -p  -private             显示所有类和成员
  -c                       对代码进行反汇编
  -s                       输出内部类型签名
  -sysinfo                 显示正在处理的类的
                           系统信息 (路径, 大小, 日期, MD5 散列)
  -constants               显示最终常量
  -classpath <path>        指定查找用户类文件的位置
  -cp <path>               指定查找用户类文件的位置
  -bootclasspath <path>    覆盖引导类文件的位置
```

### 创建测试类Test1.java

```java
package classesview;

public class Test1 {
    public static void main(String[] args) {
        int a = 2, b = 3;
        int c = a + b;
        System.out.println(c);
    }
}
```

### 查看和分析字节码文件

通过`maven compile`把源码进行编译，得到Test1.class文件，在.classs文件所在目录执行 `javap -verbose Test1.class > Test1.txt`,得Test1.txt的字节码文件，内容如下：

```java
Classfile /D:/Java_Work/classesview/out/production/classesview/classesview/Test1.class // class文件的路径
  Last modified 2018年7月27日; size 573 bytes  // 最后一次修改时间以及该class文件的大小
  MD5 checksum 6ccc47493e2c660409ad2f057996f117  // 该类的MD5值
  Compiled from "Test1.java"  // 源码文件名
public class classesview.Test1  // 包名及类名
  minor version: 0  // 版本号
  major version: 52 // 版本号
  flags: (0x0021) ACC_PUBLIC, ACC_SUPER  // 该类的权限修饰符
  this_class: #4                          // classesview/Test1  // 当前类，即this的指向
  super_class: #5                         // java/lang/Object   // 父类，即super的指向
  interfaces: 0, fields: 0, methods: 2, attributes: 1 // 接口数量、字段数量、方法数量、属性数量
Constant pool:  // 常量池，这些有顺序的数字相当于是常量池里的一个索引
   #1 = Methodref          #5.#23         // java/lang/Object."<init>":()V  // 方法引用（符号引用）
   #2 = Fieldref           #24.#25        // java/lang/System.out:Ljava/io/PrintStream; // 字段引用
   #3 = Methodref          #26.#27        // java/io/PrintStream.println:(I)V
   #4 = Class              #28            // classesview/Test1  // 类引用
   #5 = Class              #29            // java/lang/Object
   #6 = Utf8               <init>  // 这是字节码中构造函数的名称，而名称就是一段字符串，所以就会按照编码标识
   #7 = Utf8               ()V
   #8 = Utf8               Code
   #9 = Utf8               LineNumberTable
  #10 = Utf8               LocalVariableTable
  #11 = Utf8               this
  #12 = Utf8               Lclassesview/Test1;
  #13 = Utf8               main
  #14 = Utf8               ([Ljava/lang/String;)V
  #15 = Utf8               args
  #16 = Utf8               [Ljava/lang/String;
  #17 = Utf8               a
  #18 = Utf8               I
  #19 = Utf8               b
  #20 = Utf8               c
  #21 = Utf8               SourceFile
  #22 = Utf8               Test1.java
  #23 = NameAndType        #6:#7          // "<init>":()V  // 返回值
  #24 = Class              #30            // java/lang/System
  #25 = NameAndType        #31:#32        // out:Ljava/io/PrintStream;
  #26 = Class              #33            // java/io/PrintStream
  #27 = NameAndType        #34:#35        // println:(I)V
  #28 = Utf8               classesview/Test1
  #29 = Utf8               java/lang/Object
  #30 = Utf8               java/lang/System
  #31 = Utf8               out
  #32 = Utf8               Ljava/io/PrintStream;
  #33 = Utf8               java/io/PrintStream
  #34 = Utf8               println
  #35 = Utf8               (I)V
{
  public classesview.Test1();  // 构造函数
    descriptor: ()V  // 方法描述符，这里的V表示void
    flags: (0x0001) ACC_PUBLIC  // 权限修饰符
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 3: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   Lclassesview/Test1;

  public static void main(java.lang.String[]);  // main方法
    descriptor: ([Ljava/lang/String;)V  // 方法描述符，[表示引用了一个数组类型，L则表示引用的类后面跟的就是类名
    flags: (0x0009) ACC_PUBLIC, ACC_STATIC  // 权限修饰符
    Code:
      // 操作数栈的深度2，当调用一个方法的时候，实际上在JVM里对应的是一个栈帧入栈出栈的过程
      // 本地变量表最大长度（slot为单位），64位的是2，其他是1，索引从0开始，如果是非static方法索引0代表this，后面是入参，后面是本地变量
      // 1个参数，实例方法多一个this参数
      stack=2, locals=4, args_size=1
         0: iconst_2              // 常量2压栈
         1: istore_1              // 出栈保存到本地变量1里面 
         2: iconst_3              // 常量3压栈
         3: istore_2              // 出栈保存到本地变量2里面 
         4: iload_1               // 局部变量1压栈 
         5: iload_2               // 局部变量2压栈
         6: iadd                  // 栈顶两个元素相加，计算结果压栈
         7: istore_3              // 出栈保存到局部变量3里面
         8: getstatic     #2      // 这里对应的是常量池里的#2
        11: iload_3               // 局部变量3压栈
        12: invokevirtual #3      // 这里对应的是常量池里的#3
        15: return                // return void
      LineNumberTable:  // 行号表
        line 5: 0  // 源代码的第5行，0代表字节码里的0，也就是上面的常量2压栈那一行
        line 6: 2  // 源代码的第6行，2代表字节码里的2，也就是上面的常量3压栈那一行
        line 7: 4  // 以此类推...
        line 8: 8
        line 9: 15
      LocalVariableTable:  // 本地变量表
        Start  Length  Slot  Name   Signature
            0      16     0  args   [Ljava/lang/String; // 索引为0，变量名称为args
            2      14     1     a   I                   // 索引为2，变量名称为a
            4      12     2     b   I                   // 索引为4，变量名称为b
            8       8     3     c   I                   // 索引为5，变量名称为c
}
SourceFile: "Test1.java"  // 源码文件名
```

字节码里的指令与源代码的一个对应关系：

![字节码里的指令与源代码的一个对应关系](https://s1.51cto.com/images/blog/201807/27/72526930faf8061039fae7b8a6eb47c2.png)

从以上的字节码中，可以看到和Java的源代码是不太一样的，字节码里面还会用描述符来描述字段和方法，描述符有时候也被称之为签名（Signature），字段描述符与源代码里的字段：

![字节码里的描述符](https://s1.51cto.com/images/blog/201807/27/7e3119d8309a05875addfaff14361454.png)

方法描述符与源代码里的方法：

![字节码里的描述符2](https://s1.51cto.com/images/blog/201807/27/cfac75d61b01fdd73aac5c3078b02c6a.png)

**`JVM在执行字节码指令的时候，是基于栈的架构`** ，与OS中基于寄存器的架构不太一样。基于栈的好处就是指令比较短，但是指令集就会比较长了。以下用了几张图片来描述执行以上main方法里的字节码指令时，操作数栈里的一个出栈入栈的过程：

![jvm_stack1](https://s1.51cto.com/images/blog/201807/27/e1abea85b50f0534675bc20d8350f5d8.png)

![jvm_stack2](https://s1.51cto.com/images/blog/201807/27/2a38ba0690814b3b3842f954930d9ffd.png)

![jvm_stack3](https://s1.51cto.com/images/blog/201807/27/f93496e544f2757761052ab57383dcf2.png)

![jvm_stack4](https://s1.51cto.com/images/blog/201807/27/dacf4cc10e932527b4cac39cdd19d991.png)

![jvm_stack5](https://s1.51cto.com/images/blog/201807/27/8ca0abc97f7ca4a13b474f6703b451d4.png)

![jvm_stack6](https://s1.51cto.com/images/blog/201807/27/3c4479eccac658788e9f32292cb58db2.png)

## 8.3 `i++`与`++i`

我们经常会看到一个问题，i++ 与 ++i 哪个效率更高，在循环里应该使用哪一个好。虽然很多人都知道答案，但是可能不知道答案背后的原理。所以本小节将介绍一下 i++ 与 ++i 效率孰高孰低的原理。首先也是准备一些简单的测试代码，如下：

```java
public class SelfAdd {

    public static void main(String[] args) {
        f3();
        f4();
    }

    public static void f1() {
        for(int i=0;i<10;i++) {
            System.out.println(i);
        }
    }

    public static void f2() {
        for(int i=0;i<10;++i) {
            System.out.println(i);
        }
    }

    public static void f3() {
        int i=0;
        int j = i++;
        System.out.println(j);
    }

    public static void f4() {
        int i=0;
        int j = ++i;
        System.out.println(j);
    }
}
```

通过`maven compile`把源码进行编译，得到Test1.class文件，在.classs文件所在目录执行 `javap -verbose SelfAdd.class > SelfAdd.txt`,得SelfAdd.txt的字节码文件，内容如下：以上代码编译后的字节码如下：

```java
Classfile /D:/Java_Work/classesview/out/production/classesview/classesview/SelfAdd.class
  Last modified 2018年7月27日; size 980 bytes
  MD5 checksum f77d50197f39e7c67717f14297cbb504
  Compiled from "SelfAdd.java"
public class classesview.SelfAdd
  minor version: 0
  major version: 52
  flags: (0x0021) ACC_PUBLIC, ACC_SUPER
  this_class: #6                          // classesview/SelfAdd
  super_class: #7                         // java/lang/Object
  interfaces: 0, fields: 0, methods: 6, attributes: 1
Constant pool:
   #1 = Methodref          #7.#29         // java/lang/Object."<init>":()V
   #2 = Methodref          #6.#30         // classesview/SelfAdd.f3:()V
   #3 = Methodref          #6.#31         // classesview/SelfAdd.f4:()V
   #4 = Fieldref           #32.#33        // java/lang/System.out:Ljava/io/PrintStream;
   #5 = Methodref          #34.#35        // java/io/PrintStream.println:(I)V
   #6 = Class              #36            // classesview/SelfAdd
   #7 = Class              #37            // java/lang/Object
   #8 = Utf8               <init>
   #9 = Utf8               ()V
  #10 = Utf8               Code
  #11 = Utf8               LineNumberTable
  #12 = Utf8               LocalVariableTable
  #13 = Utf8               this
  #14 = Utf8               Lclassesview/SelfAdd;
  #15 = Utf8               main
  #16 = Utf8               ([Ljava/lang/String;)V
  #17 = Utf8               args
  #18 = Utf8               [Ljava/lang/String;
  #19 = Utf8               f1
  #20 = Utf8               i
  #21 = Utf8               I
  #22 = Utf8               StackMapTable
  #23 = Utf8               f2
  #24 = Utf8               f3
  #25 = Utf8               j
  #26 = Utf8               f4
  #27 = Utf8               SourceFile
  #28 = Utf8               SelfAdd.java
  #29 = NameAndType        #8:#9          // "<init>":()V
  #30 = NameAndType        #24:#9         // f3:()V
  #31 = NameAndType        #26:#9         // f4:()V
  #32 = Class              #38            // java/lang/System
  #33 = NameAndType        #39:#40        // out:Ljava/io/PrintStream;
  #34 = Class              #41            // java/io/PrintStream
  #35 = NameAndType        #42:#43        // println:(I)V
  #36 = Utf8               classesview/SelfAdd
  #37 = Utf8               java/lang/Object
  #38 = Utf8               java/lang/System
  #39 = Utf8               out
  #40 = Utf8               Ljava/io/PrintStream;
  #41 = Utf8               java/io/PrintStream
  #42 = Utf8               println
  #43 = Utf8               (I)V
{
  public classesview.SelfAdd();
    descriptor: ()V
    flags: (0x0001) ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 3: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   Lclassesview/SelfAdd;

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: (0x0009) ACC_PUBLIC, ACC_STATIC
    Code:
      stack=0, locals=1, args_size=1
         0: invokestatic  #2                  // Method f3:()V
         3: invokestatic  #3                  // Method f4:()V
         6: return
      LineNumberTable:
        line 6: 0
        line 7: 3
        line 8: 6
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       7     0  args   [Ljava/lang/String;

  public static void f1();
    descriptor: ()V
    flags: (0x0009) ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=1, args_size=0
         0: iconst_0
         1: istore_0
         2: iload_0
         3: bipush        10
         5: if_icmpge     21
         8: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
        11: iload_0
        12: invokevirtual #5                  // Method java/io/PrintStream.println:(I)V
        15: iinc          0, 1
        18: goto          2
        21: return
      LineNumberTable:
        line 28: 0
        line 29: 8
        line 28: 15
        line 31: 21
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            2      19     0     i   I
      StackMapTable: number_of_entries = 2
        frame_type = 252 /* append */
          offset_delta = 2
          locals = [ int ]
        frame_type = 250 /* chop */
          offset_delta = 18

  public static void f2();
    descriptor: ()V
    flags: (0x0009) ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=1, args_size=0
         0: iconst_0
         1: istore_0
         2: iload_0
         3: bipush        10
         5: if_icmpge     21
         8: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
        11: iload_0
        12: invokevirtual #5                  // Method java/io/PrintStream.println:(I)V
        15: iinc          0, 1
        18: goto          2
        21: return
      LineNumberTable:
        line 51: 0
        line 52: 8
        line 51: 15
        line 54: 21
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            2      19     0     i   I
      StackMapTable: number_of_entries = 2
        frame_type = 252 /* append */
          offset_delta = 2
          locals = [ int ]
        frame_type = 250 /* chop */
          offset_delta = 18

  public static void f3();
    descriptor: ()V
    flags: (0x0009) ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=2, args_size=0
         0: iconst_0
         1: istore_0
         2: iload_0
         3: iinc          0, 1
         6: istore_1
         7: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
        10: iload_1
        11: invokevirtual #5                  // Method java/io/PrintStream.println:(I)V
        14: return
      LineNumberTable:
        line 72: 0
        line 73: 2
        line 74: 7
        line 75: 14
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            2      13     0     i   I
            7       8     1     j   I

  public static void f4();
    descriptor: ()V
    flags: (0x0009) ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=2, args_size=0
         0: iconst_0
         1: istore_0
         2: iinc          0, 1
         5: iload_0
         6: istore_1
         7: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
        10: iload_1
        11: invokevirtual #5                  // Method java/io/PrintStream.println:(I)V
        14: return
      LineNumberTable:
        line 93: 0
        line 94: 2
        line 95: 7
        line 96: 14
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            2      13     0     i   I
            7       8     1     j   I
}
SourceFile: "SelfAdd.java"
```

我们先来看f4();和f3();，也就是i++与++i本身的字节码，这里没有涉及循环，两者的字节码与源代码的对比如下：

```java
f4();                        
int i=0;                     
int j = ++i;                 

0: iconst_0        // 常量0压栈                
1: istore_0        // 出栈保存到本地变量0里面，即代码中的变量i
2: iinc      0, 1  // 本地变量0加1            
5: iload_0         // 本地变量0压栈，此时这个本地变量的值为1
6: istore_1        // 出栈保存到本地变量1里面，即代码中的变量j

f3();
int i=0;
int j = i++;

0: iconst_0         // 常量0压栈
1: istore_0         // 出栈保存到本地变量0里面，即代码中的变量i
2: iload_0          // 本地变量0压栈
3: iinc       0, 1  // 本地变量0加1，注意：这里是本地变量加1，不是操作数栈，栈里依旧是0
6: istore_1         // 出栈保存到本地变量1里面，即代码中的变量j
```

从字节码层面上，可以看到**两者之间始终是区别于先+还是后+，并没有哪里少操作或多操作了一步**。知道了 i++和++i 在字节码中的执行原理后，我们再来看看f1();和f2();方法里这种使用了循环的字节码，如下：

```java
public static void f1();
    descriptor: ()V
    flags: (0x0009) ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=1, args_size=0
         0: iconst_0             // 常量0压栈
         1: istore_0             // 出栈保存到本地变量0里面
         2: iload_0              // 从本地变量0压栈
         3: bipush        10     // 常量10压栈，因为取值为-128~127，所以采用bipush指令
         5: if_icmpge     21     // 若判断的结果是大于就跳转到第21行
         8: getstatic     #4     // 对应常量池的#4             
        11: iload_0              // 本地变量0压栈
        12: invokevirtual #5     // 对应常量池的#5             
        15: iinc          0, 1   // 本地变量0加1
        18: goto          2      // goto到第2行
        21: return               // return void

  public static void f2();
    descriptor: ()V
    flags: (0x0009) ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=1, args_size=0
         0: iconst_0
         1: istore_0
         2: iload_0
         3: bipush        10
         5: if_icmpge     21
         8: getstatic     #4                  
        11: iload_0
        12: invokevirtual #5                  
        15: iinc          0, 1
        18: goto          2
        21: return
```

可以看到，这两个方法的字节码是一样的。所以实际上循环里无论是用++i还是i++效率都是一样的，当再有人问你这个问题的时候，就可以理直气壮的说它俩的效率是一样的了。因为在字节码里，它俩的指令就是一模一样的，没有任何区别 

## 8.4 字符串拼接

我们都知道，在循环里拼接字符串的话，要使用StringBuilder或StringBuffer。如果直接使用 + 进行字符串拼接的话，效率就会很低。那么为什么使用 + 进行字符串拼接效率就低，而使用StringBuilder或StringBuffer进行字符串拼接效率就高呢？这就是本小节将要说明的一个问题。同样的，我们也是从字节码的角度来看，首先编写一些测试代码 ，如下：

```java
public class StringAdd {
    public static void main(String[] args) {
        f1();
        f2();
    }

    public static void f1() {
        String src = "";
        for (int i = 0; i < 10; i++) {
            //每一次循环都会new一个StringBuilder
            src = src + "A";
        }
        System.out.println(src);
    }

    public static void f2() {
        //只要一个StringBuilder
        StringBuilder src = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            src.append("A");
        }
        System.out.println(src);
    }
}
```

通过`maven compile`把源码进行编译，得到Test1.class文件，在.classs文件所在目录执行 `javap -verbose StringAdd.class > StringAdd.txt`,得StringAdd.txt的字节码文件，以上代码编译后的字节码如下，

```java
Classfile /E:/MyProjects/jvm/target/classes/imooc/jvm/chapter08/StringAdd.class
  Last modified 2019-8-16; size 1201 bytes
  MD5 checksum d099b67ade2f9b097485474ab31f92bf
  Compiled from "StringAdd.java"
public class imooc.jvm.chapter08.StringAdd
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #14.#39        // java/lang/Object."<init>":()V
   #2 = Methodref          #13.#40        // imooc/jvm/chapter08/StringAdd.f1:()V
   #3 = Methodref          #13.#41        // imooc/jvm/chapter08/StringAdd.f2:()V
   #4 = String             #42            //
   #5 = Class              #43            // java/lang/StringBuilder
   #6 = Methodref          #5.#39         // java/lang/StringBuilder."<init>":()V
   #7 = Methodref          #5.#44         // java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
   #8 = String             #45            // A
   #9 = Methodref          #5.#46         // java/lang/StringBuilder.toString:()Ljava/lang/String;
  #10 = Fieldref           #47.#48        // java/lang/System.out:Ljava/io/PrintStream;
  #11 = Methodref          #49.#50        // java/io/PrintStream.println:(Ljava/lang/String;)V
  #12 = Methodref          #49.#51        // java/io/PrintStream.println:(Ljava/lang/Object;)V
  #13 = Class              #52            // imooc/jvm/chapter08/StringAdd
  #14 = Class              #53            // java/lang/Object
  #15 = Utf8               <init>
  #16 = Utf8               ()V
  #17 = Utf8               Code
  #18 = Utf8               LineNumberTable
  #19 = Utf8               LocalVariableTable
  #20 = Utf8               this
  #21 = Utf8               Limooc/jvm/chapter08/StringAdd;
  #22 = Utf8               main
  #23 = Utf8               ([Ljava/lang/String;)V
  #24 = Utf8               args
  #25 = Utf8               [Ljava/lang/String;
  #26 = Utf8               MethodParameters
  #27 = Utf8               f1
  #28 = Utf8               i
  #29 = Utf8               I
  #30 = Utf8               src
  #31 = Utf8               Ljava/lang/String;
  #32 = Utf8               StackMapTable
  #33 = Class              #54            // java/lang/String
  #34 = Utf8               f2
  #35 = Utf8               Ljava/lang/StringBuilder;
  #36 = Class              #43            // java/lang/StringBuilder
  #37 = Utf8               SourceFile
  #38 = Utf8               StringAdd.java
  #39 = NameAndType        #15:#16        // "<init>":()V
  #40 = NameAndType        #27:#16        // f1:()V
  #41 = NameAndType        #34:#16        // f2:()V
  #42 = Utf8
  #43 = Utf8               java/lang/StringBuilder
  #44 = NameAndType        #55:#56        // append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
  #45 = Utf8               A
  #46 = NameAndType        #57:#58        // toString:()Ljava/lang/String;
  #47 = Class              #59            // java/lang/System
  #48 = NameAndType        #60:#61        // out:Ljava/io/PrintStream;
  #49 = Class              #62            // java/io/PrintStream
  #50 = NameAndType        #63:#64        // println:(Ljava/lang/String;)V
  #51 = NameAndType        #63:#65        // println:(Ljava/lang/Object;)V
  #52 = Utf8               imooc/jvm/chapter08/StringAdd
  #53 = Utf8               java/lang/Object
  #54 = Utf8               java/lang/String
  #55 = Utf8               append
  #56 = Utf8               (Ljava/lang/String;)Ljava/lang/StringBuilder;
  #57 = Utf8               toString
  #58 = Utf8               ()Ljava/lang/String;
  #59 = Utf8               java/lang/System
  #60 = Utf8               out
  #61 = Utf8               Ljava/io/PrintStream;
  #62 = Utf8               java/io/PrintStream
  #63 = Utf8               println
  #64 = Utf8               (Ljava/lang/String;)V
  #65 = Utf8               (Ljava/lang/Object;)V
{
  public imooc.jvm.chapter08.StringAdd();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 3: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   Limooc/jvm/chapter08/StringAdd;

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=0, locals=1, args_size=1
         0: invokestatic  #2                  // Method f1:()V
         3: invokestatic  #3                  // Method f2:()V
         6: return
      LineNumberTable:
        line 5: 0
        line 6: 3
        line 7: 6
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       7     0  args   [Ljava/lang/String;
    MethodParameters:
      Name                           Flags
      args

  public static void f1();
    descriptor: ()V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=2, args_size=0
         0: ldc           #4                  // String
         2: astore_0
         3: iconst_0
         4: istore_1
         5: iload_1
         6: bipush        10
         8: if_icmpge     37
        11: new           #5                  // class java/lang/StringBuilder
        14: dup
        15: invokespecial #6                  // Method java/lang/StringBuilder."<init>":()V
        18: aload_0
        19: invokevirtual #7                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        22: ldc           #8                  // String A
        24: invokevirtual #7                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        27: invokevirtual #9                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
        30: astore_0
        31: iinc          1, 1
        34: goto          5
        37: getstatic     #10                 // Field java/lang/System.out:Ljava/io/PrintStream;
        40: aload_0
        41: invokevirtual #11                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        44: return
      LineNumberTable:
        line 10: 0
        line 11: 3
        line 13: 11
        line 11: 31
        line 15: 37
        line 16: 44
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            5      32     1     i   I
            3      42     0   src   Ljava/lang/String;
      StackMapTable: number_of_entries = 2
        frame_type = 253 /* append */
          offset_delta = 5
          locals = [ class java/lang/String, int ]
        frame_type = 250 /* chop */
          offset_delta = 31

  public static void f2();
    descriptor: ()V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=2, args_size=0
         0: new           #5                  // class java/lang/StringBuilder
         3: dup
         4: invokespecial #6                  // Method java/lang/StringBuilder."<init>":()V
         7: astore_0
         8: iconst_0
         9: istore_1
        10: iload_1
        11: bipush        10
        13: if_icmpge     29
        16: aload_0
        17: ldc           #8                  // String A
        19: invokevirtual #7                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        22: pop
        23: iinc          1, 1
        26: goto          10
        29: getstatic     #10                 // Field java/lang/System.out:Ljava/io/PrintStream;
        32: aload_0
        33: invokevirtual #12                 // Method java/io/PrintStream.println:(Ljava/lang/Object;)V
        36: return
      LineNumberTable:
        line 20: 0
        line 21: 8
        line 22: 16
        line 21: 23
        line 24: 29
        line 25: 36
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
           10      19     1     i   I
            8      29     0   src   Ljava/lang/StringBuilder;
      StackMapTable: number_of_entries = 2
        frame_type = 253 /* append */
          offset_delta = 10
          locals = [ class java/lang/StringBuilder, int ]
        frame_type = 250 /* chop */
          offset_delta = 18
}
SourceFile: "StringAdd.java"
```

我这里只截取了f1();和f2();方法的部分字节码：

```java
public static void f1();
    descriptor: ()V
    flags: (0x0009) ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=2, args_size=0
         0: ldc           #4                  // 将常量字符串压栈，对应常量池中的#4 
         2: astore_0                          // 出栈保存到本地变量0里面
         3: iconst_0                          // 常量0压栈
         4: istore_1                          // 出栈保存到本地变量1里面
         5: iload_1                           // 本地变量1压栈
         6: bipush        10                  // 常量10压栈，因为取值为-128~127，所以采用bipush指令
         8: if_icmpge     37                  // 若判断结果为大于，则执行第37行
        11: new           #5                  // class java/lang/StringBuilder 创建StringBuilder实例，如果是jdk1.4之前的版本，这里创建的是StringBuffer
        14: dup                               // 复制StringBuilder实例的引用并压入栈顶
        15: invokespecial #6                  // Method java/lang/StringBuilder."<init>":()V  使用空构造器创建的
        18: aload_0                           // 本地变量0压栈，aload是用于将对象引用压栈的指令
        19: invokevirtual #7                  // Method java/lang/StringBuilder.append: (Ljava/lang/String;)Ljava/lang/StringBuilder; 执行了append方法
        22: ldc           #8                  // 字符串A压栈，取值-2147483648~2147483647采用ldc指令
        24: invokevirtual #7                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;  执行了append方法
        27: invokevirtual #9                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;  执行了toString方法
        30: astore_0                          // 将对象引用出栈保存到本地变量0里面
        31: iinc          1, 1                // 本地变量1加1
        34: goto          5                   // 跳转到第5行
        37: getstatic     #10                 // Field java/lang/System.out:Ljava/io/PrintStream;
        40: aload_0                           // 本地变量0压栈，aload是用于将对象引用压栈的指令
        41: invokevirtual #11                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        44: return                            // return void

  public static void f2();
    descriptor: ()V
    flags: (0x0009) ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=2, args_size=0
         0: new           #5              // class java/lang/StringBuilder  创建StringBuilder实例
         3: dup                           // 复制StringBuilder实例的引用并压入栈顶
         4: invokespecial #6              // Method java/lang/StringBuilder."<init>":()V
         7: astore_0                      // 将对象引用出栈并保存到本地变量0里面
         8: iconst_0                      // 常量0压栈
         9: istore_1                      // 出栈保存到本地变量1里面
        10: iload_1                       // 本地变量1压栈
        11: bipush        10              // 常量10压栈，因为取值为-128~127，所以采用bipush指令
        13: if_icmpge     29              // 若判断结果为大于，则执行第29行
        16: aload_0                       // 本地变量0压栈，aload是用于将对象引用压栈的指令
        17: ldc           #8              // 字符串A压栈，取值-2147483648~2147483647采用ldc指令
        19: invokevirtual #7              // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;  执行了append方法
        22: pop
        23: iinc          1, 1            // 本地变量1加1
        26: goto          10              // 跳转到第10行
        29: getstatic     #10             // Field java/lang/System.out:Ljava/io/PrintStream;
        32: aload_0                       // 本地变量0压栈，aload是用于将对象引用压栈的指令
        33: invokevirtual #12             // Method java/io/PrintStream.println:(Ljava/lang/Object;)V
        36: return                        // return void
```

从以上f1();方法的字节码中，可以看到，在循环中使用 + 进行字符串拼接的话，每次循环都会new一个StringBuilder实例，同样的也是需要执行append方法来拼接字符串，最后还需要执行toString方法转换成字符串类型。而f2();方法的字节码中，只创建了一次StringBuilder的实例，并且执行的指令也要少一些。所以使用StringBuilder进行字符串拼接，比使用 + 拼接的效率高。

## 8.5 Try-Finally字节码

除了以上小节所提到的问题外，还有一个问题也很常见，这是一个关于Try-Finally的题目。就是try里会return一个字符串，而finally里则会改变这个字符串。那么到底会返回改变前的字符串还是改变后的字符串。代码如下：

```java
public class TryFinally {
    public static void main(String[] args) {
        System.out.println(f1());
    }

    public static String f1() {
        String str = "hello";
        try{
            return str;
        } finally {
            str = "finally";
        }
    }
}
```

这个问题，我们同样可以从字节码的层面进行分析。将以上代码编译后的字节码如下，我这里只截取了f1();方法的部分字节码，免得一些已经介绍过的内容占用篇幅：

```java
public static java.lang.String f1();
    descriptor: ()Ljava/lang/String;
    flags: (0x0009) ACC_PUBLIC, ACC_STATIC
    Code:
      stack=1, locals=3, args_size=0
         0: ldc           #5             // String hello 压栈
         2: astore_0                     // 出栈保存到本地变量0里
         3: aload_0                      // 本地变量0压栈
         4: astore_1                     // 出栈保存到本地变量1里
         5: ldc           #6             // String finally 压栈
         7: astore_0                     // 出栈保存到本地变量0里
         8: aload_1                      // 本地变量1压栈
         9: areturn                      // 返回栈顶元素
        10: astore_2                     // 如果发生异常，操作栈里就会存在一个异常对象，此时就会把异常对象出栈保存到本地变量2里，然后执行以下指令
        11: ldc           #6             // String finally 压栈
        13: astore_0                     // 出栈保存到本地变量0里
        14: aload_2                      // 本地变量2压栈
        15: athrow                       // 抛出异常，即把本地变量2里存储的异常对象给抛出
```

如上，从字节码中进行分析，我们就可以很清晰的看到f1();方法到底会返回哪一个字符串(hello)。所以我们才要学会分析字节码，这样我们就能够看到代码执行的本质，而不是去死记硬背这些怪题，下次再遇到这种类似代码就不会一脸懵逼了。

## 8.6 字符串常量变量 String Constant Variable

在关于字符串拼接那一小节中，我们得知了在使用 + 进行字符串拼接的时候，实际上会创建StringBuilder实例来完成字符串的拼接。但使用 + 进行字符串拼接，背后就一定是StringBuilder吗？实际上是未必的，这取决于是常量拼接还是变量拼接。同样的，我们来编写一些测试代码，然后从字节码的层面上去观察。代码如下：

```java
public class Constant {

    public static void main(String[] args) {
        f1();
        new Constant().f2();
    }

    public static void f1() {
        final String x = "hello";
        final String y = x + "world";
        String z = x + y;
        System.out.println(z);
    }

    public void f2() {
        final String x = "hello";
        String y = x + "world";
        String z = x + y;
        System.out.println(z);
    }
}
```

将以上代码编译后的字节码如下，我这里只截取了f1();和f2();方法的部分字节码：

```java
public class Constant {

    public static void main(String[] args) {
        f1();
        new Constant().f2();
    }

    public static void f1() {
        final String x = "hello";
        final String y = x + "world";
        String z = x + y;
        System.out.println(z);
    }

    public void f2() {
        final String x = "hello";
        String y = x + "world";
        String z = x + y;
        System.out.println(z);
    }
}
将以上代码编译后的字节码如下，我这里只截取了f1();和f2();方法的部分字节码：

  public static void f1();
    descriptor: ()V
    flags: (0x0009) ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=3, args_size=0
         0: ldc           #6                  // 字符串hello压栈
         2: astore_0                          // 出栈保存到本地变量0里面
         3: ldc           #7                  // 字符串helloworld压栈
         5: astore_1                          // 出栈保存到本地变量1里面
         6: ldc           #8                  // 字符串hellohelloworld压栈
         8: astore_2                          // 出栈保存到本地变量2里面
         9: getstatic     #9                  // Field java/lang/System.out:Ljava/io/PrintStream;
        12: aload_2                           // 本地变量2压栈
        13: invokevirtual #10                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V  此时操作栈里只有本地变量2，所以打印本地变量2
        16: return                            // retrun void

  public void f2();
    descriptor: ()V
    flags: (0x0001) ACC_PUBLIC
    Code:
      stack=2, locals=4, args_size=1
         0: ldc           #6                  // 字符串hello压栈
         2: astore_1                          // 出栈保存到本地变量1里面
         3: ldc           #7                  // 字符串helloworld压栈
         5: astore_2                          // 出栈保存到本地变量2里面
         6: new           #11                 // class java/lang/StringBuilder 创建StringBuilder实例，因为到这一步就是变量进行拼接了
         9: dup                               // 复制StringBuilder实例的引用并压入栈顶
        10: invokespecial #12                 // Method java/lang/StringBuilder."<init>":()V  调用构造函数完成实例的构造
        13: ldc           #6                  // 字符串hello压栈
        15: invokevirtual #13                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;  调用append方法拼接字符串
        18: aload_2                           // 本地变量2压栈
        19: invokevirtual #13                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;  调用append方法拼接字符串
        22: invokevirtual #14                 // Method java/lang/StringBuilder.toString:()Ljava/lang/String;  调用toString方法转换为字符串类型
        25: astore_3                          // 出栈保存到本地变量3里面
        26: getstatic     #9                  // Field java/lang/System.out:Ljava/io/PrintStream;
        29: aload_3                           // 本地变量3压栈
        30: invokevirtual #10                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V  此时操作栈里只有本地变量3，所以打印本地变量3
        33: return                            // return void
```

从以上的字节码中，可以看到，当常量字符串进行拼接的时候，并没有使用SpringBuilder去完成拼接，而是直接使用了一个新的字符串进行赋值，这其实是JVM在编译时会对这种常量及字面常量进行替换，因为字节码里面是没有 + 的概念的。所以只有变量拼接才会使用SpringBuilder去完成拼接。

## 8.7~8.8

常用代码优化方法

+ 尽量复用对象，不要循环创建对象，比如：for循环的字符串拼接
+ 容器类初始化的时候最好是指定长度，例如List、Map等，可以减少动态扩容的次数(ArrayList和HashMap的底层都是数组)
+ ArrayList随机遍历快(底层是数组)，LinkedList添加删除快(底层是链表)
+ 集合遍历时，尽量减少重复计算
+ 尽量使用Entry来遍历Map，代码示例（1），不要使用keySet
+ 大数组复制使用System.arraycopy，因为该方法底层是使用C实现的，所以效率高些
+ 局部变量尽量使用基本类型，而不是包装类型
+ 不要手动调用`System.gc()`，因为GC是会耗时
+ 及时消除过期对象的引用，防止内存泄露
+ 尽量使用局部变量，减小变量的作用域
+ 尽量使用非同步的容器，例如ArrayList和Vector中，应该选用ArrayList，因为Vector里大量使用了synchronized，会导致效率低下
+ 尽量减小同步作用范围，例如synchronized方法和synchronized代码块中，应该选用synchronized代码块的方式完成同步
+ 可以使用ThreadLocal缓存线程不安全的对象或重量级的对象，例如SimpleDateFormat
+ 尽量使用延迟加载，例如在单例模式中就不要使用懒汉式的，尽量用饿汉式，代码示例（2）
+ 尽量减少使用反射，如果是必须使用反射，则把反射出来的对象加缓存里，这样能避免使用反射的次数
+ 尽量使用连接池、线程池、对象池、缓存
+ 及时释放资源，I/O流、Socket、数据库连接对象
+ 慎用异常，不要用抛异常来表示正常的业务逻辑
+ String操作尽量少用支持正则表达式的方法，因为支持正则表达式方法的性能比较低
+ 日志输出注意使用不同的日志级别，避免导致日志不停的输出
+ 日志中参数拼接使用占位符，代码示例（3）

```java
// 代码示例（1）
for (Map.Entry<String, String> entry : map.entrySet()) {
    String key = entry.getKey();
    String value = entry.getValue();
}
```

```java
// 代码示例（2）
package classesview;

public class Singleton {

    private Singleton() {
    }

    private static class SingletonHolder{
        private static Singleton singleton = new Singleton();
    }

    public static Singleton getInstance(){
        return SingletonHolder.singleton;
    }
}
```

```java
// 代码示例（3）
log.info("orderId" + orderId);  // 不推荐 
log.info("orderId:{}", orderId);  // 推荐
```
