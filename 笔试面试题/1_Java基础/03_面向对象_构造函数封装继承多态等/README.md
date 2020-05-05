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

核心思想是：父类先于子类；静态先于非静态；成员先于构造函数
具体：`父类静态域——》子类静态域——》父类成员初始化——》父类构造块——》父类构造方法——》子类成员初始化——》子类构造块——》子类构造方法`

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

## 五、问答题