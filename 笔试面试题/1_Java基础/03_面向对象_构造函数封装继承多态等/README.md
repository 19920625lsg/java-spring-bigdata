# 03_面向对象_构造函数封装继承多态等

## 一、填空题

## 二、判断题


## 三、单选题
### 1.在创建派生类对象，构造函数的执行顺序（`A`）
+ A.基类构造函数，派生类对象成员构造函数，派生类本身的构造函数
+ B.派生类本身的构造函数，基类构造函数，对象成员构造函数
+ C.基类构造函数，派生类本身的构造函数，派生类对象成员构造函数
+ D.对象成员构造函数，基类构造函数，派生类本身的构造函数

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12779871

核心思想是：父类先于子类；静态先于非静态；成员先于构造函数；同类放到一起按照声明顺序执行
java对象初始化顺序
+ 1.父类静态代码块，父类静态成员变量（同级，按代码顺序执行）
+ 2.子类静态代码块，子类静态成员变量（同级，按代码顺序执行）
+ 3.父类普通代码块，父类普通成员变量（同级，按代码顺序执行）
+ 4.父类构造方法
+ 5.子类普通代码块，子类普通成员变量（同级，按代码顺序执行）
+ 6.子类构造方法

其中静态域包含静态代码块与静态方法，这个谁在前面，则先执行谁

### 2.下面字段声明中哪一个在interface主体内是合法的? （`B`）
+ A.`private final static int answer = 42;`
+ B.`public static int answer = 42;`
+ C.`final static answer = 42;`
+ D.`int answer;`

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12779862

这个问题有点类似多选题的第1题
在接口中，属性都是默认public static final修饰的，所以：
+ A（错误）：不能用private修饰；
+ B（正确）：在接口中，属性默认public static final，这三个关键字可以省略；
+ C（错误）：没写属性的类型；
+ D（错误）：final修饰的属性必须赋值；

### 3.instanceof运算符能够用来判断一个对象是否为:(`D`)
+ A.一个类的实例
+ B.一个实现指定接口的类的实例
+ C.一个子类的实例
+ D.全部正确

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12779831

```java
interface A{
     
}
class B implements A{
      
}
class C extends B{
      
}
public class Iinstanceof {
 
    public static void main(String[] args) {
        A ab=new B();
        A ac=new C();
        B bc=new C();
        B bb=new B();
        C cc=new C();
        // 对象实现一个接口，用这个对象和这个接口进行instanceof判断，都为true。
        System.out.println("ab instanceof A="+(ab instanceof A));
        System.out.println("ac instanceof A="+(ac instanceof A));
        System.out.println("bc instanceof A="+(bc instanceof A));
        System.out.println("bb instanceof A="+(bb instanceof A));
        System.out.println("cc instanceof A="+(cc instanceof A));
        // 对象和父类进行instanceof判断，都为true
        System.out.println("ab instanceof B="+(ab instanceof B));
        System.out.println("ac instanceof B="+(ac instanceof B));
        System.out.println("bc instanceof B="+(bc instanceof B));
        System.out.println("bb instanceof B="+(bb instanceof B));
        System.out.println("cc instanceof B="+(cc instanceof B));
        // 对象和他的子类进行instanceof判断为false
        System.out.println("ab instanceof C="+(ab instanceof C));
        System.out.println("ac instanceof C="+(ac instanceof C));
        System.out.println("bc instanceof C="+(bc instanceof C));
        System.out.println("bb instanceof C="+(bb instanceof C));
        System.out.println("cc instanceof C="+(cc instanceof C));
    }
}
```

输入如下：
```shell
# 对象实现一个接口，用这个对象和这个接口进行instanceof判断，都为true
ab instanceof A=true
ac instanceof A=true
bc instanceof A=true
bb instanceof A=true
cc instanceof A=true
# 对象和父类进行instanceof判断，都为true
ab instanceof B=true
ac instanceof B=true
bc instanceof B=true
bb instanceof B=true
cc instanceof B=true
#  对象和他的子类进行instanceof判断为false
ab instanceof C=false
ac instanceof C=true
bc instanceof C=true
bb instanceof C=false
cc instanceof C=true
```

### 4.面向对象方法的多态性是指（`C`）
+ A.一个类可以派生出多个特殊类
+ B.一个对象在不同的运行环境中可以有不同的变体
+ C.针对一消息，不同的对象可以以适合自身的方式加以响应
+ D.一个对象可以是由多个其他对象组合而成的

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12779700

对于同一个父类方法，不同的子类会有不同的实现方式

