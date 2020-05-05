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


## 五、问答题
