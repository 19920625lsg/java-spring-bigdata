# 01_数据类型与运算符

## 一、填空题

## 二、判断题
### 1.语句：char foo='中'，是否正确？（假设源文件以GB2312编码存储，并且以javac – encoding GB2312命令编译）(`A`)
+ A.正确
+ B.错误

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12779826

> Java语言中，中文字符所占的字节数取决于字符的编码方式，一般情况下，采用ISO8859-1编码方式时，一个中文字符与一个英文字符一样只占1个字节；采用GB2312或GBK编码方式时，一个中文字符占2个字节；而采用UTF-8编码方式时，一个中文字符会占3个字节。

> 这里的'中'应该是不管用什么编码方式存储，赋值给char变量后都会先转换成对应的unicode的序号\u4e2d,（知道了项目原编码方式，以及某个字符，然后按对应规则转--见下文a）之后再用UTF-16BE来作为\u4e2d这个字符编码的存储方案。

### 2.我们在程序中经常使用“System.out.println()”来输出信息，语句中的System是包名，out是类名，println是方法名。(`B`)
+ A.对
+ B.错

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/5248881

System是java.lang中的一个类，out是System内的一个成员变量，这个变量是一个java.io.PrintStream类的对象，println呢就是一个方法了。

### 3.以下选项中，合法的赋值语句是（`B`）
+ A.`a>1;`
+ B.`i++;`
+ C.`a= a+1=5;`
+ D.`y = int ( i );`

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/4010357

B项，中间变量缓存机制。
等同于
```java
temp = i;
i=i+1;
i=temp;
```
D:`(int)i`不是 `int(i)`

## 三、单选题

### 1.以下哪一个不是赋值符号？（`C`）
+ A.`+=`
+ B.`<<=`
+ C.`<<<=`
+ D.`>>>=`

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12777877

C.混合赋值运算符的使用
+ `<<`表示左移位
+ `>>`表示带符号右移位
+ `>>>`表示无符号右移
+ 但是没有`<<<`运算符

### 2.以下代码的输出的正确结果是(`D`)
```java
public class Test {
    public static void main(String[] args) {
        String s = "祝你考出好成绩！";
        System.out.println(s.length());
    }
}
```

+ A.24
+ B.16
+ C.15
+ D.8

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12777626

java的String底层是char数组，它的length()返回数组大小，而unicode中一个汉字是可以用一个char表示的。不管中文字符还是英文字符在Java中都是一个char，在C和C++是不一样地

### 3.下面代码在main()方法中第八行后可以正常使用的是（`AD`）
+ A.t.a
+ B.this.c
+ C.Test.b
+ D.Test.c

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/4361022

+ A、在private  修饰不能在外部类中调用，main 方法属于Test类的方法， 所以 对象 t 可以在他自己的类方法中调用它的private
+ B、static方法中不能用this
+ C、b是非静态成员变量，只能用实例引用
+ D、正确

## 四、多选题
### 1.已知
```java
String a="a";
String b="b";
String c=a+b;
String d=new String("ab");
```
以下操作结果为true的是(`AD`)

+ A.`(a+b).equals(c)`
+ B.`a+b==c` // 和"a"+"b"区分开
+ C.`c==d`
+ D.`c.equals(d)`

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12779712

+ 1.`==` 和 `equals()`:
  + (1)"=="用于比较基本数据类型时比较的是值，用于比较引用类型时比较的是引用指向的地址
  + (2)Object 中的equals() 与 “==” 的作用相同，但String类重写了equals()方法，比较的是对象中的内容
+ 2.String对象的两种创建方式:
  + (1)第一种方式: `String str1 = "aaa";`  是在常量池中获取对象("aaa" 属于字符串字面量，因此编译时期会在常量池中创建一个字符串对象，如果常量池中已经存在该字符串对象则直接引用)
  + (2)第二种方式: `String str2 = new String("aaa");` 一共会创建两个字符串对象一个在堆中，一个在常量池中（前提是常量池中还没有 "aaa" 象）。
     所以：`System.out.println(str1==str2);//false`
+ 3.String类型的常量池比较特殊。它的主要使用方法有两种：
+ (1)直接使用双引号声明出来的String对象会直接存储在常量池中。
+ (2)如果不是用双引号声明的String对象,可以使用 String 提供的 intern 方法。 String.intern() 是一个 Native 方法，它的作用是： 如果运行时常量池中已经包含一个等于此 String 对象内容的字符串，则返回常量池中该字符串的引用； 如果没有，则在常量池中创建与此 String 内容相同的字符串，并返回常量池中创建的字符串的引用
    ```java
    String s1 = new String("AAA");
    String s2 = s1.intern();
    String s3 = "AAA";
    System.out.println(s2);//AAA
    System.out.println(s1 == s2);//false，因为一个是堆内存中的String对象一个是常量池中的String对象，
    System.out.println(s2 == s3);//true， s1,s2指向常量池中的”AAA“
    ```
+ 4.字符串拼接：
    ```java
    String a = "a";
    String b = "b";
         
    String str1 = "a" + "b";//常量池中的对象
    String str2 = a + b; //在堆上创建的新的对象     
    String str3 = "ab";//常量池中的对象
    System.out.println(str1 == str2);//false
    System.out.println(str1 == str3);//true 
    System.out.println(str2 == str3);//false
    ```

### 2.下列可作为java语言标识符的是（`ABD`）
+ A.a1
+ B.$1
+ C._1
+ D.11

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12741525

Java标识符由数字，字母和下划线（_），美元符号（$）或人民币符号（￥）组成。在Java中是区分大小写的，而且还要求首位不能是数字。最重要的是，Java关键字不能当作Java标识符。

### 3.下列代码片段中，存在编译错误的语句是()
```java
byte b1=1,b2=2,b3,b6,b8;
final byte b4=4,b5=6,b7;
b3=(b1+b2);  /*语句1*/
b6=b4+b5;    /*语句2*/
b8=(b1+b4);  /*语句3*/
b7=(b2+b5);  /*语句4*/
System.out.println(b3+b6);
```
+ A.语句2
+ B.语句1
+ C.语句3
+ D.语句4

> 解答：

Java表达式转型规则由低到高转换：
+ 1、所有的byte,short,char型的值将被提升为int型；
+ 2、如果有一个操作数是long型，计算结果是long型；
+ 3、如果有一个操作数是float型，计算结果是float型；
+ 4、如果有一个操作数是double型，计算结果是double型；
+ 5、被fianl修饰的变量不会自动改变类型，当2个final修饰相操作时，结果会根据左边变量的类型而转化。
-----------------------------------------题目解析---------------------------------
+ 语句1错误：b3=(b1+b2);自动转为int，所以正确写法为b3=(byte)(b1+b2);或者将b3定义为int；
+ 语句2正确：b6=b4+b5;b4、b5为final类型，不会自动提升，所以和的类型视左边变量类型而定，即b6可以是任意数值类型；
+ 语句3错误：b8=(b1+b4);虽然b4不会自动提升，但b1仍会自动提升，所以结果需要强转，b8=(byte)(b1+b4);
+ 语句4错误：b7=(b2+b5); 同上。同时注意b7是final修饰，即只可赋值一次，便不可再改变。

## 五、问答题