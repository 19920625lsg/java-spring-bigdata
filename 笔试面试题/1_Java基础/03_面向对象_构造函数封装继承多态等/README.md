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

## 五、问答题