# 07_异常

## 一、填空题

## 二、判断题

## 三、单选题
### 1.以下对异常的描述不正确的有（`C`）
> 这个题目的异常包含了Error和Exception
+ A.异常分为Error和Exception
+ B.Throwable是所有异常类的父类
+ C.Exception是所有异常类父类
+ D.Exception包括RuntimeException和RuntimeException之外的异常

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12779867

![异常的体系结构](images/异常的体系结构.png)

### 2.以下代码执行的结果显示是多少？（`B`）
> 虽然finally会在return之前执行，但是return的值是在进finally之前决定地
```java
public class Demo {
    public static void main(String[] args) {
        System.out.print(getNumber(0));
        System.out.print(getNumber(1));
        System.out.print(getNumber(2));
        System.out.print(getNumber(4));
    }

    public static int getNumber(int num) {
        try {
            int result = 2 / num;
            return result;
        } catch (Exception exception) {
            return 0;
        } finally {
            if (num == 0) {
                return -1;
            }
            if (num == 1) {
                return 1;
            }
        }
    }
}
```

+ A.0110
+ B.-1110
+ C.0211
+ D.-1211

> 解读：https://www.nowcoder.com/profile/934336/myFollowings/detail/12779851

+ finally在return之前执行，但是trycatch中的return值是在进finally之前决定地
+ finally在return之前执行，但是如果finally使用了return或者throw语句，将会使trycatch中的return或者throw失效

### 3.以下代码段执行后的输出结果为(`D`)
```java
public class Test {
    public static void main(String[] args) {
        System.out.println(test());
    }

    private static int test() {
        int temp = 1;
        try {
            System.out.println(temp);
            return ++temp;
        } catch (Exception e) {
            System.out.println(temp);
            return ++temp;
        } finally {
            ++temp;
            System.out.println(temp);
        }
    }
}
```
+ A.`1,2,2`
+ B.`1,2,3`
+ C.`1,3,3`
+ D.`1,3,2`

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12779705

finally在return之前执行，但是trycatch中的return值是在进finally之前决定地。和上面的题很类似

### 4.对于Java中异常的描述正确的是（`D`）
+ A.用throws定义了方法可能抛出的异常，那么调用此方法时一定会抛出此异常。
+ B.如果try块中没有抛出异常，finally块中的语句将不会被执行。
+ C.抛出异常意味着程序发生运行时错误，需要调试修改
+ D.Java中的unchecked异常可能来自RuntimeException类或其子类。

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12777621

+ A错 在调用此方法的时候 也可以再次申明以将异常交由更高一级处理。
+ B错 finally块中的语句一定会被执行。除非catch块中有System.exit(0)。
+ C错 抛出异常不一定是运行时异常，也有可能是编译时异常。
+ D对 运行时异常的特点是Java编译器不会检查它。

### 5.如下代码的输出是(`B`)
```java
public class Test {
    private static void test(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            try {
                if (arr[i] % 2 == 0) {
                    throw new NullPointerException();
                } else {
                    System.out.print(i);
                }
            } finally {
                System.out.print("e");
            }
        }
    }

    public static void main(String[] args) {
        try {
            test(new int[]{0, 1, 2, 3, 4, 5});
        } catch (Exception e) {
            System.out.print("E");
        }
    }
}
```

+ A.编译出错
+ B.eE
+ C.Ee
+ D.eE1eE3eE5
+ E.Ee1Ee3Ee5

> 问题解答：

由于arr[0] =0,所以在进入 test()方法里面会在第一个if 上抛出一个 NullPointerException,接着会执行 finally 的语句, (finally语句先于 return 和 throw语句执行)，输出一个'e，然后回到 main方法中，由于捕捉到异常，所以进入到catch语句中，然后打印一个'E',所以最终结果为"eE"

这题主要是2点:
+ 1.finally中的语句一定会执行。 
+ 2.是catch捕获到异常后程序结束

## 四、多选题
### 1.有关finally语句块说法正确的是（`ABC`）
+ A.不管catch是否捕获异常，finally语句块都是要被执行的
+ B.在try语句块或catch语句块中执行到System.exit(0)直接退出程序
+ C.finally块中的return语句会覆盖try块中的return返回
+ D.finally语句块在catch语句块中的return语句之前执行

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/5366774

