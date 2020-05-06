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

### 4.StringBuffer类对象创建之后可以再修改和变动.(`A`)
+ A.正确
+ B.错误

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/4010285

+ String对象不可变的含义：举个例子：String str = "aa"; str = "aa"+"bb"; 此时str的值为"aabb"，但是"aabb"不是在开始的字符串"aa"后面直接连接的"bb"，而是又新生成了字符串"aabb"，字符串"aa"一旦被初始化，那么它的值不可能再改变了。 
+ StringBuffer对象可变的含义： StringBuffer strb = StringBuffer("aa"); strb.append("bb"); 此时的strb的值也为"aabb"，但是"aabb"是直接在开始的字符串"aa"后面连接的“bb”，并没有生成新的字符串。

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

### 4.在jdk1.5之后，下列 java 程序输出结果为(`B`)
+ A.true,false
+ B.true,true
+ C.false,true
+ D.false,false
+ E.对于不同的环境结果不同
+ F.程序无法执行

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/4010294

本题是一个自动拆装箱的考题(自动拆装箱JDK需在1.5上），下面的讨论都不针对新开辟对象的情况：
+ 1、基本型和基本型封装型进行“==”运算符的比较，基本型封装型将会自动拆箱变为基本型后再进行比较，因此Integer(0)会自动拆箱为int类型再进行比较，显然返回true；
+ 2、两个Integer类型进行“==”比较，如果其值在-128至127，那么返回true，否则返回false, 这跟Integer.valueOf()的缓冲对象有关，这里不进行赘述。
+ 3、两个基本型的封装型进行equals()比较，首先equals()会比较类型，如果类型相同，则继续比较值，如果值也相同，返回true
+ 4、基本型封装类型调用equals(),但是参数是基本类型，这时候，先会进行自动装箱，基本型转换为其封装类型，再进行3中的比较。

```java
int a=257;
Integer b=257;
Integer c=257;
Integer b2=57;
Integer c2=57;
System.out.println(a==b);
//System.out.println(a.equals(b));  编译出错，基本型不能调用equals()
System.out.println(b.equals(257.0));
System.out.println(b==c);
System.out.println(b2==c2);
```
因此上面的代码的结果因此为 true, false, false, true

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

### 4.Math.floor(-8.5)=(`D`)
+ A.`(float)-8.0`
+ B.`(long)-9`
+ C.`(long)-8`
+ D.`(double)-9.0`

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/3945822

+ floor: 求小于参数的最大整数。返回double类型-----n. 地板，地面
         例如：Math.floor(-4.2) = -5.0

+ ceil:   求大于参数的最小整数。返回double类型-----vt. 装天花板；
         例如：Math.ceil(5.6) = 6.0

+ round: 对小数进行四舍五入后的结果。返回int类型
         例如：Math.round(-4.6) = -5

### 5.下面代码的执行结果是：`A`
+ A. Ceil d1=-0.0
floor d1=-1.0
+ B.Ceil d1=0.0
floor d1=-1.0
+ C.Ceil d1=-0.0
floor d1=-0.0
+ D.Ceil d1=0.0
floor d1=0.0
+ E.Ceil d1=0
floor d1=-1

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/3813666

注意：-0.0的写法

### 6.下面代码的输出结果是(`A`)
```java
class Value{
    public int i=15;
}
public class Test{
    public static void main(String argv[]){
        Test t=new Test( );
        t.first( );
    }
 
public void first( ){
    int i=5;
    Value v=new Value( );
    v.i=25;
    second(v,i);
    System.out.println(v.i);
}
 
public void second(Value v,int i){
    i = 0;
    v.i = 20;
    Value val = new Value( );
    v = val;
    System.out.println(v.i+" "+i);
   }
}
```

+ A.`15 0 20`
+ B.`15 0 15`
+ C.`20 0 20`
+ D.`0 15 20`

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/3793985

可能有人会选择B，包括我刚开始也是。总以为v不是已经指向了val了吗？？为什么还是20呢？不应该是15吗？
其实，原因很简单。现在我们把second（）换一下
```java
public void second(Value tmp,int i){
    i = 0;
    tmp.i = 20;
    Value val = newValue( );
    tmp = val;
    System.out.println(tmp.i+" "+i);
}
```
这个tmp其实相当于是一个指向原来first中的V这个对象的指针，也就是对v对象的引用而已。但是引用是会改变所指的地址的值的。  
所以在second中当tmp.i= 20的时候，就把原来first中的v的i值改为20了。接下来，又把tmp指向了新建的一个对象，所以在second中的tmp  
现在指的是新的对象val，i值为15.  
当执行完毕second后，在first中在此输出v.i的时候，应为前面second中已经把该位置的i的值改为了20，所以输出的是20.  
至于疑惑v指向了val，其实只是名字的问题，在second中的v实践也是另外的一个变量，名字相同了而已，这个估计也是纠结的重点。  

### 7.下列哪个选项是正确计算42度（角度）的余弦值？（`C`）
+ A.`double d=Math.cos(42)`
+ B.`double d=Math.cosine(42)`
+ C.`double d=Math.cos(Math.toRadians(42))`
+ D.`double d=Math.cos(Math.toDegrees(42))`

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/3793721

Math.cos为计算弧度的余弦值，Math.toRadians函数讲角度转换为弧度

### 8.有以下代码片段：
```java
String str1="hello";
String str2="he"+ new String("llo");
System.out.println(str1==str2);
```
请问输出的结果是：（`D`）
+ A.true
+ B.都不对
+ C.null
+ D.false

> 解答：

+ `String str1 = "hello";`这里的str1指的是方法区中的字符串常量池中的“hello”，编译时期就知道的；
+ `String str2 = "he" + new String("llo");`这里的str2必须在运行时才知道str2是什么，所以它是指向的是堆里定义的字符串“hello”，所以这两个引用是不一样的。
  > 如果用str1.equal(str2)，那么返回的是true；因为String类重写了equals()方法。
    编译器没那么智能,它不知道"he" + new String("llo")的内容是什么,所以才不敢贸然把"hello"这个对象的引用赋给str2.
    如果语句改为:"he"+"llo"这样就是true了

### 9.下列Java代码中的变量a、b、c分别在内存的____存储区存放。(`C`)
+ A.堆区、堆区、堆区
+ B.堆区、栈区、堆区
+ C.堆区、栈区、栈区
+ D.堆区、堆区、栈区
+ E.静态区、栈区、堆区
+ F.静态区、栈区、栈区

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/3598071

a是类中的成员变量，存放在堆区；b、c都是方法中的局部变量，存放在栈区

扩展:
+ 堆区：只存放类对象，线程共享；
+ 方法区：又叫静态存储区，存放class文件和静态数据，线程共享;
+ 栈区：存放方法局部变量，基本类型变量区、执行环境上下文、操作指令区，线程不共享;

### 10.以下代码执行的结果显示是多少（`D`）？
```java
public class Demo {
    public static void main(String[] args) {
        Integer i1 = 128;
        Integer i2 = 128;
        System.out.print((i1 == i2) + ",");
        String i3 = "100";
        String i4 = "1" + new String("00");
        System.out.print((i3 == i4) + ",");
        Integer i5 = 100;
        Integer i6 = 100;
        System.out.print((i5 == i6));
    }
}
```
+ A.true,false,true
+ B.false,true,false
+ C.true,true,false
+ D.false,false,true

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/3563994

其实当我们在为Integer赋值的时候，java编译器会将其翻译成调用valueOf()方法。比如Integer i=127翻译为Integer i=Integer.valueOf(127)
然后我们来看看valueOf()函数的源码：
```java
public static Integer valueOf(int i){
    //high为127
    if(i >= -128 && i <= IntegerCache.high)
        return IntegerCache.***[i + 128];
    else
        return new Integer(i);
}
```
可以看出，对于-128到127之间的数(i>=-128 && i<=127)，Java会对其进行缓存。而超出这个范围则新建一个对象。  
所以现在回到这道问题  
i1和i2为128，超出范围，所以都需要新建对象，对象比较为false；  
i5和i6为100，在范围之内，在执行Integer i5=100时，就会直接缓存到内存中，但执行执行Integer i6=100时，就直接从缓存里取，而不需要新建对象，所以为true。  

字符串相关的解释如下：

+ String s = "abc"：通过字面量赋值创建字符串。则将栈中的引用直接指向该字符串，如不存在，则在常量池中生成一个字符串，再将栈中的引用指向该字符串
+ String s = “a”+“bc”：编译阶段会直接将“a”和“bc”结合成“abc”，这时如果方法区已存在“abc”，则将s的引用指向该字符串，如不存在，则在方法区中生成字符串“abc”对象，然后再将s的引用指向该字符串
+ String s = "a" + new String("bc"):栈中先创建一个"a"字符串常量，再创建一个"bc"字符串常量，编译阶段不会进行拼接，在运行阶段拼接成"abc"字符串常量并将s的引用指向它，效果相当于String s = new String("abc")，只有'+'两边都是字符串常量才会在编译阶段优化

### 11.关于如下程序的描述哪个是正确的？（`C`）
```java
public class Person{

static int arr[] = new int[5];

public static void main(String a[]){
        System.out.println(arr[0]);
    }
}
```

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/3524953

主要考察各个变量类型的默认值

![常用变量类型的默认值](https://uploadfiles.nowcoder.com/images/20180713/3807435_1531450430478_A1E3D6CDC367DED01B519240FF9FA63F)

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

### 4.下面为true的是(`G`)
```java
Integer i = 42;
Long l = 42l;
Double d = 42.0;
```

+ A.`(i == l)`
+ B.`(i == d)`
+ C.`(l == d)`
+ D.`i.equals(d)`
+ E.`d.equals(l)`
+ F.`i.equals(l)`
+ G.`l.equals(42L)`

> 解答：

+ 1、基本型和基本型封装型进行“==”运算符的比较，基本型封装型将会自动拆箱变为基本型后再进行比较，因此Integer(0)会自动拆箱为int类型再进行比较，显然返回true；
    ```java
    int a = 220;
    Integer b = 220;
    System.out.println(a==b);//true
    ```

+ 2、两个Integer类型进行“==”比较， 如果其值在-128至127  ，那么返回true，否则返回false, 这跟Integer.valueOf()的缓冲对象有关，这里不进行赘述。
    ```java
    Integer c=3;
    Integer h=3;
    Integer e=321;
    Integer f=321;
    System.out.println(c==h);//true
    System.out.println(e==f);//false
    ```

+ 3、两个基本型的封装型进行equals()比较，首先equals()会比较类型，如果类型相同，则继续比较值，如果值也相同，返回true。
    ```java
    Integer a=1;
    Integer b=2;
    Integer c=3;
    System.out.println(c.equals(a+b));//true
    ```

+ 4、基本型封装类型调用equals(),但是参数是基本类型，这时候，先会进行自动装箱，基本型转换为其封装类型，再进行3中的比较。 
    ```java
    int i=1;
    int j = 2;
    Integer c=3;
    System.out.println(c.equals(i+j));//true
    ```

### 5.对于下面的代码，选项中那个会返回true?(`CDE`)
```java
Integer s=new Integer(9);
Integer t=new Integer(9);
Long u=new Long(9);
```

+ A.`(s==u)`
+ B.`(s==t)`
+ C.`(s.equals(t))`
+ D.`(s.equals(9))`
+ E.`(s.equals(new Integer(9))`

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/3793853

- int和int之间，用==比较，肯定为true。基本数据类型没有equals方法
- int和Integer比较，Integer会自动拆箱，== 和 equals都肯定为true
- int和new Integer比较，Integer会自动拆箱，调用intValue方法, 所以 == 和 equals都肯定为true
- Integer和Integer比较的时候，由于直接赋值的话会进行自动的装箱。所以当值在[-128,127]中的时候，由于值缓存在IntegerCache中，那么当赋值在这个区间的时候，不会创建新的Integer对象，而是直接从缓存中获取已经创建好的Integer对象。而当大于这个区间的时候，会直接new Integer。
  当Integer和Integer进行==比较的时候，在[-128,127]区间的时候，为true。不在这个区间，则为false
  当Integer和Integer进行equals比较的时候，由于Integer的equals方法进行了重写，比较的是内容，所以为true
- Integer和new Integer ： new Integer会创建对象，存储在堆中。而Integer在[-128,127]中，从缓存中取，否则会new Integer.
  所以 Integer和new Integer 进行==比较的话，肯定为false ; Integer和new Integer 进行equals比较的话，肯定为true
- new Integer和new Integer进行==比较的时候，肯定为false ; 进行equals比较的时候，肯定为true
  原因是new的时候，会在堆中创建对象，分配的地址不同，==比较的是内存地址，所以肯定不同
- 装箱过程是通过调用包装器的valueOf方法实现的
  拆箱过程是通过调用包装器的xxxValue方法实现的（xxx表示对应的基本数据类型）
- 总结：Byte、Short、Integer、Long这几个类的valueOf方法实现类似的。所以在[-128,127]区间内，==比较的时候，值总是相等的（指向的是同一对象），在这个区间外是不等的。
  而Float和Double则不相等， Boolean的值总是相等的

针对题目的解析：
+ A.`(s==u)` ，因为， s 是 Integer 类型， u 是 Long 类型，两个不同类型的引用不能进行 == 比较。
+ B.`(s==t)` ， s 是指向一个 9 的引用，而 t 也是一个指向 9 的引用，虽然都是指向 9 ，但却是指向不同的 9 ，即是两个不同的引用。因此 == 比较返回的是假。
+ C.`(s.equals(t))` ， Integer 的 equals 方法如下：
    ```java
    public boolean equals(Object obj) {
        if (obj instanceof Integer) {
            return value == ((Integer)obj).intValue();
        }
        return false ;
    }
    ```
    是 Integer 的实例且 value 值也相等的情况下返回真，其他返回假。

    在这里， s 和 t 都是 Integer 类型且值都为 9 ，因此结果为真。

+ D.`(s.equals(9))` , 在进行 equals 比较之前，会对 9 调用 Integer.valueOf 方法，进行自动装箱 , 由于 IntegerCache 中已经存在 9 ，所以，直接返回其引用，引用相同， equals 就自然相同了。所以结果为真。
+ E.`(s.equals( new Integer(9))` ，直接创建了一个新的 Integer 实例，但且值也为 9 ，所以，满足条件，返回真。

### 6.在Java语言中，下列关于字符集编码（Character set encoding）和国际化（i18n）的问题，哪些是正确的？(`CD`)
+ A.每个中文字符占用2个字节，每个英文字符占用1个字节
+ B.假设数据库中的字符是以GBK编码的，那么显示数据库数据的网页也必须是GBK编码的。
+ C.Java的char类型，通常以UTF-16 Big Endian的方式保存一个字符。
+ D.实现国际化应用常用的手段是利用ResourceBundle类

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/3793733

+ A 显然是错误的，Java一律采用Unicode编码方式，每个字符无论中文还是英文字符都占用2个字节。
+ B 也是不正确的，不同的编码之间是可以转换的，通常流程如下：
  将字符串S以其自身编码方式分解为字节数组，再将字节数组以你想要输出的编码方式重新编码为字符串。
  例：String newUTF8Str = new String(oldGBKStr.getBytes("GBK"), "UTF8");
+ C 是正确的。Java虚拟机中通常使用UTF-16的方式保存一个字符
+ D 也是正确的。ResourceBundle能够依据Local的不同，选择性的读取与Local对应后缀的properties文件，以达到国际化的目的。

## 五、问答题