### 5.关于访问权限说法正确 的是 ？ (`B`)
+ A.外部类前面可以修饰public,protected和private
+ B.成员内部类前面可以修饰public,protected和private
+ C.局部内部类前面可以修饰public,protected和private
+ D.以上说法都不正确

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12779616

|            | private | default | protected | public |
| ---------- | ------- | ------- | --------- | ------ |
| 同一个类中 | √       | √       | √         | √      |
| 同一个包中 |         | √       | √         | √      |
| 子类中     |         |         | √         | √      |
| 全局范围内 |         |         |           | √      |

+ 1) 对于外部类而言，它也可以使用访问控制符修饰，但外部类只能有两种访问控制级别： public 和默认。因为外部类没有处于任何类的内部，也就没有其所在类的内部、所在类的子类两个范围，因此 private 和 protected 访问控制符对外部类没有意义。
+ 2) 内部类的上一级程序单元是外部类，它具有4个作用域：同一个类(private)、同一个包(protected)和任何位置(public)
+ 3) 因为局部成员的作用域是所在方法，其他程序单元永远不可能访问另一个方法中的局部变量，所以所有的`局部成员都不能使用访问控制修饰符修饰`。

### 6.在Java中，以下关于方法重载(overload)和方法重写(overide)描述正确的是？（`D`）
+ A.方法重载和方法的重写实现的功能相同
+ B.方法重载出现在父子关系中，方法重写是在同一类中
+ C.方法重载的返回值类型必须一致，参数项必须不同
+ D.方法重写的返回值类型必须相同或相容。（或是其子类）

> 解答：

+ 方法重载(overload)：
  + 1.必须是同一个类
  + 2方法名（也可以叫函数）一样
  + 3参数类型不一样或参数数量不一样