> 结论：
+ 1、不管有木有出现异常，finally块中代码都会执行；
+ 2、当try和catch中有return时，finally仍然会执行；
+ 3、finally是在return后面的表达式运算后执行的（此时并没有返回运算后的值，而是先把要返回的值保存起来，管finally中的代码怎么样，返回的值都不会改变，任然是之前保存的值），所以函数返回值是在finally执行前确定的；
+ 4、finally中最好不要包含return，否则程序会提前退出，返回值不是try或catch中保存的返回值。


> 举例：

+ **情况1**：
    ```java
    try {
        ...
    } catch (Exception e) {
        ...
    } finally {
        ...
    } return;
    ```
    显然程序按顺序执行

+ **情况2**:
    ```java
    try {
        ...
        return;
    } catch (Exception e) {
        ...
    } finally {
        ...
    } return;
    ```
  + 1）程序执行try块中return之前（包括return语句中的表达式运算）代码；
  + 2）再执行finally块，最后执行try中return(上一步return的值保存了，finally即使对return的值修改了也不会影响return的值地);
  + 3）finally块之后的语句return，因为程序在try中已经return所以不再执行

+ **情况3**:
   ```java
    try {
        ...
    } catch (Exception e) {
        ...
        return;
    } finally {
        ...
    }
    return;
    ```
  > 程序先执行try，如果遇到异常执行catch块，
  + 有异常：
    + 则执行catch中return之前（包括return语句中的表达式运算）代码，再执行finally语句中全部代码(前面return的值保存了，finally即使对return的值修改了也不会影响return的值地)
    + 最后执行catch块中return. finally之后也就是4处的代码不再执行。
  + 无异常：执行完try再finally再return.
   

+ 情况4:
    ```java
    try {
        ...
        return;
    } catch (Exception e) {
        ...
    } finally {
        ...
        return;
    }
    ```
  + 程序执行try块中return之前（包括return语句中的表达式运算）代码；
  + 再执行finally块，因为finally块中有return所以提前退出，以finally中返return的值为准。


+ 情况5:
    ```java
    try {
        ...
    } catch (Exception e) {
        ...
        return;
    } finally {
        ...
        return;
    }
    ```
  + 程序执行catch块中return之前（包括return语句中的表达式运算）代码；
  + 再执行finally块，因为finally块中有return所以提前退出.以finally中返return的值为准。


+ 情况6:
    ```java
    try {
        return;
    } catch (Exception e) {
        return;
    } finally {
        return;
    }
    ```
  + 程序执行try块中return之前（包括return语句中的表达式运算）代码；
    + 有异常：
      + 执行catch块中return之前（包括return语句中的表达式运算）代码；
      + 再执行finally块，因为finally块中有return所以提前退出。
    + 无异常：则再执行finally块，因为finally块中有return所以提前退出。

> 最终结论：
+ 1.任何执行try 或者catch中的return语句之前，如果finally存在的话,都会先执行finally语句，
+ 2.如果finally中有return语句，那么程序就return了，所以finally中的return是一定会被return的，编译器把finally中的return实现为一个warning。
+ 3.如果finally中无return语句，而try或catch中与return语句，则会在try或catch中把要return的值先计算好，待finally执行完毕，再返回之前计算好的return值(即使finally中对返回值有修改也不会生效)

### 2.下面有关 JAVA 异常类的描述,说法正确的有(`ABC`)
+ A.异常的继承结构:基类为 Throwable,Error 和 Exception 实现 Throwable,RuntimeException 和 IOException 等继承 Exception
+ B.非 RuntimeException 一般是外部错误(不考虑Error的情况下),其必须在当前类被 try{}catch 语句块所捕获
+ C.Error 类体系描述了 Java 运行系统中的内部错误以及资源耗尽的情形,Error 不需要捕捉
+ D.RuntimeException 体系包括错误的类型转换、数组越界访问和试图访问空指针等等,必须 被 try{}catch 语句块所捕获

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/5234921

![异常的体系结构2](images/异常的体系结构2.png)
都是Throwable的子类：
+ 1.Exception（异常） :是程序本身可以处理的异常。
+ 2.Error（错误）: 是程序无法处理的错误。这些错误表示故障发生于虚拟机自身、或者发生在虚拟机试图执行应用时，一般不需要程序处理。
+ 3.检查异常（编译器要求必须处置的异常） ：  除了Error，RuntimeException及其子类以外，其他的Exception类及其子类都属于可查异常。这种异常的特点是Java编译器会检查它，也就是说，当程序中可能出现这类异常，要么用try-catch语句捕获它，要么用throws子句声明抛出它，否则编译不会通过
+ 4.非检查异常(编译器不要求处置的异常): 包括运行时异常（RuntimeException与其子类）和错误（Error）

## 五、问答题