+ 方法的重写(override(两同两小一大原则：
  + 方法名相同，参数类型相同
  + 子类返回类型小于等于父类方法返回类型，
  + 子类抛出异常小于等于父类方法抛出异常，
  + 子类访问权限大于等于父类方法访问权限。

### 7.关于以下代码的说明，正确的是（`C`）
```java
class StaticStuff {
    static int x = 10;

    static {
        x += 5;
    }

    public static void main(String[] args) {
        System.out.println("x=" + x);
    }

    static {
        x /= 3;
    }
}
```
+ A.4行与9行不能通过编译，因为缺少方法名和返回类型
+ B.9行不能通过编译，因为只能有一个静态初始化器
+ C.编译通过，执行结果为：x=5
+ D.编译通过，执行结果为：x=3

> 解答：参考第1题。都是静态代码块或者静态成员，则按照声明顺序从上往下执行。所以(10 + 5) /3 = 5

### 8.对于子类的构造函数说明，下列叙述中错误的是（`A`）
+ A.子类可以继承父类的构造函数
+ B.子类中调用父类构造函数不可以直接书写父类构造函数，而应该用super();。
+ C.用new创建子类的对象时，若子类没有带参构造函数，将先执行父类的无参构造函数，然后再执行自己的构造函数。
+ D.子类的构造函数中可以调用其他函数。

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12777655

+ A、java继承中对构造函数是不继承的，只是显式或者隐式调用
+ B、C 子类构造器的第一行默认都是super()，但是一旦父类中没有无参构造，第一行必须显式的调+ 用某一个有参构造
+ D、构造器中可以调用别的方法

### 9.Java1.8之后，Java接口的修饰符可以为（`D`）
+ A.private
+ B.protected
+ C.final
+ D.abstract

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12741674

接口是一种特殊的抽象类，先说明抽象类中的抽象方法，再说明接口

抽象类中的抽象方法(其前有 abstract1修饰)不能用 private、 static、 synchronized、native访回修饰符修饰。原因如下:
+ 1.private
  抽象方法没有方法体,是用来被继承的,所以不能用 private修饰;
+ 2.static
  static修饰的方法可以通过类名来访间该方法(即该方法的方法体),抽象方法用sttic修饰没有意义;
+ 3.synchronized
  该关键字是为该方法加一个锁。而如果该关键字修饰的方法是 static方法。则使用的锁就是class变量的锁。如果是修饰类方法。则用this变量锁。  
  
  但是抽象类不能实例化对象,因为该方法不是在该抽象类中实现的。是在其子类实现的。所以，锁应该归其子类所有。所以，抽象方法也就不能用 synchronized关键字修饰了;
+ 3.native
  native这个东西本身就和 abstract冲突,他们都是方法的声明,只是一个把方法实现移交给子类,另一个是移交给本地操作系统。如果同时出现,就相当于即把实现移交给子类,又把实现移交给本地操作系统,那到底谁来实现具体方法呢

>终于说到了接口！
接口是一种特殊的抽象类,接口中的方法全部是抽象方法(但其前的 abstract可以省略),所以抽象类中的抽象方法不能用的访间修饰符这里也不能用。

同时额外说明一下protect关键词
+ 4. protect
  protected访问修饰符也不能使用,因为接口可以让所有的类去实现(非继承),不只是其子类,但是要用public去修饰。接口可以去继承一个已有的接口。
  参考链接：http://www.360doc.com/content/16/0406/21/26211242_548419991.shtml

### 10.假定Base b = new Derived（）; 调用执行b.methodOne（）后，输出结果是什么？(`A`)
```java
public class Base {
    public void methodOne() {
        System.out.print("A");
        methodTwo();
    }

    public void methodTwo() {
        System.out.print("B");
    }
}

public class Derived extends Base {
    public void methodOne() {
        super.methodOne();
        System.out.print("C");
    }

    public void methodTwo() {
        super.methodTwo();
        System.out.print("D");
    }
}
```
+ A.`ABDC`
+ B.`AB`
+ C.`ABCD`
+ D.`ABC`

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12741598

简答说就是：`只要是被子类重写的方法，不被super调用都是调用子类方法`

这是一道类多态的向上转型题

向上转型：父类只能调用父类方法或者子类覆写后的方法,而子类中的单独方法则是无法调用的。
因此：
调用的顺序是：(1)、(2)、(3)、(4)、(5)、(6)、(7) ; 得到的结果是：ABDC

```java
class Base
{ 
    public void methodOne()  // (3)
    {
        System.out.print("A");
        methodTwo();  // 关键：执行到这里的时候调用子类的覆盖方法 (4)
    } 
    
    public void methodTwo() // (7)
    {
        System.out.print("B");
    }
} 
 class Derived extends Base 
 { 
     public void methodOne() // (1)
    { super.methodOne();   // (2)
        System.out.print("C");
    } 
    
    public void methodTwo()  //该方法为重写了父类的方法 （5）
    { super.methodTwo(); //(6)
        System.out.print("D");
    }
}
```

### 11.对于子类的构造函数说明，下列叙述中错误的是（`D`）
+ A.子类不能继承父类的无参构造函数
+ B.子类可以在自己的构造函数中使用super关键字来调用父类的含参数构造函数，但这个调用语句必须是子类构造函数的第一个可执行语句。
+ C.在创建子类的对象时，若不含带参构造函数，将先执行父类的无参构造函数，然后再执行自己的无参构造函数。
+ D.子类不但可以继承父类的无参构造函数，也可以继承父类的有参构造函数

> 解答: https://www.nowcoder.com/profile/934336/myFollowings/detail/12741584

构造函数不能被继承，构造方法只能被显式或隐式的调用

### 12.关于对象成员占用内存的说法哪个正确？（`B`）
+ A.同一个类的对象共用同一段内存
+ B.同一个类的对象使用不同的内存段，但静态成员共享相同的内存空间
+ C.对象的方法不占用内存
+ D.以上都不对

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12741581

当你创建一个新的实例对象时，它会得到一块新的内存空间。但是类中的静态成员变量是所有对象共有的，也就是在一片属于类的存储空间中，被所有对象共有

### 13.多态的表现形式有(`A`)
+ A.重写
+ B.抽象
+ C.继承
+ D.封装

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12741516

继承是根源，重写是表现形式

### 14.类Parent和Child定义如下：
```java
class Parent {
    public float aFun(float a, float b) {
    }
}

class Child extends Parent {
    // 5.待插入代码的位置
}
```
将以下哪种方法插入行5是不合法的。（`A`）
+ A.`float aFun(float  a,  float  b){ }`
+ B.`public int aFun(int a, int b) { }`
+ C.`public float aFun(float  p,  float q){ }`
+ D.`private int aFun(int a,  int  b){ }`

> 解答：https://www.nowcoder.com/questionTerminal/7d7814b41ded4364aeee6c671233ddb8

方法重写要遵循“两同两小一大”规则，
+ “两同”即方法名相同、形参列表相同
+ “两小”指的是子类方法返回值类型应比父类方法返回值类型更小或相等，子类方法声明抛出的异常类应比父类方法声明抛出的异常类更小或相等；
+ “一大”指的是子类方法的访问权限应比父类方法的访问权限更大或相等。并且，覆盖方法和被覆盖方法要么都是类方法，要么都是实例方法，不能一个是类方法一个是实例方法。

A选项是重写，但是默认访问修饰符比父类小，插入第五行编辑器会报错。
B、D不是重写。因为形参列表和返回值类型不同，不满足“三同”。所以写在第五行以普通方法对待，插入第五行没有错误。
C选项满足重写的各项条件，是正确的重写，所以插入第五行没有错误。

### 15.程序的输出结果(`D`)
```java
class Base {
    public void method() {
        System.out.println("Base");
    }
}

class Son extends Base {
    public void method() {
        System.out.println("Son");
    }

    public void methodB() {
        System.out.println("SonB");
    }
}

public class Test01 {
    public static void main(String[] args) {
        Base base = new Son(); // 已经转成子类对象了，没有methodB()了
        base.method();
        base.methodB();
    }
}
```

+ A.Base SonB
+ B.Son SonB
+ C.Base Son SonB
+ D.编译不通过

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/5234918

```java
Base base = new Son();  // 这句new 了一个派生类，赋值给基类，所以下面的操作编译器认为base对象就是Base类型的Base类中不存在methodB()方法，所以编译不通过
```
这类多态问题中，无论向上或向下转型，都记住一句话就可以了。
`编译看左边，运行看右边`。意思编译时候，看左边有没有该方法，运行的时候结果看 new 的对象是谁，就调用的谁。

### 16.关于访问权限，说法正确的是？ (`A`)
+ A.类A和类B在同一包中，类B有个protected的方法testB，类A不是类B的子类（或子类的子类），类A可以访问类B的方法testB
+ B.类A和类B在同一包中，类B有个protected的方法testB，类A不是类B的子类（或子类的子类），类A不可以访问类B的方法testB
+ C.访问权限大小范围：public > 包权限 > protected > private
+ D.访问权限大小范围：public > 包权限 > private > protected

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/3793711

+ C: public>protected>default>private老生常谈了

详细分析见下面的表

| 修饰符    | 类内部 | 同一个包 | 子类 | 任何地方 |
| --------- | ------ | -------- | ---- | -------- |
| private   | Yes    |          |      |          |
| default   | Yes    | Yes      |      |          |
| protected | Yes    | Yes      | Yes  |          |
| public    | Yes    | Yes      | Yes  | Yes      |

### 17.关于以下程序代码的说明正确的（`D`）
```java
class HasStatic{
    private static int x = 100;
    public static void main(String args[ ]){
        HasStatic hs1 = new HasStatic();
        hs1.x++;
        HasStatic hs2 = new HasStatic();
        hs2.x++;
        hs1=new HasStatic();
        hs1.x++;
        HasStatic.x--;
        System.out.println( "x=" +x);
    }
}
```

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/3793327

静态类变量既可以用类名访问，也可以用实例名访问

### 18.关于抽象类和接口叙述正确的是？ (`D`)
+ A.抽象类和接口都能实例化的
+ B.抽象类不能实现接口
+ C.抽象类方法的访问权限默认都是public
+ D.接口方法的访问权限默认都是public

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/3604291

+ A、抽象类和方法都不能被实例化
+ B、抽象类可以实现接口
+ C、抽象类方法默认访问权限都是default
+ D、接口就是访问的，默认访问权限都是public

更多总结：
+ 抽象类
  + 1.抽象类中可以构造方法
  + 2.抽象类中可以存在普通属性，方法，静态属性和方法。
  + 3.抽象类中可以存在抽象方法。
  + 4.如果一个类中有一个抽象方法，那么当前类一定是抽象类；抽象类中不一定有抽象方法。
  + 5.抽象类中的抽象方法，需要有子类实现，如果子类不实现，则子类也需要定义为抽象的。
  + 6,抽象类不能被实例化，抽象类和抽象方法必须被abstract修饰

关键字使用注意：
抽象类中的抽象方法（其前有abstract修饰）不能用private、static、synchronized、native访问修饰符修饰。

+ 接口
  + 1.在接口中只有方法的声明，没有方法体。
  + 2.在接口中只有常量，因为定义的变量，在编译的时候都会默认加上public static final
  + 3.在接口中的方法，永远都被public来修饰。
  + 4.接口中没有构造方法，也不能实例化接口的对象。（所以接口不能继承类）
  + 5.接口可以实现多继承
  + 6.接口中定义的方法都需要有实现类来实现，如果实现类不能实现接口中的所有方法则实现类定义为抽象类。
  + 7.接口可以继承接口，用extends

### 19.以下代码在编译和运行过程中会出现什么情况?(`A`)
```java
public class TestDemo{
    private int count;
    public static void main(String[] args) {
        TestDemo test=new TestDemo(88);
        System.out.println(test.count);
    }
     TestDemo(int a) {
         count=a;
    }
}
```
+ A.编译运行通过，输出结果是88
+ B.编译时错误，count变量定义的是私有变量
+ C.编译时错误，System.out.println方法被调用时test没有被初始化
+ D.编译和执行时没有输出结果

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/3598067

解析：通过实例化可以调用本类的私有属性，但是不能直接调用（system.out.println（conut）），因为主函数为静态函数，而属性为非静态。

### 20.下面代码的输出是什么？（`A`）
+ A.null
+ B.sub
+ C.base

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/3590511

new Sub();在创造派生类的过程中首先创建基类对象，然后才能创建派生类。
创建基类即默认调用Base()方法，在方法中调用callName()方法，由于派生类中存在此方法，则被调用的callName（）方法是派生类中的方法，此时派生类还未构造，所以变量baseName的值为null

### 21.对文件名为Test.java的java代码描述正确的是(`C`)
+ A.输出：0000
+ B.输出：123
+ C.编译报错
+ D.输出：No name

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/3528574

选C，父类没有无参的构造函数，所以子类需要在自己的构造函数中显式调用父类的构造函数，添加super("nm");否则报错

### 22.如果一个接口Cow有个方法drink()，有个类Calf实现接口Cow，则在类Calf中正确的是？  (`C`)
+ A.`void drink() { …}`
+ B.`protected void drink() { …}`
+ C.`public void drink() { …}`
+ D.以上语句都可以用在类Calf中

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/3523999

子类重写父类方法时，方法的访问权限不能小于原访问权限，在接口中，方法的默认权限就是public，所以子类重写后只能是public

### 23.下面代码的输出结果是：(`C`)

```java
public class HelloB extends HelloA {
    public HelloB() {
    }

    {
        System.out.println("I’m B class");
    }

    static {
        System.out.println("static B");
    }

    public static void main(String[] args) {
        new HelloB();
    }
}

class HelloA {
    public HelloA() {
    }

    {
        System.out.println("I’m A class");
    }

    static {
        System.out.println("static A");
    }
}
```

+ A.
    ```shell
    static A
    I’m A class
    static B
    I’m B class
    ```
+ B.
    ```shell
    I’m A class
    I’m B class
    static A
    static B
    ```
+ C.
    ```shell
    static A
    static B
    I’m A class
    I’m B class
    ```
+ D.
    ```shell
    I’m A class
    static A
    I’m B class
    static B
    ```

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/3513983

参考第1题：
```java
其中涉及：静态初始化代码块、构造代码块、构造方法
当涉及到继承时，按照如下顺序执行：
1、执行父类的静态代码块
static {
        System.out.println("static A");
    }
输出:static A
2、执行子类的静态代码块
static {
        System.out.println("static B");
    }
输出:static B
3、执行父类的构造代码块
{
        System.out.println("I’m A class");
    }
输出:I'm A class
4、执行父类的构造函数
public HelloA() {
    }
输出：无
5、执行子类的构造代码块
{
        System.out.println("I’m B class");
    }
输出:I'm B class
6、执行子类的构造函数
public HelloB() {
    }
输出：无

那么，最后的输出为：
static A
static B
I'm A class
I'm B class
正确答案：C
```

### 24.以下程序的输出结果为(`D`)
```java
class Base{
    public Base(String s){
        System.out.print("B");
    }
}
public class Derived extends Base{
    public Derived (String s) {
        System.out.print("D");
    }
    public static void main(String[] args){
        new Derived("C");
    }
}
```

子类构造方法在调用时必须先调用父类的，由于父类没有无参构造，必须在子类中显式调用，修改子类构造方法如下即可：
```java
public Derived(String s){
    super("s");
    System.out.print("D");
}
```

## 四、多选题
### 1.以下代码可以使用的修饰符是：（`ACD`）
+ A.final
+ b.static
+ C.abstract
+ D.public

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12779880

+ 接口中字段的修饰符：public static final（默认不写）
+ 接口中方法的修饰符：public abstract（默认不写）

### 2.观察以下代码：
```java
class Car extends Vehicle {
    public static void main(String[] args) {
        new Car().run();
    }

    private final void run() {
        System.out.println("Car");
    }
}

class Vehicle {
    private final void run() {
        System.out.println("Vehicle");
    }
}
```
下列哪些针对代码运行结果的描述是正确的？(`A`)
+ A.Car
+ B.Vehicle
+ C.Compiler error at line 3
+ D.Compiler error at line 5
+ E.Exception thrown at runtime

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12779857
首先final声明的方法是不能被覆盖的，但是这里并不错误，因为方法是private的，也就是子类没有继承父类的run方法，因此子类的run方法跟父类的run方法无关，并不是覆盖。new Car().run()也是调用子类的run方法。

### 3.有关静态初始化块说法正确的是？（`ABC`）
+ A.无法直接调用静态初始化块
+ B.在创建第一个实例前或引用任何静态成员之前，将自动调用静态初始化块来初始化
+ C.静态初始化块既没有访问修饰符，也没有参数
+ D.在程序中，用户可以控制合适执行静态初始化块

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12779623

java对象初始化顺序
先说结论：
+ 1.父类静态代码块，父类静态成员变量（同级，按代码顺序执行）
+ 2.子类静态代码块，子类静态成员变量（同级，按代码顺序执行）
+ 3.父类普通代码块，父类普通成员变量（同级，按代码顺序执行）
+ 4.父类构造方法
+ 5.子类普通代码块，子类普通成员变量（同级，按代码顺序执行）
+ 6.子类构造方法

注意点：

+ 1.静态成员和静态代码块只有在类加载的时候执行一次，再次创建实例时，不再执行，因为只在方法区存在一份，属于一整个类
+ 默认调用父类的无参构造方法，可以在子类构造方法中利用super指定调用父类的哪个构造方法。

代码测试：
```java
/**
 * Description: Java对象初始化顺序
 * Created by yangyz on 2018/12/25
 */
class Father {
    public Father() {
        System.out.println("父类无参构造方法");
    }
    static {
        System.out.println("父类静态代码块1");
    }
    private static int a = Help.fatherStaticMemberVarInit();
    static {
        System.out.println("父类静态代码块2");
    }
    {
        System.out.println("父类普通代码块1");
    }
    private int b = Help.fatherMemberVarInit();
    {
        System.out.println("父类普通代码块2");
    }
    public Father(int v) {
        System.out.println("父类带参构造方法");
    }
}
 
class Son extends Father {
    static {
        System.out.println("子类静态代码块1");
    }
    private static int a = Help.sonStaticMemberVarInit();
    static {
        System.out.println("子类静态代码块2");
    }
    {
        System.out.println("子类普通代码块1");
    }
    private int b = Help.sonMemberVarInit();
    {
        System.out.println("子类普通代码块2");
    }
    public Son() {
        // super(2018);
        System.out.println("子类构造方法");
    }
}
 
class Help {
    public static int fatherStaticMemberVarInit() {
        System.out.println("父类静态成员变量");
        return 0;
    }
    public static int fatherMemberVarInit() {
        System.out.println("父类普通成员变量");
        return 0;
    }
    public static int sonStaticMemberVarInit() {
        System.out.println("子类静态成员变量");
        return 0;
    }
    public static int sonMemberVarInit() {
        System.out.println("子类普通成员变量");
        return 0;
    }
}
 
public class Test {
    public static void main(String[] args) {
        Son son1 = new Son();
        System.out.println("===================");
        Son son2 = new Son();
    }
}
```

### 4.下面的对象创建方法中哪些会调用构造方法 （`AC`）？
+ A.new语句创建对象
+ B.调用Java.io.ObjectInputStream的readObject方法
+ C.java反射机制使用java.lang.Class或java.lang.reflect.Constructor的newInstance()方法
+ D.调用对象的clone()方法

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12778262

题目的四个选项是构造方法new，序列化对象，反射，克隆分别创建一个对象的方法，，只有new和反射用到了构造方法。readObject方法只是从文件中还原对象(在设计模式中的序列化模式中用到了)，clone只是一种复制拷贝对象

### 5.在使用super和this关键字时，以下描述错误的是（`BCD`）
+ A.在子类构造方法中使用super()显示调用父类的构造方法，super()必须写在子类构造方法的第一行，否则编译不通过
+ B.super()和this()不一定要放在构造方法内第一行
+ C.this()和super()可以同时出现在一个构造函数中
+ D.this()和super()可以在static环境中使用，包括static方法和static语句块

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12777882

super和this都只能位于构造器的第一行，而且不能同时使用，这是因为会造成初始化两次，this用于调用重载的构造器，super用于调用父类被子类重写的方法

### 6.关于下列代码的执行顺序，
```java
public class HelloA {
    public HelloA() {
        System.out.println("A的构造函数");
    }

    {
        System.out.println("A的构造代码块");
    }

    static {
        System.out.println("A的静态代码块");
    }

    public static void main(String[] args) {
        HelloA a = new HelloA();
    }
}
```
下面描述正确的有哪些选项(`ABC`)
+ A.打印顺序A的静态代码块> A的构造函数
+ B.打印顺序A的静态代码块> A的构造代码块
+ C.打印顺序A的构造代码块> A的构造函数
+ D.打印顺序A的构造函数> A的构造代码块

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12777880

参考第3题。顺序应该是：A的静态代码块==》A的构造代码块==》A的构造函数

### 7.对于构造方法，下列叙述正确的是（`ACD`）
+ A.构造方法的优先级一般比代码块低
+ B.构造方法的返回类型只能是void型。
+ C.构造方法的主要作用是完成对类的对象的初始化工作。
+ D.一般在创建新对象时，系统会自动调用构造方法。

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12777769

 主要是选项A，这里的优先级就是指类实例化的顺序高低，上一个题就是考这个地
+ A：静态成员变量或静态代码块>main方法>非静态成员变量或非静态代码块>构造方法
+ B：构造器本身并没有任何返回值
+ C: 构造方法的主要作用是完成对类的对象的初始化工作。
+ D: 一般在创建(new)新对象时，系统会自动调用构造方法。

### 8.局部内部类可以用哪些修饰符修饰？(`CD`)
+ A.public
+ B.private
+ C.abstract
+ D.final

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12777572

![Java内部类](images/Java内部类.png)

### 9.给出下面的代码段:
```java
public class Base {
    int w, x, y, z;

    public Base(int a, int b) {
        x = a;
        y = b;
    }

    public Base(int a, int b, int c, int d) {
        // assignment x=a, y=b
        w = d;
        z = c;
    }
}
```
在代码说明`// assignment x=a, y=b`处写入如下哪几个代码是正确的？（`CD`）
+ A.`Base(a,b);`
+ B.`x=a, y=b;`
+ C.`x=a; y=b;`
+ D.`this(a,b);`

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/5249151

+ A选项：实例化的时候才是这么调用地~在类中不能这么用，只能像D一样用this
+ B选项：可以使用逗号的是变量初始化的语句，比如`int i=1,b=2;`如果是赋值语句，不能用逗号分隔。

### 10.下列选项中是正确的方法声明的是？（`ABCD`）
+ A.`protected abstract void f1();`
+ B.`public final void f1() {}`
+ C.`static final void fq(){}`
+ D.`private void f1() {}`

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/4010367

+ A：抽象方法只可以被public 和 protected修饰；
+ B：final可以修饰类、方法、变量，分别表示：该类不可继承、该方法不能重写、该变量是常量
+ C：static final 可以表达在一起来修饰方法，表示是该方法是静态的不可重写的方法
+ D：private 修饰方法（这太常见的）表示私有方法，本类可以访问，外界不能访问

### 11. 对于abstract声明的类，下面说法正确的是(`E`)
+ A.可以实例化
+ B.不可以被继承
+ C.子类为abstract
+ D.只能被继承
+ E.可以被抽象类继承

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/3793963

+ A,抽象类不能实例化，因为有抽象方法未实现
+ B,可以被继承。派生类可以实现抽象方法
+ C.子类可以是抽象的，也可以非抽象的
+ D.只能被继承说法太肯定，也可以只用类名调用其静态方法
    ```java
    public abstract class FanLi {
        public abstract void nouse();
        public static void main(String[] args) {
            FanLi.fun();
        }
        public static void fun() {
            System.out.println("我是反例");
        }
    }
    ```  
+ E.可以被抽象类继承，也可以被非抽象类继承

### 12.关于抽象类与接口，下列说法正确的有？（`AC`）
+ A.优先选用接口，尽量少用抽象类
+ B.抽象类可以被声明使用，接口不可以被声明使用
+ C.抽象类和接口都不能被实例化。
+ D.以上说法都不对

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/3598070

+ A.java只是单继承，但是可以实现多个接口，继承的耦合性太强，java推荐高内聚低耦合的设计思路，不推荐使用继承。在用继承的情况下，如果还必须要继承另外的接口会很麻烦，尽量用接口，这样在你必须要用到继承的时候就可以用了。
+ B.声明使用的意思是像：Object s = new String();这里的Object类就被声明使用了，抽象类和接口都可以被声明使用
+ C.抽象类和接口都不能被实例化。接口没有构造方法，不能被实例化，但是抽象方法可以有构造方法，不过不是用来实例化对象的，使用来初始化的。

### 13.下列说法错误的有（`ACD` ）
+ A.在类方法中可用this来调用本类的类方法
+ B.在类方法中调用本类的类方法时可直接调用
+ C.在类方法中只能调用本类中的类方法
+ D.在类方法中绝对不能调用实例方法

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/3564017

首先：
成员方法又称为实例方法
静态方法又称为类方法
其次：
+ a，静态方法中没有this指针
+ c，可以通过类名作用域的方式调用Class::fun();
+ d，太绝对化了，在类中申请一个类对象或者参数传递一个对象或者指针都可以调用;

### 14.Java类Demo中存在方法func0、func1、func2、func3和func4，请问该方法中，哪些是不合法的定义？(`AD`)
```java
public class Demo{
　　float func0()
　　{
　　　　byte i=1;
　　　　return i;
　　}
　　float func1()
　　{
　　　　int i=1;
　　　　return;
　　}
　　float func2()
　　{
　　　　short i=2;
　　　　return i;
　　}
　　float func3()
　　{
　　　　long i=3;
　　　　return i;
　　}
　　float func4()
　　{
　　　　double i=4;
　　　　return i;
　　}
}
```
+ A.func1
+ B.func2
+ C.func3
+ D.func4

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/3528518

这道题考的是数据类型转换问题。由大到小需要强制转换，由小到大不需要。
+ A：return;   没有返回值，错误
+ B：short → float 无须强制转换，正确
+ C：long → float  无须强制转换（这个最选项容易出错），正确。

float占4个字节为什么比long占8个字节大呢，因为底层的实现方式不同。
浮点数的32位并不是简单直接表示大小，而是按照一定标准分配的。
第1位，符号位，即S
接下来8位，指数域，即E。
剩下23位，小数域，即M，取值范围为[1 ,2 ) 或[0 , 1)
然后按照公式： V=(-1)^s * M * 2^E
也就是说浮点数在内存中的32位不是简单地转换为十进制，而是通过公式来计算而来，通过这个公式虽然，只有4个字节，但浮点数最大值要比长整型的范围要大
+ D：double → float 没有强制转换，错误。

### 15.下列描述错误的是?(`BD`)
+ A.类只能继承一个父类，但是可以实现多个接口
+ B.抽象类自身可以定义成员而接口不可以
+ C.抽象类和接口都不能被实例化(忽略匿名内部类)
+ D.一个类可以有多个父类和多个基接口

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/3523963

+ A、java为单继承，多实现。可以实现多个接口。
+ B、接口允许定义成员，但必须是常量
+ C、抽象类和接口类的无法实例化，任何编译器中直接使用new会报错
+ D、同A，单继承，多实现

### 16.关于继承和实现说法正确的 是 ？ (`A`)
+ A.类可以实现多个接口，接口可以继承（或扩展）多个接口
+ B.类可以实现多个接口，接口不能继承（或扩展）多个接口
+ C.类和接口都可以实现多个接口
+ D.类和接口都不可以实现多个接口 // 接口和接口间的关系叫继承

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/3513811

+ 1.类与类之间的关系为继承，只能单继承，但可以多层继承
+ 2.类与接口之间的关系为实现，既可以单实现，也可以多实现
+ 3.接口与接口之间的关系为继承，既可以单继承，也可以多继承

### 17.下面哪些类可以被继承？ Java.lang.Thread、java.lang.Number、java.lang.Double、java.lang.Math、 java.lang.ClassLoader(`ABE`)
+ A.Thread
+ B.Number
+ C.Double
+ D.Math
+ E.ClassLoader

> 解读：https://www.nowcoder.com/profile/934336/myFollowings/detail/3513682

+ A，Thread可以被继承，用于创建新的线程
+ B，Number类可以被继承，Integer，Float，Double等都继承自Number类
+ C，Double类的声明为`public final class Doubleextends Numberimplements Comparable<Double>`,  final声明的类不能被继承
+ D，Math类的声明为`public final class Mathextends Object`不能被继承
+ E，ClassLoader可以被继承，用户可以自定义类加载器

### 18.jdk1.8中，下面有关java 抽象类和接口的区别，说法错误的是？(`BD`)
+ A.抽象类可以有构造方法，接口中不能有构造方法
+ B.抽象类中可以包含非抽象的普通方法，接口中的方法必须是抽象的，不能有非抽象的普通方法
+ C.一个类可以实现多个接口，但只能继承一个抽象类
+ D.接口中可以有普通成员变量，抽象类中没有普通成员变量

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/3513537

+ 抽象类特点:
  + 1.抽象类中可以构造方法
  + 2.抽象类中可以存在普通属性，方法，静态属性和方法。
  + 3.抽象类中可以存在抽象方法。
  + 4.如果一个类中有一个抽象方法，那么当前类一定是抽象类；抽象类中不一定有抽象方法。
  + 5.抽象类中的抽象方法，需要有子类实现，如果子类不实现，则子类也需要定义为抽象的。
+ 接口
  + 1.在接口中只有方法的声明，没有方法体。
  + 2.在接口中只有常量，因为定义的变量，在编译的时候都会默认加上
  + public static final 
  + 3.在接口中的方法，永远都被public来修饰。
  + 4.接口中没有构造方法，也不能实例化接口的对象。
  + 5.接口可以实现多继承
  + 6.接口中定义的方法都需要有实现类来实现，如果实现类不能实现接口中的所有方法
  + 7.则实现类定义为抽象类
## 五、问答题
